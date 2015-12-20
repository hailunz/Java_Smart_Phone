package client;

import adapter.BuildAuto;
import adapter.CreateAuto;
import adapter.UpdateAuto;

/**
 * Hailun Zhu
 * ID: hailunz
 * Date: 10/4/15
 */
public class SelectCarOption implements UpdateAuto, CreateAuto {
    private CreateAuto auto1;
    private UpdateAuto auto2;
    String modelname;

    public SelectCarOption(){
        auto1 = new BuildAuto();
        auto2 = new BuildAuto();
    }

    public SelectCarOption(BuildAuto auto){
        auto1 = auto;
        auto2 = auto;
        modelname = auto.getModelName();
    }
    @Override
    public void updateOptionSetName(String Modelname, String OptionSetname, String newName) {
        auto2.updateOptionSetName(Modelname,OptionSetname, newName);
    }

    @Override
    public void updateOptionPrice(String Modelname, String Optionname, String Option, float newprice) {
        auto2.updateOptionPrice(Modelname,Optionname,Option,newprice);
    }

    @Override
    public void setOptionChoice(String modelname, String setName, String optionName) {
        auto2.setOptionChoice(modelname, setName, optionName);
    }

    @Override
    public void BuildAuto(String filename) {
        auto1.BuildAuto(filename);
    }

    @Override
    public void BuildAuto(Object file, int fileType) {
        auto1.BuildAuto(file, fileType);
    }

    @Override
    public void printAuto(String modelname) {
        auto1.printAuto(modelname);
    }
}
