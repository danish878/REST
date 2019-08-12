package com.danny.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Passenger {

    private long id;
    private String name;

    public Passenger() {

    }

    public Passenger(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
