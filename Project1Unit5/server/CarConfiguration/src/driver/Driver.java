package driver;


import adapter.BuildAuto;
import adapter.CreateAuto;
import scale.EditThread;
import adapter.UpdateAuto;
import exception.AutoException;
import scale.EditOptions;
import server.DefaultSocketClientServer;

/**
 * Hailun Zhu
 * Id: hailunz
 * Date: 09/14/2015
 */
public class Driver {

    public static void main(String [] args) {
        testUnit4();
    }

    public static void testUnit4(){
        DefaultSocketClientServer server = new DefaultSocketClientServer("Server",12345);
        server.start();
    }

    public static void testUnit3(){

        // create Automobile model
        BuildAuto auto = new BuildAuto();
        auto.BuildAuto("test.txt");
        auto.printAuto("Focus Wagon ZTW");

        // create EditOptionThread

        EditThread edit1 = auto;
        // edit
        EditOptions t1 = new EditOptions("thread1", edit1,0,"Focus Wagon ZTW");
        // read
        EditOptions t2 = new EditOptions("thread2", edit1,1,"Focus Wagon ZTW");


        // test1: test edit, edit
        System.out.println();
        System.out.println("==========Test change Option Choice ===============");
        t1.start();
        t2.start();

        boolean t1IsAlive = true;
        boolean t2IsAlive = true;

        do {
            if(t1IsAlive && !t1.isAlive()) {
                t1IsAlive = false;
                System.out.println("t1 is dead.");
            }

            if(t2IsAlive && !t2.isAlive()) {
                t2IsAlive = false;
                System.out.println("t2 is dead.");
            }
        } while(t1IsAlive || t2IsAlive);

        // test2: test edit and get
        System.out.println();
        System.out.println("==========Test change Option Choice Price===============");
        //edit
        t1 = new EditOptions("thread1", edit1,2,"Focus Wagon ZTW");
        //read
        t2 = new EditOptions("thread2", edit1,3,"Focus Wagon ZTW");

        t2.start();
        t1.start();

        t1IsAlive = true;
        t2IsAlive = true;

        do {
            if(t1IsAlive && !t1.isAlive()) {
                t1IsAlive = false;
                System.out.println("t1 is dead.");
            }

            if(t2IsAlive && !t2.isAlive()) {
                t2IsAlive = false;
                System.out.println("t2 is dead.");
            }
        } while(t1IsAlive || t2IsAlive);


        System.out.println("==========Test change Option Choice Price===============");
        //edit
        t1 = new EditOptions("thread1", edit1,4,"Focus Wagon ZTW");
        //read
        t2 = new EditOptions("thread2", edit1,5,"Focus Wagon ZTW");

        t1.start();
        t2.start();

        t1IsAlive = true;
        t2IsAlive = true;

        do {
            if(t1IsAlive && !t1.isAlive()) {
                t1IsAlive = false;
                System.out.println("t1 is dead.");
            }

            if(t2IsAlive && !t2.isAlive()) {
                t2IsAlive = false;
                System.out.println("t2 is dead.");
            }
        } while(t1IsAlive || t2IsAlive);
    }

    public static void testUnit2(){
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

