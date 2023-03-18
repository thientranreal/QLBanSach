package DAO;

import DTO.CT_HD_Product_DTO;
import DTO.CT_HD_ShowTable_DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CT_HD_DAO {
    public ArrayList<CT_HD_ShowTable_DTO> getCT_HD_ByOrderID(String orderID) {
        JDBC.openConnection();
        ArrayList<CT_HD_ShowTable_DTO> list = new ArrayList<>();

        try {
            Connection con = JDBC.getCon();
            String sql = "Select HoaDon.MaHD, KhachHang.MaKH, KhachHang.HoTen CustomerName, "+
                    "Sach.MaSach, Sach.TenSach ProductName, SL_Mua, Gia" +
                    " from CT_HD, HoaDon, KhachHang, Sach " +
                    "Where CT_HD.MaHD = HoaDon.MaHD " +
                    "and HoaDon.MaKH = KhachHang.MaKH " +
                    "and CT_HD.MaSach = Sach.MaSach " +
                    "and HoaDon.MaHD = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, orderID);
            ResultSet rs = st.executeQuery();

            // add each row to arrayList
            CT_HD_ShowTable_DTO ct;
            while (rs.next()) {
                ct = new CT_HD_ShowTable_DTO();
                ct.setOrderID(rs.getString("MaHD"));
                ct.setCustomerID(rs.getString("MaKH"));
                ct.setCustomerName(rs.getNString("CustomerName"));
                ct.setProductID(rs.getString("MaSach"));
                ct.setProductName(rs.getNString("ProductName"));
                ct.setQuantity(rs.getInt("SL_Mua"));
                ct.setPrice(rs.getFloat("Gia"));
                list.add(ct);
            }
        } catch (SQLException ex) {
            return null;
        }

        JDBC.closeConnection();
        return list;
    }

    public ArrayList<CT_HD_Product_DTO> getProductInfo() {
        JDBC.openConnection();
        ArrayList<CT_HD_Product_DTO> result = new ArrayList<>();

        try {
            Connection con = JDBC.getCon();
            String sql = "Select MaSach, TenSach, TheLoai, SL from Sach";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new CT_HD_Product_DTO(rs.getString("MaSach"),
                        rs.getNString("TenSach"),
                        rs.getNString("TheLoai"),
                        rs.getInt("SL")));
            }
        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return null;
        }

        JDBC.closeConnection();
        return result;
    }

    // get data to display on TTChung
    public String getCusPhoneAddress(String customerId) {
        JDBC.openConnection();
        String result = "";
        try {
            Connection con = JDBC.getCon();
            String sql = "Select DiaChi, SoDT " +
                    "from KhachHang " +
                    "where MaKH = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, customerId);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result += rs.getString("DiaChi") + ":" + rs.getString("SoDT");
            }
        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return result;
        }

        JDBC.closeConnection();
        return result;
    }

    public int updateSLByProOrderId(String proId, String OrderId, int sl) {
        JDBC.openConnection();
        int updatedRows = 0;

        try {
            Connection con = JDBC.getCon();
            String sql = "Update CT_HD Set SL_Mua = ? Where MaHD = ? and MaSach = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, sl);
            st.setString(2, OrderId);
            st.setString(3, proId);
            updatedRows = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return updatedRows;
        }

        JDBC.closeConnection();
        return updatedRows;
    }

    public int addNewCT_HD(String proId, String OrderId, int sl) {
        JDBC.openConnection();
        int updatedRows = 0;

        try {
            Connection con = JDBC.getCon();
            String sql = "Insert Into CT_HD Values (?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, OrderId);
            st.setString(2, proId);
            st.setInt(3, sl);

            updatedRows = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return updatedRows;
        }

        JDBC.closeConnection();
        return updatedRows;
    }
    public int updateProductStock(String proId, int stock) {
        JDBC.openConnection();
        int updatedRows = 0;

        try {
            Connection con = JDBC.getCon();
            String sql = "Update Sach Set SL = ? Where MaSach = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, stock);
            st.setString(2, proId);
            updatedRows = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return updatedRows;
        }

        JDBC.closeConnection();
        return updatedRows;
    }
    public int deleteProduct(String OrderId, String proId) {
        JDBC.openConnection();
        int updatedRows = 0;

        try {
            Connection con = JDBC.getCon();
            String sql = "Delete From CT_HD Where MaHD = ? and MaSach = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, OrderId);
            st.setString(2, proId);

            updatedRows = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return updatedRows;
        }

        JDBC.closeConnection();
        return updatedRows;
    }

    public int deleteAllOrderId(String OrderId) {
        JDBC.openConnection();
        int updatedRows = 0;

        try {
            Connection con = JDBC.getCon();
            String sql = "Delete From CT_HD Where MaHD = ?";
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
}
