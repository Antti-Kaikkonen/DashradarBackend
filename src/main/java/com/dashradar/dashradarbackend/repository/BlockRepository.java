package com.dashradar.dashradarbackend.repository;

import com.dashradar.dashradarbackend.domain.Block;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends Neo4jRepository<Block, Long> {

    @Query("MATCH (n:Block) RETURN n ORDER BY n.height DESC LIMIT 1")
    Block findLastBlock();//Find highest block

    @Query("MATCH (b:Block { hash:{0} }) RETURN b")
    Block findBlockByHash(String hash);
    
    @Query("MATCH (b:Block { hash:{0} }) RETURN b.height")
    Long findBlockHeightByHash(String hash);
    
    @Query("MATCH (b:BestBlock) RETURN b.hash;")
    String findBestBlockHash();
    
    @Query(
            "MATCH (oldTip:BestBlock) "+
            "CREATE (oldTip)<-[:PREVIOUS_BLOCK]-(newTip:Block:BestBlock) "+
            "REMOVE oldTip:BestBlock "+
            "SET newTip = { "+
            "  bits: {0}, chainwork: {1}, difficulty: {2}, hash: {3}, height: {4}, mediantime: {5}, merkleroot: {6}, nonce: {7}, size: {8}, time: {9}, version: {10} "+
            "}"
    )
    void createEmptyBestBlock(String bits, String chainwork, double difficulty, String hash, long height, long mediantime, String merkleroot, long nonce, long size, long time, int version);

    @Query(
            "CREATE (newTip:Block:BestBlock) "+
            "SET newTip = { "+
            "  bits: {0}, chainwork: {1}, difficulty: {2}, hash: {3}, height: {4}, mediantime: {5}, merkleroot: {6}, nonce: {7}, size: {8}, time: {9}, version: {10} "+
            "}"
    )
    void createGenesisBlock(String bits, String chainwork, double difficulty, String hash, long height, long mediantime, String merkleroot, long nonce, long size, long time, int version);
    
    @Query(
            "MATCH (b:Block { hash:{0} })<-[:PREVIOUS_BLOCK*]-(subsequentBlock:Block)<-[:INCLUDED_IN]-(tx:Transaction) "
            + "WITH subsequentBlock, tx "
            + "MATCH (input:TransactionInput)-[:INPUT]->(tx)-[:OUTPUT]->(output:TransactionOutput) "
            + "WITH subsequentBlock, tx, input, output "
            + "OPTIONAL MATCH (tx)-[:CREATES]->(balanceEvent:BalanceEvent) "
            + "DETACH DELETE subsequentBlock, tx, input, output, balanceEvent"
    )
    void deleteSubsequentBlocks(String hash);
    
    @Query(
    "MATCH (b:Block { hash:{0} })<-[:PREVIOUS_BLOCK*]-(subsequentBlock:Block)\n" +
    "WITH b, subsequentBlock\n" +
    "REMOVE subsequentBlock:Block:BestBlock\n" +
    "SET subsequentBlock:OrphanedBlock\n" +
    "SET b:BestBlock;"
    )
    void orphanSubsequentBlocks(String hash);
    
    @Query(
    "MATCH (b:OrphanedBlock {hash:{0}})\n" +
    "REMOVE b:OrphanedBlock\n" +
    "SET b:Block\n" +
    "RETURN true;"
    )
    Boolean unorhanBlock(String blockhash);

}
