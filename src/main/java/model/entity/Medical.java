package model.entity;

import java.sql.Date;

public class Medical {
    private int thuocId;
    private String tenThuoc;
    private String nhomThuoc;
    private int soLuong;
    private String nhaSx;
    private Date hsd;
    private int price;

    public Medical(int thuocId, String tenThuoc, String nhomThuoc, int soLuong, String nhaSx, Date hsd, int price) {
        this.thuocId = thuocId;
        this.tenThuoc = tenThuoc;
        this.nhomThuoc = nhomThuoc;
        this.soLuong = soLuong;
        this.nhaSx = nhaSx;
        this.hsd = hsd;
        this.price = price;
    }




    public int getThuocId() {
        return thuocId;
    }

    public void setThuocId(int thuocId) {
        this.thuocId = thuocId;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public String getNhomThuoc() {
        return nhomThuoc;
    }

    public void setNhomThuoc(String nhomThuoc) {
        this.nhomThuoc = nhomThuoc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getNhaSx() {
        return nhaSx;
    }

    public void setNhaSx(String nhaSx) {
        this.nhaSx = nhaSx;
    }

    public Date getHsd() {
        return hsd;
    }

    public void setHsd(Date hsd) {
        this.hsd = hsd;
    }

    public int getPrice() {
        return price;
    }
    //setter
    public void setPrice(int price) {
        this.price = price;
    }
}