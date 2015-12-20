package scale;

/**
 * Hailun Zhu
 * ID: hailunz
 * Date: 9/24/15
 */
public class EditOptions extends Thread implements EditThread{

    public String id;
    private EditThread t;
    private int editOption;
    private String modelname;

    public EditOptions(String id, EditThread t1, int editOption, String modelname){
        super(id);
        this.id = id;
        this.t = t1;
        this.editOption = editOption;
        this.modelname = modelname;
    }
    void randomWait() {
        try {
            Thread.currentThread().sleep((long)(3000*Math.random()));
        }
        catch(InterruptedException e) {
            System.out.println("Interrupted!");
        }
    }


    // edit option choice, default is change Transmission to manual
    private void test0(){
        this.setOptionChoice(modelname, "Transmission", "manual");
    }

    // get option choice, get the choice  of Transmission
    private void test1(){
        this.getOptionChoice(modelname, "Transmission");

    }

    // edit option price, change the price of manual to be $10
    private void test2(){
        this.updateOptionChoicePrice(modelname, "Transmission", 10);
    }

    // get option price, get current price of manual
    private void test3(){
        this.getOptionChoicePrice(modelname, "Transmission");
    }

    private void test4(){
        System.out.println("test4");
        this.addOptionChoicePrice(modelname, "Transmission",10);
        float price= this.getOptionChoicePrice(modelname, "Transmission");
        System.out.println("test4:"+ price);
    }
    private void test5(){
        System.out.println("test5");
        this.addOptionChoicePrice(modelname, "Transmission",10);
        float price= this.getOptionChoicePrice(modelname, "Transmission");
        System.out.println("test5:"+price);

    }


    public void run(){
        switch (this.editOption){

            case 0:
                test0();
                break;
            case 1:
                test1();
                break;
            case 2:
                test2();
                break;
            case 3:
                test3();
                break;
            case 4:
                test4();
                break;
            case 5:
                test5();
                break;
        }

    }


    @Override
    public synchronized void setOptionChoice(String modelname, String setName, String optionName) {
        this.t.setOptionChoice(modelname, setName, optionName);
    }

    @Override
    public synchronized String getOptionChoice(String modelname, String setName) {
        return  this.t.getOptionChoice(modelname, setName);
    }

    @Override
    public synchronized void updateOptionChoicePrice(String modelname,
                                                     String OptionSet,
                                                     float newprice) {
        this.t.updateOptionChoicePrice(modelname, OptionSet, newprice);
    }

    @Override
    public synchronized float getOptionChoicePrice(String modelname, String setName) {
        return  this.t.getOptionChoicePrice(modelname, setName);
    }

    @Override
    public synchronized void addOptionChoicePrice(String modelname, String OptionSet,
                                     float add) {
        this.t.addOptionChoicePrice(modelname, OptionSet, add);

    }

    @Override
    public synchronized void updateOption(String modelname, String setName,
                                          String optionName, String newName, float newPrice) {
        this.t.updateOption(modelname,setName,optionName, newName, newPrice);
    }
}
