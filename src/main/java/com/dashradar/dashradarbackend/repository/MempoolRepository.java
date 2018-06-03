package com.dashradar.dashradarbackend.repository;

import com.dashradar.dashradarbackend.domain.Mempool;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MempoolRepository extends Neo4jRepository<Mempool, Long> {
    
    
}
