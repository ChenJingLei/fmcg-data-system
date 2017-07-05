package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cjl20 on 6/4/2017.
 */
@Entity
@Table(name = "Good")
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String wname;
    private String imageUrl;
    private String goodUrl;
    private double price;
    private int sales;
    private String allCnt;
    private int salesNum;
    private String type;
    private String brand;
    private String platform;

    public Good() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGoodUrl() {
        return goodUrl;
    }

    public void setGoodUrl(String goodUrl) {
        this.goodUrl = goodUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public String getAllCnt() {
        return allCnt;
    }

    public void setAllCnt(String allCnt) {
        this.allCnt = allCnt;
    }

    public int getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(int salesNum) {
        this.salesNum = salesNum;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Good{" +
                "Id=" + Id +
                ", wname='" + wname + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", goodUrl='" + goodUrl + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", allCnt='" + allCnt + '\'' +
                ", salesNum=" + salesNum +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", platform='" + platform + '\'' +
                '}';
    }
}
