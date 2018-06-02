package com.dashradar.dashradarbackend.repository;

import com.dashradar.dashradarbackend.domain.TransactionInput;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionInputRepository extends Neo4jRepository<TransactionInput, Long> {

    @Query(
            "MATCH (tx:Transaction {txid:{0}}) "+
            "CREATE (tx)<-[:INPUT]-(in:TransactionInput) "+
            "SET in = {sequence:{1}, coinbase:{1}};"
    )
    void createCoinbaseInput(String txid, long sequence, String coinbase);
    
    @Query(
            "MATCH (tx:Transaction {txid:{0}}) "+
            "CREATE (tx)<-[:INPUT]-(input:TransactionInput) "+
            "SET input = {sequence:{1}, coinbase:{1}} "+
            "WITH input " +        
            "MATCH (:Transaction {txid:{2}})-[:OUTPUT]-(spentOutput:TransactionOutput {n:{3}})" +
            "CREATE (spentOutput)-[:SPENT_IN]->(input)"
    )
    void createTransactionInput(String txid, long sequence, String outputTxid, int outputN);
    
}
