package driver;



import database.*;
import model.*;
import java.io.*;
import adapter.*;
import util.*;

/**
 * Hailun Zhu
 * Id: hailunz
 * Date: 09/14/2015
 */
public class Driver {

    public static void main(String [] args) throws Exception {
        testUnit6();
    }

    // test the database operation directly and interaction with
    public static void testUnit6() throws Exception {
        String path = "/Users/hailunzhu/cmu/course/18641/mini1/" +
                "hailunz_project1/Project1Unit6/server/CarConfiguration/";

        System.out.println("Create Table if not exist.");

        UpdateTable up = new UpdateTable(path + "dbSetting.txt");
        up.insertUser();

        CreateTable ct = new CreateTable(path + "dbSetting.txt");
        ct.createTable(path + "tables.txt");

        System.out.println();
        System.out.println("test with LinkedHashMap.");

        BuildAuto buildAuto = new BuildAuto();
        buildAuto.setDbFile(path + "dbSetting.txt");

        System.out.println("Add model");
        buildAuto.BuildAuto(path + "test2.txt", 0);
        buildAuto.BuildAuto(path + "test.txt", 0);

        System.out.println("Delete model");
        buildAuto.delete("Toyota Corola");

        System.out.println("Update model");
        buildAuto.updateOptionSetName("Focus Wagon ZTW", "Color", "myColor");

        System.out.println("Update option price");
        buildAuto.updateOptionPrice("Focus Wagon ZTW", "myColor", "Grabber Green Clearcoat Metallic", 100);

        System.out.println("Update option name");
        buildAuto.updateOptionName("Focus Wagon ZTW", "myColor", "Grabber Green Clearcoat Metallic", "red");
    }

    public static void testDB(String path) throws Exception {
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        boolean eof = false;
        String line;
        String file = "test.txt";
        while (!eof) {
            StringBuilder welcome = new StringBuilder();
            welcome.append("*** Welcome to the User Interface! ***\n");
            welcome.append("*** This is a Car Configuration client. ***\n");
            welcome.append("*** To use this program, follow the instructions below. ***\n");
            System.out.println(welcome);

            System.out.println("\n* * * * * * * * * * * * * * *");
            System.out.println("1: create table");
            System.out.println("2: add model");
            System.out.println("3: delete model");
            System.out.println("4: update model");
            System.out.println("end: exit");
            line = buff.readLine();
            switch (line) {
                case "end":
                    eof = true;
                    break;
                case "1":
                    System.out.println("Create Table if not exist.");
                    CreateTable ct = new CreateTable(path + "dbSetting.txt");
                    ct.createTable(path + "tables.txt");
                    break;
                case "2":
                    System.out.println("Add model");
                    AddModel am = new AddModel(path + "dbSetting.txt");
                    Properties prop = new Properties();
                    FileInputStream in = new FileInputStream(new File(path + file));
                    prop.load(in);
                    Automobile auto = prop.build();
                    auto.print();
                    am.addMobile(auto);

                    prop = new Properties();
                    prop.load(new FileInputStream(new File(path + "test2.txt")));
                    auto = prop.build();
                    auto.print();
                    am.addMobile(auto);

                    am.close();
                    break;
                case "3":
                    System.out.println("Delete model");
                    DeleteModel dm = new DeleteModel(path + "dbSetting.txt");
                    dm.delete("Toyota Corola");
                    break;

                case "4":
                    System.out.println("Update set name");
                    UpdateTable ut = new UpdateTable(path + "dbSetting.txt");
                    ut.updateOptionSetName("Focus Wagon ZTW", "Color", "myColor");
                    System.out.println("Update option price");
                    ut.updateOptionPrice("Focus Wagon ZTW", "myColor", "Grabber Green Clearcoat Metallic", 100);
                    System.out.println("Update option name");
                    ut.updateOptionName("Focus Wagon ZTW", "myColor", "Grabber Green Clearcoat Metallic", "red");
            }
        }
    }
}

