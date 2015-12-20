package model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Hailun Zhu
 * Id: hailunz
 * Date: 09/14/2015
 */
//inner class
public class OptionSet implements Serializable{
    private Option opt[];
    private String name;

    //default constructure
    protected OptionSet(){
        this.opt = new Option[2];
        this.name = "default";

        for(int i=0;i<opt.length;i++)
            opt[i] = new Option();
    }

    protected OptionSet(String name, int size) {
        this.opt = new Option[size];
        this.name = name;

        for(int i=0;i<opt.length;i++)
            opt[i] = new Option();
    }

    //inner class
    protected class Option implements Serializable{
        private String name;
        private float price;

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
    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected Option getOpt(int index){
        return opt[index];
    }

    protected Option[] getOpts(){
        return this.opt;
    }

    protected String getOptName(int index){
        return this.opt[index].getName();
    }

    protected float getOptPrice(int index){
        return this.opt[index].getPrice();
    }

    protected int getOptionSize(){
        return this.opt.length;
    }

    protected Option findOpt(int index) {
        return this.opt[index];
    }

    protected Option findOptByName(String name){
        for (Option o: this.opt){
            if(o.getName().equals(name))
                return o;
        }
        return null;
    }

    protected int findOptIndexByName(String name){
        for (int i=0;i<this.opt.length;i++){
            if(opt[i].getName().equals(name))
                return i;
        }
        return -1;
    }

    protected  void setOption(int index, String name, float price){
        if (index<this.opt.length){
            this.opt[index] = new Option(name, price);
        }else{
            Option[] set = new Option [this.opt.length+1];
            System.arraycopy(this.opt,0,set,0,this.opt.length);
            set[this.opt.length] = new Option(name,price);
            this.opt = set;
        }
    }

    protected void updateOptions(int index, String name, float price){
        this.opt[index].setName(name);
        this.opt[index].setPrice(price);
    }

    protected void updateOptions(String option, String name, float price){
        Option opt = findOptByName(option);
        if (opt!= null) {
            opt.setName(name);
            opt.setPrice(price);
        }
    }

    protected void updateOptionName(int index, String name){
        this.opt[index].setName(name);
    }

    protected void updateOptionPrice(int index, float price){
        this.opt[index].setPrice(price);
    }

    protected void deleteOption(int index){
        if (index >= 0 && index<this.opt.length){
            int len = this.opt.length-1;
            while(index<len) {
                this.opt[index] = this.opt[index+1];
            }
            this.opt = Arrays.copyOf(this.opt, len);
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
        for(Option opt : this.opt)
            opt.print();
    };
}