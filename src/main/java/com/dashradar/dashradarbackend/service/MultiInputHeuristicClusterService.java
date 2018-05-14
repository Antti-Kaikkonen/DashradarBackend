package com.dashradar.dashradarbackend.service;


public interface MultiInputHeuristicClusterService {
    
    public void clusteerizeBlock(long height);
    
    public void clusterizeTransaction(String txid);
    
}
