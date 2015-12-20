package server;

import adapter.BuildAuto;
import model.Automobile;
import util.Properties;

import java.util.List;

/**
 * Hailun Zhu
 * ID: hailunz
 * Date: 10/4/15
 */
public class BuildCarModelOptions implements AutoServer {
    @Override
    public void BuildAuto(Properties prop){
        AutoServer auto = new BuildAuto();
        auto.BuildAuto(prop);
    }

    @Override
    public String getAvailableModels() {
        AutoServer auto = new BuildAuto();
        return auto.getAvailableModels();
    }

    @Override
    public Automobile getModel(String modelname) {
        AutoServer auto = new BuildAuto();
        return auto.getModel(modelname);
    }

}
