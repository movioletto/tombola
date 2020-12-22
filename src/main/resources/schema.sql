drop table animale if exists;
create table animale(id_animale serial, nome varchar(20));

drop table aggettivo if exists;
create table aggettivo(id_aggettivo serial, nome varchar(20));

drop table stanza if exists;
create table stanza(id_stanza varchar(10) unique, nome varchar(200));

drop table tabella if exists;
create table tabella(id_tabella varchar(50) unique, id_stanza varchar(50), sequenza varchar(80));

drop table numero_uscito if exists;
create table numero_uscito(id_stanza varchar(50), numero number(3,0), data datetime);

drop table vincita if exists;
create table vincita(id_stanza varchar(50), premio number(1,0), id_tabella varchar(50));