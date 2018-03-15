package com.dashradar.dashradarbackend.repository;

import com.dashradar.dashradarbackend.domain.TransactionOutput;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionOutputRepository extends Neo4jRepository<TransactionOutput, Long> {

    TransactionOutput findByTransactionTxidAndN(String txid, int n, @Depth int depth);

    @Query("MATCH (tr:Transaction)-[:OUTPUT]->(to:TransactionOutput) WHERE (tr.txid = {0} AND to.n = {1}) RETURN to")
    TransactionOutput findByTransactionTxidAndN2(String txid, int n);

}
