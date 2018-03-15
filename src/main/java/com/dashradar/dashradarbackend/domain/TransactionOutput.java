package com.dashradar.dashradarbackend.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class TransactionOutput {

    @GraphId
    private Long id;

    private int n;

    private ScriptPublicKey scriptPubKey;

    @Relationship(type = "OUTPUT", direction = Relationship.INCOMING)
    private Transaction transaction;

    //private double value;
    private long valueSat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public ScriptPublicKey getScriptPubKey() {
        return scriptPubKey;
    }

    public void setScriptPubKey(ScriptPublicKey scriptPubKey) {
        this.scriptPubKey = scriptPubKey;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public double getValue() {
        return valueSat / 100000000;
    }

    public long getValueSat() {
        return valueSat;
    }

    public void setValueSat(long valueSat) {
        this.valueSat = valueSat;
    }

}
