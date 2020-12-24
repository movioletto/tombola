DROP TABLE animale if exists;
CREATE TABLE animale(
    id_animale serial,
    nome varchar(20),
    --genere varchar(1),
    PRIMARY KEY (id_animale)
);

DROP TABLE aggettivo if exists;
CREATE TABLE aggettivo(
    id_aggettivo serial,
    nome varchar(20),
    --maschile varchar(20),
    --femminile varchar(20),
    PRIMARY KEY (id_aggettivo)
);

DROP TABLE stanza if exists;
CREATE TABLE stanza(
    id_stanza varchar(10),
    nome varchar(200),
    PRIMARY KEY (id_stanza)
);

DROP TABLE tabella if exists;
CREATE TABLE tabella(
    id_tabella varchar(50),
    id_stanza varchar(50),
    sequenza varchar(80),
    PRIMARY KEY (id_tabella, id_stanza),
    CONSTRAINT stanza_tabella FOREIGN KEY (id_stanza) REFERENCES stanza (id_stanza)
);

DROP TABLE numero_uscito if exists;
CREATE TABLE numero_uscito(
    id_stanza varchar(50),
    numero number(3,0),
    data datetime,
    PRIMARY KEY (id_stanza, numero),
    CONSTRAINT stanza_numero_uscito FOREIGN KEY (id_stanza) REFERENCES stanza (id_stanza)
);

DROP TABLE vincita if exists;
CREATE TABLE vincita(
    id_stanza varchar(50),
    id_tabella varchar(50),
    premio number(1,0),
    PRIMARY KEY (id_stanza, id_tabella),
    CONSTRAINT tabella_vincita FOREIGN KEY (id_stanza, id_tabella) REFERENCES tabella (id_stanza, id_tabella)
);