package com.dashradar.dashradarbackend.repository;

import com.dashradar.dashradarbackend.domain.MultiInputHeuristicCluster;
import com.dashradar.dashradarbackend.domain.dto.TransactionClusterData;
import java.util.List;
import java.util.Set;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultiInputHeuristicClusterRepository extends Neo4jRepository<MultiInputHeuristicCluster, Long> {

    
    @Query(
            "MATCH (a:Address {address:{1}}) "
                    + "WITH a "
                    + "MATCH (cluster:MultiInputHeuristicCluster) WHERE ID(cluster)={0} "
                    + "WITH a, cluster "
                    + "MERGE (a)<-[:INCLUDED_IN]-(cluster)"
                    + "ON CREATE SET cluster.clusterSize = cluster.clusterSize + 1;"
    )
    public void addAddressToCluster(Long clusterId, String address);
    
    @Query(
            "CREATE (cluster:MultiInputHeuristicCluster {clusterSize: size({0})}) "
            + "WITH cluster "
            + "UNWIND {0} as address "
            + "MATCH (a:Address {address:address}) "
            + "WITH cluster, a "
            + "MERGE (cluster)<-[:INCLUDED_IN]-(a);"
    )
    public void createClusterWithAddresses(Set<String> addresses);
    
    @Query(
            "MATCH (cluster:MultiInputHeuristicCluster) WHERE ID(cluster)={0} DETACH DELETE cluster;"
    )
    public void detachDeleteCluster(long clusterid);
    
    
    @Query(
            "MATCH (tx:Transaction)-[:INCLUDED_IN]->(block:Block {height:{0}}) WHERE tx.pstype < 3 OR tx.pstype > 7 RETURN tx.txid;"
    )
    public List<String> blockNonMixingTransactions(long height);
    
    
    @Query(
            "MATCH (tx:Transaction)-[:INCLUDED_IN]->(block:Block) WHERE (tx.pstype < 3 OR tx.pstype > 7) AND block.height >= height RETURN tx.txid;"
    )
    public List<String> nonMixingTransactionsAfterHeight(long height);
    
    @Query(
            "MATCH (tx:Transaction {txid:{0}})<-[:INPUT]-(:TransactionInput)<-[:SPENT_IN]-(:TransactionOutput)-[:ADDRESS]->(address:Address)\n" +
            "WITH distinct address\n" +
            "OPTIONAL MATCH (address)-[:INCLUDED_IN]->(cluster:MultiInputHeuristicCluster)\n" +
            "RETURN ID(cluster) as clusterId, cluster.clusterSize as clusterSize, collect(address.address) as addresses;"
    )
    public List<TransactionClusterData> addressClustersOfTransaction(String txid);
    
    
}
