package com.gupao.edu.lotus.server.service.search.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SqlFormat {
    CSV("csv","text/csv"),
    JSON("json","application/json"),
    TSV("tsv","text/tab-separated-values"),
    TXT("txt","text/plain"),
    YAML("yaml","application/yaml"),
    CBOR("cbor","application/cbor"),
    SMILE("smile","application/smile");


    String format;
    String acceptHttpHeader;

    @Override
    public String toString() {
        return this.format;
    }



}
