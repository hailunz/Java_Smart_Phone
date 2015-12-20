package client;

import adapter.BuildAuto;
import adapter.CreateAuto;
import util.Properties;

import java.io.FileInputStream;

/**
 * Hailun Zhu
 * ID: hailunz
 * Date: 10/4/15
 */
public class CarModelOptionsIO implements CreateAuto{
    private  CreateAuto auto = new BuildAuto();
    @Override
    public void BuildAuto(String filename) {
        auto.BuildAuto(filename);
    }

    @Override
    public void BuildAuto(Object file, int fileType) {
        auto.BuildAuto(file, fileType);
    }

    @Override
    public void printAuto(String modelname) {
       auto.printAuto(modelname);
    }

    public Properties load(FileInputStream in){
        Properties prop = new Properties();
        prop.load(in);
        return prop;
    }
}
