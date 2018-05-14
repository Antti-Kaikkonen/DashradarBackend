package com.dashradar.dashradarbackend.domain;

import java.util.ArrayList;
import java.util.List;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class MultiInputHeuristicCluster {
    
    @GraphId
    private Long id;
    
    private long clusterSize;
    
    @Relationship(type = "INCLUDED_IN", direction = Relationship.INCOMING)
    private List<Address> addresses = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public long getClusterSize() {
        return clusterSize;
    }

    public void setClusterSize(long clusterSize) {
        this.clusterSize = clusterSize;
    }
    
}
