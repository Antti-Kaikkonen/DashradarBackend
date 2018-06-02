package com.dashradar.dashradarbackend.service;

public interface BalanceEventService {
    
    public void createBalances(long blockheight);
    
    void createBalances(String txid);
    
    public void setLastBlockContainingBalanceEvent(long height);
     
    public Long lastBlockContainingBalanceEvent();
}
