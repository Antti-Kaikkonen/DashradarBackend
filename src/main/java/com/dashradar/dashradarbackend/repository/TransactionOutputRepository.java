package com.dashradar.dashradarbackend.repository;

import com.dashradar.dashradarbackend.domain.TransactionOutput;
import java.util.List;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionOutputRepository extends Neo4jRepository<TransactionOutput, Long> {

    TransactionOutput findByTransactionTxidAndN(String txid, int n, @Depth int depth);

    @Query("MATCH (tr:Transaction)-[:OUTPUT]->(to:TransactionOutput) WHERE (tr.txid = {0} AND to.n = {1}) RETURN to")
    TransactionOutput findByTransactionTxidAndN2(String txid, int n);
    
    @Query(
        "MATCH (tx:Transaction {txid:{0}}) " +
        "CREATE (tx)-[:OUTPUT]->(out:TransactionOutput) SET out = {n:{1}, valueSat:{2}} " +
        "FOREACH (addrStr IN {3} | " +
        "   MERGE (address:Address {address:addrStr}) " +
        "   CREATE (out)-[:ADDRESS]->(address) " +
        ");"
    )
    void createTransactionOutput(String txid, int n, long valueSat, List<String> addresses);

}
