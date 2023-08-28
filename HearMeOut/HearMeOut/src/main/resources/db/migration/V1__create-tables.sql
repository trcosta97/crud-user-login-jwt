-- Tabela "address"
CREATE TABLE tb_address (
    id NUMBER PRIMARY KEY,
    number VARCHAR2(255),
    street VARCHAR2(255) NOT NULL,
    city VARCHAR2(255) NOT NULL,
    cep VARCHAR2(255) NOT NULL,
    province VARCHAR2(255) NOT NULL,
    complement VARCHAR2(255)
);

-- Tabela "user"
CREATE TABLE tb_user (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    cpf VARCHAR2(255) NOT NULL UNIQUE,
    email VARCHAR2(255) NOT NULL UNIQUE,
    password VARCHAR2(255) NOT NULL,
    address_id NUMBER NOT NULL,
    gender VARCHAR2(255) NOT NULL,
    sign_date TIMESTAMP,
    status NUMBER(1)
);

-- Adicionar chave estrangeira para a tabela "user"
ALTER TABLE tb_user ADD CONSTRAINT fk_address FOREIGN KEY (address_id) REFERENCES address(id);

-- Sequencias que geram id dos inserts
CREATE SEQUENCE address_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE user_seq START WITH 1 INCREMENT BY 1;