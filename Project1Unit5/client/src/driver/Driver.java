package driver;


import adapter.*;
import client.DefaultSocketClient;
import exception.AutoException;
import scale.*;

import java.io.*;


/**
 * Hailun Zhu
 * Id: hailunz
 * Date: 09/14/2015
 */
public class Driver {

    public static void main(String [] args) {
        testUnit4Client1();
    }

    public static void testUnit4Client1(){
        StringBuilder welcome = new StringBuilder();
        welcome.append("*** Welcome to the User Interface! ***\n");
        welcome.append("*** This is a Car Configuration client. ***\n");
        welcome.append("*** To use this program, follow the instructions below. ***\n");

        System.out.println(welcome);

        BufferedReader readcom = new BufferedReader(new InputStreamReader(System.in));
        String filePath =null;

        System.out.println("* Give server name:");
        //BufferedReader readcom = new BufferedReader(new InputStreamReader(System.in));
        String lname=null;
        try {
            lname=readcom.readLine();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        System.out.println("* Give server port:");
        //BufferedReader readcom = new BufferedReader(new InputStreamReader(System.in));
        int port = 12345;
        try {
            port=Integer.parseInt(readcom.readLine());
        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }

}

