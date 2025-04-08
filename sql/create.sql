-- Création de la base de données
CREATE DATABASE examen;
USE examen;

CREATE table user(
    id INT  AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    password VARCHAR(255) 
);

CREATE table credit(
    idcredit INT PRIMARY KEY AUTO_INCREMENT,
    libelle varchar(50),
    montant int
);

CREATE table depense(
    iddepense INT PRIMARY KEY AUTO_INCREMENT,
    idcredit int,
    montant int,
    foreign key (idcredit) references credit(idcredit)
);

insert into user(name,password) values('admin','admin');
insert into user(name,password) values('user','user');
