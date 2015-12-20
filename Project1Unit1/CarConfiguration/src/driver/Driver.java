package driver;


import model.Automotive;
import util.FileIO;
/**
 * Hailun Zhu
 * Id: hailunz
 * Date: 09/14/2015
 */
public class Driver {

    public static void main(String [] args) {
        //Build Automobile Object from a file.
        Automotive FordZTW =FileIO.buildAutoObject("test.txt");
        //Print attributes before serialization
        FordZTW.print();
        //Serialize the object
        FileIO.serializeAuto(FordZTW);
        //Deserialize the object and read it into memory.
        Automotive newFordZTW = FileIO.DeserializeAuto("auto.ser");
        //Print new attributes.
        newFordZTW.print();

        FordZTW.deleteOption("Power Moonroof", "not present");
        FordZTW.print();

        FordZTW.deleteOptionSet("Power Moonroof");
        FordZTW.print();

        FordZTW.setOpset(FordZTW.getOptionSetsSize(), "Power Moonroof", 1);
        FordZTW.setOption("Power Moonroof", 0, "present", 10);
        FordZTW.print();

        FordZTW.updateOptionSetName(0, "colorr");
        FordZTW.print();

    }

}

