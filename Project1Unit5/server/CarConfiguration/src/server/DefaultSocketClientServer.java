package server;

import java.io.*;
import java.net.*;

/**
 * Hailun Zhu
 * ID: hailunz
 * Date: 10/4/15
 */
public class DefaultSocketClientServer extends Thread implements SocketClientConstants,
                                                    SocketClientInterface {
    private BufferedReader reader;
    private BufferedWriter writer;
    private ServerSocket sock;
    private Socket cliSock;
    private String strHost;
    private int iPort;
    private BuildCarModelOptions cars;

    //constructor
    public DefaultSocketClientServer(String strHost, int iPort) {
        setPort(iPort);
        setHost(strHost);
        cars = new BuildCarModelOptions();
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
            sock = new ServerSocket(iPort);
        }
        catch(IOException socketError){
            if (DEBUG) System.err.println
                    ("Unable to connect to " + strHost);
            return false;
        }
        return true;
    }

    @Override
    public void handleSession(){
        String strInput = "";
        if (DEBUG) System.out.println ("Handling session with "
                + strHost + ":" + iPort);
        boolean flag = false;
        while(true){
            try {
                cliSock = sock.accept();
                DefaultSocketClientHandler handler
                        = new DefaultSocketClientHandler(cliSock, cars);
                handler.start();

                if (DEBUG)
                    System.out.println("serverSocket: have created a new connection!"
                            + cliSock.getRemoteSocketAddress());

            }
            catch (Exception e){
                if (DEBUG) System.out.println ("Handling session with "
                        + strHost + ":" + iPort);
            }
        }

    }

    public void sendOutput(String strOutput){
        try {
            writer.write(strOutput, 0, strOutput.length());
        }
        catch (IOException e){
            if (DEBUG) System.out.println
                    ("Error writing to " + strHost);
        }
    }

    public void handleInput(String strInput){
        System.out.println(strInput);
    }

    public void closeSession(){
        try {
            writer = null;
            reader = null;
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
