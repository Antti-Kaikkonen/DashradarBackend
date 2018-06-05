package com.dashradar.dashradarbackend.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Meta {
    
    @GraphId
    private Long id;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
