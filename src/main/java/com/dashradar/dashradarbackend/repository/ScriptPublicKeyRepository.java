package com.dashradar.dashradarbackend.repository;

import com.dashradar.dashradarbackend.domain.ScriptPublicKey;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScriptPublicKeyRepository extends Neo4jRepository<ScriptPublicKey, Long> {

    ScriptPublicKey findByHex(String hex);

}
