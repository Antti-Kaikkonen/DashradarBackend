package com.dashradar.dashradarbackend.domain;

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

}
