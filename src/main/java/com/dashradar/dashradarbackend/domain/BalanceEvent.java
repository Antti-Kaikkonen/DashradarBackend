package com.dashradar.dashradarbackend.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class BalanceEvent {
    
    @Relationship(type = "INCLUDED_IN", direction = Relationship.OUTGOING)
    private Address address;
    
    private long balanceChangeSat;//delta in satoshis
    
    private long balanceAfterSat;

    @GraphId
    private Long id;

    @Relationship(type = "CREATES", direction = Relationship.INCOMING)
    private Transaction transaction;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public long getBalanceChangeSat() {
        return balanceChangeSat;
    }

    public void setBalanceChangeSat(long balanceChangeSat) {
        this.balanceChangeSat = balanceChangeSat;
    }

    public long getBalanceAfterSat() {
        return balanceAfterSat;
    }

    public void setBalanceAfterSat(long balanceAfterSat) {
        this.balanceAfterSat = balanceAfterSat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
    
}
