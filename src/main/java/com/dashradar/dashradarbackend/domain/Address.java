package com.dashradar.dashradarbackend.domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Address {

    private String address;

    private Long id;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
