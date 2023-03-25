package DAO;
import java.sql.*;
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
    public static ResultSet executeQuery(String query,Object[] param){
       try{ 
        PreparedStatement st = con.prepareStatement(query);
        if(param !=null ){
            for(int i =0 ;i < param.length;i++){
               st.setObject(i+1,param[i]);
            }
        }
        return st.executeQuery();
       }
        catch(SQLException ex){
            System.out.println(ex);
            return null;
        }
    }
    public static int executeNonQuery(String query ,Object[] param){
       try{
        PreparedStatement st = con.prepareStatement(query);
        if(param !=null){
            for(int i =0 ;i < param.length;i++){
               st.setObject(i+1,param[i]);
            }
        }
        return st.executeUpdate();
       }catch(SQLException ex){
           System.out.println(ex);
           return -1;
       }
    }
}
