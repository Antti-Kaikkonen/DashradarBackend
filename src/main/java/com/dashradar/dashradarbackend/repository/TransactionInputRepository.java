package com.dashradar.dashradarbackend.repository;

import com.dashradar.dashradarbackend.domain.TransactionInput;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionInputRepository extends Neo4jRepository<TransactionInput, Long> {

}
