package server;

import adapter.BuildAuto;
import adapter.CreateAuto;
import model.Automobile;
import util.Properties;

import java.io.*;
import java.net.*;

/**
 * Hailun Zhu
 * ID: hailunz
 * Date: 10/4/15
 */
public class DefaultSocketClientHandler extends Thread
        implements SocketClientConstants, SocketClientInterface{

    private Socket clientsocket=null;
    private ObjectInputStream objInput= null;
    private ObjectOutputStream objOutput = null;
    private String cliHost = null;
    private BuildCarModelOptions cars ;

    public DefaultSocketClientHandler(Socket cs, BuildCarModelOptions cars){
        this.clientsocket = cs;
        this.cliHost = cs.toString();
        this.cars = cars;
    }
    public void run(){
        if(openConnection()){
            handleSession();
            closeSession();
        }

    }
    @Override
    public boolean openConnection() {
        if (clientsocket == null)
            return false;
        return true;
    }

    @Override
    public void handleSession() {
        String strInput = "";
        if (DEBUG) System.out.println ("Handling session in handler!");
        try {
            objInput = new ObjectInputStream(this.clientsocket.getInputStream());
            objOutput = new ObjectOutputStream(this.clientsocket.getOutputStream());
            BuildCarModelOptions buildCar = cars;
            String command = (String) objInput.readObject();
            switch(command){
                case "Send txt file":
                    byte[] file = (byte[]) objInput.readObject();
                    CreateAuto auto = new BuildAuto();
                    auto.BuildAuto(file, 2);
                    sendMsg("Build model successfully!");
                    break;
                case "Send prop file":
                    Properties prop = (Properties)objInput.readObject();
                    buildCar.BuildAuto(prop);
                    sendMsg("Build model successfully!");
                    break;
                case "Request model list":
                    //sendMsg("Available models list");
                    System.out.println("Request model list");
                    String list = buildCar.getAvailableModels();
                    objOutput.writeObject(list);
                    break;

                case "Select Model":
                    System.out.println("Select Model");
                    String modelname = (String) objInput.readObject();
                    Automobile selectAuto = buildCar.getModel(modelname);
                    selectAuto.print();
                    objOutput.writeObject(selectAuto);

                default:
                    break;
            }
        }
        catch (IOException e){
            if (DEBUG)
                System.out.println ("Handling session in handler!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    void sendMsg(String msg){
        try {
            objOutput.writeObject(msg);
        } catch (IOException e) {
            System.out.println("SendMSG error: can't write!");
            e.printStackTrace();
        }
    }


    @Override
    public void closeSession() {
        if (DEBUG) System.out.println ("Close session in handler!");
        try {
            objInput = null;
            objOutput = null;
            clientsocket.close();
        }
        catch (IOException e){
            if (DEBUG) System.err.println
                    ("Error closing socket to " + cliHost);
        }

    }
}
