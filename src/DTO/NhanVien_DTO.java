package DTO;

public class NhanVien_DTO {
    private String manv;
    private String name;
    private String birth;
    private String diachi;
    private String sdt;
    private String gender;
    private String username;
    private String password;
    private String maquyen;
    private String tenquyen;
    public NhanVien_DTO(String manv,String name,String birth,String diachi,String sdt ,String gender,String username,String password,String maquyen,String tenquyen){
        this.manv = manv;
        this.name = name;
        this.birth = birth;
        this.diachi = diachi;
        this.sdt = sdt ;
        this.gender = gender;
        this.username = username;
        this.password = password;
        this.maquyen = maquyen;
        this.tenquyen = tenquyen;
    }
    public String getMaNV(){
        return manv;
    }
    public String getName(){
        return name;
    }
    public String getBirth(){
        return birth;
    }
    public String getDiachi(){
        return diachi;
    }
    public String getSdt(){
        return sdt;
    }
    public String getGender(){
        return gender;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getMaquyen(){
        return maquyen;
    }
    public String getTenQuyen(){
        return tenquyen;
    }
    public String toString(){
        return manv + name + birth + diachi+sdt+gender+username+password+maquyen+tenquyen;
    }
}
