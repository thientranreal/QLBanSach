package BUS;

import DTO.NhanVien_DTO;

public class NhanVien_BUS {
    private static NhanVien_BUS instance;
    public static NhanVien_BUS getInstance(){
        if(instance==null){
            instance = new NhanVien_BUS();
        }
        return instance;
    }
    public NhanVien_DTO getNhanVienByUsernameAndPassword(String username,String password){

        return DAO.NhanVien_DAO.getInstance().getNhanVienByUsernameAndPassword(username,password);
    }
    public java.util.List<NhanVien_DTO> getAllNhanVien(){
        return DAO.NhanVien_DAO.getInstance().getAllNhanVien();
    }
    public DTO.NhanVien_DTO getNhanVienByID(String manv){
        return DAO.NhanVien_DAO.getInstance().getNhanVienByID(manv);
    }
    public java.util.List<NhanVien_DTO> getNhanVienLikeNameOrID(String like){
        return DAO.NhanVien_DAO.getInstance().getNhanVienLikeNameOrID(like);
    }
    public boolean isExists(String manv){
        return DAO.NhanVien_DAO.getInstance().isExists(manv);
    }
    public boolean isExistsUsername(String username){
        return DAO.NhanVien_DAO.getInstance().isExistsUsername(username);
    }
    public boolean insert(NhanVien_DTO dto){
        return DAO.NhanVien_DAO.getInstance().insert(dto);
    }
    public boolean update(NhanVien_DTO dto){
        return DAO.NhanVien_DAO.getInstance().update(dto);
    }
    public boolean remove(String manv){
        return DAO.NhanVien_DAO.getInstance().remove(manv);
    }
    public java.util.List<String> getListQuyen(){
        return DAO.NhanVien_DAO.getInstance().getListQuyen();
    }
    public String getMaQuyenByTenQuyen(String tenquyen){
        return DAO.NhanVien_DAO.getInstance().getMaQuyenByTenQuyen(tenquyen);
    }
}
