package com.dashradar.dashradarbackend.service;

public interface BalanceEventService {
    
    public void createBalances(long blockheight);
    
    void createBalances(String txid);
    
    void handleUnorphanedBlock(String blockhash);
    
    public void setLastBlockContainingBalanceEvent(long height);
     
    public Long lastBlockContainingBalanceEvent();
    
    public void handleOrphanedBlocks();
}
