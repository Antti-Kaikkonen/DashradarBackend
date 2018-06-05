package com.dashradar.dashradarbackend.repository;

import com.dashradar.dashradarbackend.domain.Transaction;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends Neo4jRepository<Transaction, Long> {

    Transaction findByTxid(String txid, @Depth int depth);

    List<Transaction> findByPstype(int pstype, @Depth int depth, Pageable pageable);

    /*
    INCOMPLETE
     */
    @Query(
            "MATCH \n"
            + "	(spentOutput:TransactionOutput)-[:SPENT_IN]->(input:TransactionInput {pstype: -1})-[:INPUT]->(tx:Transaction {txid: -1})-[:OUTPUT]->(output:TransactionOutput)")
    Transaction findMixingTransaction();
    
    @Query(
            "MATCH (tx:Transaction)-[:INCLUDED_IN]->(:Mempool)\n"+
            "RETURN tx.txid;"        
    )
    List<String> getMempoolTxids();
    
    @Query("MATCH (mempool:Mempool) "+
           "CREATE (tx:Transaction)-[:INCLUDED_IN]->(mempool) "+
           "SET tx = {n: {0}, locktime: {1}, pstype: {2}, size: {3}, txid: {4}, version: {5}};")
    void createMempoolTransaction(int n, 
            long locktime, 
            int pstype, 
            long size, 
            String txid, 
            int version);
   
    @Query("MATCH (block:Block {hash:{6}}) "+
           "CREATE (tx:Transaction)-[:INCLUDED_IN]->(block) "+
           "SET tx = {n: {0}, locktime: {1}, pstype: {2}, size: {3}, txid: {4}, version: {5}};")
    void createBlockTransaction(int n, 
            long locktime, 
            int pstype, 
            long size, 
            String txid, 
            int version,
            String blockhash);
    
    
    @Query(
        "MATCH (spentOutput:TransactionOutput)-[:SPENT_IN]->(:TransactionInput)-[:INPUT]->(tx:Transaction {txid:{0}})\n" +
        "WITH tx, sum(spentOutput.valueSat) as inSats\n"+
        "MATCH (tx)-[:OUTPUT]->(output:TransactionOutput)\n"+
        "WITH tx, inSats-sum(output.valueSat) as tx_fee\n" +
        "SET tx.feesSat = tx_fee;"
    )
    void compute_tx_fee(String txid);
    
    @Query(
        "MATCH (b:Block {hash:{0}})<-[:INCLUDED_IN]-(tx1:Transaction {pstype:{1}})<-[:INPUT]-(i:TransactionInput)<-[:SPENT_IN]-(o:TransactionOutput)<-[:OUTPUT]-(tx2:Transaction {pstype:{1}})\n" +
        "WITH tx1,tx2, count(o) as rel_count\n" +
        "CREATE (tx1)-[:PREVIOUS_ROUND {connections:rel_count}]->(tx2);"
    )
    void create_previous_connections(String blockhash, int pstype);
    
    @Query("MATCH (tx:Transaction {txid:{0}})-[r:INCLUDED_IN]->(mempoo:Mempool) "+
           "DELETE r WITH tx MATCH (block:Block {hash:{1}}) CREATE (tx)-[:INCLUDED_IN]->(block);")
    void moveMempooTransactionToBlock(String txid, String blockHash);
    
    @Query(
            "MATCH (:Block {height:{0}})<-[:INCLUDED_IN]-(tx:Transaction) RETURN tx.txid ORDER BY tx.n;"
    )
    List<String> findBlockTxIds(long blockheight);
}
