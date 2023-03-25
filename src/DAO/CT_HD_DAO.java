package DAO;

import DTO.CT_HD_Product_DTO;
import DTO.CT_HD_ShowTable_DTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CT_HD_DAO {
    public ArrayList<CT_HD_ShowTable_DTO> getCT_HD_ByOrderID(String orderID) {
        JDBC.openConnection();
        ArrayList<CT_HD_ShowTable_DTO> list = new ArrayList<>();

        try {
            
            String sql = "Select HoaDon.MaHD, KhachHang.MaKH, KhachHang.HoTen CustomerName, "+
                    "Sach.MaSach, Sach.TenSach ProductName, SL_Mua, Gia" +
                    " from CT_HD, HoaDon, KhachHang, Sach " +
                    "Where CT_HD.MaHD = HoaDon.MaHD " +
                    "and HoaDon.MaKH = KhachHang.MaKH " +
                    "and CT_HD.MaSach = Sach.MaSach " +
                    "and HoaDon.MaHD = ?";
            ResultSet rs = JDBC.executeQuery(sql,new Object[]{orderID});

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
            String sql = "Select MaSach, TenSach, TheLoai, SL from Sach";
            ResultSet rs = JDBC.executeQuery(sql,new Object[]{});
            while (rs.next()){
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
            String sql = "Select DiaChi, SoDT " +
                    "from KhachHang " +
                    "where MaKH = ?";

            ResultSet rs = JDBC.executeQuery(sql,new Object[]{customerId});
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
            
            String sql = "Update CT_HD Set SL_Mua = ? Where MaHD = ? and MaSach = ?";
            updatedRows = JDBC.executeNonQuery(sql,new Object[]{sl,OrderId,proId});

        } catch (Exception e) {
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
            String sql = "Insert Into CT_HD Values (?, ?, ?)";
            
            updatedRows =JDBC.executeNonQuery(sql,new Object[]{OrderId,proId,sl});

        } catch (Exception e) {
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
            String sql = "Update Sach Set SL = ? Where MaSach = ?";
            updatedRows = JDBC.executeNonQuery(sql,new Object[]{stock,proId});

        } catch (Exception e) {
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
            String sql = "Delete From CT_HD Where MaHD = ? and MaSach = ?";
            updatedRows =JDBC.executeNonQuery(sql,new Object[]{OrderId,proId});

        } catch (Exception e) {
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
            String sql = "Delete From CT_HD Where MaHD = ?";
            updatedRows = JDBC.executeNonQuery(sql,new Object[]{OrderId});

        } catch (Exception e) {
            System.out.println("Không lấy được dữ liệu");
            return updatedRows;
        }

        JDBC.closeConnection();
        return updatedRows;
    }
}
