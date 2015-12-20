package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Hailun Zhu
 * Id: hailunz
 * Date: 09/14/2015
 */
//inner class
public class OptionSet implements Serializable{
    private ArrayList<Option> opt;
    private String name;
    private Option choice;

    private static final long serialVersionUID = 8156005235747311549l;

    //default constructure
    protected OptionSet(){
        this.opt = new ArrayList<>();
        this.name = "default";
    }

    protected OptionSet(String name, int size) {
        this.opt = new ArrayList<>();
        this.name = name;
    }

    //inner class
    protected class Option implements Serializable{
        private String name;
        private float price;
        private static final long serialVersionUID =-3371013337461687760l;

        //Constructors
        protected Option(){
            this.name = "default";
            this.price = 0;
        }

        protected Option(String name, float price){
            this.name = name;
            this.price = price;
        }

        //Getter and Setter
        protected String getName() {
            return name;
        }

        protected void setName(String name) {
            this.name = name;
        }

        protected float getPrice() {
            return price;
        }

        protected void setPrice(float price) {
            this.price = price;
        }

        protected void print(){
            StringBuffer line = new StringBuffer();
            line.append("-- Option[");
            line.append(this.name);
            line.append("]- $");
            line.append(this.price);
            System.out.println(line.toString());
        }
    }

    //Getter and Setter

    protected Option getOptionChoice(){
        return this.choice;
    }

    protected float getOptionChoicePrice(){
        if (this.choice == null)
            return 0;
        return this.choice.price;
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected Option getOpt(int index){
        return opt.get(index);
    }

    protected ArrayList<Option> getOpts(){
        return this.opt;
    }

    protected String getOptName(int index){
        return this.opt.get(index).getName();
    }

    protected float getOptPrice(int index){
        return this.opt.get(index).getPrice();
    }

    protected int getOptionSize(){
        return this.opt.size();
    }

    protected Option findOpt(int index) {
        return this.opt.get(index);
    }

    protected Option findOptByName(String name){
        for (Option o: this.opt){
            if(o.getName().equals(name))
                return o;
        }
        return null;
    }

    protected int findOptIndexByName(String name){
        for (int i=0;i<this.opt.size();i++){
            if(opt.get(i).getName().equals(name))
                return i;
        }
        return -1;
    }

    protected  void setOptionChoice(String name){
        int i = findOptIndexByName(name);
        this.choice = this.opt.get(i);
    }

    protected  void setOption(int index, String name, float price){
        if (index<this.opt.size()) {
            this.opt.set(index, new Option(name, price));
        }else{
            this.opt.add(new Option(name, price));
        }
    }

    protected void updateOptions(int index, String name, float price){
        this.opt.get(index).setName(name);
        this.opt.get(index).setPrice(price);
    }

    protected void updateOptions(String option, String name, float price){
        Option opt = findOptByName(option);
        if (opt!= null) {
            opt.setName(name);
            opt.setPrice(price);
        }
    }

    protected void updateOptionName(int index, String name){
        this.opt.get(index).setName(name);
    }

    protected void updateOptionName(String optName,String name){
        Option opt = findOptByName(optName);
        if (opt!=null){
            opt.setName(name);
        }
    }

    protected void updateOptionPrice(int index, float price){
        this.opt.get(index).setPrice(price);
    }

    protected void deleteOption(int index){
        if (index >= 0 && index<this.opt.size()){
            this.opt.remove(index);
        }
    }

    protected void deleteOption(String name){
        int index = findOptIndexByName(name);
        deleteOption(index);
    }

    protected void print(){
        StringBuffer line = new StringBuffer();
        line.append("* OptionSet[");
        line.append(this.name);
        line.append("]\n");
        line.append("- Options:");
        System.out.println(line.toString());
        int len = this.opt.size();
        for(int i =0; i<len; i++)
            opt.get(i).print();

        System.out.println("My Choice: " );
        if (choice!=null)
            choice.print();
        else{
            System.out.println("Haven't choose.");
        }
    };
}