package com.dashradar.dashradarbackend.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class PrivateSendTotals {
    
    private long height;

    @GraphId
    private Long id;

    private long privatesend_tx_input_count;
   
    
    private long privatesend_mixing_0_01_output_count;

    private long privatesend_mixing_0_1_output_count;

    private long privatesend_mixing_100_0_output_count;

    private long privatesend_mixing_10_0_output_count;

    private long privatesend_mixing_1_0_output_count;
    
    
    private long privatesend_mixing_0_01_spent_output_count;

    private long privatesend_mixing_0_1_spent_output_count;

    private long privatesend_mixing_100_0_spent_output_count;

    private long privatesend_mixing_10_0_spent_output_count;

    private long privatesend_mixing_1_0_spent_output_count;

    //TODO: Add total_size by type (0.01, 0.1, 1.0, 10.0, 100.0, ps, create_denoms)
    
    private long privatesend_mixing_0_01_count;

    private long privatesend_mixing_0_1_count;

    private long privatesend_mixing_100_0_count;

    private long privatesend_mixing_10_0_count;

    private long privatesend_mixing_1_0_count;

    private long privatesend_tx_count;

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

    public long getPrivatesend_tx_input_count() {
        return privatesend_tx_input_count;
    }

    public void setPrivatesend_tx_input_count(long privatesend_tx_input_count) {
        this.privatesend_tx_input_count = privatesend_tx_input_count;
    }

    public long getPrivatesend_mixing_0_01_output_count() {
        return privatesend_mixing_0_01_output_count;
    }

    public void setPrivatesend_mixing_0_01_output_count(long privatesend_mixing_0_01_output_count) {
        this.privatesend_mixing_0_01_output_count = privatesend_mixing_0_01_output_count;
    }

    public long getPrivatesend_mixing_0_1_output_count() {
        return privatesend_mixing_0_1_output_count;
    }

    public void setPrivatesend_mixing_0_1_output_count(long privatesend_mixing_0_1_output_count) {
        this.privatesend_mixing_0_1_output_count = privatesend_mixing_0_1_output_count;
    }

    public long getPrivatesend_mixing_100_0_output_count() {
        return privatesend_mixing_100_0_output_count;
    }

    public void setPrivatesend_mixing_100_0_output_count(long privatesend_mixing_100_0_output_count) {
        this.privatesend_mixing_100_0_output_count = privatesend_mixing_100_0_output_count;
    }

    public long getPrivatesend_mixing_10_0_output_count() {
        return privatesend_mixing_10_0_output_count;
    }

    public void setPrivatesend_mixing_10_0_output_count(long privatesend_mixing_10_0_output_count) {
        this.privatesend_mixing_10_0_output_count = privatesend_mixing_10_0_output_count;
    }

    public long getPrivatesend_mixing_1_0_output_count() {
        return privatesend_mixing_1_0_output_count;
    }

    public void setPrivatesend_mixing_1_0_output_count(long privatesend_mixing_1_0_output_count) {
        this.privatesend_mixing_1_0_output_count = privatesend_mixing_1_0_output_count;
    }

    public long getPrivatesend_mixing_0_01_spent_output_count() {
        return privatesend_mixing_0_01_spent_output_count;
    }

    public void setPrivatesend_mixing_0_01_spent_output_count(long privatesend_mixing_0_01_spent_output_count) {
        this.privatesend_mixing_0_01_spent_output_count = privatesend_mixing_0_01_spent_output_count;
    }

    public long getPrivatesend_mixing_0_1_spent_output_count() {
        return privatesend_mixing_0_1_spent_output_count;
    }

    public void setPrivatesend_mixing_0_1_spent_output_count(long privatesend_mixing_0_1_spent_output_count) {
        this.privatesend_mixing_0_1_spent_output_count = privatesend_mixing_0_1_spent_output_count;
    }

    public long getPrivatesend_mixing_100_0_spent_output_count() {
        return privatesend_mixing_100_0_spent_output_count;
    }

    public void setPrivatesend_mixing_100_0_spent_output_count(long privatesend_mixing_100_0_spent_output_count) {
        this.privatesend_mixing_100_0_spent_output_count = privatesend_mixing_100_0_spent_output_count;
    }

    public long getPrivatesend_mixing_10_0_spent_output_count() {
        return privatesend_mixing_10_0_spent_output_count;
    }

    public void setPrivatesend_mixing_10_0_spent_output_count(long privatesend_mixing_10_0_spent_output_count) {
        this.privatesend_mixing_10_0_spent_output_count = privatesend_mixing_10_0_spent_output_count;
    }

    public long getPrivatesend_mixing_1_0_spent_output_count() {
        return privatesend_mixing_1_0_spent_output_count;
    }

    public void setPrivatesend_mixing_1_0_spent_output_count(long privatesend_mixing_1_0_spent_output_count) {
        this.privatesend_mixing_1_0_spent_output_count = privatesend_mixing_1_0_spent_output_count;
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

    
}
