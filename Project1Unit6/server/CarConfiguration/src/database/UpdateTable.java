package database;

import adapter.BuildAuto;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Hailun Zhu
 * ID: hailunz
 * Date: 10/13/15
 */
public class UpdateTable extends JDBCAdapter{
    private BuildAuto ba;
    public UpdateTable(String propFile) throws Exception {
        super(propFile);
        ba = new BuildAuto();
    }

    public UpdateTable(String url, String driverName) {
        super(url, driverName);
        ba = new BuildAuto();
    }

    public UpdateTable(String url, String driverName, String user, String passwd) {
        super(url, driverName, user, passwd);
        ba = new BuildAuto();
    }

    public void updateOptionName(String modelname, String Optionset, String Option, String newName){
        StringBuffer sql = new StringBuffer();
        sql.append("Select id from Automobile where name='" + modelname + "';");
        ResultSet rs ;
        int auto_id = -1;
        try {
            rs = this.statement.executeQuery(sql.toString());
            if (rs.next()){
                auto_id = rs.getInt(1);
                int set_id = -1;
                sql = new StringBuffer();
                sql.append("Select id from OptionSet where name='"
                        + Optionset + "' and auto_id=" + auto_id + ";");
                System.out.println(sql.toString());
                rs = this.statement.executeQuery(sql.toString());

                if (rs.next()){
                    set_id = rs.getInt(1);
                    sql = new StringBuffer();
                    sql.append("UPDATE Options SET name='"+newName +
                            "' where name='"+ Option + "' and optset_id=" + set_id+";");
                    this.statement.executeUpdate(sql.toString());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateOptionSetName(String Modelname, String OptionSetname, String newName) {
        StringBuffer sql = new StringBuffer();
        sql.append("Select id from Automobile where name='" + Modelname + "';");

        int auto_id = -1;
        try {
            this.resultSet= this.statement.executeQuery(sql.toString());
            if (resultSet.next()){
                auto_id = resultSet.getInt(1);
                sql = new StringBuffer();
                sql.append("UPDATE OptionSet SET name='"+ newName+
                        "' where name='"+ OptionSetname + "' and auto_id=" + auto_id+";");

                System.out.println(sql.toString());
                this.statement.executeUpdate(sql.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateOptionPrice(String Modelname, String Optionset, String Option, float newprice) {
        StringBuffer sql = new StringBuffer();
        sql.append("Select id from Automobile where name='" + Modelname + "';");
        ResultSet rs ;
        int auto_id = -1;
        try {
            rs = this.statement.executeQuery(sql.toString());
            if (rs.next()){
                auto_id = rs.getInt(1);
                int set_id = -1;
                sql = new StringBuffer();
                sql.append("Select id from OptionSet where name='"
                        + Optionset + "' and auto_id=" + auto_id + ";");
                System.out.println(sql.toString());
                rs = this.statement.executeQuery(sql.toString());

                if (rs.next()){
                    set_id = rs.getInt(1);
                    sql = new StringBuffer();
                    sql.append("UPDATE Options SET price="+newprice +
                            " where name='"+ Option + "' and optset_id=" + set_id+";");
                    this.statement.executeUpdate(sql.toString());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUser(){
        try {
            PreparedStatement updateemp = connection.prepareStatement
                    ("insert into user_info values(?,?,?)");
            updateemp.setString(1,"test1");
            updateemp.setString(2, "CEO");
            updateemp.setBytes(3,"testtest".getBytes());
            updateemp.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
