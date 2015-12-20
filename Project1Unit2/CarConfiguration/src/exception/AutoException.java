package exception;


import adapter.FixAuto;

import java.io.*;
import java.util.HashMap;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Hailun Zhu
 * ID: hailunz
 * Date: 9/19/15
 */
public class AutoException extends Exception implements FixAuto {

    enum Error {
        Error0(0, "Missing price for Automobile in text file!"),
        Error1(1, "Missing OptionSet data (or part of it)."),
        Error2(2, "Missing Option data in text file."),
        Error3(3, "Missing filename or wrong filename."),
        Error4(4, "Missing name for Automobile in text file");

        private int number;
        private String message;

        Error(int num){
            this.number = num;
        }

        Error(int number, String msg) {
            this.message = msg;
            this.number = number;
        }

        private String getMessage() {
            return message;
        }

        private int getNumber() {
            return number;
        }
    }

    private int errno;
    private String message;
    private static LinkedHashMap<String,String> map = new LinkedHashMap<>();
    private static HashMap<Integer,Integer>count = new HashMap<>();

    public AutoException(int errno, String msg){
        super(msg);
        this.errno = errno;
        this.message = msg;
        String timestamp = (new Date()).toString();
        map.put(timestamp,errno+":"+msg);
        if (count.containsKey(errno)){
            count.put(errno ,count.get(errno)+1);
        }else{
            count.put(errno,1);
        }
    }

    public int getErrno() {
        return errno;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void fix(int errno) {
        Fix0to4 f1 = new Fix0to4();
         switch(errno){
             default:
                 break;
         }
    }

    @Override
    public String fixString(int errno) throws IOException {
        Fix0to4 f1 = new Fix0to4();
        switch (errno){
            case 1:{
                return f1.fix1(errno);
            }
            case 2:{
                return f1.fix2(errno);
            }
            case 3:{
                return f1.fix3(errno);
            }
            case 4:{
                return f1.fix4(errno);
            }
        }
        return null;
    }

    @Override
    public float fixFloat(int errno) throws IOException {
        Fix0to4 f1 = new Fix0to4();
        switch(errno){
            case 0:
                return f1.fix0(errno);
        }
        return 0;
    }

    public void logError(){
        String filename = "log.txt";
        try{
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));
            String line = null;
            for(String key: map.keySet()){
                line = key +" "+map.get(key)+"\n";
                out.write(line);
            }
            out.close();
        }catch (Exception e){
            System.out.println("Error ­­ " + e.toString());
            System.exit(1);
        }
    }

}
