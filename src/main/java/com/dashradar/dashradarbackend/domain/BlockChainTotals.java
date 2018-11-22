package com.dashradar.dashradarbackend.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class BlockChainTotals {

    private long height;

    @GraphId
    private Long id;

    private long input_count;

    private long output_count;

    private long privatesend_mixing_0_01_count;

    private long privatesend_mixing_0_1_count;

    private long privatesend_mixing_100_0_count;

    private long privatesend_mixing_10_0_count;

    private long privatesend_mixing_1_0_count;

    private long privatesend_tx_count;

    private long time;

    private long total_block_rewards_sat;

    private long total_block_size;
    
    private long total_difficulty;

    private long total_fees_sat;

    private long total_output_volume_sat;

    private long total_transaction_size;

    private long tx_count;
    
    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getInput_count() {
        return input_count;
    }

    public void setInput_count(long input_count) {
        this.input_count = input_count;
    }

    public long getOutput_count() {
        return output_count;
    }

    public void setOutput_count(long output_count) {
        this.output_count = output_count;
    }

    public long getPrivatesend_mixing_0_01_count() {
        return privatesend_mixing_0_01_count;
    }

    public void setPrivatesend_mixing_0_01_count(long privatesend_mixing_0_01_count) {
        this.privatesend_mixing_0_01_count = privatesend_mixing_0_01_count;
    }

    public long getPrivatesend_mixing_0_1_count() {
        return privatesend_mixing_0_1_count;
    }

    public void setPrivatesend_mixing_0_1_count(long privatesend_mixing_0_1_count) {
        this.privatesend_mixing_0_1_count = privatesend_mixing_0_1_count;
    }

    public long getPrivatesend_mixing_100_0_count() {
        return privatesend_mixing_100_0_count;
    }

    public void setPrivatesend_mixing_100_0_count(long privatesend_mixing_100_0_count) {
        this.privatesend_mixing_100_0_count = privatesend_mixing_100_0_count;
    }

    public long getPrivatesend_mixing_10_0_count() {
        return privatesend_mixing_10_0_count;
    }

    public void setPrivatesend_mixing_10_0_count(long privatesend_mixing_10_0_count) {
        this.privatesend_mixing_10_0_count = privatesend_mixing_10_0_count;
    }

    public long getPrivatesend_mixing_1_0_count() {
        return privatesend_mixing_1_0_count;
    }

    public void setPrivatesend_mixing_1_0_count(long privatesend_mixing_1_0_count) {
        this.privatesend_mixing_1_0_count = privatesend_mixing_1_0_count;
    }

    public long getPrivatesend_tx_count() {
        return privatesend_tx_count;
    }

    public void setPrivatesend_tx_count(long privatesend_tx_count) {
        this.privatesend_tx_count = privatesend_tx_count;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTotal_block_rewards_sat() {
        return total_block_rewards_sat;
    }

    public void setTotal_block_rewards_sat(long total_block_rewards_sat) {
        this.total_block_rewards_sat = total_block_rewards_sat;
    }

    public long getTotal_block_size() {
        return total_block_size;
    }

    public void setTotal_block_size(long total_block_size) {
        this.total_block_size = total_block_size;
    }

    public long getTotal_difficulty() {
        return total_difficulty;
    }

    public void setTotal_difficulty(long total_difficulty) {
        this.total_difficulty = total_difficulty;
    }
    
    public long getTotal_fees_sat() {
        return total_fees_sat;
    }

    public void setTotal_fees_sat(long total_fees_sat) {
        this.total_fees_sat = total_fees_sat;
    }

    public long getTotal_output_volume_sat() {
        return total_output_volume_sat;
    }

    public void setTotal_output_volume_sat(long total_output_volume_sat) {
        this.total_output_volume_sat = total_output_volume_sat;
    }

    public long getTotal_transaction_size() {
        return total_transaction_size;
    }

    public void setTotal_transaction_size(long total_transaction_size) {
        this.total_transaction_size = total_transaction_size;
    }

    public long getTx_count() {
        return tx_count;
    }

    public void setTx_count(long tx_count) {
        this.tx_count = tx_count;
    }

}
