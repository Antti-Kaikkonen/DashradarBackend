package com.dashradar.dashradarbackend.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Meta {
    
    @GraphId
    private Long id;
    
    long lastBlockHeightWithBalanceEvent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getLastBlockHeightWithBalanceEvent() {
        return lastBlockHeightWithBalanceEvent;
    }

    public void setLastBlockHeightWithBalanceEvent(long lastBlockHeightWithBalanceEvent) {
        this.lastBlockHeightWithBalanceEvent = lastBlockHeightWithBalanceEvent;
    }

}
