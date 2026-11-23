package com.devsuperior.bds02.excecao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@Setter
@Getter
public class StandardException {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String menssagem;
    private String path;


}
