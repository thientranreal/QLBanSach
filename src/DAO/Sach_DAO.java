package DAO;

import DTO.Sach_DTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Sach_DAO {
	public static List<Sach_DTO> FindALL() {
		List<Sach_DTO> SachList = new ArrayList<>();
		String query = "select * from Sach";
		JDBC.openConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = JDBC.getCon().createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Sach_DTO st = new Sach_DTO(rs.getString("MaSach"), rs.getString("TenSach"), rs.getString("TacGia"),
						rs.getString("NXB"), rs.getInt("Gia"), rs.getInt("GiaNhap"), rs.getString("TheLoai"),
						rs.getInt("SL"));
				SachList.add(st);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JDBC.closeConnection();
		}
		return SachList;
	}

	public static void insert(Sach_DTO st) {
		String query = "insert into Sach (MaSach, TenSach, TacGia, NXB, Gia, GiaNhap, TheLoai, SL) values(?,?,?,?,?,?,?,?)";
		JDBC.openConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = JDBC.getCon().prepareStatement(query);
			pstmt.setString(1, st.getMaSach());
			pstmt.setString(2, st.getTenSach());
			pstmt.setString(3, st.getTacGia());
			pstmt.setString(4, st.getNXB());
			pstmt.setInt(5, st.getGia());
			pstmt.setInt(6, st.getGiaNhap());
			pstmt.setString(7, st.getTheLoai());
			pstmt.setInt(8, st.getSl());
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBC.closeConnection();
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void delete(String maS) {
		String query = "delete from Sach where MaSach = ? ";
		JDBC.openConnection();
		try {

			PreparedStatement pstmt = JDBC.getCon().prepareStatement(query);
			pstmt.setString(1, maS);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		JDBC.closeConnection();
	}

	public static void Update(Sach_DTO st) {
		String query = "UPDATE Sach SET TenSach=?,TacGia=?,NXB=?,Gia=?,GiaNhap=?,TheLoai=?,SL=? WHERE MaSach = ?";
		JDBC.openConnection();
		try {
			PreparedStatement pstmt = JDBC.getCon().prepareStatement(query);
			pstmt.setString(1, st.getTenSach());
			pstmt.setString(2, st.getTacGia());
			pstmt.setString(3, st.getNXB());
			pstmt.setInt(4, st.getGia());
			pstmt.setInt(5, st.getGiaNhap());
			pstmt.setString(6, st.getTheLoai());
			pstmt.setInt(7, st.getSl());
			pstmt.setString(8, st.getMaSach());
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		JDBC.closeConnection();
	}

	public static List<Sach_DTO> findByname(String tenS) {

		List<Sach_DTO> Sachl = new ArrayList<>();
		JDBC.openConnection();
		String query = "SELECT * FROM Sach WHERE LOWER(TenSach) LIKE LOWER(?)";
		try {
			PreparedStatement pstmt = JDBC.getCon().prepareStatement(query);
			pstmt.setString(1, "%" + tenS + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Sach_DTO st = new Sach_DTO(rs.getString("MaSach"), rs.getString("TenSach"), rs.getString("TacGia"),
						rs.getString("NXB"), rs.getInt("Gia"), rs.getInt("GiaNhap"), rs.getString("TheLoai"),
						rs.getInt("SL"));
				Sachl.add(st);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		JDBC.closeConnection();
		return Sachl;
	}

	public static boolean isExist(String maS) {
		String query = "SELECT * FROM Sach WHERE MaSach=?";
		JDBC.openConnection();
		try {
			PreparedStatement pstmt = JDBC.getCon().prepareStatement(query);
			pstmt.setString(1, maS);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JDBC.closeConnection();
		}
		return false;
	}

}