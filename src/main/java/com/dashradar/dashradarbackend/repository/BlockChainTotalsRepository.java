package com.dashradar.dashradarbackend.repository;

import com.dashradar.dashradarbackend.domain.BlockChainTotals;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BlockChainTotalsRepository extends Neo4jRepository<BlockChainTotals, Long> {

    @Query(
        "MATCH (b:Block {hash:{0}})\n" +
        "WITH b\n" +
        "OPTIONAL MATCH (previousTotals:BlockChainTotals)<-[:BLOCKCHAIN_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
        "WITH b, previousTotals\n" +
        "OPTIONAL MATCH (b)<-[:INCLUDED_IN]-(:Transaction)<-[:INPUT]-(input:TransactionInput)\n" +
        "WITH b, coalesce(previousTotals.input_count, 0)+count(input) as input_count\n" +
        "MERGE (b)-[:BLOCKCHAIN_TOTALS]->(bct:BlockChainTotals) \n" +
        "SET bct += {\n" +
        "	input_count: input_count\n" +
        "};"
    )
    void compute_input_counts(String blockhash);

    @Query(
        "MATCH (b:Block {hash:{0}})\n" +
        "WITH b\n" +
        "OPTIONAL MATCH (previousTotals:BlockChainTotals)<-[:BLOCKCHAIN_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
        "WITH b, previousTotals\n" +
        "OPTIONAL MATCH (b)<-[:INCLUDED_IN]-(:Transaction)-[:OUTPUT]->(output:TransactionOutput)\n" +
        "WITH b, coalesce(previousTotals.output_count, 0)+count(output) as output_count\n" +
        "MERGE (b)-[:BLOCKCHAIN_TOTALS]->(bct:BlockChainTotals) \n" +
        "SET bct += {\n" +
        "	output_count: output_count\n" +
        "};"
    )
    void compute_output_counts(String blockhash);
    
    @Query(
        "MATCH (b:Block {hash:{0}})\n" +
        "WITH b\n" +
        "OPTIONAL MATCH (previousTotals:BlockChainTotals)<-[:BLOCKCHAIN_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
        "WITH b, previousTotals\n" +
        "OPTIONAL MATCH (b)<-[:INCLUDED_IN]-(:Transaction {n:0})-[:OUTPUT]->(output:TransactionOutput)\n" +
        "WITH b, coalesce(previousTotals.total_block_rewards_sat, 0)+sum(output.valueSat) as block_rewards\n" +
        "MERGE (b)-[:BLOCKCHAIN_TOTALS]->(bct:BlockChainTotals) \n" +
        "SET bct += {\n" +
        "	total_block_rewards_sat: block_rewards\n" +
        "};"
    )
    void compute_total_block_rewards(String blockhash);
    
    @Query(
        "MATCH (b:Block {hash:{0}})\n" +
        "WITH b\n" +
        "OPTIONAL MATCH (previousTotals:BlockChainTotals)<-[:BLOCKCHAIN_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
        "WITH b, coalesce(previousTotals.total_block_size, 0)+b.size as blocksize\n" +
        "MERGE (b)-[:BLOCKCHAIN_TOTALS]->(bct:BlockChainTotals) \n" +
        "SET bct += {\n" +
        "	total_block_size: blocksize\n" +
        "};"
    )
    void compute_total_block_size(String blockhash);
    
    @Query(
        "MATCH (b:Block {hash:{0}})\n" +
        "WITH b\n" +
        "OPTIONAL MATCH (previousTotals:BlockChainTotals)<-[:BLOCKCHAIN_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
        "WITH b, coalesce(previousTotals.total_difficulty, 0)+b.difficulty as difficulty\n" +
        "MERGE (b)-[:BLOCKCHAIN_TOTALS]->(bct:BlockChainTotals) \n" +
        "SET bct += {\n" +
        "	total_difficulty: difficulty\n" +
        "};"
    )
    void compute_total_difficulty(String blockhash);
    
    
    @Query(
        "MATCH (b:Block {hash:{0}})\n" +
        "WITH b\n" +
        "OPTIONAL MATCH (previousTotals:BlockChainTotals)<-[:BLOCKCHAIN_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
        "WITH b, previousTotals\n" +
        "OPTIONAL MATCH (b)<-[:INCLUDED_IN]-(tx:Transaction)\n" +
        "WITH b, coalesce(previousTotals.total_fees_sat, 0)+sum(tx.feesSat) as fees\n" +
        "MERGE (b)-[:BLOCKCHAIN_TOTALS]->(bct:BlockChainTotals) \n" +
        "SET bct += {\n" +
        "	total_fees_sat: fees\n" +
        "};"
    )
    void compute_total_fees(String blockhash);
    
    @Query(
        "MATCH (b:Block {hash:{0}})\n" +
        "WITH b\n" +
        "OPTIONAL MATCH (previousTotals:BlockChainTotals)<-[:BLOCKCHAIN_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
        "WITH b, previousTotals\n" +
        "OPTIONAL MATCH (b)<-[:INCLUDED_IN]-(:Transaction)-[:OUTPUT]->(output:TransactionOutput)\n" +
        "WITH b, coalesce(previousTotals.total_output_volume_sat, 0)+sum(output.valueSat) as output_volume\n" +
        "MERGE (b)-[:BLOCKCHAIN_TOTALS]->(bct:BlockChainTotals) \n" +
        "SET bct += {\n" +
        "	total_output_volume_sat: output_volume\n" +
        "};"
    )
    void compute_total_output_volume(String blockhash);
    
    
    @Query(
        "MATCH (b:Block {hash:{0}})\n" +
        "WITH b\n" +
        "OPTIONAL MATCH (previousTotals:BlockChainTotals)<-[:BLOCKCHAIN_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
        "WITH b, previousTotals\n" +
        "OPTIONAL MATCH (b)<-[:INCLUDED_IN]-(tx:Transaction)\n" +
        "WITH b, coalesce(previousTotals.total_transaction_size, 0)+sum(tx.size) as tx_size\n" +
        "MERGE (b)-[:BLOCKCHAIN_TOTALS]->(bct:BlockChainTotals) \n" +
        "SET bct += {\n" +
        "	total_transaction_size: tx_size\n" +
        "};"
    )
    void compute_total_transaction_size(String blockhash);
    
    
    @Query(
        "MATCH (b:Block {hash:{0}})\n" +
        "WITH b\n" +
        "OPTIONAL MATCH (previousTotals:BlockChainTotals)<-[:BLOCKCHAIN_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
        "WITH b, previousTotals\n" +
        "OPTIONAL MATCH (b)<-[:INCLUDED_IN]-(tx:Transaction)\n" +
        "WITH b, coalesce(previousTotals.tx_count, 0)+count(tx) as tx_count\n" +
        "MERGE (b)-[:BLOCKCHAIN_TOTALS]->(bct:BlockChainTotals) \n" +
        "SET bct += {\n" +
        "	tx_count: tx_count\n" +
        "};"
    )
    void compute_total_tx_count(String blockhash);
    
}
