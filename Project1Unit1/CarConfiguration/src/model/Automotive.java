package model;


import java.io.Serializable;
import java.util.Arrays;

/**
 * Hailun Zhu
 * Id: hailunz
 * Date: 09/14/2015
 */
public class Automotive implements Serializable{
    private float baseprice;
    private String name;
    private OptionSet opset[] ;

    //constructors
    public Automotive(){
        this.name = "Ford Wagon ZTW";// default name
        this.baseprice = 18445; // default baseprice
        this.opset = new OptionSet[5]; // default size
        
        for(int i=0;i<5;i++){
            this.opset[i] = new OptionSet();
        }
    }
    
    public Automotive(String name, float baseprice, int OptionSetSize){
        this.opset = new OptionSet[OptionSetSize];
        this.name = name;
        this.baseprice = baseprice;
        
        for(int i=0;i<opset.length;i++) 
            opset[i] = new OptionSet();
    }

    //Getter
    public float getBaseprice() {
        return baseprice;
    }

    public String getName() {
        return name;
    }

    public OptionSet getOpset(int index) {
        return opset[index];
    }

    public String getOpsetName(int index){
        return opset[index].getName();
    }

    public int getOptionSetsSize(){
        return this.opset.length;
    }

    public int getOptionsSize(int index){
        return this.opset[index].getOptionSize();
    }

    public String getOptionName(int opset, int opt){
        return this.opset[opset].getOpt(opt).getName();
    }

    public float getOptionPrice(int opset, int opt){
        return this.opset[opset].getOpt(opt).getPrice();
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
        for (int i=0;i<this.opset.length;i++){
            if(opset[i].getName().equals(name))
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
    public void setBaseprice(float baseprice) {
        this.baseprice = baseprice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOpsets(OptionSet[] opset) {
        this.opset = opset;
    }

    public void setOpset(int index, String name, int size){
        if (index<this.opset.length){
            this.opset[index] = new OptionSet(name, size);
        }else{
            OptionSet[] set = new OptionSet [this.opset.length+1];
            System.arraycopy(this.opset,0,set,0,this.opset.length);
            set[this.opset.length] = new OptionSet(name,size);
            this.opset = set;
        }
    }

    public void setOption(String opset, int index, String opt, float price){
        OptionSet set = this.findOpset(opset);
        set.setOption(index,opt, price);
    }

    //Delete
    public void deleteOptionSet(int index){
        if (index >= 0 && index<this.opset.length){
            int len = this.opset.length-1;
            while(index<len) {
                this.opset[index] = this.opset[index+1];
            }
            this.opset = Arrays.copyOf(this.opset, len);
        }
    }

    public void deleteOptionSet(String name){
        int index = findOpsetIndexByName(name);
        if (index >= 0 && index<this.opset.length){
            int len = this.opset.length-1;
            while(index<len) {
                this.opset[index] = this.opset[index+1];
            }
            this.opset = Arrays.copyOf(this.opset, len);
        }
    }

    public void deleteOption(String opset, String opt){
        OptionSet set = this.findOpset(opset);
        set.deleteOption(opt);
    }

    //Update
    public void updateOptionSet(int index, OptionSet optionSet){
        try{
            this.opset[index] = optionSet;
        }catch (Exception e){
            System.out.println(e.toString());
        }

    }

    public void updateOptionSet(int index, String name, int size){
        try{
            this.opset[index] = this.opset[index] = new OptionSet(name, size);
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
        this.opset[index].setName(name);
    }

    public void updateOption(int opset,int opt, String name, float price){
        this.opset[opset].setOption(opt, name, price);
    }

    public void updateOption(String opset, String opt, String name, float price){
        OptionSet set = findOpset(opset);
        if (set!=null){
            set.updateOptions(opt,name, price);
        }
    }

    public void print(){
        StringBuffer str = new StringBuffer();
        str.append("Automotive:[" + name + "] \n");
        str.append("baseprice: " + baseprice +"\n\n");
        str.append("OptionSets:");
        System.out.println(str);
        for(OptionSet set: this.opset){
            set.print();
            System.out.println();
        }
    }


}
