package adapter;

/**
 * Hailun Zhu
 * ID: hailunz
 * Date: 9/19/15
 */

import exception.Fix0to4;
import model.*;
import util.FileIO;

import java.io.IOException;
import java.util.LinkedHashMap;

public abstract class proxyAutomobile {

    private static Automobile a1;
    private static LinkedHashMap<String, Automobile> mobileMap =
            new LinkedHashMap<String, Automobile>();

    public void BuildAuto(String filename) {
        this.a1 = FileIO.buildAutoObject(filename);
        this.mobileMap.put(this.a1.getName(), this.a1);
    }


    public void printAuto(String modelname) {
       Automobile a = mobileMap.get(modelname);
        if (a!=null)
            a.print();
        else
            System.out.println("No model is named: " + modelname);
    }

    public void updateOptionSetName(String modelname,
                                    String OptionSetname, String newName) {
        Automobile a = mobileMap.get(modelname);
        if (a!=null)
            a.updateOptionSetName(OptionSetname, newName);
        else
            System.out.println("No model is named: " + modelname);

    }

    // test synchronization
    public void setOptionChoice( String modelname,
                                 String setName, String optionName){
        Automobile a = mobileMap.get(modelname);
        a.testSetOptionChoice(setName, optionName);
    }


    public String getOptionChoice(String modelname, String setName){
        Automobile a = mobileMap.get(modelname);
        if (a!=null){
            String name = a.getOptionChoice(setName);
            System.out.println( modelname + " OptionSet["+ setName + "] - Choice:");
            System.out.println(name);
            return name;
        }else
            System.out.println("No model is named: " + modelname);
        return null;
    }

    public void updateOptionChoicePrice(String modelname, String OptionSet,
                                        float newprice) {

        Automobile a = mobileMap.get(modelname);

        if (a!=null){
            String choice = a.getOptionChoice(OptionSet);
            a.updateOption(OptionSet, choice, choice, newprice);
        }else
            System.out.println("No model is named: " + modelname);


    }

    public float getOptionChoicePrice(String modelname, String setName){
        Automobile a = mobileMap.get(modelname);
        if (a!=null){
            float price = a.testGetOptionChoicePrice(setName);
            System.out.println( modelname + " OptionSet["+ setName + "] " +
                    "- choice price:" );
            System.out.println(price);
            return price;
        }else
            System.out.println("No model is named: " + modelname);
        return 0;
    }

    public void updateOptionPrice(String modelname, String OptionSet,
                                  String Option, float newprice) {
        Automobile a = mobileMap.get(modelname);
        if (a!=null)
            a.updateOption(OptionSet, Option, Option, newprice);
        else
            System.out.println("No model is named: " + modelname);
    }

    public void addOptionChoicePrice(String modelname,String OptionSet,float add){
        Automobile a = mobileMap.get(modelname);
        if (a!=null){
            String choice = a.getOptionChoice(OptionSet);
            float price = a.getOptionChoicePrice(OptionSet);
            a.updateOption(OptionSet, choice, choice, price+add);
        }else
            System.out.println("No model is named: " + modelname);
    }
    /*
        Serializable
     */

    public void writeToFile(String modelname){
        Automobile a= mobileMap.get(modelname);
        if (a!=null)
            FileIO.serializeAuto(a);
        else
            System.out.println("No model is named: " + modelname);
    }

    public void readFromFile(String filename){
        this.a1 = FileIO.DeserializeAuto(filename);
        mobileMap.put(a1.getName(), a1);
    }

    public void print(){
        this.a1.print();
    }

    public void getTotalPrice(String modelName){
        Automobile a= mobileMap.get(modelName);
        if (a!=null) {
            System.out.println("Total price for "+ modelName);
            System.out.println(a.getTotalPrice());
        }
        else
            System.out.println("No model is named: " + modelName);
    }

    /*
     *  Deal with exceptions
     */

    public void fix(int errno) {
        Fix0to4 f1 = new Fix0to4();
        switch(errno){
            default:
                break;
        }
    }

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

    public float fixFloat(int errno) throws IOException {
        Fix0to4 f1 = new Fix0to4();
        switch(errno){
            case 0:
                return f1.fix0(errno);
        }
        return 0;
    }
}
