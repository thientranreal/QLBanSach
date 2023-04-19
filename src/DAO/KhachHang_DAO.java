package DAO;

import DTO.KhachHang_DTO;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public class KhachHang_DAO {
    private static KhachHang_DAO instance;
    public static KhachHang_DAO getInstance(){
        if(instance==null){
            instance = new KhachHang_DAO();
        }
        return instance;
    }
    public java.util.List<DTO.KhachHang_DTO> getAllKhachHang(){
        String query = "Select * from khachhang";
        java.util.List<DTO.KhachHang_DTO> list = new ArrayList<DTO.KhachHang_DTO>();
        JDBC.openConnection();
        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            java.sql.ResultSet rs = st.executeQuery();
            while(rs.next()){
                list.add(new KhachHang_DTO(rs.getString("makh"),rs.getString("hoten"),rs.getString("ngaysinh"),rs.getString("diachi"),rs.getString("sodt"),rs.getString("gioitinh")));

            }

        }catch(Exception ex){

        }
        JDBC.closeConnection();
        return list;
    }
    public DTO.KhachHang_DTO getKhachHangByID(String makh){
        DTO.KhachHang_DTO dto=null;
        String query = "Select * from khachhang where makh = ?";
        JDBC.openConnection();
        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            st.setString(1,makh);
            java.sql.ResultSet rs = st.executeQuery();
            if(rs.next()){
                dto = new DTO.KhachHang_DTO(rs.getString("makh"),rs.getString("hoten"),rs.getString("ngaysinh"),rs.getString("diachi"),rs.getString("sodt"),rs.getString("gioitinh"));

            }
        }catch(Exception ex){

        }
        JDBC.closeConnection();
        return dto;
    }
    public java.util.List<KhachHang_DTO> getKhachHangLikeNameOrID(String like){
        java.util.List<KhachHang_DTO> list = new ArrayList<KhachHang_DTO>();
        String query = "Select * from khachhang where hoten like ? or makh like ? ";
        JDBC.openConnection();
        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            st.setString(1,like+"%");
            st.setString(2,like+"%");
            java.sql.ResultSet rs = st.executeQuery();
            while(rs.next()){
                list.add(new KhachHang_DTO(rs.getString("makh"),rs.getString("hoten"),rs.getString("ngaysinh"),rs.getString("diachi"),rs.getString("sodt"),rs.getString("gioitinh")));
            }
        }catch(Exception ex){

        }
        JDBC.closeConnection();
        return list;
    }
    public boolean isExists(String makh){
        String query = "Select * from khachhang where makh = ?";
        JDBC.openConnection();
        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            st.setString(1,makh);
            java.sql.ResultSet rs = st.executeQuery();
            if(rs.next()){
                JDBC.closeConnection();
                return true;
            }
            
        }catch(Exception ex){
            
        }
        JDBC.closeConnection();
        return false;
    }
    public boolean insert(DTO.KhachHang_DTO dto){
        String query = "insert into khachhang(makh,hoten,ngaysinh,diachi,sodt,gioitinh) values(?,?,?,?,?,?)";
        JDBC.openConnection();
        int n=0;
        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            st.setString(1,dto.getMaKH());
            st.setString(2,dto.getName());
            st.setString(3,dto.getBirth());
            st.setString(4,dto.getDiachi());
            st.setString(5,dto.getSdt());
            st.setString(6,dto.getGender());
            n= st.executeUpdate();
        }catch(Exception ex){

        }
        JDBC.closeConnection();
        if(n>0){
            return true;
        }
        return false;
    }
    public boolean update(DTO.KhachHang_DTO dto){
        String query = "Update khachhang set hoten = ? ,ngaysinh = ? ,diachi = ? ,sodt = ? ,gioitinh = ? where makh = ?";
        JDBC.openConnection();
        int n =0;
        try{
            PreparedStatement st = JDBC.getCon().prepareStatement(query);
            st.setString(1,dto.getName());
            st.setString(2,dto.getBirth());
            st.setString(3,dto.getDiachi());
            st.setString(4,dto.getSdt());
            st.setString(5,dto.getGender());
            st.setString(6,dto.getMaKH());
            n = st.executeUpdate();
        }catch(Exception ex){

        }
        JDBC.closeConnection();
        if (n>0){
            return true;
        }
        return false;

    }
    public boolean remove(String makh){
        String query = "Delete from khachhang where makh = ?";
        JDBC.openConnection();
        int n =0;
        try{
           PreparedStatement st = JDBC.getCon().prepareStatement(query);
           st.setString(1,makh);
           n = st.executeUpdate();
        }catch(Exception ex){

        }
        JDBC.closeConnection();
        if(n>0){
            return true;
        }
        return false;
    }
}
