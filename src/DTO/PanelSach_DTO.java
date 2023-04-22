package DTO;

public class PanelSach_DTO {
	private String masach;
    private String tensach;
    private String tacgia;
    private String NXB;
    private int gia;
    private int gianhap;
    private String theloai;
    private int sl;
    public PanelSach_DTO(String masach,String tensach,String tacgia,String NXB,int gia,int gianhap,String theloai,int sl) {
        this.masach = masach;
        this.tensach = tensach;
        this.tacgia =tacgia;
        this.NXB = NXB;
        this.gia = gia ;
        this.gianhap = gianhap;
        this.theloai = theloai;
        this.sl =sl;
    }
    
 public void setMaSach(String masach) {
   	 this.masach=masach;
    }
 public void setTenSach(String tensach) {
	 this.tensach=tensach;
 }
  public void setTacGia(String tacgia) {
	  this.tacgia =tacgia;
  }
  public void setNXB(String NXB) {
	  this.NXB = NXB;
  }
  public void setGia(int gia) {
	  this.gia = gia ;
  }
  public void setGiaNhap(int gianhap) {
	  this.gianhap = gianhap;
  }
  public void setTheLoai(String theloai) {
	  this.theloai = theloai;
  }
  public void setSL(int sl) {
	  this.sl =sl;
  }
  
  public String getMaSach() {
	  return masach;
  }
  public String getTenSach() {
	  return tensach;
  }
  public String getTacGia() {
	  return tacgia;
  }
  public String getNXB() {
	  return NXB;
  }
  public int getGia() {
	  return gia;
  }
  public int getGiaNhap() {
	  return gianhap;
  }
  public String getTheLoai() {
	  return theloai;
  }
  public int getSl() {
	  return sl;
  }
}