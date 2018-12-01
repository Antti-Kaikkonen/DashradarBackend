package com.dashradar.dashradarbackend.repository;

import com.dashradar.dashradarbackend.domain.DailyPercentiles;
import com.dashradar.dashradarbackend.domain.Transaction;
import java.util.List;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyPercentilesRepository extends Neo4jRepository<DailyPercentiles, Long> {
    
    @Query(
        "MATCH (d:Day)\n"+
        "MERGE (d)-[:DAILY_MEDIANS]->(:DailyMedians);"
    )
    void create_daily_medians();
    
//    @Query(
//        "MATCH (b:Block)<-[:INCLUDED_IN]-(tx:Transaction)\n" +
//        "WITH b.time/86400 as day, percentileCont(1.0*tx.feesSat/tx.size, 0.5) as feePerByte\n" +
//        "MATCH (d:Day {day:day})\n" +
//        "MERGE (d)-[:DAILY_PERCENTILES]->(dp:DailyPercentiles {percentile:0.5})\n" +
//        "SET dp.feePerByteSat = feePerByte;"
//    )
//    void computeMedianFeePerByteAllDays();
    
    @Query(
        "MATCH (b:Block)<-[:INCLUDED_IN]-(tx:Transaction)\n" +
        "WHERE b.time >= {0}*86400 AND b.time < ({0}+1)*86400\n" +
        "WITH percentileCont(1.0*tx.feesSat/tx.size, {1}) as feePerByte\n" +
        "MATCH (d:Day {day:{0}})\n" +
        "MERGE (d)-[:DAILY_PERCENTILES]->(dp:DailyPercentiles {percentile:{1}})\n" +
        "SET dp.tx_fee_per_byte_sat = feePerByte;"
    )
    void createDailyFeePerByteSat(long day, double percentile);
    
    @Query(
        "MATCH (b:Block)<-[:INCLUDED_IN]-(tx:Transaction)\n" +
        "WHERE b.time >= {0}*86400 AND b.time < ({0}+1)*86400\n" +
        "WITH percentileCont(tx.feesSat, {1}) as fee\n" +
        "MATCH (d:Day {day:{0}})\n" +
        "MERGE (d)-[:DAILY_PERCENTILES]->(dp:DailyPercentiles {percentile:{1}})\n" +
        "SET dp.tx_fee_sat = fee;"
    )
    void createDailyFeeSat(long day, double percentile);
    
    @Query(
        "CYPHER planner=rule MATCH (b:Block)\n" +
        "WHERE b.time >= {0}*86400 AND b.time < ({0}+1)*86400\n" +
        "WITH b\n" +
        "MATCH (b)<-[:INCLUDED_IN]-(tx:Transaction)\n"+
        "WHERE tx.pstype = "+Transaction.PRIVATE_SEND+"\n"+
        "WITH percentileCont(1.0*tx.feesSat/tx.size, {1}) as feePerByte\n" +
        "MATCH (d:Day {day:{0}})\n" +
        "MERGE (d)-[:DAILY_PERCENTILES]->(dp:DailyPercentiles {percentile:{1}})\n" +
        "SET dp.privatesend_tx_fee_per_byte_sat = feePerByte;"
    )
    void createPrivateSendFeePerByteSat(long day, double percentile);
    
    @Query(
        "CYPHER planner=rule MATCH (b:Block)\n" +
        "WHERE b.time >= {0}*86400 AND b.time < ({0}+1)*86400\n" +
        "WITH b\n" +
        "MATCH (b)<-[:INCLUDED_IN]-(tx:Transaction)\n"+
        "WHERE tx.pstype = "+Transaction.PRIVATE_SEND+"\n"+
        "WITH percentileCont(tx.feesSat, {1}) as fee\n" +
        "MATCH (d:Day {day:{0}})\n" +
        "MERGE (d)-[:DAILY_PERCENTILES]->(dp:DailyPercentiles {percentile:{1}})\n" +
        "SET dp.privatesend_tx_fee_sat = fee;"
    )
    void createPrivateSendFeeSat(long day, double percentile);
    
    @Query(
        "CYPHER planner=rule MATCH (b:Block)\n" +
        "WHERE b.time >= {0}*86400 AND b.time < ({0}+1)*86400\n" +
        "WITH b\n" +
        "MATCH (b)<-[:INCLUDED_IN]-(tx:Transaction)\n"+
        "WHERE tx.pstype = "+Transaction.PRIVATE_SEND_CREATE_DENOMINATIONS+"\n"+
        "WITH percentileCont(1.0*tx.feesSat/tx.size, {1}) as feePerByte\n" +
        "MATCH (d:Day {day:{0}})\n" +
        "MERGE (d)-[:DAILY_PERCENTILES]->(dp:DailyPercentiles {percentile:{1}})\n" +
        "SET dp.create_denominations_tx_fee_per_byte_sat = feePerByte;"
    )
    void createCreateDenominationsFeePerByteSat(long day, double percentile);
    
    @Query(
        "CYPHER planner=rule MATCH (b:Block)\n" +
        "WHERE b.time >= {0}*86400 AND b.time < ({0}+1)*86400\n" +
        "WITH b\n" +
        "MATCH (b)<-[:INCLUDED_IN]-(tx:Transaction)\n"+
        "WHERE tx.pstype = "+Transaction.PRIVATE_SEND_CREATE_DENOMINATIONS+"\n"+
        "WITH percentileCont(tx.feesSat, {1}) as fee\n" +
        "MATCH (d:Day {day:{0}})\n" +
        "MERGE (d)-[:DAILY_PERCENTILES]->(dp:DailyPercentiles {percentile:{1}})\n" +
        "SET dp.create_denominations_tx_fee_sat = fee;"
    )
    void createCreateDenominationsFeeSat(long day, double percentile);
    
    @Query(
        "CYPHER planner=rule MATCH (b:Block)\n" +
        "WHERE b.time >= {0}*86400 AND b.time < ({0}+1)*86400\n" +
        "WITH b\n" +
        "MATCH (b)<-[:INCLUDED_IN]-(tx:Transaction)\n"+
        "WHERE tx.pstype = "+Transaction.PRIVATE_SEND_NONE+"\n"+
        "WITH percentileCont(1.0*tx.feesSat/tx.size, {1}) as feePerByte\n" +
        "MATCH (d:Day {day:{0}})\n" +
        "MERGE (d)-[:DAILY_PERCENTILES]->(dp:DailyPercentiles {percentile:{1}})\n" +
        "SET dp.regular_tx_fee_per_byte_sat = feePerByte;"
    )
    void createRegularTransactionFeePerByteSat(long day, double percentile);
    
    @Query(
        "CYPHER planner=rule MATCH (b:Block)\n" +
        "WHERE b.time >= {0}*86400 AND b.time < ({0}+1)*86400\n" +
        "WITH b\n" +
        "MATCH (b)<-[:INCLUDED_IN]-(tx:Transaction)\n"+
        "WHERE tx.pstype = "+Transaction.PRIVATE_SEND_NONE+"\n"+
        "WITH percentileCont(tx.feesSat, {1}) as fee\n" +
        "MATCH (d:Day {day:{0}})\n" +
        "MERGE (d)-[:DAILY_PERCENTILES]->(dp:DailyPercentiles {percentile:{1}})\n" +
        "SET dp.regular_tx_fee_sat = fee;"
    )
    void createRegularTransactionFeeSat(long day, double percentile);
    
    @Query(
        "MATCH (b:Block)<-[:INCLUDED_IN]-(tx:Transaction)\n" +
        "WHERE b.time >= {0}*86400 AND b.time < ({0}+1)*86400\n" +
        "WITH percentileCont(tx.size, {1}) as size\n" +
        "MATCH (d:Day {day:{0}})\n" +
        "MERGE (d)-[:DAILY_PERCENTILES]->(dp:DailyPercentiles {percentile:{1}})\n" +
        "SET dp.tx_size = size;"
    )
    void create_tx_size(long day, double percentile);
    
    @Query(
        "CYPHER planner=rule MATCH (b:Block)\n" +
        "WHERE b.time >= {0}*86400 AND b.time < ({0}+1)*86400\n" +
        "WITH b\n" +
        "MATCH (b)<-[:INCLUDED_IN]-(tx:Transaction)\n"+
        "WHERE tx.pstype = "+Transaction.PRIVATE_SEND+"\n"+
        "WITH percentileCont(tx.size, {1}) as size\n" +
        "MATCH (d:Day {day:{0}})\n" +
        "MERGE (d)-[:DAILY_PERCENTILES]->(dp:DailyPercentiles {percentile:{1}})\n" +
        "SET dp.privatesend_tx_size = size;"
    )
    void create_privatesend_tx_size_size(long day, double percentile);
    
    @Query(
        "CYPHER planner=rule MATCH (b:Block)\n" +
        "WHERE b.time >= {0}*86400 AND b.time < ({0}+1)*86400\n" +
        "WITH b\n" +
        "MATCH (b)<-[:INCLUDED_IN]-(tx:Transaction)\n"+
        "WHERE tx.pstype = "+Transaction.PRIVATE_SEND_NONE+"\n"+
        "WITH percentileCont(tx.size, {1}) as size\n" +
        "MATCH (d:Day {day:{0}})\n" +
        "MERGE (d)-[:DAILY_PERCENTILES]->(dp:DailyPercentiles {percentile:{1}})\n" +
        "SET dp.regular_tx_size = size;"
    )
    void create_regular_tx_size_size(long day, double percentile);
    
    
    @Query(
        "CYPHER planner=rule MATCH (b:Block)\n" +
        "WHERE b.time >= {0}*86400 AND b.time < ({0}+1)*86400\n" +
        "WITH b\n" +
        "MATCH (b)<-[:INCLUDED_IN]-(tx:Transaction)\n"+
        "WHERE tx.pstype = "+Transaction.PRIVATE_SEND_MIXING_10_0+"\n"+
        "WITH percentileCont(tx.size, {1}) as size\n" +
        "MATCH (d:Day {day:{0}})\n" +
        "MERGE (d)-[:DAILY_PERCENTILES]->(dp:DailyPercentiles {percentile:{1}})\n" +
        "SET dp.mixing_10_0_tx_size = size;"
    )
    void create_mixing_10_0_tx_size_size(long day, double percentile);
    
    
    @Query(
        "CYPHER planner=rule MATCH (b:Block)\n" +
        "WHERE b.time >= {0}*86400 AND b.time < ({0}+1)*86400\n" +
        "WITH b\n" +
        "MATCH (b)<-[:INCLUDED_IN]-(tx:Transaction)\n"+
        "WHERE tx.pstype = "+Transaction.PRIVATE_SEND_MIXING_1_0+"\n"+
        "WITH percentileCont(tx.size, {1}) as size\n" +
        "MATCH (d:Day {day:{0}})\n" +
        "MERGE (d)-[:DAILY_PERCENTILES]->(dp:DailyPercentiles {percentile:{1}})\n" +
        "SET dp.mixing_1_0_tx_size = size;"
    )
    void create_mixing_1_0_tx_size_size(long day, double percentile);
    
    @Query(
        "CYPHER planner=rule MATCH (b:Block)\n" +
        "WHERE b.time >= {0}*86400 AND b.time < ({0}+1)*86400\n" +
        "WITH b\n" +
        "MATCH (b)<-[:INCLUDED_IN]-(tx:Transaction)\n"+
        "WHERE tx.pstype = "+Transaction.PRIVATE_SEND_MIXING_0_1+"\n"+
        "WITH percentileCont(tx.size, {1}) as size\n" +
        "MATCH (d:Day {day:{0}})\n" +
        "MERGE (d)-[:DAILY_PERCENTILES]->(dp:DailyPercentiles {percentile:{1}})\n" +
        "SET dp.mixing_0_1_tx_size = size;"
    )
    void create_mixing_0_1_tx_size_size(long day, double percentile);
    
    @Query(
        "CYPHER planner=rule MATCH (b:Block)\n" +
        "WHERE b.time >= {0}*86400 AND b.time < ({0}+1)*86400\n" +
        "WITH b\n" +
        "MATCH (b)<-[:INCLUDED_IN]-(tx:Transaction)\n"+
        "WHERE tx.pstype = "+Transaction.PRIVATE_SEND_MIXING_0_01+"\n"+
        "WITH percentileCont(tx.size, {1}) as size\n" +
        "MATCH (d:Day {day:{0}})\n" +
        "MERGE (d)-[:DAILY_PERCENTILES]->(dp:DailyPercentiles {percentile:{1}})\n" +
        "SET dp.mixing_0_01_tx_size = size;"
    )
    void create_mixing_0_01_tx_size_size(long day, double percentile);
    
    @Query(
        "CYPHER planner=rule MATCH (b:Block)\n" +
        "WHERE b.time >= {0}*86400 AND b.time < ({0}+1)*86400\n" +
        "WITH b\n" +
        "MATCH (b)<-[:INCLUDED_IN]-(tx:Transaction)\n"+
        "WHERE tx.pstype = "+Transaction.PRIVATE_SEND_CREATE_DENOMINATIONS+"\n"+
        "WITH percentileCont(tx.size, {1}) as size\n" +
        "MATCH (d:Day {day:{0}})\n" +
        "MERGE (d)-[:DAILY_PERCENTILES]->(dp:DailyPercentiles {percentile:{1}})\n" +
        "SET dp.create_denominations_tx_size = size;"
    )
    void create_create_denominations_tx_size(long day, double percentile);
    
    
    @Query(
        "MATCH (d:Day)\n" +
        "WHERE NOT (d)-[:DAILY_PERCENTILES]->(:DailyPercentiles {percentile:{0}})\n" +
        "RETURN d.day;"
    )        
    List<Long> daysMissingDailyPercentiles(double percentile);
    
    
}
