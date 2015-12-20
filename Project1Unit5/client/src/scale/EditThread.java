package scale;

import model.Automobile;

/**
 * Hailun Zhu
 * ID: hailunz
 * Date: 9/27/15
 */

/**
 * Interface used for interaction between EditOption and BuildAuto class
 */
public interface EditThread {
    public void setOptionChoice(String modelname,
                                String setName, String optionName);
    public String getOptionChoice(String modelname, String setName);

    public void updateOptionChoicePrice(String modelname, String OptionSet,
                                        float newprice);
    public float getOptionChoicePrice(String modelname, String setName);
    public void addOptionChoicePrice(String modelname, String OptionSet, float add);

    public void updateOption(String modelname, String setName,
                             String optionName, String newName, float newPrice);
    
    public Automobile getModel(String modelname);

}
