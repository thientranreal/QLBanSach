package DAO;

import DTO.HD_DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhapHang_DAO {
    public ArrayList<HD_DTO> getAllOrders() {
        JDBC.openConnection();
        ArrayList<HD_DTO> result = new ArrayList<>();

        try {
            Connection con = JDBC.getCon();
            String sql = "Select MaNH, KhachHang.MaKH CusID, NhanVien.MaNV EmID" +
                    ", NgayNhap, KhachHang.HoTen CusName, NhanVien.HoTen EmName " +
                    "From NhapHang " +
                    "Left join KhachHang ON KhachHang.MaKH = NhapHang.MaKH " +
                    "Left join NhanVien ON NhanVien.MaNV = NhapHang.MaNV";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new HD_DTO(rs.getString("MaNH"),
                        rs.getString("EmID"),
                        rs.getNString("EmName"),
                        rs.getString("CusID"),
                        rs.getNString("CusName"),
                        rs.getString("NgayNhap")));
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
            Connection con = JDBC.getCon();
            String sql = "Insert Into NhapHang Values (?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, OrderId);
            st.setString(2, cusId);
            st.setString(3, emId);
            st.setString(4, dateTime);

            updatedRows = st.executeUpdate();

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
            Connection con = JDBC.getCon();
            String sql = "Select MaNV, HoTen From NhanVien";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
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
            Connection con = JDBC.getCon();
            String sql = "Select MaKH, HoTen From KhachHang";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
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
            Connection con = JDBC.getCon();
            String sql = "Delete From NhapHang Where MaNH = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, OrderId);

            updatedRows = st.executeUpdate();

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
            Connection con = JDBC.getCon();
            String sql = "Update NhapHang Set MaKH = ?, MaNV = ?, NgayNhap = ? " +
                    "Where MaNH = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, CusId);
            st.setString(2, EmId);
            st.setString(3, orderDate);
            st.setString(4, OrderId);

            updatedRows = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return updatedRows;
        }

        JDBC.closeConnection();
        return updatedRows;
    }

    public int deleteOrderAndAllProduct(String OrderId) {
        JDBC.openConnection();
        int updatedRows = 0;

        try {
            Connection con = JDBC.getCon();
            String sql = "Delete From CT_Nhap Where MaNH = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, OrderId);
            st.executeUpdate();

            sql = "Delete From NhapHang Where MaNH = ?";
            st = con.prepareStatement(sql);
            st.setString(1, OrderId);

            updatedRows = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return updatedRows;
        }

        JDBC.closeConnection();
        return updatedRows;
    }
}
