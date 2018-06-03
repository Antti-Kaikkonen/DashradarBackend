package com.dashradar.dashradarbackend.repository;

import com.dashradar.dashradarbackend.domain.Day;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayRepository extends Neo4jRepository<Day, Long> {
    
    @Query(
        "MATCH (d:Day) RETURN d.day ORDER BY d.day LIMIT 1;"
    )
    Long lastDay();
    
    
    @Query(
        "MATCH (b:Block {hash:{0}}) CREATE (b)-[:LAST_BLOCK]->(d:Day) SET d.day = b.time/86400;"
    )
    void setLastBlockOfDay(String blockhash);
}
