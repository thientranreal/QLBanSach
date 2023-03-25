package DAO;

import DTO.HD_DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HD_DAO {
    public ArrayList<HD_DTO> getAllOrders() {
        JDBC.openConnection();
        ArrayList<HD_DTO> result = new ArrayList<>();

        try {
            String sql = "Select MaHD, KhachHang.MaKH CusID, NhanVien.MaNV EmID" +
                    ", NgayXuat, KhachHang.HoTen CusName, NhanVien.HoTen EmName " +
                    "From HoaDon, KhachHang, NhanVien " +
                    "Where KhachHang.MaKH = HoaDon.MaKH and NhanVien.MaNV = HoaDon.MaNV";
            ResultSet rs = JDBC.executeQuery(sql);
            while (rs.next()) {
                result.add(new HD_DTO(rs.getString("MaHD"),
                        rs.getString("EmID"),
                        rs.getNString("EmName"),
                        rs.getString("CusID"),
                        rs.getNString("CusName"),
                        rs.getString("NgayXuat")));
            }
        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return null;
        }

        JDBC.closeConnection();
        return result;
    }

    public int addNewHD(String OrderId, String emId, String cusId, String dateTime) {
        JDBC.openConnection();
        int updatedRows = 0;

        try {
            
            String sql = "Insert Into HoaDon Values (?, ?, ?, ?)";
            updatedRows = JDBC.executeNonQuery(sql,new object[]{OrderId,cusId,emId,dateTime});

        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return updatedRows;
        }

        JDBC.closeConnection();
        return updatedRows;
    }

    public ArrayList<String> getAllEm() {
        JDBC.openConnection();
        ArrayList<String> result = new ArrayList<>();

        try {
            String sql = "Select MaNV, HoTen From NhanVien";
            ResultSet rs = JDBC.executeQuery(sql);
            while (rs.next()) {
                result.add(rs.getString("MaNV") + ":" + rs.getNString("HoTen"));
            }
        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return null;
        }

        JDBC.closeConnection();
        return result;
    }
    public ArrayList<String> getAllCus() {
        JDBC.openConnection();
        ArrayList<String> result = new ArrayList<>();

        try {
   
            String sql = "Select MaKH, HoTen From KhachHang";
            ResultSet rs = JDBC.executeQuery(sql);
            while (rs.next()) {
                result.add(rs.getString("MaKH") + ":" + rs.getNString("HoTen"));
            }
        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return null;
        }

        JDBC.closeConnection();
        return result;
    }

    public int deleteOrder(String OrderId) {
        JDBC.openConnection();
        int updatedRows = 0;

        try {
            
            String sql = "Delete From HoaDon Where MaHD = ?";
            updatedRows = JDBC.executeNonQuery(sql,new object[]{OrderId});

        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return updatedRows;
        }

        JDBC.closeConnection();
        return updatedRows;
    }

    public int updateOrder(String OrderId, String CusId, String EmId, String orderDate) {
        JDBC.openConnection();
        int updatedRows = 0;

        try {
            String sql = "Update HoaDon Set MaKH = ?, MaNV = ?, NgayXuat = ? " +
                    "Where MaHD = ?";

            updatedRows = JDBC.executeNonQuery(sql,new object[]{CusId,EmId,orderDate,OrderId});

        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return updatedRows;
        }

        JDBC.closeConnection();
        return updatedRows;
    }

}
