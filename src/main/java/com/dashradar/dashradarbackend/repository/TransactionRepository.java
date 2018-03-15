package com.dashradar.dashradarbackend.repository;

import com.dashradar.dashradarbackend.domain.Transaction;
import java.util.List;
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

}
