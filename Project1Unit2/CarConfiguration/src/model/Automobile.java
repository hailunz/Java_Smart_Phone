package model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Hailun Zhu
 * Id: hailunz
 * Date: 09/14/2015
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

    public float getTotalPrice(){
        float sum = this.baseprice;
        for(OptionSet s: this.opset){
            sum += s.getOptionChoicePrice();
        }
        return sum;
    }

    public String getMake() {
        return make;
    }

    public String getOptionChoice(String setName){
        int index = findOpsetIndexByName(setName);
        return this.opset.get(index).getOptionChoice().getName();
    }
    public float getOptionChoicePrice(String setName){
        int index = findOpsetIndexByName(setName);
        return this.opset.get(index).getOptionChoicePrice();
    }

    public String getModel() {
        return model;
    }

    public float getBaseprice() {
        return baseprice;
    }

    public String getName() {
        return name;
    }

    public OptionSet getOpset(int index) {
        return opset.get(index);
    }

    public String getOpsetName(int index){
        return opset.get(index).getName();
    }

    public int getOptionSetsSize(){
        return this.opset.size();
    }

    public int getOptionsSize(int index){
        return this.opset.get(index).getOptionSize();
    }

    public String getOptionName(int opset, int opt){
        return this.opset.get(opset).getOpt(opt).getName();
    }

    public float getOptionPrice(int opset, int opt){
        return this.opset.get(opset).getOpt(opt).getPrice();
    }

    //Find
    public OptionSet findOpset(String name) {
         for(OptionSet s: this.opset){
             if(s.getName().equals(name))
                    return s;
         }
        return null;
    }
    public int findOpsetIndexByName(String name){
        for (int i=0;i<this.opset.size();i++){
            if(opset.get(i).getName().equals(name))
                return i;
        }
        return -1;
    }

    public OptionSet.Option findOption(String opset, String opt){
        OptionSet set = findOpset(opset);
        if (set!=null){
            return set.findOptByName(opt);
        }
        return null;
    }

    //Setter

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setOptionChoice( String setName, String optionName){
        int setIndex = findOpsetIndexByName(setName);
        this.opset.get(setIndex).setOptionChoice(optionName);
    }

    public void setBaseprice(float baseprice) {
        this.baseprice = baseprice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOpsets(ArrayList<OptionSet> opset) {
        this.opset = opset;
    }

    public void setOpset(int index, String name, int size){
        if (index<this.opset.size()){
            this.opset.set(index, new OptionSet(name, size));
        }else{
            this.opset.add(new OptionSet(name, size));
        }
    }

    public void setOption(String opset, int index, String opt, float price){
        OptionSet set = this.findOpset(opset);
        set.setOption(index,opt, price);
    }

    //Delete
    public void deleteOptionSet(int index){
        if (index >= 0 && index<this.opset.size()){

            this.opset.remove(index);
        }
    }

    public void deleteOptionSet(String name){
        int index = findOpsetIndexByName(name);
        if (index >= 0 && index<this.opset.size()){
            this.opset.remove(index);
        }
    }

    public void deleteOption(String opset, String opt){
        OptionSet set = this.findOpset(opset);
        set.deleteOption(opt);
    }

    //Update
    public void updateOptionSet(int index, OptionSet optionSet){
        try{
            this.opset.set(index, optionSet);
        }catch (Exception e){
            System.out.println(e.toString());
        }

    }

    public void updateOptionSet(int index, String name, int size){
        try{
            this.opset.set(index, new OptionSet(name, size));
        }catch (Exception e){
            System.out.println(e.toString());
        }

    }

    public void updateOptionSetName(String opset, String name){
        OptionSet set = findOpset(opset);
        if(set !=null)
            set.setName(name);
    }

    public void updateOptionSetName(int index, String name){
        this.opset.get(index).setName(name);
    }

    public void updateOption(int opset,int opt, String name, float price){
        this.opset.get(opset).setOption(opt, name, price);
    }

    public void updateOption(String opset, String opt, String name, float price){
        OptionSet set = findOpset(opset);
        if (set!=null){
            set.updateOptions(opt,name, price);
        }
    }

    public void print(){
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
