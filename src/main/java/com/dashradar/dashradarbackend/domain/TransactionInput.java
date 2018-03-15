package com.dashradar.dashradarbackend.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class TransactionInput {

    private String coinbase;

    @GraphId
    private Long id;

    private ScriptSignature scriptSig;

    private long sequence;

    @Relationship(type = "SPENT_IN", direction = Relationship.INCOMING)
    private TransactionOutput source;

    @Relationship(type = "INPUT", direction = Relationship.OUTGOING)
    private Transaction transaction;

    public String getCoinbase() {
        return coinbase;
    }

    public void setCoinbase(String coinbase) {
        this.coinbase = coinbase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ScriptSignature getScriptSig() {
        return scriptSig;
    }

    public void setScriptSig(ScriptSignature scriptSig) {
        this.scriptSig = scriptSig;
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public TransactionOutput getSource() {
        return source;
    }

    public void setSource(TransactionOutput source) {
        this.source = source;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

}
