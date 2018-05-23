package com.dashradar.dashradarbackend.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class DailyPercentiles {
    
    @GraphId
    private Long id;
    
    private double percentile;//0.5 is median etc
    
    private double tx_fee_per_byte_sat;//all transactions except coinbase
    
    private double tx_fee_sat;//all transations except coinbase
    
    private double privatesend_tx_fee_per_byte_sat;
    
    private double privatesend_tx_fee_sat;
    
    private double regular_tx_fee_per_byte_sat;
    
    private double regular_tx_fee_sat;
    
    private double create_denominations_tx_fee_per_byte_sat;
    
    private double create_denominations_tx_fee_sat;
    
    
    
    
    private double tx_size;//all transactions
    
    private double privatesend_tx_size;
    
    private double regular_tx_size;
    
    private double mixing_10_0_tx_size;
    
    private double mixing_1_0_tx_size;
    
    private double mixing_0_1_tx_size;
    
    private double mixing_0_01_tx_size;
    
    private double create_denominations_tx_size;
    
    @Relationship(type = "DAILY_PERCENTILES", direction = Relationship.INCOMING)
    private Day day;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPercentile() {
        return percentile;
    }

    public void setPercentile(double percentile) {
        this.percentile = percentile;
    }

    public double getTx_fee_per_byte_sat() {
        return tx_fee_per_byte_sat;
    }

    public void setTx_fee_per_byte_sat(double tx_fee_per_byte_sat) {
        this.tx_fee_per_byte_sat = tx_fee_per_byte_sat;
    }

    public double getTx_fee_sat() {
        return tx_fee_sat;
    }

    public void setTx_fee_sat(double tx_fee_sat) {
        this.tx_fee_sat = tx_fee_sat;
    }

    public double getPrivatesend_tx_fee_per_byte_sat() {
        return privatesend_tx_fee_per_byte_sat;
    }

    public void setPrivatesend_tx_fee_per_byte_sat(double privatesend_tx_fee_per_byte_sat) {
        this.privatesend_tx_fee_per_byte_sat = privatesend_tx_fee_per_byte_sat;
    }

    public double getPrivatesend_tx_fee_sat() {
        return privatesend_tx_fee_sat;
    }

    public void setPrivatesend_tx_fee_sat(double privatesend_tx_fee_sat) {
        this.privatesend_tx_fee_sat = privatesend_tx_fee_sat;
    }

    public double getRegular_tx_fee_per_byte_sat() {
        return regular_tx_fee_per_byte_sat;
    }

    public void setRegular_tx_fee_per_byte_sat(double regular_tx_fee_per_byte_sat) {
        this.regular_tx_fee_per_byte_sat = regular_tx_fee_per_byte_sat;
    }

    public double getRegular_tx_fee_sat() {
        return regular_tx_fee_sat;
    }

    public void setRegular_tx_fee_sat(double regular_tx_fee_sat) {
        this.regular_tx_fee_sat = regular_tx_fee_sat;
    }

    public double getCreate_denominations_tx_fee_per_byte_sat() {
        return create_denominations_tx_fee_per_byte_sat;
    }

    public void setCreate_denominations_tx_fee_per_byte_sat(double create_denominations_tx_fee_per_byte_sat) {
        this.create_denominations_tx_fee_per_byte_sat = create_denominations_tx_fee_per_byte_sat;
    }

    public double getCreate_denominations_tx_fee_sat() {
        return create_denominations_tx_fee_sat;
    }

    public void setCreate_denominations_tx_fee_sat(double create_denominations_tx_fee_sat) {
        this.create_denominations_tx_fee_sat = create_denominations_tx_fee_sat;
    }

    public double getTx_size() {
        return tx_size;
    }

    public void setTx_size(double tx_size) {
        this.tx_size = tx_size;
    }

    public double getPrivatesend_tx_size() {
        return privatesend_tx_size;
    }

    public void setPrivatesend_tx_size(double privatesend_tx_size) {
        this.privatesend_tx_size = privatesend_tx_size;
    }

    public double getRegular_tx_size() {
        return regular_tx_size;
    }

    public void setRegular_tx_size(double regular_tx_size) {
        this.regular_tx_size = regular_tx_size;
    }

    public double getMixing_10_0_tx_size() {
        return mixing_10_0_tx_size;
    }

    public void setMixing_10_0_tx_size(double mixing_10_0_tx_size) {
        this.mixing_10_0_tx_size = mixing_10_0_tx_size;
    }

    public double getMixing_1_0_tx_size() {
        return mixing_1_0_tx_size;
    }

    public void setMixing_1_0_tx_size(double mixing_1_0_tx_size) {
        this.mixing_1_0_tx_size = mixing_1_0_tx_size;
    }

    public double getMixing_0_1_tx_size() {
        return mixing_0_1_tx_size;
    }

    public void setMixing_0_1_tx_size(double mixing_0_1_tx_size) {
        this.mixing_0_1_tx_size = mixing_0_1_tx_size;
    }

    public double getMixing_0_01_tx_size() {
        return mixing_0_01_tx_size;
    }

    public void setMixing_0_01_tx_size(double mixing_0_01_tx_size) {
        this.mixing_0_01_tx_size = mixing_0_01_tx_size;
    }

    public double getCreate_denominations_tx_size() {
        return create_denominations_tx_size;
    }

    public void setCreate_denominations_tx_size(double create_denominations_tx_size) {
        this.create_denominations_tx_size = create_denominations_tx_size;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

}
