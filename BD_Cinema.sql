CREATE DATABASE Cinema
USE Cinema

Create table Sala(
N_sala Varchar (5) NOT NULL,
cod_Sala Varchar(5)PRIMARY KEY NOT NULL,
Desc_sala Varchar (150) NOT NULL,
Localizacao Varchar (150) NOT NULL
);

Create table Projeção(
cod_sala Varchar (5) foreign key references Sala NOT NULL,
cod_filme Varchar (5) foreign key references Filme NOT NULL,
Descricao Varchar (150) NOT NULL,
data_inicio date NOT NULL,
data_fim date NOT NULL
);

create table Filme(
cod_filme Varchar (5) PRIMARY KEY NOT NULL,
Sinopse Varchar (200) NOT NULL,
Duracao time NOT NULL,
Nome Varchar (50) NOT NULL,
Classificacao_Ind Int NOT NULL,
Diretor Varchar (50) NOT NULL,
Genero Varchar (20) NOT NULL
);

create table Assistir(
cod_filme Varchar (5) foreign key references Filme NOT NULL,
Cod_cliente Varchar (5) foreign key references Cliente NOT NULL,
Data_asiste date NOT NULL
);

create table Cliente(
cod_cliente Varchar (5) PRIMARY KEY NOT NULL,
Nome Varchar (50) NOT NULL,
Data_nasc Date NOT NULL,
RG Varchar (20) NOT NULL,
CPF Varchar (15) NOT NULL
);

create table Compra(
cod_cliente Varchar (5) foreign key references Cliente NOT NULL,
cod_produto Varchar (5) foreign key references Produto NOT NULL,
Comprovante Varchar(10) NOT NULL
);

create table Produto(
cod_produto VarChar(5) PRIMARY KEY,
nome_produto VarChar(10) NOT NULL, 
quant_produtos INT NOT NULL,
desc_produto VarChar(200) NOT NULL
);
