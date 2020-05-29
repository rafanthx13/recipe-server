--CREATE TABLE recipe (
--    id INTEGER PRIMARY KEY AUTO_INCREMENT,
--    title VARCHAR(100),
--    img_src VARCHAR(200),
--    badges VARCHAR(200),
--    ingredients VARCHAR(200),
--    tab_prepare VARCHAR(200),
--    tab_comments VARCHAR(200),
--    tab_others VARCHAR(200)
--);

-- https://walkingtechie.blogspot.com/2018/12/execute-schema-and-data-sql-on-startup-spring-boot.html
-- https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto-database-initialization

insert into recipe (id, title , badges , img_src , ingredients , tab_prepare , tab_comments , tab_others )
values (1, 'titulo1', 'badges', 'https://cdn.quasar.dev/img/mountains.jpg', '9854y33', 'gd9uig09df', 'sdhfuihfi', 'gdsgisjos');

--insert into recipe (id, title , badges , img_src , ingredients , tab_prepare , tab_comments , tab_others )
--values (2, 'titulo2', 'badges', 'https://cdn.quasar.dev/img/mountains.jpg', '9854y33', 'gd9uig09df', 'sdhfuihfi', 'gdsgisjos');
--
--insert into recipe (id, title , badges , img_src , ingredients , tab_prepare , tab_comments , tab_others )
--values (3, 'titulo3', 'badges', 'https://cdn.quasar.dev/img/mountains.jpg', '9854y33', 'gd9uig09df', 'sdhfuihfi', 'gdsgisjos');
--
--insert into recipe (id, title , badges , img_src , ingredients , tab_prepare , tab_comments , tab_others )
--values (4, 'titulo4', 'badges', 'https://cdn.quasar.dev/img/mountains.jpg', '9854y33', 'gd9uig09df', 'sdhfuihfi', 'gdsgisjos');



-- insert into recipe (id, title , badges , img_src , ingredients , tab_prepare , tab_comments , tab_others )
--   values (1, 'titulo', 'badges', 'https://cdn.quasar.dev/img/mountains.jpg', '9854y33', 'gd9uig09df', 'sdhfuihfi', 'gdsgisjos');

-- CREATE TABLE PRODUTO (
--     ID INTEGER PRIMARY KEY AUTO_INCREMENT,
--     DESCRICAO VARCHAR(100),
--     PRECO_UNITARIO NUMERIC(20,2)
-- );

-- CREATE TABLE PEDIDO (
--     ID INTEGER PRIMARY KEY AUTO_INCREMENT,
--     CLIENTE_ID INTEGER REFERENCES CLIENTE (ID),
--     DATA_PEDIDO TIMESTAMP,
--     STATUS VARCHAR(20)
--     TOTAL NUMERIC(20,2)
-- );

-- CREATE TABLE ITEM_PEDIDO (
--     ID INTEGER PRIMARY KEY AUTO_INCREMENT,
--     PEDIDO_ID INTEGER REFERENCES PEDIDO (ID),
--     PRODUTO_ID INTEGER REFERENCES PRODUTO (ID),
--     QUANTIDADE INTEGER
-- );

--  INSERT INTO billionaires (first_name, last_name, career) VALUES
--      ('Aliko', 'Dangote', 'Billionaire Industrialist'),
--      ('Bill', 'Gates', 'Billionaire Tech Entrepreneur'),
--      ('Folrunsho', 'Alakija', 'Billionaire Oil Magnate');
