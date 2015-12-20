package database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Hailun Zhu
 * ID: hailunz
 * Date: 10/13/15
 */
public class DeleteModel extends JDBCAdapter {
    public DeleteModel(String propFile) throws Exception {
        super(propFile);
    }

    public DeleteModel(String url, String driverName) {
        super(url, driverName);
    }

    public DeleteModel(String url, String driverName, String user, String passwd) {
        super(url, driverName, user, passwd);
    }

    public void delete(String modelname) throws SQLException {
        String sql = "Select id from Automobile where name='" + modelname+"';";
        this.resultSet = this.statement.executeQuery(sql);
        if (resultSet.next()){
            int id = resultSet.getInt(1);
            sql = "delete from Automobile where id="+String.valueOf(id)+";";
            this.statement.executeUpdate(sql);
            System.out.println("delete automobile with id :" + id);
        }
    }
}
