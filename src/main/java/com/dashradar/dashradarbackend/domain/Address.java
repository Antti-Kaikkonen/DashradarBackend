package com.dashradar.dashradarbackend.domain;

import java.util.ArrayList;
import java.util.List;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Address {

    private String address;

    private Long id;

    @Relationship(type = "CURRENT_BALANCE", direction = Relationship.OUTGOING)
    private BalanceEvent currentBalance;
    
    @Relationship(type = "INCLUDED_IN", direction = Relationship.INCOMING)
    private List<BalanceEvent> balanceEvents = new ArrayList<>();
    
    @Relationship(type = "ADDRESS", direction = Relationship.INCOMING)
    private List<TransactionOutput> transactionOutputs = new ArrayList<>();
    
    @Relationship(type = "INCLUDED_IN", direction = Relationship.OUTGOING)
    private MultiInputHeuristicCluster multiInputHeuristicCluster;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MultiInputHeuristicCluster getMultiInputHeuristicCluster() {
        return multiInputHeuristicCluster;
    }

    public void setMultiInputHeuristicCluster(MultiInputHeuristicCluster multiInputHeuristicCluster) {
        this.multiInputHeuristicCluster = multiInputHeuristicCluster;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BalanceEvent getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BalanceEvent currentBalance) {
        this.currentBalance = currentBalance;
    }

    public List<BalanceEvent> getBalanceEvents() {
        return balanceEvents;
    }

    public void setBalanceEvents(List<BalanceEvent> balanceEvents) {
        this.balanceEvents = balanceEvents;
    }

    public List<TransactionOutput> getTransactionOutputs() {
        return transactionOutputs;
    }

    public void setTransactionOutputs(List<TransactionOutput> transactionOutputs) {
        this.transactionOutputs = transactionOutputs;
    }

}
