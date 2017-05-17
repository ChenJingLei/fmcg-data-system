package com.john.crawler.model;

import java.util.UUID;

/**
 * Created by cjl20 on 2017/5/14.
 */
public class ExecuteReturn {

    private UUID id = UUID.randomUUID();
    private String timestamp;
    private double cost;
    private String status;

    public ExecuteReturn() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ExecuteReturn{" +
                "id=" + id +
                ", timestamp='" + timestamp + '\'' +
                ", cost=" + cost +
                ", status='" + status + '\'' +
                '}';
    }
}
