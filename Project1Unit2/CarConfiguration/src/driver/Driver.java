package driver;


import adapter.BuildAuto;
import adapter.CreateAuto;
import adapter.UpdateAuto;
import exception.AutoException;

/**
 * Hailun Zhu
 * Id: hailunz
 * Date: 09/14/2015
 */
public class Driver {

    public static void main(String [] args) {

        // test creation
        CreateAuto c1 = new BuildAuto();
        UpdateAuto u1 = new BuildAuto();
        BuildAuto ba1 = new BuildAuto();

        c1.BuildAuto("test.txt");
        c1.printAuto("Focus Wagon ZTW");
        c1.BuildAuto("test2.txt");
        c1.printAuto("Toyota Camery");

        ba1.BuildAuto("test3.txt");

        // test serializable and deserializable
        ba1.writeToFile("Toyota Corola");
        ba1.readFromFile("auto.ser");
        ba1.print();


        // test update
        u1.updateOptionSetName("Toyota Corola","Color","MyColor");
        u1.updateOptionPrice("Toyota Corola", "MyColor",
                "Fort Knox Gold Clearcoat Metallic", 10);
        u1.setOptionChoice("Toyota Corola","Side Impact Air Bags","present");
        c1.printAuto("Toyota Corola");

        // test Choice prices
        ba1.getTotalPrice("Toyota Camery");
        ba1.getTotalPrice("Toyota Corola");

        // test Exception

        // test missing filename
        // test missing modelname
        // test missing baseprice
        // test missing optionSet data
        // test missing option data

        // the accepted input was:
        // test4.txt
        // Option Data: only have one: something like: present@0
        // optionSet: something like, could have multiple values
        // : present@0,not present@10
        // some float value
        // My model

        System.out.println();
        System.out.println("==========Test Exception===============");
        c1.BuildAuto(null);
        c1.printAuto("My model");


        System.out.println();
        System.out.println("==========Test Exception Log===============");
        AutoException e = new AutoException(6,"test");
        e.logError();

    }




}

