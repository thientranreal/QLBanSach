package DAO;

import DTO.NhanVien_DTO;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public class NhanVien_DAO {
     private static NhanVien_DAO instance;
     public static NhanVien_DAO getInstance(){
         if(instance==null){
             instance = new NhanVien_DAO();
         }
         return instance;
     }
     public NhanVien_DTO getNhanVienByUsernameAndPassword(String username,String password){
         NhanVien_DTO dto  = null;
         JDBC.openConnection();
         String query = "Select manv,hoten,ngaysinh,diachi,sodt,gioitinh,account,password,nhanvien.maquyen as maquyen,quyen.tenquyen as tenquyen from nhanvien,quyen where account = ? and password = ? and nhanvien.maquyen = quyen.maquyen";
         try {
             PreparedStatement st = JDBC.getCon().prepareStatement(query);
             st.setString(1,username);
             st.setString(2,password);
             java.sql.ResultSet rs = st.executeQuery();
             if(rs.next()){
                 dto = new NhanVien_DTO(rs.getString("manv"),rs.getString("hoten"),rs.getString("ngaysinh"),rs.getString("diachi"),rs.getString("sodt"),rs.getString("gioitinh"),rs.getString("account"),rs.getString("password"),rs.getString("maquyen"),rs.getString("tenquyen"));
             }
             
         } catch (Exception  ex) {
             System.out.println(ex);
         }
         JDBC.closeConnection();
             return dto;

     }
     public java.util.List<DTO.NhanVien_DTO> getAllNhanVien(){
        String query = "Select manv,hoten,ngaysinh,diachi,sodt,gioitinh,account,password,nhanvien.maquyen as maquyen,quyen.tenquyen as tenquyen from nhanvien,quyen where nhanvien.maquyen = quyen.maquyen";
        java.util.List<DTO.NhanVien_DTO> list = new ArrayList<DTO.NhanVien_DTO>();
        JDBC.openConnection();
        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            java.sql.ResultSet rs = st.executeQuery();
            while(rs.next()){
                list.add(new NhanVien_DTO(rs.getString("manv"),rs.getString("hoten"),rs.getString("ngaysinh"),rs.getString("diachi"),rs.getString("sodt"),rs.getString("gioitinh"),rs.getString("account"),rs.getString("password"),rs.getString("maquyen"),rs.getString("tenquyen")));

            }

        }catch(Exception ex){
            System.out.println(ex);
        }
        JDBC.closeConnection();
        return list;
    }
    public DTO.NhanVien_DTO getNhanVienByID(String manv){
        DTO.NhanVien_DTO dto=null;
        String query = "Select manv,hoten,ngaysinh,diachi,sodt,gioitinh,account,password,nhanvien.maquyen as maquyen,quyen.tenquyen as tenquyen from nhanvien,quyen where manv = ? and nhanvien.maquyen = quyen.maquyen";
        JDBC.openConnection();
        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            st.setString(1,manv);
            java.sql.ResultSet rs = st.executeQuery();
            if(rs.next()){
                dto = new NhanVien_DTO(rs.getString("manv"),rs.getString("hoten"),rs.getString("ngaysinh"),rs.getString("diachi"),rs.getString("sodt"),rs.getString("gioitinh"),rs.getString("account"),rs.getString("password"),rs.getString("maquyen"),rs.getString("tenquyen"));

            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        JDBC.closeConnection();
        return dto;
    }
    public java.util.List<NhanVien_DTO> getNhanVienLikeNameOrID(String like){
        java.util.List<NhanVien_DTO> list = new ArrayList<NhanVien_DTO>();
        String query = "Select manv,hoten,ngaysinh,diachi,sodt,gioitinh,account,password,nhanvien.maquyen as maquyen,quyen.tenquyen as tenquyen from NhanVien,quyen where (hoten like ? or manv like ?) and nhanvien.maquyen = quyen.maquyen";
        JDBC.openConnection();
        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            st.setString(1,like+"%");
            st.setString(2,like+"%");
            java.sql.ResultSet rs = st.executeQuery();
            while(rs.next()){
                list.add(new NhanVien_DTO(rs.getString("manv"),rs.getString("hoten"),rs.getString("ngaysinh"),rs.getString("diachi"),rs.getString("sodt"),rs.getString("gioitinh"),rs.getString("account"),rs.getString("password"),rs.getString("maquyen"),rs.getString("tenquyen")));
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        JDBC.closeConnection();
        return list;
    }
    public boolean isExists(String manv){
        String query = "Select manv,hoten,ngaysinh,diachi,sodt,gioitinh,account,password,nhanvien.maquyen as maquyen,quyen.tenquyen as tenquyen from NhanVien,quyen where manv = ? and nhanvien.maquyen = quyen.maquyen";
        JDBC.openConnection();
        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            st.setString(1,manv);
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
    public boolean insert(DTO.NhanVien_DTO dto){
        String query = "insert into NhanVien(manv,hoten,ngaysinh,diachi,sodt,gioitinh,account,password,maquyen) values(?,?,?,?,?,?,?,?,?)";
        JDBC.openConnection();
        int n =0;
        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            st.setString(1,dto.getMaNV());
            st.setString(2,dto.getName());
            st.setString(3,dto.getBirth());
            st.setString(4,dto.getDiachi());
            st.setString(5,dto.getSdt());
            st.setString(6,dto.getGender());
            st.setString(7,dto.getUsername());
            st.setString(8,dto.getPassword());
            st.setString(9,dto.getMaquyen());
            n = st.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex);
        }
        JDBC.closeConnection();
        if(n>0){
            return true;
        }
        return false;
    }
    public boolean update(DTO.NhanVien_DTO dto){
        String query = "Update NhanVien set hoten = ? ,ngaysinh = ? ,diachi = ? ,sodt = ? ,gioitinh = ?,password = ? ,maquyen = ? where manv = ?";
        JDBC.openConnection();
        int n =0;
        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            st.setString(1,dto.getName());
            st.setString(2,dto.getBirth());
            st.setString(3,dto.getDiachi());
            st.setString(4,dto.getSdt());
            st.setString(5,dto.getGender());
            st.setString(6,dto.getPassword());
            st.setString(7,dto.getMaquyen());
            st.setString(8,dto.getMaNV());
            n = st.executeUpdate();
        }catch(Exception ex){
              System.out.println(ex);
        }
        JDBC.closeConnection();
        if(n>0)return true;
        return false;
    }
    public boolean remove(String manv){
        String query = "Delete from nhanvien where manv = ?";
        JDBC.openConnection();
        int n =0;
        try{
           PreparedStatement st = JDBC.getCon().prepareStatement(query);
           st.setString(1,manv);
           n = st.executeUpdate();
        }catch(Exception ex){
             System.out.println(ex);
        }
        JDBC.closeConnection();
        if(n>0)return true;
        return false;
    }
    public java.util.List<String> getListQuyen(){
        java.util.List<String> list = new ArrayList<String>();
        String query = "Select tenquyen from quyen";
        JDBC.openConnection();
        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            java.sql.ResultSet rs = st.executeQuery();
            while(rs.next()){
                list.add(rs.getString("tenquyen"));
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        JDBC.closeConnection();
        return list;

    }
    public String getMaQuyenByTenQuyen(String tenquyen){
        String maquyen = "";
        String query = "Select maquyen from quyen where tenquyen= ?";
        JDBC.openConnection();
        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            st.setString(1,tenquyen);

            java.sql.ResultSet rs = st.executeQuery();
            if(rs.next()){
                maquyen = rs.getString("maquyen");
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        JDBC.closeConnection();
        return maquyen;
    }
    public boolean isExistsUsername(String username){
         String query = "Select * from nhanvien where account = ?";
         JDBC.openConnection();
         try{
             PreparedStatement st = JDBC.getCon().prepareStatement(query);
             st.setString(1,username);
             java.sql.ResultSet rs = st.executeQuery();
             if(rs.next()){
                 return true;
             }
         }catch(Exception ex){

         }
         JDBC.closeConnection();
         return false;
    }
}
