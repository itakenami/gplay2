# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table analista (
  id                        bigint not null,
  nome                      varchar(255),
  especialidade             varchar(255),
  cargo_id                  bigint,
  constraint pk_analista primary key (id))
;

create table cargo (
  id                        bigint not null,
  nome                      varchar(255),
  constraint pk_cargo primary key (id))
;

create table projeto (
  id                        bigint not null,
  nome                      varchar(255),
  descricao                 varchar(255),
  data_inicio               timestamp,
  data_fim                  timestamp,
  constraint pk_projeto primary key (id))
;


create table projeto_analista (
  projeto_id                     bigint not null,
  analista_id                    bigint not null,
  constraint pk_projeto_analista primary key (projeto_id, analista_id))
;
create sequence analista_seq;

create sequence cargo_seq;

create sequence projeto_seq;

alter table analista add constraint fk_analista_cargo_1 foreign key (cargo_id) references cargo (id) on delete restrict on update restrict;
create index ix_analista_cargo_1 on analista (cargo_id);



alter table projeto_analista add constraint fk_projeto_analista_projeto_01 foreign key (projeto_id) references projeto (id) on delete restrict on update restrict;

alter table projeto_analista add constraint fk_projeto_analista_analista_02 foreign key (analista_id) references analista (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists analista;

drop table if exists cargo;

drop table if exists projeto;

drop table if exists projeto_analista;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists analista_seq;

drop sequence if exists cargo_seq;

drop sequence if exists projeto_seq;

