package DTO;

public class Quyen_DTO {
    String maquyen;
    String tenquyen;
    public Quyen_DTO(String maquyen,String tenquyen){
        this.maquyen = maquyen;
        this.tenquyen = tenquyen;
    }
    public String getMaquyen(){
        return maquyen;
    }
    public String getTenquyen(){
        return tenquyen;
    }
}
