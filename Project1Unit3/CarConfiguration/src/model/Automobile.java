package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Hailun Zhu
 * Id: hailunz
 * Date: 09/14/2015
 */


/**
 * I have every methods of Automobile class synchronized.
 * Only synchronize the method in the EditOption class
 * will not deal with the read and write situation.
 * So that I synchronized method in Automobile class.
 * 
 * for the update and addOptionChoicePrice methods, I also synchronize the
 * method in the EditOption class.
 */
public class Automobile implements Serializable{
    private float baseprice;
    private String name;
    private ArrayList<OptionSet> opset ;
    private String make;
    private String model;

    //constructors
    public Automobile(){
        this.opset = new ArrayList<>(); // default size
    }
    
    public Automobile(String name, float baseprice, int OptionSetSize){
        this.opset = new ArrayList<>();
        this.name = name;
        this.baseprice = baseprice;
    }

    //Getter

    public synchronized float getTotalPrice(){
        float sum = this.baseprice;
        for(OptionSet s: this.opset){
            sum += s.getOptionChoicePrice();
        }
        return sum;
    }

    public synchronized String getMake() {
        return make;
    }

    public synchronized String getOptionChoice(String setName){
        int index = findOpsetIndexByName(setName);
        return this.opset.get(index).getOptionChoice().getName();

    }
    public synchronized float getOptionChoicePrice(String setName){
        int index = findOpsetIndexByName(setName);
        return this.opset.get(index).getOptionChoicePrice();
    }

    public synchronized float testGetOptionChoicePrice(String setName){
        try{
            Thread.sleep(5000);
        }catch (Exception e){

        }
        int index = findOpsetIndexByName(setName);
        return this.opset.get(index).getOptionChoicePrice();
    }

    public synchronized String getModel() {
        return model;
    }

    public synchronized float getBaseprice() {
        return baseprice;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized OptionSet getOpset(int index) {
        return opset.get(index);
    }

    public synchronized String getOpsetName(int index){
        return opset.get(index).getName();
    }

    public synchronized int getOptionSetsSize(){
        return this.opset.size();
    }

    public synchronized int getOptionsSize(int index){
        return this.opset.get(index).getOptionSize();
    }

    public synchronized String getOptionName(int opset, int opt){
        return this.opset.get(opset).getOpt(opt).getName();
    }

    public synchronized float getOptionPrice(int opset, int opt){
        return this.opset.get(opset).getOpt(opt).getPrice();
    }

    //Find
    public synchronized OptionSet findOpset(String name) {
         for(OptionSet s: this.opset){
             if(s.getName().equals(name))
                    return s;
         }
        return null;
    }
    public synchronized int findOpsetIndexByName(String name){
        for (int i=0;i<this.opset.size();i++){
            if(opset.get(i).getName().equals(name))
                return i;
        }
        return -1;
    }


    public synchronized OptionSet.Option findOption(String opset, String opt){
        OptionSet set = findOpset(opset);
        if (set!=null){
            return set.findOptByName(opt);
        }
        return null;
    }

    //Setter

    public synchronized void setMake(String make) {
        this.make = make;
    }

    public synchronized void setModel(String model) {
        this.model = model;
    }

    public synchronized void setOptionChoice( String setName, String optionName){
        int setIndex = findOpsetIndexByName(setName);
        this.opset.get(setIndex).setOptionChoice(optionName);
    }

    public synchronized void testSetOptionChoice( String setName, String optionName){
        System.out.println("test set option choice");
        try{
            Thread.sleep(5000);
        }catch (Exception e){

        }
        int setIndex = findOpsetIndexByName(setName);
        this.opset.get(setIndex).setOptionChoice(optionName);
    }

    public synchronized void setBaseprice(float baseprice) {
        this.baseprice = baseprice;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized void setOpsets(ArrayList<OptionSet> opset) {
        this.opset = opset;
    }

    public synchronized void setOpset(int index, String name, int size){
        if (index<this.opset.size()){
            this.opset.set(index, new OptionSet(name, size));
        }else{
            this.opset.add(new OptionSet(name, size));
        }
    }

    public synchronized void setOption(String opset, int index, String opt, float price){
        OptionSet set = this.findOpset(opset);
        set.setOption(index,opt, price);
    }

    //Delete
    public synchronized void deleteOptionSet(int index){
        if (index >= 0 && index<this.opset.size()){

            this.opset.remove(index);
        }
    }

    public synchronized void deleteOptionSet(String name){
        int index = findOpsetIndexByName(name);
        if (index >= 0 && index<this.opset.size()){
            this.opset.remove(index);
        }
    }

    public synchronized void deleteOption(String opset, String opt){
        OptionSet set = this.findOpset(opset);
        set.deleteOption(opt);
    }

    //Update
    public synchronized void updateOptionSet(int index, OptionSet optionSet){
        try{
            this.opset.set(index, optionSet);
        }catch (Exception e){
            System.out.println(e.toString());
        }

    }

    public synchronized void updateOptionSet(int index, String name, int size){
        try{
            this.opset.set(index, new OptionSet(name, size));
        }catch (Exception e){
            System.out.println(e.toString());
        }

    }

    public synchronized void updateOptionSetName(String opset, String name){
        OptionSet set = findOpset(opset);
        if(set !=null)
            set.setName(name);
    }

    public synchronized void updateOptionSetName(int index, String name){
        this.opset.get(index).setName(name);
    }

    public synchronized void updateOption(int opset,int opt, String name, float price){
        this.opset.get(opset).setOption(opt, name, price);
    }

    public synchronized void updateOption(String opset, String opt, String name, float price){
        OptionSet set = findOpset(opset);
        if (set!=null){
            set.updateOptions(opt,name, price);
        }
    }

    public synchronized void testUpdateOption(String opset, String opt, String name, float price){
        OptionSet set = findOpset(opset);
        if (set!=null){
            set.updateOptions(opt,name, price);
        }
    }



    public synchronized void print(){
        StringBuffer str = new StringBuffer();
        str.append("Automobile:[" + name + "] \n");
        str.append("baseprice: " + baseprice +"\n\n");
        str.append("OptionSets:");
        System.out.println(str);
        for(OptionSet set: this.opset){
            set.print();
            System.out.println();
        }
    }



}
