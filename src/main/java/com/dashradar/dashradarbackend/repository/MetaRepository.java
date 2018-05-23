package com.dashradar.dashradarbackend.repository;

import com.dashradar.dashradarbackend.domain.Meta;
import java.util.List;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaRepository extends Neo4jRepository<Meta, Long> {

    
    @Query(
            "MERGE (m:Meta) SET m.lastBlockHeightWithBalanceEvent = {0};"
    )
    public void setLastBlockHeightWithBalanceEvent(long lastBlockHeightWithBalanceEvent);
    
    @Query(
            "MATCH (m:Meta) RETURN m.lastBlockHeightWithBalanceEvent;"
    )
    public List<Long> lastBlockContainingBalanceEvent();
}
