package com.example.demo.model;

import javax.persistence.*;

/**
 * Created by cjl20 on 6/5/2017.
 */
@Entity
@Table(name = "UserGood")
public class UserGood {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long uid;
    private Long gid;

    public UserGood() {
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserGood{" +
                "id=" + id +
                ", uid=" + uid +
                ", gid=" + gid +
                '}';
    }
}
