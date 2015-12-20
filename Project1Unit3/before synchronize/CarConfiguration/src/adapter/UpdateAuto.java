package adapter;

/**
 * Hailun Zhu
 * ID: hailunz
 * Date: 9/19/15
 */
public interface UpdateAuto {
    public void updateOptionSetName(String Modelname, String OptionSetname,
                                    String newName);
    public void updateOptionPrice(String Modelname, String Optionname,
                                  String Option, float newprice);

    public void setOptionChoice( String modelname,
                                 String setName, String optionName);

}

