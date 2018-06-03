package com.dashradar.dashradarbackend.repository;

import com.dashradar.dashradarbackend.domain.PrivateSendTotals;
import com.dashradar.dashradarbackend.domain.Transaction;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateSendTotalsRepository extends Neo4jRepository<PrivateSendTotals, Long> {
    
    @Query("MATCH\n"
    + "	 (b:Block)\n"
    + "WITH\n"
    + "	 b\n"
    + "ORDER BY \n"
    + "	 b.height\n"
    + "MERGE \n"
    + "	 (a:PrivateSendTotals {height: b.height})\n"
    + "ON CREATE SET \n"
    + "	 a += {\n"
    + "	   time: b.time\n"
    + "	 }")
    void create_privatesend_totals();
    
    
    @Query(
        "MATCH (b:Block {hash:{0}})\n" +
        "OPTIONAL MATCH (previousTotals:PrivateSendTotals)<-[:PRIVATESEND_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
        "WITH b, previousTotals\n" +
        "OPTIONAL MATCH (b)<-[:INCLUDED_IN]-(tx:Transaction {pstype:"+Transaction.PRIVATE_SEND_MIXING_100_0+"})\n" +
        "WITH b, coalesce(previousTotals.privatesend_mixing_100_0_count, 0)+count(tx) as tx_count\n" +
        "MERGE (b)-[:PRIVATESEND_TOTALS]->(pst:PrivateSendTotals)\n" +
        "SET pst += {privatesend_mixing_100_0_count: tx_count};"
    )
    void compute_mixing_100_0_counts(String blockhash);
    
    @Query(
        "MATCH (b:Block {hash:{0}})\n" +
        "OPTIONAL MATCH (previousTotals:PrivateSendTotals)<-[:PRIVATESEND_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
        "WITH b, previousTotals\n" +
        "OPTIONAL MATCH (b)<-[:INCLUDED_IN]-(tx:Transaction {pstype:"+Transaction.PRIVATE_SEND_MIXING_10_0+"})\n" +
        "WITH b, coalesce(previousTotals.privatesend_mixing_10_0_count, 0)+count(tx) as tx_count\n" +
        "MERGE (b)-[:PRIVATESEND_TOTALS]->(pst:PrivateSendTotals)\n" +
        "SET pst += {privatesend_mixing_10_0_count: tx_count};"
    )
    void compute_mixing_10_0_counts(String blockhash);
    
    @Query(
        "MATCH (b:Block {hash:{0}})\n" +
        "OPTIONAL MATCH (previousTotals:PrivateSendTotals)<-[:PRIVATESEND_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
        "WITH b, previousTotals\n" +
        "OPTIONAL MATCH (b)<-[:INCLUDED_IN]-(tx:Transaction {pstype:"+Transaction.PRIVATE_SEND_MIXING_1_0+"})\n" +
        "WITH b, coalesce(previousTotals.privatesend_mixing_1_0_count, 0)+count(tx) as tx_count\n" +
        "MERGE (b)-[:PRIVATESEND_TOTALS]->(pst:PrivateSendTotals)\n" +
        "SET pst += {privatesend_mixing_1_0_count: tx_count};"
    )
    void compute_mixing_1_0_counts(String blockhash);
    
    @Query(
        "MATCH (b:Block {hash:{0}})\n" +
        "OPTIONAL MATCH (previousTotals:PrivateSendTotals)<-[:PRIVATESEND_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
        "WITH b, previousTotals\n" +
        "OPTIONAL MATCH (b)<-[:INCLUDED_IN]-(tx:Transaction {pstype:"+Transaction.PRIVATE_SEND_MIXING_0_1+"})\n" +
        "WITH b, coalesce(previousTotals.privatesend_mixing_0_1_count, 0)+count(tx) as tx_count\n" +
        "MERGE (b)-[:PRIVATESEND_TOTALS]->(pst:PrivateSendTotals)\n" +
        "SET pst += {privatesend_mixing_0_1_count: tx_count};"
    )
    void compute_mixing_0_1_counts(String blockhash);
    
    @Query(
    "MATCH (b:Block {hash:{0}})\n" +
    "OPTIONAL MATCH (previousTotals:PrivateSendTotals)<-[:PRIVATESEND_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
    "WITH b, previousTotals\n" +
    "OPTIONAL MATCH (b)<-[:INCLUDED_IN]-(tx:Transaction {pstype:"+Transaction.PRIVATE_SEND_MIXING_0_01+"})\n" +
    "WITH b, coalesce(previousTotals.privatesend_mixing_0_01_count, 0)+count(tx) as tx_count\n" +
    "MERGE (b)-[:PRIVATESEND_TOTALS]->(pst:PrivateSendTotals)\n" +
    "SET pst += {privatesend_mixing_0_01_count: tx_count};"
    )
    void compute_mixing_0_01_counts(String blockhash);
    
    
    @Query(
    "MATCH (b:Block {hash:{0}})\n" +
    "OPTIONAL MATCH (previousTotals:PrivateSendTotals)<-[:PRIVATESEND_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
    "WITH b, previousTotals\n" +
    "OPTIONAL MATCH (b)<-[:INCLUDED_IN]-(:Transaction {pstype:"+Transaction.PRIVATE_SEND_MIXING_0_01+"})-[:OUTPUT]->(output:TransactionOutput)\n" +
    "WITH b, coalesce(previousTotals.privatesend_mixing_0_01_output_count, 0)+count(output) as output_count\n" +
    "MERGE (b)-[:PRIVATESEND_TOTALS]->(pst:PrivateSendTotals)\n" +
    "SET pst += {privatesend_mixing_0_01_output_count: output_count};"
    )
    void compute_privatesend_mixing_0_01_output_count(String blockhash);
    
    
    @Query(
    "MATCH (b:Block {hash:{0}})\n" +
    "OPTIONAL MATCH (previousTotals:PrivateSendTotals)<-[:PRIVATESEND_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
    "WITH b, previousTotals\n" +
    "OPTIONAL MATCH (b)<-[:INCLUDED_IN]-(:Transaction {pstype:"+Transaction.PRIVATE_SEND_MIXING_0_1+"})-[:OUTPUT]->(output:TransactionOutput)\n" +
    "WITH b, coalesce(previousTotals.privatesend_mixing_0_1_output_count, 0)+count(output) as output_count\n" +
    "MERGE (b)-[:PRIVATESEND_TOTALS]->(pst:PrivateSendTotals)\n" +
    "SET pst += {privatesend_mixing_0_1_output_count: output_count};"
    )
    void compute_privatesend_mixing_0_1_output_count(String blockhash);
    
    
    @Query(
    "MATCH (b:Block {hash:{0}})\n" +
    "OPTIONAL MATCH (previousTotals:PrivateSendTotals)<-[:PRIVATESEND_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
    "WITH b, previousTotals\n" +
    "OPTIONAL MATCH (b)<-[:INCLUDED_IN]-(:Transaction {pstype:"+Transaction.PRIVATE_SEND_MIXING_1_0+"})-[:OUTPUT]->(output:TransactionOutput)\n" +
    "WITH b, coalesce(previousTotals.privatesend_mixing_1_0_output_count, 0)+count(output) as output_count\n" +
    "MERGE (b)-[:PRIVATESEND_TOTALS]->(pst:PrivateSendTotals)\n" +
    "SET pst += {privatesend_mixing_1_0_output_count: output_count};"
    )
    void compute_privatesend_mixing_1_0_output_count(String blockhash);
    
    
    @Query(
    "MATCH (b:Block {hash:{0}})\n" +
    "OPTIONAL MATCH (previousTotals:PrivateSendTotals)<-[:PRIVATESEND_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
    "WITH b, previousTotals\n" +
    "OPTIONAL MATCH (b)<-[:INCLUDED_IN]-(:Transaction {pstype:"+Transaction.PRIVATE_SEND_MIXING_10_0+"})-[:OUTPUT]->(output:TransactionOutput)\n" +
    "WITH b, coalesce(previousTotals.privatesend_mixing_10_0_output_count, 0)+count(output) as output_count\n" +
    "MERGE (b)-[:PRIVATESEND_TOTALS]->(pst:PrivateSendTotals)\n" +
    "SET pst += {privatesend_mixing_10_0_output_count: output_count};"
    )
    void compute_privatesend_mixing_10_0_output_count(String blockhash);
    
    
    @Query(
    "MATCH (b:Block {hash:{0}})\n" +
    "OPTIONAL MATCH (previousTotals:PrivateSendTotals)<-[:PRIVATESEND_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
    "WITH b, previousTotals\n" +
    "OPTIONAL MATCH (b)<-[:INCLUDED_IN]-(:Transaction {pstype:"+Transaction.PRIVATE_SEND_MIXING_10_0+"})-[:OUTPUT]->(output:TransactionOutput)\n" +
    "WITH b, coalesce(previousTotals.privatesend_mixing_100_0_output_count, 0)+count(output) as output_count\n" +
    "MERGE (b)-[:PRIVATESEND_TOTALS]->(pst:PrivateSendTotals)\n" +
    "SET pst += {privatesend_mixing_100_0_output_count: output_count};"
    )
    void compute_privatesend_mixing_100_0_output_count(String blockhash);
    
    
    
    @Query(
    "MATCH (b:Block {hash:{0}})\n" +
    "OPTIONAL MATCH (previousTotals:PrivateSendTotals)<-[:PRIVATESEND_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
    "WITH b, previousTotals\n" +
    "OPTIONAL MATCH (b)<-[:INCLUDED_IN]-(:Transaction {pstype:"+Transaction.PRIVATE_SEND+"})<-[:INPUT]-(input:TransactionInput)\n" +
    "WITH b, coalesce(previousTotals.privatesend_tx_input_count, 0)+count(input) as input_count\n" +
    "MERGE (b)-[:PRIVATESEND_TOTALS]->(pst:PrivateSendTotals)\n" +
    "SET pst += {privatesend_tx_input_count: input_count};"
    )
    void compute_privatesend_tx_input_count(String blockhash);
    
    
    @Query(
    "MATCH (b:Block {hash:{0}})\n" +
    "OPTIONAL MATCH (previousTotals:PrivateSendTotals)<-[:PRIVATESEND_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
    "WITH b, previousTotals\n" +
    "OPTIONAL MATCH (b)<-[:INCLUDED_IN]-(tx:Transaction {pstype:"+Transaction.PRIVATE_SEND+"})\n" +
    "WITH b, coalesce(previousTotals.privatesend_tx_count, 0)+count(tx) as tx_count\n" +
    "MERGE (b)-[:PRIVATESEND_TOTALS]->(pst:PrivateSendTotals)\n" +
    "SET pst += {privatesend_tx_count: tx_count};"
    )
    void compute_privatesend_tx_count(String blockhash);
    
    
    
    
    
    @Query(
    "MATCH (b:Block {hash:{0}})\n" +
    "OPTIONAL MATCH (previousTotals:PrivateSendTotals)<-[:PRIVATESEND_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
    "WITH b, previousTotals\n" +
    "OPTIONAL MATCH (b)<-[:INCLUDED_IN]-(:Transaction)<-[:INPUT]-(:TransactionInput)<-[:SPENT_IN]-(output:TransactionOutput)<-[:OUTPUT]-(tx:Transaction {pstype:"+Transaction.PRIVATE_SEND_MIXING_0_01+"})\n" +
    "WITH b, coalesce(previousTotals.privatesend_mixing_0_01_spent_output_count, 0) + count(output) as spent_output_count\n" +
    "MERGE (b)-[:PRIVATESEND_TOTALS]->(pst:PrivateSendTotals)\n" +
    "SET pst += {privatesend_mixing_0_01_spent_output_count: spent_output_count};"
    )
    void compute_privatesend_mixing_0_01_spent_output_count(String blockhash);
    
    
    
    
    
    @Query(
    "MATCH (b:Block {hash:{0}})\n" +
    "OPTIONAL MATCH (previousTotals:PrivateSendTotals)<-[:PRIVATESEND_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
    "WITH b, previousTotals\n" +
    "OPTIONAL MATCH (b)<-[:INCLUDED_IN]-(:Transaction)<-[:INPUT]-(:TransactionInput)<-[:SPENT_IN]-(output:TransactionOutput)<-[:OUTPUT]-(tx:Transaction {pstype:"+Transaction.PRIVATE_SEND_MIXING_0_1+"})\n" +
    "WITH b, coalesce(previousTotals.privatesend_mixing_0_1_spent_output_count, 0) + count(output) as spent_output_count\n" +
    "MERGE (b)-[:PRIVATESEND_TOTALS]->(pst:PrivateSendTotals)\n" +
    "SET pst += {privatesend_mixing_0_1_spent_output_count: spent_output_count};"
    )
    void compute_privatesend_mixing_0_1_spent_output_count(String blockhash);
    
    
    @Query(
    "MATCH (b:Block {hash:{0}})\n" +
    "OPTIONAL MATCH (previousTotals:PrivateSendTotals)<-[:PRIVATESEND_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
    "WITH b, previousTotals\n" +
    "OPTIONAL MATCH (b)<-[:INCLUDED_IN]-(:Transaction)<-[:INPUT]-(:TransactionInput)<-[:SPENT_IN]-(output:TransactionOutput)<-[:OUTPUT]-(tx:Transaction {pstype:"+Transaction.PRIVATE_SEND_MIXING_1_0+"})\n" +
    "WITH b, coalesce(previousTotals.privatesend_mixing_1_0_spent_output_count, 0) + count(output) as spent_output_count\n" +
    "MERGE (b)-[:PRIVATESEND_TOTALS]->(pst:PrivateSendTotals)\n" +
    "SET pst += {privatesend_mixing_1_0_spent_output_count: spent_output_count};"
    )
    void compute_privatesend_mixing_1_0_spent_output_count(String blockhash);
    
    
    @Query(
    "MATCH (b:Block {hash:{0}})\n" +
    "OPTIONAL MATCH (previousTotals:PrivateSendTotals)<-[:PRIVATESEND_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
    "WITH b, previousTotals\n" +
    "OPTIONAL MATCH (b)<-[:INCLUDED_IN]-(:Transaction)<-[:INPUT]-(:TransactionInput)<-[:SPENT_IN]-(output:TransactionOutput)<-[:OUTPUT]-(tx:Transaction {pstype:"+Transaction.PRIVATE_SEND_MIXING_10_0+"})\n" +
    "WITH b, coalesce(previousTotals.privatesend_mixing_10_0_spent_output_count, 0) + count(output) as spent_output_count\n" +
    "MERGE (b)-[:PRIVATESEND_TOTALS]->(pst:PrivateSendTotals)\n" +
    "SET pst += {privatesend_mixing_10_0_spent_output_count: spent_output_count};"
    )
    void compute_privatesend_mixing_10_0_spent_output_count(String blockhash);
    
    
    @Query("MATCH (b:Block {hash:{0}})\n" +
    "OPTIONAL MATCH (previousTotals:PrivateSendTotals)<-[:PRIVATESEND_TOTALS]-(:Block)<-[:PREVIOUS_BLOCK]-(b)\n" +
    "WITH b, previousTotals\n" +
    "OPTIONAL MATCH (b)<-[:INCLUDED_IN]-(:Transaction)<-[:INPUT]-(:TransactionInput)<-[:SPENT_IN]-(output:TransactionOutput)<-[:OUTPUT]-(tx:Transaction {pstype:"+Transaction.PRIVATE_SEND_MIXING_100_0+"})\n" +
    "WITH b, coalesce(previousTotals.privatesend_mixing_100_0_spent_output_count, 0) + count(output) as spent_output_count\n" +
    "MERGE (b)-[:PRIVATESEND_TOTALS]->(pst:PrivateSendTotals)\n" +
    "SET pst += {privatesend_mixing_100_0_spent_output_count: spent_output_count};")
    void compute_privatesend_mixing_100_0_spent_output_count(String blockhash);
    
    
    
    
}
