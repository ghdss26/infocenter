-- comentários 
-- criando um banco de dados

CREATE DATABASE infocenter; 

-- escolhendo o banco de dados 

USE infocenter;

-- o bloco de instruções abaixo cria uma tabela

CREATE TABLE usuarios(

	iduser int PRIMARY KEY,
    usuario VARCHAR (50) NOT NULL, 
    fone VARCHAR(15), 
    login VARCHAR(15) NOT NULL UNIQUE, 
    senha VARCHAR(15) NOT NULL
);
-- O comando abaixo descreve a tabela 
describe usuarios;

-- a linha abaixo insere dados na tabela (CRUD) 
-- criar dados 

INSERT INTO usuarios(iduser, usuario, fone, login, senha) 
VALUES(1, 'Gustavo', '9999-9999', 'gustavohenrique', '123456'); 

INSERT INTO usuarios(iduser, usuario, fone, login, senha) 
VALUES(2, 'Administrador', '9999-9999', 'admin', 'admin'); 

INSERT INTO usuarios(iduser, usuario, fone, login, senha) 
VALUES(3, 'Souza', '9999-9999', 'souza@henrique', '123456'); 

-- a linha abaixo exibe os dados da tabela (CRUD) 

SELECT * FROM usuarios;

-- a linha abaixo modifica os dados na tabela (CRUD) 
-- update -> update 

UPDATE usuarios set fone = '8888-8888' WHERE iduser = 2; 

-- a linha abaixo apaga um registro da tabela (CRUD) 
-- delete -> delete 

DELETE FROM usuarios WHERE iduser=3; 

SELECT * FROM usuarios;

CREATE TABLE clientes( 
	
    idcli int PRIMARY KEY auto_increment, 
    nomecli VARCHAR(50) NOT NULL, 
    endcli VARCHAR(100), 
    fonecli VARCHAR(50) NOT NULL, 
    emailcli VARCHAR(50)
); 

describe clientes; 

INSERT INTO clientes(nomecli, endcli, fonecli, emailcli) 
VALUES('Doni Henrique', 'Rua Eurico de Souza Leão, 456', '9999-9999', 'doni@gmail.com');  

SELECT * FROM clientes;

CREATE TABLE os(
	
    os INT PRIMARY KEY AUTO_INCREMENT, 
    dataos timestamp DEFAULT CURRENT_TIMESTAMP, 
    equipamento VARCHAR(150) NOT NULL, 
    defeito VARCHAR(150) NOT NULL, 
    servico VARCHAR(150),
    tecnico VARCHAR(30), 
    valor decimal(10, 2), 
    idcli INT NOT NULL, 
    FOREIGN KEY(idcli) REFERENCES clientes(idcli)
); 

describe os;

INSERT INTO os(equipamento, defeito, servico, tecnico, valor, idcli) 
VALUES('Notebook', 'Não Liga', 'Troca da fonte', 'Washigton', 87.50, 1);

SELECT * FROM os;

-- o código abaixo traz informações de duas tabelas 
SELECT O.os, equipamento, defeito, servico, valor, 
C.nomecli, fonecli FROM os AS O 
INNER JOIN clientes AS C ON (O.idcli = C.idcli); 