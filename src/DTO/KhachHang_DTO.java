package DTO;

public class KhachHang_DTO {
    private String makh;
    private String name;
    private String birth;
    private String diachi;
    private String sdt ;
    private String gender;
    public KhachHang_DTO(String makh,String name,String birth,String diachi,String sdt ,String gender){
          this.makh = makh ;
          this.name = name;
          this.birth = birth;
          this.diachi = diachi;
          this.sdt = sdt ;
          this.gender = gender;
    }
    public String getMaKH(){
        return makh;
    }
    public String getName(){
        return name;
    }

    public java.lang.String getGender() {
        return gender;
    }

    public java.lang.String getSdt() {
        return sdt;
    }

    public java.lang.String getDiachi() {
        return diachi;
    }

    public String getBirth() {
        return birth;
    }

}
