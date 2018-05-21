package com.dashradar.dashradarbackend.domain.dto;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class AddressBalanceChange {
    public String address;
    public Long deltaSat;//Delta in satoshis
}
