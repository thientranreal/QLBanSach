package DAO;

import DTO.ThongKe_DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ThongKe_DAO {
    public ArrayList<String> getAllProduct() {
        JDBC.openConnection();
        ArrayList<String> result = new ArrayList<>();

        try {
            String sql = "Select MaSach, TenSach From Sach";
            ResultSet rs = JDBC.executeQuery(sql);
            while (rs.next()) {
                result.add(rs.getString("MaSach") + ":" + rs.getNString("TenSach"));
            }
        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return null;
        }

        JDBC.closeConnection();
        return result;
    }

//    Lay doanh thu theo ngay
    public ArrayList<ThongKe_DTO> thongKe(String fromDate, String toDate) {
        JDBC.openConnection();
        ArrayList<ThongKe_DTO> result = new ArrayList<>();

        try {
            String sql = "Select P.MaSach, TenSach, Gia, Sum(SL_Mua) AS SLMua, Sum(SL_Mua) * Gia AS Total\n" +
                    "From CT_HD OD, Sach P, HoaDon O\n" +
                    "Where OD.MaSach = P.MaSach And O.MaHD = OD.MaHD And Convert(Date, NgayXuat) BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)\n" +
                    "Group By P.MaSach, TenSach, Gia";
   
            ResultSet rs = JDBC.executeQuery(sql,new object[]{fromDate,toDate});
            while (rs.next()) {
                result.add(new ThongKe_DTO(rs.getString("MaSach"),
                        rs.getNString("TenSach"),
                        rs.getInt("SLMua"),
                        rs.getFloat("Gia"),
                        rs.getFloat("Total")));
            }
        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return null;
        }

        JDBC.closeConnection();
        return result;
    }

//      Lay doanh thu theo ngay va ma SP
    public ArrayList<ThongKe_DTO> thongKeDieuKien(String fromDate, String toDate, String proId) {
        JDBC.openConnection();
        ArrayList<ThongKe_DTO> result = new ArrayList<>();

        try {
            String sql = "Select P.MaSach, TenSach, Gia, Sum(SL_Mua) AS SLMua, Sum(SL_Mua) * Gia AS Total\n" +
                    "From CT_HD OD, Sach P, HoaDon O\n" +
                    "Where OD.MaSach = P.MaSach And O.MaHD = OD.MaHD And Convert(Date, NgayXuat) BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)\n" +
                    "And P.MaSach = ?\n"+
                    "Group By P.MaSach, TenSach, Gia";
     

            ResultSet rs = JDBC.executeQuery(sql,new object[]{fromDate,toDate,proId});
            while (rs.next()) {
                result.add(new ThongKe_DTO(rs.getString("MaSach"),
                        rs.getNString("TenSach"),
                        rs.getInt("SLMua"),
                        rs.getFloat("Gia"),
                        rs.getFloat("Total")));
            }
        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return null;
        }

        JDBC.closeConnection();
        return result;
    }
}
