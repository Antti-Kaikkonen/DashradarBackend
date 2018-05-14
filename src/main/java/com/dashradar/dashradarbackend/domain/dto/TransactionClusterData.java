package com.dashradar.dashradarbackend.domain.dto;

import java.util.Set;
import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class TransactionClusterData {
    public Long clusterId;
    public Long clusterSize;
    public Set<String> addresses;
}
