package com.baldur.model.pojo;

import java.util.Date;

public class Order {
    private String origin;
    private String destination;
    private Date time;

    public Order(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
        this.time = new Date();
    }

    public Order(String origin, String destination, Date time) {
        this.origin = origin;
        this.destination = destination;
        this.time = time;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Date getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s", origin, destination, time);
    }
}
