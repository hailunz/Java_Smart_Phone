package util;

import model.Automotive;

import java.io.*;


/**
 * Hailun Zhu
 * Id: hailunz
 * Date: 09/14/2015
 */
public class FileIO {
    
    public static Automotive buildAutoObject(String filename) {
        Automotive auto = new Automotive();
        int count = 0;
        try {
            FileReader file = new FileReader(filename);
            BufferedReader buff = new BufferedReader(file);
            boolean eof = false;
            while (!eof) {
                String line = buff.readLine();
                if (line == null)
                    eof = true;
                else{
                    String[] args = line.split("=");
                    if (args[0].equals("Autoname")){
                        auto.setName(args[1]);
                    }else if(args[0].equals("Baseprice")){
                        auto.setBaseprice(Float.parseFloat(args[1]));
                    }else{
                        String opsetName = args[0];
                        String[] params = args[1].split(",");
                        auto.setOpset(count,opsetName,params.length);
                        for(int i=0;i<params.length;i++){
                            String[] opt = params[i].split("@");
                            auto.setOption(opsetName,i,opt[0]
                                    ,Float.parseFloat(opt[1]));
                        }
                        count++;
                    }
                }
            }
            buff.close();
        } catch (Exception e) {
            System.out.println("Error ­­ " + e.toString());
            System.exit(1);
        }
        return auto;
    }

//    public static Automotive buildAutoObject(String filename, Automotive a1) {
//        Automotive auto = new Automotive();
//        return auto;
//    }

    public static void serializeAuto(Automotive auto){
        String filename = "auto.ser";
        try{
            ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(auto);
            out.close();
        }catch (Exception e){
            System.out.println("Error ­­ " + e.toString());
            System.exit(1);
        }
    }

    public static Automotive DeserializeAuto(String filename){
        Automotive auto = null;
        try{
            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(filename));
            auto = (Automotive) in.readObject();
        }catch (Exception e){
            System.out.println("Error ­­ " + e.toString());
            System.exit(1);
        }
        return auto;
    }
}
