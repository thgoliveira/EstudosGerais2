drop table if exists veiculosdb.veiculo CASCADE;
create table veiculosdb.veiculo (id bigint generated by default as identity, ano integer, atualizacao date, criacao date, descricao varchar(255), marca varchar(255), status boolean, veiculo varchar(255), primary key (id));
