package BUS;

import java.util.List;

import DAO.Sach_DAO;
import DTO.Sach_DTO;

public class Sach_BUS {
	private static Sach_BUS instance;

	public static Sach_BUS getInstance() {
		if (instance == null) {
			instance = new Sach_BUS();
		}
		return instance;
	}

	public List<Sach_DTO> findAll() {
		return Sach_DAO.FindALL();
	}
	public List<Sach_DTO> findByName(String tenS){
		return Sach_DAO.findByname(tenS);
	}

	public void add(Sach_DTO item) {
		Sach_DAO.insert(item);
	}

	public void update(Sach_DTO item) {
		Sach_DAO.Update(item);
	}

	public void delete(String maS) {
		Sach_DAO.delete(maS);
	}

	public boolean isExist(String maS) {
		return Sach_DAO.isExist(maS);
	}
}
