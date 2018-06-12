package com.dashradar.dashradarbackend.repository;

import com.dashradar.dashradarbackend.domain.Address;
import java.util.List;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends Neo4jRepository<Address, Long> {

    Address findByAddress(String address, @Depth int depth);
    
    @Query(
    "MATCH (b:Block {hash:{0}})\n" +
    "WITH b\n" +
    "OPTIONAL MATCH (spendingAddress:Address)<-[:ADDRESS]-(:TransactionOutput)-[:SPENT_IN]->(:TransactionInput)-[:INPUT]->(:Transaction)-[:INCLUDED_IN]->(b)\n" +
    "WITH b, collect(distinct spendingAddress.address) as spendingAddresses\n" +
    "OPTIONAL MATCH (b)<-[:INCLUDED_IN]-(:Transaction)-[:OUTPUT]->(:TransactionOutput)-[:ADDRESS]->(receivingAddress:Address)\n" +
    "WITH spendingAddresses, collect(distinct receivingAddress.address) as receivingAddresses\n" +
    "UNWIND (spendingAddresses+receivingAddresses) as address\n" +
    "RETURN distinct address ORDER BY address;"        
    )
    List<String> findBlockAddresses(String blockhash);

}
