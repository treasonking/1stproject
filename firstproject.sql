CREATE TABLE JoinDate (
    joind DATE DEFAULT SYSDATE,
    id VARCHAR2(20),
    CONSTRAINT joind_pk PRIMARY KEY (joind),
    CONSTRAINT joind_id_fk FOREIGN KEY (id) REFERENCES LoginInfo(id)
);

CREATE TABLE LoginInfo (
    id VARCHAR2(20),
    pw VARCHAR2(100),
    name VARCHAR(4),
    birth DATE,
    joinddate DATE DEFAULT SYSDATE,
    CONSTRAINT lginfo_id_pk PRIMARY KEY (id),
    CONSTRAINT lginfo_pw_nn CHECK (pw IS NOT NULL),
    CONSTRAINT lginfo_name_nn CHECK (name IS NOT NULL),
    CONSTRAINT lginfo_br_nn CHECK (birth IS NOT NULL)
    
);

CREATE TABLE Game_person (
    id VARCHAR2(20),
    score INT NOT NULL,
    CONSTRAINT gp_id_fk FOREIGN KEY (id) REFERENCES LoginInfo(id)
);

CREATE TABLE Game_wtd (
    id VARCHAR2(20),
    score number(3,1) NOT NULL,
    CONSTRAINT gw_id_fk FOREIGN KEY (id) REFERENCES LoginInfo(id)
);

CREATE TABLE newsdate (
    id VARCHAR2(20),
    good INT NOT NULL,
    
    CONSTRAINT nd_id_fk FOREIGN KEY (id) REFERENCES LoginInfo(id)
);
