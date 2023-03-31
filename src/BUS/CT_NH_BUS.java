package BUS;

import DAO.CT_HD_DAO;
import DAO.CT_NH_DAO;
import DTO.CT_HD_Product_DTO;
import DTO.CT_HD_ShowTable_DTO;

import java.util.ArrayList;

public class CT_NH_BUS {
    private static CT_NH_DAO daoCTHD = new CT_NH_DAO();
    public ArrayList<CT_HD_ShowTable_DTO> getCT_HD_ByOrderID(String orderID) {
        return daoCTHD.getCT_Nhap_ByOrderID(orderID);
    }
    public ArrayList<CT_HD_Product_DTO> getProductInfo() {
        return daoCTHD.getProductInfo();
    }
    public String getCusPhoneAddress(String customerId) {
        return daoCTHD.getCusPhoneAddress(customerId);
    }
    public int updateSLByProOrderId(String proId, String OrderId, int sl) {
        return daoCTHD.updateSLByProOrderId(proId, OrderId, sl);
    }
    public int addNewCT_HD(String proId, String OrderId, int sl) {
        return daoCTHD.addNewCT_Nhap(proId, OrderId, sl);
    }
    public int updateProductStock(String proId, int stock) {
        return daoCTHD.updateProductStock(proId, stock);
    }
    public int deleteProduct(String OrderId, String proId) {
        return daoCTHD.deleteProduct(OrderId, proId);
    }
    public int deleteAllOrderId(String OrderId) {
        return daoCTHD.deleteAllOrderId(OrderId);
    }
}
