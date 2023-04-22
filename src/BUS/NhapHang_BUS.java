package BUS;

import DAO.HD_DAO;
import DAO.NhapHang_DAO;
import DTO.HD_DTO;

import java.util.ArrayList;

public class NhapHang_BUS {
    private static NhapHang_DAO hdDao = new NhapHang_DAO();
    public ArrayList<HD_DTO> getAllOrders() {
        return hdDao.getAllOrders();
    }
    public int addNewHD(String OrderId, String emId, String cusId, String dateTime) {
        return hdDao.addNewHD(OrderId, emId, cusId, dateTime);
    }
    public ArrayList<String> getAllEm() {
        return hdDao.getAllEm();
    }
    public ArrayList<String> getAllCus() {
        return hdDao.getAllCus();
    }
    public int deleteOrder(String OrderId) {
        return hdDao.deleteOrder(OrderId);
    }
    public int updateOrder(String OrderId, String CusId, String EmId, String orderDate) {
        return hdDao.updateOrder(OrderId, CusId, EmId, orderDate);
    }
    public int deleteOrderAndAllProduct(String OrderId) {
        return hdDao.deleteOrderAndAllProduct(OrderId);
    }

}
