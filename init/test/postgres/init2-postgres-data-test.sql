
INSERT INTO users
(id, "role", email, firstname, lastname, "password")
VALUES (1, 2, 'admin@test.com', 'José', 'Duseaux', '$2a$10$LCWL7MWZWdt8ISLeLAjeG.0UYx4uFDVFOb.5h3ZG0QJUjGi8Z2cGS'),
       (2, 1, 'veto@test.com', 'Alfred', 'Levetaux', '$2a$10$LCWL7MWZWdt8ISLeLAjeG.0UYx4uFDVFOb.5h3ZG0QJUjGi8Z2cGS'),
       (3, 0, 'employee@test.com', 'Juliette', 'Dupont', '$2a$10$LCWL7MWZWdt8ISLeLAjeG.0UYx4uFDVFOb.5h3ZG0QJUjGi8Z2cGS');

INSERT INTO food
(id, name)
VALUES (1, 'Foin'), 
       (2, 'Bananes'), 
       (3, 'Poulet'), 
       (4, 'Cacahuètes'), 
       (5, 'Herbes');

INSERT INTO breed
(id, label)
VALUES (1, 'Gazelle'), 
       (2, 'Girafe'), 
       (3, 'Crocodile'), 
       (4, 'Singe'), 
       (5, 'Eléphant'), 
       (6, 'Perroquet');

INSERT INTO service
(id, name, description)
VALUES (1, 'Reataurant', '16 lieux de restauration vous accueillent au Zoo Arcadia. Self-service, crêperie, restauration rapide… Il y en a pour tous les goûts !'), 
       (2, 'Petit train', E'Partez à l’aventure sur la piste des animaux du monde le temps d\’une expédition d\’une heure en safari-train. Embarquement immédiat !'), 
       (3, 'Visite guidée', 'Ce parcours sauvage vous emmène au cœur des grands espaces et vous promet des rencontres animalières uniques.');

INSERT INTO habitat
(id, name, description, comment)
VALUES (1, 'Savane', 'Animaux de la savane', ''), 
       (2, 'Jungle', 'Animaux de la jungle', ''), 
       (3, 'Marais', 'Animaux des marais', '');

INSERT INTO hours
(id, monday, tuesday, wednesday, thursday, friday, saturday, sunday)
VALUES (1, '08h00 - 22-00', '08h00 - 22-00', '08h00 - 22-00', '08h00 - 22-00', '08h00 - 22-00', '08h00 - 22-00', '08h00 - 22-00');

INSERT INTO animal
(id, firstname, health, description, breed_id, habitat_id)
VALUES (1, 'Flash', 'Bon état de santé', 'Animal rapide de la savane', 1, 1), 
       (2, 'Jumbo', 'Bon état de santé', 'Animal imposant de la savane', 5, 1), 
       (3, 'Coco', 'Bon état de santé', 'Animal coloré de la jungle', 6, 2), 
       (4, 'Gigi', 'Bon état de santé', 'Animal trés haut de la savane', 2, 1),
       (5, 'Croco', 'Bon état de santé', 'Animal dangereux des marais', 3, 3) 
       ;

INSERT INTO food_animal
(gramage, date_food, food_id, animal_id)
VALUES (5500, NOW() - interval '3 day', 1, 2), 
       (5500, NOW() - interval '2 day', 1, 2),
       (5500, NOW() - interval '1 day', 1, 2),
       (4500, NOW(), 1, 2),
       (500, NOW(), 4, 3),
       (1500, NOW(), 3, 5)
       ;

INSERT INTO veterinary_report
(gramage, detail, date, food_id, animal_id, user_id)
VALUES (5000, 'Anémie, augmentation du régime', NOW() - interval '21 day', 1, 2, 2), 
       (5500, E'Encore augmenter les portions d\'un dixième', NOW() - interval '14 day', 1, 2, 2),
       (5500, 'Bon état de santé, maintient du régime', NOW() - interval '7 day', 1, 2, 2),
       (4500, 'Poids stabilisé, retour aux portions initiales', NOW(), 1, 2, 2),
       (500, 'Bon état de santé', NOW(), 4, 3, 2),
       (1500, 'Régime sans sel', NOW(), 3, 5, 2)
       ;

INSERT INTO image
(id, hash, image_name)
VALUES (1, '', 'animal_a0d8ddb54d182a83a713390f08ca6ec0.webp'),
       (2, '', 'habitat_20b648b11d33ea33108e8f93f64436d1.webp'),
       (3, '', 'habitat_17879e6bdb906bc35393b2baf0ffa4bf.webp'),
       (4, '', 'habitat_3ee8c08eda8007dd10468768d8c10a91.webp'),
       (5, '', 'animal_fd59057b67706f2cc3f9e4d0a1fdab9e.webp'),
       (6, '', 'animal_d47620b2181d04dddc29cb763188c461.webp'),
       (7, '', 'animal_7f361047fa5a1fee56c04265c3faf271.webp'),
       (8, '', 'animal_3d3b412698d15066d7b5e7933bcf2467.webp'),
       (9, '', 'animal_c3574c6e3c7416674f025e2f6f702d50.webp'),
       (10, '', 'animal_8dfa0c0eb2d3f06a68a6ed6344556772.webp')
       ;

INSERT INTO animal_image
(animal_id, image_id)
VALUES (1, 1),
       (2, 6),
       (4, 7),
       (4, 8),
       (3, 5),
       (5, 9),
       (5, 10)
       ;

INSERT INTO habitat_image
(habitat_id, image_id)
VALUES (1, 2),
       (2, 3),
       (3, 4)
       ;

