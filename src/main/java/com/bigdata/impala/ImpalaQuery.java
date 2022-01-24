package com.bigdata.impala;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ImpalaQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    private String connectionUrl;
    private String query;
}
