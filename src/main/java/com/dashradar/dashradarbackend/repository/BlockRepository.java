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

    @Query(
            "MATCH (b:Block { hash:{0} })<-[:PREVIOUS*]-(subsequentBlock:Block)<-[:INCLUDED_IN]-(tx:Transaction) "
            + "WITH subsequentBlock, tx "
            + "MATCH (input:TransactionInput)-[:INPUT]->(tx)-[:OUTPUT]->(output:TransactionOutput) "
            + "WITH subsequentBlock, tx, input, output "
            + "OPTIONAL MATCH (tx)-[:CREATES]->(balanceEvent:BalanceEvent) "
            + "DETACH DELETE subsequentBlock, tx, input, output, balanceEvent"
    )
    Block deleteSubsequentBlocks(String hash);

}
