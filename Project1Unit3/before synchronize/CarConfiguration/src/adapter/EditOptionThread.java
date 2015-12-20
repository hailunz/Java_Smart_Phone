package adapter;

/**
 * Hailun Zhu
 * ID: hailunz
 * Date: 9/27/15
 */
public interface EditOptionThread {
    public void setOptionChoice( String modelname,
                                 String setName, String optionName);
    public String getOptionChoice(String modelname, String setName);

    public void updateOptionChoicePrice(String modelname, String OptionSet,
                                   float newprice);
    public float getOptionChoicePrice(String modelname, String setName);
    public void addOptionChoicePrice(String modelname,String OptionSet,float add);
}
