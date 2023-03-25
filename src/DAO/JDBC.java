package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {

    public JDBC(){

    }
    private static Connection con;
    public static boolean openConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionStr = "jdbc:sqlserver://localhost:1433;"+
                    "databaseName=BookStore;"+
                    "user=sa;"+
                    "password=123456;"+
                    "encrypt=true;trustServerCertificate=true";
            con = DriverManager.getConnection(connectionStr);
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }
    public static void closeConnection() {
        try {
            if (con != null)
                con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public static ResultSet executeQuery(string query,object[] param=null){
        PreparedStatement st = con.prepareStatement(query);
        if(param !=null){
            for(int i =0 ;i < param.length;i++){
               st.setObject(i+1,param.get(i));
            }
        }
        return st.executeQuery();
    }
    public static int executeNonQuery(string query ,object[] param=null){
        PreparedStatement st = con.prepareStatement(query);
        if(param !=null){
            for(int i =0 ;i < param.length;i++){
               st.setObject(i+1,param.get(i));
            }
        }
        return st.executeUpdate();
    }
}
