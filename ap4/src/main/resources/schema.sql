-- Versão simplificada para rodar em HSQLDB Mem
SET DATABASE SQL SYNTAX MYS TRUE;

DROP TABLE post_tag IF EXISTS CASCADE;
DROP TABLE post IF EXISTS CASCADE;
DROP TABLE usuario IF EXISTS CASCADE;

CREATE TABLE usuario(
    id              BIGINT          NOT NULL AUTO_INCREMENT,
    username        VARCHAR(50)     NOT NULL,
    nome            VARCHAR(200)    NOT NULL,
    idade           INT             NOT NULL,
    url_imagem      VARCHAR(1000)   NOT NULL,
    CONSTRAINT PK_USUARIO          PRIMARY KEY (id)
);

CREATE TABLE post(
     id              BIGINT          NOT NULL AUTO_INCREMENT,
     data_postagem   TIMESTAMP       NOT NULL,
     mensagem        VARCHAR(2200)   NOT NULL,
     usuario_id      BIGINT          NOT NULL,
     CONSTRAINT PK_POST              PRIMARY KEY (id),
     CONSTRAINT FK_POST_USUARIO_ID   FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE post_tag(
     post_id           BIGINT          NOT NULL,
     nome              VARCHAR(255)    NOT NULL,
     ordem             INT             NOT NULL DEFAULT 0,
     CONSTRAINT PK_POST_TAG    PRIMARY KEY (post_id, nome),
     CONSTRAINT FK_POST_TAG_POST   FOREIGN KEY (post_id) REFERENCES post(id)
);
