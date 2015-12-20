package client;

import adapter.*;
import model.Automobile;
import util.Properties;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Hailun Zhu
 * ID: hailunz
 * Date: 10/4/15
 */
public class DefaultSocketClient extends Thread implements SocketClientConstants,
        SocketClientInterface {
    private ObjectInputStream input;
    private ObjectOutput output;
    private Socket sock;
    private String strHost;
    private int iPort;
    private CarModelOptionsIO carModel;
    private SelectCarOption selectCar;

    //constructor
    public DefaultSocketClient(String strHost, int iPort) {
        setPort(iPort);
        setHost(strHost);
        carModel = new CarModelOptionsIO();
    }

    // run
    public void run(){
        if (openConnection()){
            handleSession();
            closeSession();
        }
    }

    public boolean openConnection(){

        try {
            sock = new Socket(strHost, iPort);
        }
        catch(IOException socketError){
            if (DEBUG) System.err.println
                    ("Unable to connect to " + strHost);
            return false;
        }
        try {
            output = new ObjectOutputStream(sock.getOutputStream());
        }
        catch (Exception e){
            if (DEBUG) System.err.println
                    ("Unable to obtain stream to/from " + strHost);
            return false;
        }
        return true;
    }

    @Override
    public void handleSession(){
        String strInput = "";
        if (DEBUG) System.out.println ("Handling session with "
                + strHost + ":" + iPort);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // user interface, write in

        System.out.println("\n* * * * * * * * * * * * * * *");
        System.out.println("main wait, type your command:");
        System.out.println("Usage-");
        System.out.println("1.Load and send property file");
        System.out.println("2.Send txt file");
        System.out.println("3.Request available model");
        System.out.println("bye: to exit ");
        System.out.println(">");
        try {
            String command = br.readLine();
            if (command.startsWith("bye")){
                System.out.println("end");

            }
            else if (command.startsWith("1")){
                System.out.print("give property file path:");
                String filename=br.readLine();
                FileInputStream in = new FileInputStream(new File(filename));
                Properties prop = carModel.load(in);
                sendString("Send prop file");
                sendObject(prop);
                // wait the response
                input = new ObjectInputStream(this.sock.getInputStream());
                try {
                    String response = (String) input.readObject();
                    System.out.println(response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            else if (command.startsWith("2")){
                System.out.print("give txt file path:");
                String filename=br.readLine();
                File in = new File(filename);
                byte[] content = Files.readAllBytes(in.toPath());
                sendString("Send txt file");
                sendObject(content);
                // wait the response
                input = new ObjectInputStream(this.sock.getInputStream());
                try {
                    String response = (String) input.readObject();
                    System.out.println(response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else if (command.startsWith("3")){
                System.out.print("Request available model");
                sendString("Request model list");

                input = new ObjectInputStream(this.sock.getInputStream());
                try {
                    String list = (String) input.readObject();
                    System.out.println("Available models:");
                    System.out.print(list);
                    System.out.println();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                //
                System.out.print("Enter modelname:");
                String modelname = br.readLine();
                sendString(modelname);
                try {
                    Automobile auto = (Automobile) input.readObject();
                    BuildAuto build = new BuildAuto();
                    build.setAuto(auto);
                    selectCar = new SelectCarOption(build);
                    auto.print();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                // configure car
                System.out.print("Car configuration:");
                selectCar.printAuto(selectCar.modelname);
                boolean optionFlag = true;
                while(optionFlag){
                    System.out.println("Choose optionset:");
                    String optionSet = br.readLine();
                     if (optionSet.equals("end"))
                         break;
                    System.out.println("Choose option:");
                    String option = br.readLine();
                    selectCar.setOptionChoice(selectCar.modelname, optionSet, option);
                }
                System.out.println("Display the options:");
                selectCar.printAuto(selectCar.modelname);
            }
            else{
                System.out.println("Wrong command!");
            }
        } catch (IOException e) {
            System.out.println("main error!");
            e.printStackTrace();
        }

    }

    public void sendString(String msg){
        try {
            output.writeObject(msg);
        }
        catch (IOException e){
            if (DEBUG) System.out.println
                    ("Error writing to " + strHost);
        }
    }

    public void sendObject(Object obj){
        try {
            output.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleInput(String strInput){
        System.out.println(strInput);
    }

    public void closeSession(){
        if (DEBUG) System.err.println
                ("closing socket to " + strHost);
        try {
            input = null;
            output = null;
            sock.close();
        }
        catch (IOException e){
            if (DEBUG) System.err.println
                    ("Error closing socket to " + strHost);
        }
    }

    public void setHost(String strHost){
        this.strHost = strHost;
    }

    public void setPort(int iPort){
        this.iPort = iPort;
    }

}
