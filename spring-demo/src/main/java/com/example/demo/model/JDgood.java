package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by cjl20 on 2017/5/15.
 */

@Entity
@Table(name = "jdgood")
public class JDgood implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String wareId = "";
    private String wname = "";
    private String totalCount = "";
    private String catid = "";
    private String cid1 = "";
    private String cid2 = "";
    private String good = "";
    private String imageurl = "";
    private String jdPrice = "";
    private String goodUrl = "";
    private String type = "";
    private String brand = "";
    private int salesRate = 0;
    private Date date = new Date();

    public JDgood() {
    }

    public String getWareId() {
        return wareId;
    }

    public void setWareId(String wareId) {
        this.wareId = wareId;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getCid1() {
        return cid1;
    }

    public void setCid1(String cid1) {
        this.cid1 = cid1;
    }

    public String getCid2() {
        return cid2;
    }

    public void setCid2(String cid2) {
        this.cid2 = cid2;
    }

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getJdPrice() {
        return jdPrice;
    }

    public void setJdPrice(String jdPrice) {
        this.jdPrice = jdPrice;
    }

    public String getGoodUrl() {
        return goodUrl;
    }

    public void setGoodUrl(String goodUrl) {
        this.goodUrl = goodUrl;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSalesRate() {
        return salesRate;
    }

    public void setSalesRate(int salesRate) {
        this.salesRate = salesRate;
    }

    @Override
    public String toString() {
        return "JDgood{" +
                "wareId='" + wareId + '\'' +
                ", wname='" + wname + '\'' +
                ", totalCount='" + totalCount + '\'' +
                ", catid='" + catid + '\'' +
                ", cid1='" + cid1 + '\'' +
                ", cid2='" + cid2 + '\'' +
                ", good='" + good + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", jdPrice='" + jdPrice + '\'' +
                ", goodUrl='" + goodUrl + '\'' +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
