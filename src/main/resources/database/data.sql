INSERT INTO role (name, description) VALUES
    ('ADMIN', 'Administrateur de la plateforme'),
    ('USAGER', 'Utilisateur public');

INSERT INTO adresse (rue, ville, code_postal) VALUES
    ('12 rue de Paris', 'Abidjan', '01001');

INSERT INTO telephone (indicatif, numero) VALUES
    ('+225', '0102030405');

INSERT INTO contact (telephone_id, email, adresse_id) VALUES
    ((SELECT id FROM telephone WHERE numero = '0102030405'),
     'admin@gestion-compte.local',
     (SELECT id FROM adresse WHERE rue = '12 rue de Paris'));

INSERT INTO compte (nom, application, status, contact_id) VALUES
    ('Compte principal', 'gestion-compte', 'ACTIF',
     (SELECT id FROM contact WHERE email = 'admin@gestion-compte.local'));

INSERT INTO utilisateur (email, first_name, last_name, mot_de_passe, active, compte_id, administrateur) VALUES
    ('test@gestion-compte.com', 'Prenom', 'Nom', 'passWord123', TRUE,
     (SELECT id FROM compte WHERE nom = 'Compte principal'), TRUE);

INSERT INTO utilisateur_role (utilisateur_id, role_id) VALUES
    ((SELECT id FROM utilisateur WHERE email = 'test@gestion-compte.com'),
     (SELECT id FROM role WHERE name = 'ADMIN')),
    ((SELECT id FROM utilisateur WHERE email = 'test@gestion-compte.com'),
     (SELECT id FROM role WHERE name = 'USAGER'));
