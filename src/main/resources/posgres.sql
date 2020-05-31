-- mysql
-- Akashi4die_1301

SELECT b.tag
FROM recipe r
LEFT JOIN recipe_badge rb ON r.id = rb.recipe_id
LEFT JOIN badge b ON b.id = rb.badge_id
WHERE r.id = 2;



CREATE TABLE `recipe_app`.`recipe` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NULL,
  `img_src` INT NULL,
  `badges` INT NULL,
  `ingredients` VARCHAR(1000) NULL,
  `tab_prepare` VARCHAR(1000) NULL,
  `tab_comments` VARCHAR(1000) NULL,
  `tab_others` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC));


CREATE TABLE `recipe_app`.`badge` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));


CREATE TABLE `recipe_app`.`image` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fileName` VARCHAR(90) NOT NULL,
  `fileType` VARCHAR(45) NULL,
  `data` BLOB NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`fileName` ASC));


CREATE TABLE `recipe_app`.`recipe_badge` (
  `id` INT NOT NULL,
  `recipe_id` INT NOT NULL,
  `badge_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_recipe_badge_1_idx` (`recipe_id` ASC),
  INDEX `fk_recipe_badge_2_idx` (`badge_id` ASC),
  CONSTRAINT `fk_recipe_badge_1`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `recipe_app`.`recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_recipe_badge_2`
    FOREIGN KEY (`badge_id`)
    REFERENCES `recipe_app`.`badge` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);



