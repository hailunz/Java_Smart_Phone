package server;

import model.Automobile;
import util.Properties;

import java.util.List;

/**
 * Hailun Zhu
 * ID: hailunz
 * Date: 10/4/15
 */
public interface AutoServer {
    public void BuildAuto(Properties prop);
    public String getAvailableModels();
    public Automobile getModel(String modelname);
}
