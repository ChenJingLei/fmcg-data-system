package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by cjl20 on 6/8/2017.
 */
@Entity
public class TaoBaoGood {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private double price;
    private Long monthSales;
    private Long totalCount;
    private Long restCount;
    private String imageurl;
    private String goodUrl;
    private String type;
    private String brand;
    private Date time = new Date();

    public TaoBaoGood() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getMonthSales() {
        return monthSales;
    }

    public void setMonthSales(Long monthSales) {
        this.monthSales = monthSales;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getRestCount() {
        return restCount;
    }

    public void setRestCount(Long restCount) {
        this.restCount = restCount;
    }

    @Override
    public String toString() {
        return "TaoBaoGood{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", monthSales=" + monthSales +
                ", totalCount=" + totalCount +
                ", restCount=" + restCount +
                ", imageurl='" + imageurl + '\'' +
                ", goodUrl='" + goodUrl + '\'' +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", time=" + time +
                '}';
    }
}
