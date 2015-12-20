package util;

import exception.AutoException;
import exception.Fix0to4;
import model.Automobile;

import java.io.*;


/**
 * Hailun Zhu
 * Id: hailunz
 * Date: 09/14/2015
 */
public class FileIO {
    
    public static Automobile buildAutoObject(String filename) {
        Automobile auto = new Automobile();

        int count = 0;
        try {
            FileReader file = null;
            try{
                if (filename == null || !filename.endsWith(".txt"))
                    throw new AutoException(3, "Missing filename or wrong filename.");
                else
                    file = new FileReader(filename);
            }catch (AutoException e){
                    filename = e.fixString(e.getErrno());
                    file = new FileReader(filename);
            }

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
                    }else if (args[0].equals("Make")){
                        auto.setMake(args[1]);
                    }
                    else if (args[0].equals("Model")){
                        auto.setModel(args[1]);
                    }
                    else if(args[0].equals("Baseprice")){
                        auto.setBaseprice(Float.parseFloat(args[1]));
                    }else if(args[0].startsWith("Option[")){
                        String optSetName = args[0].substring(args[0]
                                .indexOf("[")+1,args[0].length()-1);
                        auto.setOptionChoice(optSetName,args[1]);
                    }
                    else{
                        String opsetName = args[0];
                        try{
                            if (args.length<2 || args[1].length()<1)
                                throw new AutoException(1,
                                        "Missing OptionSet data (or part of it).");
                        }catch(AutoException e){
                            System.out.println("OptionSet Data for OptionSet "+ opsetName);
                            args = new String[2];
                            args[0] = opsetName;
                            args[1] = e.fixString(e.getErrno());
                        }
                        String[] params = args[1].split(",");
                        auto.setOpset(count,opsetName,params.length);
                        for(int i=0;i<params.length;i++){
                            String[] opt = params[i].split("@");

                            try{
                                if (opt.length!=2)
                                    throw new AutoException(2,
                                            "Missing Option data in text file.");
                            }catch(AutoException e){
                                System.out.println("Option Data for OptionSet "+ opsetName);
                                opt = e.fixString(e.getErrno()).split("@");
                            }

                            auto.setOption(opsetName,i,opt[0]
                                    ,Float.parseFloat(opt[1]));
                        }
                        count++;
                    }
                }
            }
            try{
                if (auto.getBaseprice() <= 0 ){
                    throw new AutoException(0,
                            "Missing price for Automobile in text file!");
                }
            }catch (AutoException e){
                auto.setBaseprice(e.fixFloat(e.getErrno()));
            }

            try{
                if (auto.getName() == null){
                    throw new AutoException(4,
                            "Missing name for Automobile in text file");
                }
            }catch (AutoException e){
               auto.setName(e.fixString(e.getErrno()));
            }
            buff.close();
            file.close();
        } catch (Exception e) {
            System.out.println("Error ­­ " + e.toString());
            System.exit(1);
        }
        return auto;
    }

    public static void serializeAuto(Automobile auto){
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

    public static Automobile DeserializeAuto(String filename){
        Automobile auto = null;
        try{
            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(filename));
            auto = (Automobile) in.readObject();
        }catch (Exception e){
            System.out.println("Error ­­ " + e.toString());
            System.exit(1);
        }
        return auto;
    }
}
