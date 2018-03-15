package com.dashradar.dashradarbackend.domain;

import java.util.ArrayList;
import java.util.List;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class ScriptPublicKey {

    @Relationship(type = "Address", direction = Relationship.OUTGOING)
    private List<Address> addresses = new ArrayList<>();//Can be multiple if type is multisig

    private String asm;

    private String hex;

    private Long id;

    private int reqSigs;

    private String type;//pubkey,pubkeyhash,scripthash,nulldata,nonstandard,multisig

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

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

    public int getReqSigs() {
        return reqSigs;
    }

    public void setReqSigs(int reqSigs) {
        this.reqSigs = reqSigs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
