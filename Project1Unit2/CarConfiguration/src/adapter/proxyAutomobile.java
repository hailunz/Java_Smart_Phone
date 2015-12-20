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

    public void setOptionChoice( String modelname,
                                 String setName, String optionName){
        Automobile a = mobileMap.get(modelname);
        a.setOptionChoice(setName,optionName);
    }

    public void updateOptionSetName(String modelname,
                                    String OptionSetname, String newName) {
        Automobile a = mobileMap.get(modelname);
        if (a!=null)
            a.updateOptionSetName(OptionSetname, newName);
        else
            System.out.println("No model is named: " + modelname);

    }


    public void updateOptionPrice(String modelname, String OptionSet,
                                  String Option, float newprice) {
        Automobile a = mobileMap.get(modelname);
        if (a!=null)
            a.updateOption(OptionSet, Option, Option, newprice);
        else
            System.out.println("No model is named: " + modelname);

    }

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
