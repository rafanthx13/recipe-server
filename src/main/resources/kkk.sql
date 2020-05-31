SELECT b.id, b.tag
FROM recipe r
LEFT JOIN recipe_badge rb ON r.id = rb.recipe_id
LEFT JOIN badge b ON b.id = rb.badge_id
WHERE r.id = 2;

CREATE TABLE `badge` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tag` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`tag`)
) 

CREATE TABLE `recipe_badge` (
  `id` int NOT NULL AUTO_INCREMENT,
  `recipe_id` int NOT NULL,
  `badge_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_recipe_badge_1_idx` (`recipe_id`),
  KEY `fk_recipe_badge_2_idx` (`badge_id`),
  CONSTRAINT `fk_recipe_badge_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_recipe_badge_2` FOREIGN KEY (`badge_id`) REFERENCES `badge` (`id`) ON UPDATE CASCADE
)