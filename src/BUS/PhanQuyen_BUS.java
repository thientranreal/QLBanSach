package BUS;

import DAO.NhanVien_DAO;

public class PhanQuyen_BUS {
    private static PhanQuyen_BUS instance;
    public static PhanQuyen_BUS getInstance(){
        if (instance==null){
            instance = new PhanQuyen_BUS();
        }
        return instance;
    }
    public java.util.List<String> getChucNang(String maquyen){
        return DAO.PhanQuyen_DAO.getInstance().getChucNang(maquyen);
    }
    public java.util.List<String> getTenChucNang(String maquyen){
        return DAO.PhanQuyen_DAO.getInstance().getTenChucNang(maquyen);
    }
    public java.util.List<String> getAllTenChucNang(){
        return DAO.PhanQuyen_DAO.getInstance().getAllTenChucNang();
    }
    public java.util.List<DTO.Quyen_DTO> getAllQuyen(){
         return DAO.PhanQuyen_DAO.getInstance().getAllQuyen();
    }
    public java.util.List<String> getLeftChucNang(String maquyen)
    {
         return DAO.PhanQuyen_DAO.getInstance().getLeftChucNang(maquyen);
    }
    public boolean isExists(String maquyen){
        return DAO.PhanQuyen_DAO.getInstance().isExists(maquyen);
    }
    public boolean isExistsTenQuyen(String tenquyen){
        return DAO.PhanQuyen_DAO.getInstance().isExistsTenQuyen(tenquyen);

    }
    public boolean isExistsTenQuyenExceptMe(String maquyen,String tenquyen){
        return DAO.PhanQuyen_DAO.getInstance().isExistsTenQuyenExceptMe(maquyen,tenquyen);
    }
    public boolean removeQuyen(String maquyen){
        return DAO.PhanQuyen_DAO.getInstance().removeQuyen(maquyen);
    }
    public boolean insert(String maquyen,String tenquyen){
        return DAO.PhanQuyen_DAO.getInstance().insert(maquyen,tenquyen);
    }
    public boolean isExistsTenChucNangInQuyen(String maquyen,String tenchucnang){
        return DAO.PhanQuyen_DAO.getInstance().isExistsTenChucNangInQuyen(maquyen,tenchucnang);
    }
    public boolean insertChucNangToQuyen(String maquyen,String tenchucnang){
        return DAO.PhanQuyen_DAO.getInstance().insertChucNangToQuyen(maquyen,tenchucnang);

    }
    public boolean removeTenChucNangInQuyen(String maquyen,String tenchucnang){
        return DAO.PhanQuyen_DAO.getInstance().removeTenChucNangInQuyen(maquyen,tenchucnang);
    }
    public boolean updateQuyen(String maquyen,String tenquyen){
        return DAO.PhanQuyen_DAO.getInstance().updateQuyen(maquyen,tenquyen);
    }
}
