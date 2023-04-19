package BUS;

import DTO.KhachHang_DTO;

public class KhachHang_BUS {
    private static KhachHang_BUS instance;
    public static KhachHang_BUS getInstance(){
        if(instance==null){
            instance= new KhachHang_BUS();
        }
        return instance;
    }
    public java.util.List<KhachHang_DTO> getAllKhachHang(){
        return DAO.KhachHang_DAO.getInstance().getAllKhachHang();
    }
    public DTO.KhachHang_DTO getKhachHangByID(String makh){
        return DAO.KhachHang_DAO.getInstance().getKhachHangByID(makh);
    }
    public java.util.List<KhachHang_DTO> getKhachHangLikeNameOrID(String like){
        return DAO.KhachHang_DAO.getInstance().getKhachHangLikeNameOrID(like);
    }
    public boolean isExists(String makh){
        return DAO.KhachHang_DAO.getInstance().isExists(makh);
    }
    public boolean insert(KhachHang_DTO dto){
        return DAO.KhachHang_DAO.getInstance().insert(dto);
    }
    public boolean update(KhachHang_DTO dto){
        return DAO.KhachHang_DAO.getInstance().update(dto);
    }
    public boolean remove(String makh){
        return DAO.KhachHang_DAO.getInstance().remove(makh);
    }
}
