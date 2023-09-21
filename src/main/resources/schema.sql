DROP TABLE animale if exists;
CREATE TABLE animale(
    id_animale integer auto_increment,
    nome varchar(20),
    genere varchar(1),
    PRIMARY KEY (id_animale)
);

DROP TABLE aggettivo if exists;
CREATE TABLE aggettivo(
    id_aggettivo integer auto_increment,
    maschile varchar(20),
    femminile varchar(20),
    PRIMARY KEY (id_aggettivo)
);

DROP TABLE stanza if exists;
CREATE TABLE stanza(
    id_stanza integer auto_increment,
    codice varchar(20),
    nome varchar(200),
    PRIMARY KEY (id_stanza)
);


DROP TABLE opzioni_stanza if exists;
CREATE TABLE opzioni_stanza(
   id_opzioni integer auto_increment,
   id_stanza integer,
   codice_stanza_custom boolean,
   nomi_tabella_custom  boolean,
   tombolino boolean,
   icone_tabella boolean,
   controllo_riga_gia_vinta boolean,
   premi_custom varchar(500),
   PRIMARY KEY (id_opzioni)
);

DROP TABLE tabella if exists;
CREATE TABLE tabella(
    id_tabella integer auto_increment,
    id_stanza integer,
    nome varchar(50),
    aggettivo varchar(20),
    icona varchar(50),
    sequenza varchar(80),
    PRIMARY KEY (id_tabella),
    CONSTRAINT stanza_tabella FOREIGN KEY (id_stanza) REFERENCES stanza (id_stanza)
);

DROP TABLE numero_uscito if exists;
CREATE TABLE numero_uscito(
    id_numero_uscito integer auto_increment,
    id_stanza integer,
    numero integer,
    data datetime,
    PRIMARY KEY (id_numero_uscito),
    CONSTRAINT stanza_numero_uscito FOREIGN KEY (id_stanza) REFERENCES stanza (id_stanza)
);

DROP TABLE vincita if exists;
CREATE TABLE vincita(
    id_vincita integer auto_increment,
    id_stanza integer,
    id_tabella integer,
    premio integer,
    PRIMARY KEY (id_vincita),
    CONSTRAINT stanza_vincita FOREIGN KEY (id_stanza) REFERENCES stanza (id_stanza)
);