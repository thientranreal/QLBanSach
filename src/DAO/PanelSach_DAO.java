package DAO;
import DTO.PanelSach_DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import DAO.JDBC;


public class PanelSach_DAO {
		public static List<PanelSach_DTO>FindALL(){
			List<PanelSach_DTO>SachList= new ArrayList<>();
			String query="select*from Sach";
			 JDBC.openConnection();
			try {
				Statement stmt = JDBC.getCon().createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()) {
					PanelSach_DTO st = new PanelSach_DTO(rs.getString("masach"),rs.getString("tensach"),rs.getString("tacgia"),rs.getString("NXB"),rs.getInt("gia"),rs.getInt("gianhap"),rs.getString("theloai"),rs.getInt("sl"));
					SachList.add(st);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			  JDBC.closeConnection();
			return SachList;
			}
		public static void insert(PanelSach_DTO st) {
			String query= "insert into Sach(tensach,tacgia,NXB,gia,gianhap,theloai,sl) values(?,?,?,?,?,?,?)";
			JDBC.openConnection();
			try {
				PreparedStatement pstmt = JDBC.getCon().prepareStatement(query);
					pstmt.setString(1, st.getTenSach());
					pstmt.setString(2,  st.getTacGia());
					pstmt.setString(3, st.getNXB());
					pstmt.setInt(4, st.getGia());
					pstmt.setInt(5, st.getGiaNhap());
					pstmt.setString(6, st.getTheLoai());
					pstmt.setInt(7, st.getSl());
					pstmt.executeUpdate();
		
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			  JDBC.closeConnection();
		}
			public static void delete(PanelSach_DTO st) {
				String query = "delete from Sach where tensach= ' "+st.getTenSach()+" ' ";
				JDBC.openConnection();
				try {
					
					PreparedStatement pstmt = JDBC.getCon().prepareStatement(query);
					pstmt.executeUpdate();
				} catch (Exception e) {
					// TODO: handle exception
				}
				JDBC.closeConnection();
			}
			
			public static void Update(PanelSach_DTO st) {
				String query= "Update Sach set tensach=?,tacgia=?,NXB=?,gia=?,gianhap=?,theloai=?,sl=? where tensach = ' "+st.getTenSach() +" ' ";
				JDBC.openConnection();
				try {
					
					PreparedStatement pstmt = JDBC.getCon().prepareStatement(query);
					pstmt.setString(1, st.getTenSach());
					pstmt.setString(2,  st.getTacGia());
					pstmt.setString(3, st.getNXB());
					pstmt.setInt(4, st.getGia());
					pstmt.setInt(5, st.getGiaNhap());
					pstmt.setString(6, st.getTheLoai());
					pstmt.setInt(7, st.getSl());
					pstmt.executeUpdate();
				} catch (Exception e) {
					// TODO: handle exception
				}
				JDBC.closeConnection();
				
			}	
				public static List<PanelSach_DTO>findByname(PanelSach_DTO s) {
					
					List<PanelSach_DTO>Sachl= new ArrayList<>();
					JDBC.openConnection();
					String query="select from Sach where name=' "+s.getTenSach()+" ' ";
					try {
						
						Statement stmt = JDBC.getCon().createStatement();
						ResultSet rs = stmt.executeQuery(query);
						while(rs.next()) {
							PanelSach_DTO st = new PanelSach_DTO(rs.getString("masach"),rs.getString("tensach"),rs.getString("tacgia"),rs.getString("NXB"),rs.getInt("gia"),rs.getInt("gianhap"),rs.getString("theloai"),rs.getInt("sl"));
							Sachl.add(st);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
					JDBC.closeConnection();
					return Sachl ;
				}
}