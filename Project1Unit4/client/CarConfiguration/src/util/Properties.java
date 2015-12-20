package util;

import exception.AutoException;
import model.Automobile;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Hailun Zhu
 * ID: hailunz
 * Date: 10/4/15
 */
public class Properties implements Serializable{

    private Hashtable<String, ArrayList<String>>  properties;
    private Hashtable<String, String> choices;
    private Automobile auto;

    public Properties(){
        this.properties = new Hashtable<>();
        this.choices = new Hashtable<>();
    }

    public Automobile getAuto() {
        return auto;
    }

    public void load(FileInputStream in){
        int count = 0;
        try {
            BufferedReader buff = new BufferedReader(new InputStreamReader(in));
            boolean eof = false;
            while (!eof) {
                String line = buff.readLine();
                if (line == null)
                    eof = true;
                else{
                    String[] args = line.split("=");
                    if (args[0].equals("Autoname")){
                        ArrayList<String> tmp = new ArrayList<>();
                        tmp.add(args[1]);
                        this.properties.put("Autoname",tmp);
                    }else if (args[0].equals("Make")){
                        ArrayList<String> tmp = new ArrayList<>();
                        tmp.add(args[1]);
                        this.properties.put("Make", tmp);
                    }
                    else if (args[0].equals("Model")){
                        ArrayList<String> tmp = new ArrayList<>();
                        tmp.add(args[1]);
                        this.properties.put("Model", tmp);
                    }
                    else if(args[0].equals("Baseprice")){
                        ArrayList<String> tmp = new ArrayList<>();
                        tmp.add(args[1]);
                        this.properties.put("Baseprice", tmp);
                    }else if(args[0].startsWith("Option[")){
                        String optSetName = args[0].substring(args[0]
                                .indexOf("[")+1,args[0].length()-1);
                        this.choices.put(optSetName,args[1]);
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

                        ArrayList<String> tmp = new ArrayList<>();
                        String[] params = args[1].split(",");
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

                            tmp.add(params[i]);
                        }
                        properties.put(opsetName,tmp);
                        count++;
                    }
                }
            }
            try{
                if (!this.properties.containsKey("Baseprice") ){
                    throw new AutoException(0,
                            "Missing price for Automobile in text file!");
                }
            }catch (AutoException e){
                auto.setBaseprice(e.fixFloat(e.getErrno()));
            }

            try{
                if (!this.properties.containsKey("Autoname")){
                    throw new AutoException(4,
                            "Missing name for Automobile in text file");
                }
            }catch (AutoException e){
                auto.setName(e.fixString(e.getErrno()));
            }
            buff.close();
        } catch (Exception e) {
            System.out.println("Error ­­ " + e.toString());
            System.exit(1);
        }

    }

    public Automobile build(){
        Automobile auto = new Automobile();
        for(String prop : properties.keySet()){
            switch(prop){
                case "Autoname":
                    auto.setName(properties.get("Autoname").get(0));
                    break;
                case "Make":
                    auto.setMake(properties.get("Make").get(0));
                    break;
                case "Model":
                    auto.setModel(properties.get("Model").get(0));
                    break;
                case "Baseprice":
                    auto.setBaseprice(Float.parseFloat(properties.get("Baseprice").get(0)));
                    break;
                default:
                    ArrayList<String> tmp = properties.get(prop);
                    int size = tmp.size();
                    auto.addOptionSet(prop,size);
                    for(int i=0;i<size;i++) {
                        String[] opt = tmp.get(i).split("@");
                        auto.setOption(prop, i, opt[0]
                                , Float.parseFloat(opt[1]));
                    }
                    break;
            }

        }
        return auto;
    }

    public Automobile loadAutomobile(InputStream in) {
        this.auto = new Automobile();

        int count = 0;
        try {
            BufferedReader buff = new BufferedReader(
                    new InputStreamReader(in));
            boolean eof = false;
            while (!eof) {
                String line = buff.readLine();
                if (line == null)
                    eof = true;
                else{
                    String[] args = line.split("=");
                    if (args[0].equals("Autoname")){
                        auto.setName(args[1]);
                        ArrayList<String> tmp = new ArrayList<>();
                        tmp.add(args[1]);
                        this.properties.put("Autoname",tmp);
                    }else if (args[0].equals("Make")){
                        auto.setMake(args[1]);
                        ArrayList<String> tmp = new ArrayList<>();
                        tmp.add(args[1]);
                        this.properties.put("Make", tmp);
                    }
                    else if (args[0].equals("Model")){
                        auto.setModel(args[1]);
                        ArrayList<String> tmp = new ArrayList<>();
                        tmp.add(args[1]);
                        this.properties.put("Model", tmp);
                    }
                    else if(args[0].equals("Baseprice")){
                        auto.setBaseprice(Float.parseFloat(args[1]));
                        ArrayList<String> tmp = new ArrayList<>();
                        tmp.add(args[1]);
                        this.properties.put("Baseprice", tmp);
                    }else if(args[0].startsWith("Option[")){
                        String optSetName = args[0].substring(args[0]
                                .indexOf("[")+1,args[0].length()-1);
                        auto.setOptionChoice(optSetName, args[1]);
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

                        ArrayList<String> tmp = new ArrayList<>();
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
                            tmp.add(opt[0]);
                        }
                        properties.put(opsetName,tmp);
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
        } catch (Exception e) {
            System.out.println("Error ­­ " + e.toString());
            System.exit(1);
        }

        return auto;
    }


    public ArrayList<String> getProperty(String propName){
        if (!this.properties.containsKey(propName)){
           return this.properties.get(propName);
        }
        System.out.println("Property name : " + propName + " does not exist!");
        return null;
    }

}
