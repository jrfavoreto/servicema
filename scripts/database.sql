CREATE TABLE if not exists lote
(
    id BIGINT NOT NULL PRIMARY KEY,
    numero VARCHAR(255) NOT NULL
    
);

CREATE SEQUENCE if not exists id_lote_sq
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
;

CREATE TABLE if not exists item_estoque
( 
    id BIGINT NOT NULL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    valor NUMBER(15,2) NOT NULL,
    lote_fk BIGINT NOT NULL
);


CREATE SEQUENCE if not exists id_item_estoque_sq
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
;
