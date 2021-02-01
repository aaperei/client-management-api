DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS client_address;

CREATE TABLE client_address(
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  street VARCHAR(255) NOT NULL,
  street_number INT NOT NULL,
  city VARCHAR(100) NOT NULL,
  neighborhood VARCHAR(100) NOT NULL,
  zip_code INT NOT NULL,
  country VARCHAR(100) NOT NULL,
  created DATE NOT NULL,
  updated DATE NOT NULL
);

CREATE TABLE client(
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  cpf BIGINT NOT NULL,
  name VARCHAR(50) NOT NULL,
  birth_date DATE NOT NULL,
  address_id INT NULL,
  email VARCHAR(50) NOT NULL,
  created DATE NOT NULL,
  updated DATE NOT NULL,

  CONSTRAINT FK_CLIENT_ADDRESS FOREIGN KEY (address_id) REFERENCES client_address(id)
);


INSERT INTO client_address (street, street_number, city, neighborhood, zip_code, country, created, updated) VALUES
	('Rua dos Amores', 1000, 'Paraíso', 'Bosque', 4641616, 'BRASIL', SYSDATE, SYSDATE),
	('Rua dos Amores', 1000, 'Paraíso', 'Bosque', 4641616, 'BRASIL', SYSDATE, SYSDATE),
	('Rua dos Amores', 1000, 'Paraíso', 'Bosque', 4641616, 'BRASIL', SYSDATE, SYSDATE),
	('Rua dos Amores', 1000, 'Paraíso', 'Bosque', 4641616, 'BRASIL', SYSDATE, SYSDATE),
	('Rua dos Amores', 1000, 'Paraíso', 'Bosque', 4641616, 'BRASIL', SYSDATE, SYSDATE),
	('Rua dos Amores', 1000, 'Paraíso', 'Bosque', 4641616, 'BRASIL', SYSDATE, SYSDATE),
	('Rua dos Amores', 1000, 'Paraíso', 'Bosque', 4641616, 'BRASIL', SYSDATE, SYSDATE);

INSERT INTO client (cpf, name, birth_date, address_id, email, created, updated) VALUES
	(16559815900, 'JOSE GERALDO NETO', '1990-02-03', 1, 'test@test.com.br', SYSDATE, SYSDATE),
	(51845648161, 'AFONSO VIEIRA DA GLORIA', '1995-05-03', 2, 'test@test.com.br', SYSDATE, SYSDATE),
	(00254198223, 'MARIA ANTONIETA DE MELO', '1993-07-30', 3, 'test@test.com.br', SYSDATE, SYSDATE),
	(45616511343, 'GILSON MOTA', '1889-02-03', 4, 'test@test.com.br', SYSDATE, SYSDATE),
	(98731987414, 'HELENA RODRIGUES', '1989-06-03', 5, 'test@test.com.br', SYSDATE, SYSDATE),
	(45164767671, 'JORGE MELO', '1990-02-20', 6, 'test@test.com.br', SYSDATE, SYSDATE),
	(79191439043, 'RITA ANDRADE', '1991-02-03', 7, 'test@test.com.br', SYSDATE, SYSDATE);
