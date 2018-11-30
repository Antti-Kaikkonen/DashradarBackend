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
            "MATCH (a:Address {address:{0}})<-[:INCLUDED_IN]-(be:BalanceEvent)<-[:CREATES]-(tx:Transaction)-[:INCLUDED_IN]->(b:Block)\n"+
            "WITH a, be ORDER BY b.height DESC, tx.n DESC LIMIT 1\n"+
            "MERGE (a)-[:CURRENT_BALANCE]->(be);"
    )
    public void updateCurrentBalance(String address);
    
    @Query(
            "MATCH (a:Address {address:{0}})-[c:CURRENT_BALANCE]->(:BalanceEvent)\n"+
            "DELETE c;"
    )
    public void deleteCurrentBalance(String address);
    
    
    @Query(
            "MATCH (:OrphanedBlock)<-[:INCLUDED_IN]-(tx:Transaction)-[:CREATES]->(:BalanceEvent)<-[c:CURRENT_BALANCE]-(a:Address)\n"+
            "WHERE NOT (tx)-[:INCLUDED_IN]->(:Block)\n"+        
            "DELETE c\n"+
            "RETURN a.address;"        
    )
    public List<String> deleteCurrentBalanceFromOrphanedBlocks();
    
    
    @Query(
            "MATCH (:OrphanedBlock)<-[:INCLUDED_IN]-(tx:Transaction)-[:CREATES]->(be:BalanceEvent)\n"+
            "WHERE NOT (tx)-[:INCLUDED_IN]->(:Block)\n"+  
            "DETACH DELETE be;"       
    )
    public void deleteAllBalanceEventsFromOrphanedBlocks();
    
    
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
