package database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Hailun Zhu
 * ID: hailunz
 * Date: 10/13/15
 */
public class CreateTable extends JDBCAdapter {
    public CreateTable(String propFile) throws Exception {
        super(propFile);
    }

    public CreateTable(String url, String driverName) {
        super(url, driverName);
    }

    public CreateTable(String url, String driverName, String user, String passwd) {
        super(url, driverName, user, passwd);
    }

    public void createTable( String filename){
        try {
            FileReader file = new FileReader(filename);
            BufferedReader buff = new BufferedReader(file);
            boolean eof = false;
            String line = null;
            StringBuffer sb = new StringBuffer();

            while (!eof) {
                sb = new StringBuffer();
                line = buff.readLine();
                if (line == null){
                    eof = true;
                }else if (line.length()!=0){
                   while(line.indexOf(';')==-1){
                       sb.append(line);
                       line = buff.readLine();
                   }
                    sb.append(line);
                    System.out.println(sb.toString());
                    System.out.println("executed!");
                    this.statement.executeUpdate(sb.toString());
                }
            }

            buff.close();
            file.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
