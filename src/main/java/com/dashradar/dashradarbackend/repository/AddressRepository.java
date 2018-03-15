package com.dashradar.dashradarbackend.repository;

import com.dashradar.dashradarbackend.domain.Address;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends Neo4jRepository<Address, Long> {

    Address findByAddress(String address, @Depth int depth);

}
