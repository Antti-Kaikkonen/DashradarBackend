package com.dashradar.dashradarbackend.domain;

import java.util.ArrayList;
import java.util.List;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Day {

    private long day;

    @GraphId
    private Long id;

    @Relationship(type = "LAST_BLOCK", direction = Relationship.OUTGOING)
    private Block lastBlock;
    
    @Relationship(type = "DAILY_PERCENTILES", direction = Relationship.OUTGOING)
    private List<DailyPercentiles> dailyPercentiles = new ArrayList<>();

    public long getDay() {
        return day;
    }

    public void setDay(long day) {
        this.day = day;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Block getLastBlock() {
        return lastBlock;
    }

    public void setLastBlock(Block lastBlock) {
        this.lastBlock = lastBlock;
    }

    public List<DailyPercentiles> getDailyPercentiles() {
        return dailyPercentiles;
    }

    public void setDailyPercentiles(List<DailyPercentiles> dailyPercentiles) {
        this.dailyPercentiles = dailyPercentiles;
    }

}
