package DAO;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public class PhanQuyen_DAO {
    private static PhanQuyen_DAO instance;
    public static PhanQuyen_DAO getInstance(){
        if (instance==null){
            instance = new PhanQuyen_DAO();
        }
        return instance;
    }
    public java.util.List<String> getChucNang(String maquyen){
        String query = "Select macn from ct_quyen where maquyen = ? ";
        java.util.List<String> list = new ArrayList<String>();

        JDBC.openConnection();
        try {
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            st.setString(1,maquyen);
            java.sql.ResultSet rs = st.executeQuery();
            while(rs.next()){
                 list.add(rs.getString("macn"));
            }


        }catch(Exception ex){
            System.out.println(ex);
        }
        JDBC.closeConnection();
        return list;

    }
    public java.util.List<String> getLeftChucNang(String maquyen){
        java.util.List<String> list = new ArrayList<String>();
        String query = "Select tencn from chucnang where macn not in (Select macn from ct_quyen where maquyen = ? )";
        JDBC.openConnection();
        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            st.setString(1,maquyen);
            java.sql.ResultSet rs = st.executeQuery();
            while(rs.next()){
                list.add(rs.getString("tencn"));
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        JDBC.closeConnection();
        return list;


    }
    public java.util.List<String> getTenChucNang(String maquyen){
        String query = "Select chucnang.tencn as tencn  from ct_quyen,chucnang where ct_quyen.maquyen = ? and ct_quyen.macn = chucnang.macn  ";
        java.util.List<String> list = new ArrayList<String>();

        JDBC.openConnection();
        try {
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            st.setString(1,maquyen);
            java.sql.ResultSet rs = st.executeQuery();
            while(rs.next()){
                 list.add(rs.getString("tencn"));
            }


        }catch(Exception ex){
            System.out.println(ex);
        }
        JDBC.closeConnection();
        return list;

    }
    public java.util.List<String> getAllTenChucNang(){
        java.util.List<String> list = new ArrayList<String>();
        String query =  "Select tencn from chucnang";
        JDBC.openConnection();
        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            java.sql.ResultSet rs = st.executeQuery();
            while(rs.next()){
                list.add(rs.getString("tencn"));
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        JDBC.closeConnection();
        return list;
     }
     public java.util.List<DTO.Quyen_DTO> getAllQuyen(){
        java.util.List<DTO.Quyen_DTO> list = new ArrayList<DTO.Quyen_DTO>();
        String query = "Select * from quyen";
        JDBC.openConnection();
        try{
             PreparedStatement st = JDBC.getCon().prepareStatement(query);
             java.sql.ResultSet rs = st.executeQuery();
             while(rs.next()){
                list.add(new DTO.Quyen_DTO(rs.getString("maquyen"),rs.getString("tenquyen")));

             }
        }catch(Exception ex){
            System.out.println(ex);
        }
        JDBC.closeConnection();
        return list;

     }
     public String getMaChucNangByTenChucNang(String tenchucnang){
        String query = "select macn from chucnang where tencn = ?";
        String result="";
        JDBC.openConnection();
        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            st.setString(1,tenchucnang);
            java.sql.ResultSet rs = st.executeQuery();
            if(rs.next()){
                result = rs.getString("macn");
            }
        }catch(Exception ex){System.out.println(ex);

         }
        JDBC.closeConnection();
        return result;
     }
     public boolean insertChucNangToQuyen(String maquyen,String tenchucnang){
        String query = "insert into ct_quyen(maquyen,macn) values(?,?)";
        JDBC.openConnection();
        int n =0;
        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            st.setString(1,maquyen);
            st.setString(2,getMaChucNangByTenChucNang(tenchucnang));
            n= st.executeUpdate();

        }catch(Exception ex){
            System.out.println(ex);
        }
        JDBC.closeConnection();
        if(n>0)return true;
        return false;
     }
     public boolean removeTenChucNangInQuyen(String maquyen,String tenchucnang){
        String query = "delete from ct_quyen where maquyen = ? and macn = ?";
        JDBC.openConnection();
        int n =0;
        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            st.setString(1,maquyen);
            st.setString(2,getMaChucNangByTenChucNang(tenchucnang));
            n=st.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex);
        }
       JDBC.closeConnection();
        if(n>0)return true;
        return false;

    }
    public boolean removeQuyen(String maquyen){
        String query = "Delete from quyen where maquyen = ?";
        JDBC.openConnection();
        int n =0;
        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            st.setString(1,maquyen);
            n = st.executeUpdate();
        }catch (Exception ex){
            System.out.println(ex);
        }
        JDBC.closeConnection();
        if(n>0)return true;
        return false;

    }
    public boolean isExistsTenQuyenExceptMe(String maquyen,String tenquyen){
        String query  = "Select * from quyen where tenquyen = ? and maquyen <> ?";
        JDBC.openConnection();
        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            st.setString(1,tenquyen);
            st.setString(2,maquyen);
            java.sql.ResultSet rs = st.executeQuery();
            if(rs.next()){
                JDBC.closeConnection();
                return true;
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        JDBC.closeConnection();
        return  false;
    }
     public boolean updateQuyen(String maquyen,String tenquyen){
        String query = "update quyen set tenquyen = ? where maquyen = ? ";
        JDBC.openConnection();
        int n =0;
        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            st.setString(1,tenquyen);
            st.setString(2,maquyen);
            n = st.executeUpdate();


        }catch(Exception ex){
            System.out.println(ex);
        }
        JDBC.closeConnection();
        if(n>0)return  true;
        return false;
     }
     public boolean isExists(String maquyen){
        String query = "Select * from quyen where maquyen = ?";
        JDBC.openConnection();
        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            st.setString(1,maquyen);
            java.sql.ResultSet rs = st.executeQuery();
            if(rs.next()){
                JDBC.closeConnection();
                return true;
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        JDBC.closeConnection();
        return false;
     }
     public boolean isExistsTenQuyen(String tenquyen){
        String query = "Select * from quyen where tenquyen = ?";
        JDBC.openConnection();
        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            st.setString(1,tenquyen);
            java.sql.ResultSet rs = st.executeQuery();
            if(rs.next()){
                JDBC.closeConnection();
                return true;
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        JDBC.closeConnection();
        return false;
     }
     public boolean insert(String maquyen,String tenquyen){
        String query = "insert into quyen(maquyen,tenquyen) values(?,?)";
        JDBC.openConnection();
        int n =0;
        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            st.setString(1,maquyen);
            st.setString(2,tenquyen);
            n = st.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex);
        }
        JDBC.closeConnection();
        if(n>0)return true;
        return false;
     }
     public boolean isExistsTenChucNangInQuyen(String maquyen,String tenchucnang){
        String query = "Select * from ct_quyen,chucnang where ct_quyen.macn = chucnang.macn and ct_quyen.maquyen = ? and chucnang.tencn = ?";
        JDBC.openConnection();

        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            st.setString(1,maquyen);
            st.setString(2,tenchucnang);
            java.sql.ResultSet rs = st.executeQuery();
            if(rs.next()){
                JDBC.closeConnection();
                return true;
            }

        }catch(Exception ex){
            System.out.println(ex);
        }
        JDBC.closeConnection();
        return false;
     }
}
