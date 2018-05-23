package com.dashradar.dashradarbackend.repository;

import com.dashradar.dashradarbackend.domain.BalanceEvent;
import com.dashradar.dashradarbackend.domain.dto.AddressBalanceChange;
import java.util.List;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceEventRepository extends Neo4jRepository<BalanceEvent, Long> {
    
    @Query(
            "MATCH (a:Address)<-[:ADDRESS]-(spentOutput:TransactionOutput)-[:SPENT_IN]->(:TransactionInput)-[:INPUT]->(tx:Transaction {txid:{0}})\n"+
            "WITH a, -sum(spentOutput.valueSat) as deltaSat\n"+
            "RETURN a.address as address, deltaSat;"
    )
    public List<AddressBalanceChange> findAddressBalanceChangesOfTransactionInputs(String txid);
    
    @Query(
            "MATCH (tx:Transaction {txid:{0}})-[:OUTPUT]->(receivedOutput:TransactionOutput)-[:ADDRESS]->(a:Address)\n"+
            "WITH a, sum(receivedOutput.valueSat) as deltaSat\n"+
            "RETURN a.address as address, deltaSat;"
    )
    public List<AddressBalanceChange> findAddressBalanceChangesOfTransactionOutputs(String txid);
    
    
    @Query(
            "MATCH (tx:Transaction {txid:{0}})\n"+
            "WITH tx\n"+
            "MATCH (a:Address {address:{1}})\n"+
            "WITH tx, a\n"+
            "OPTIONAL MATCH (a)-[c:CURRENT_BALANCE]->(b:BalanceEvent)\n"+
            "WITH tx, a, coalesce(b.balanceAfterSat, 0) as oldBalance, c\n"+
            "DELETE c\n"+
            "CREATE (b:BalanceEvent {balanceAfterSat:oldBalance+{2}, balanceChangeSat:{2}})\n"+
            "CREATE (a)<-[:INCLUDED_IN]-(b)\n"+
            "CREATE (b)<-[:CREATES]-(tx)\n"+
            "CREATE (a)-[:CURRENT_BALANCE]->(b);"
    )
    public void changeAdderssBalance(String txid, String address, long balanceChange);
    
    @Query(
            "MATCH (b:Block)<-[:INCLUDED_IN]-(:Transaction)-[:CREATES]->(:BalanceEvent) RETURN max(b.height) as maxHeight;"
    )
    public List<Long> lastBlockContainingBalanceEventOld();
    
   
}
