package database;

import adapter.BuildAuto;
import model.Automobile;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Hailun Zhu
 * ID: hailunz
 * Date: 10/13/15
 */
public class AddModel extends JDBCAdapter {
    public AddModel(String propFile) throws Exception {
        super(propFile);
    }

    public AddModel(String url, String driverName) {
        super(url, driverName);
    }

    public AddModel(String url, String driverName, String user, String passwd) {
        super(url, driverName, user, passwd);
    }

    public void addMobile(Automobile model){

        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO Automobile (name, make, model, base_price) VALUES (");
        sb.append("'"+model.getName()+"'" + ",");
        sb.append("'"+model.getMake() +"'"+ ",");
        sb.append("'"+model.getModel()+"'" + ",");
        sb.append(model.getBaseprice());
        sb.append(");");
        int auto_id = -1;
        try {
            System.out.println(sb.toString());
            this.statement.executeUpdate(sb.toString(),Statement.RETURN_GENERATED_KEYS);
             ResultSet rs = this.statement.getGeneratedKeys();
            if (rs.next())
                auto_id = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        System.out.println("insert to automobile id:" + String.valueOf(auto_id));
        try{
            if (auto_id == -1){
                throw new Exception("Wrong insert id");
            }

        }catch (Exception e){
            e.printStackTrace();
            return;
        }

        int set_id = -1;
        int size = model.getOptionSetsSize();
        StringBuffer option ;
        for(int i=0;i<size;i++){
            sb = new StringBuffer();
            sb.append("INSERT INTO OptionSet (name, auto_id) VALUES( "
                    +"'" +model.getOpsetName(i)+"'" + ",");
            sb.append(auto_id);
            sb.append(");");

            try {
                System.out.println(sb.toString());
                this.statement.executeUpdate(sb.toString(),Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = this.statement.getGeneratedKeys();
                if (rs.next()) {
                    set_id = rs.getInt(1);
                    System.out.println("insert to optionSet id:" + String.valueOf(set_id));

                    int optionSize = model.getOptionsSize(i);

                    for (int j = 0; j < optionSize; j++) {
                        option = new StringBuffer();
                        option.append("INSERT INTO Options (name,price, optset_id) VALUES (");
                        option.append("'"+ model.getOptionName(i,j)+"'"+",");
                        option.append(model.getOptionPrice(i, j)+",");
                        option.append(set_id);
                        option.append(");");
                        this.statement.executeUpdate(option.toString(), Statement.RETURN_GENERATED_KEYS);
                        System.out.println("insert to option.");
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
