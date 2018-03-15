package com.dashradar.dashradarbackend.domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class ScriptSignature {

    private String asm;

    private String hex;

    private Long id;

    public String getAsm() {
        return asm;
    }

    public void setAsm(String asm) {
        this.asm = asm;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
