DROP TABLE IF EXISTS utilisateur_role;
DROP TABLE IF EXISTS utilisateur;
DROP TABLE IF EXISTS compte;
DROP TABLE IF EXISTS contact;
DROP TABLE IF EXISTS telephone;
DROP TABLE IF EXISTS adresse;
DROP TABLE IF EXISTS role;

CREATE TABLE role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE adresse (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rue VARCHAR(255),
    ville VARCHAR(100),
    code_postal VARCHAR(20)
);

CREATE TABLE telephone (
    id INT AUTO_INCREMENT PRIMARY KEY,
    indicatif VARCHAR(10),
    numero VARCHAR(30)
);

CREATE TABLE contact (
    id INT AUTO_INCREMENT PRIMARY KEY,
    telephone_id INT,
    email VARCHAR(255),
    adresse_id INT,
    CONSTRAINT fk_contact_telephone FOREIGN KEY (telephone_id) REFERENCES telephone (id),
    CONSTRAINT fk_contact_adresse FOREIGN KEY (adresse_id) REFERENCES adresse (id)
);

CREATE TABLE compte (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    application VARCHAR(255),
    status VARCHAR(50),
    contact_id INT,
    CONSTRAINT fk_compte_contact FOREIGN KEY (contact_id) REFERENCES contact (id)
);

CREATE TABLE utilisateur (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    mot_de_passe VARCHAR(255),
    active BOOLEAN,
    compte_id INT,
    administrateur BOOLEAN,
    CONSTRAINT fk_utilisateur_compte FOREIGN KEY (compte_id) REFERENCES compte (id)
);

CREATE TABLE utilisateur_role (
    utilisateur_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (utilisateur_id, role_id),
    CONSTRAINT fk_utilisateur_role_utilisateur FOREIGN KEY (utilisateur_id) REFERENCES utilisateur (id),
    CONSTRAINT fk_utilisateur_role_role FOREIGN KEY (role_id) REFERENCES role (id)
);
