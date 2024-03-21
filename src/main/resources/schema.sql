-- 各種テーブル削除
DROP VIEW IF EXISTS v_order_details;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS order_details;
DROP TABLE IF EXISTS users;

-- カテゴリーテーブル
CREATE TABLE categories
(
   id SERIAL PRIMARY KEY,
   name TEXT
);
-- 商品テーブル
CREATE TABLE items
(
   id SERIAL PRIMARY KEY,
   category_id INTEGER,
   name TEXT,
   price INTEGER
);
-- 顧客テーブル
CREATE TABLE customers
(
   id SERIAL PRIMARY KEY,
   username varchar(256),
   password varchar(256),
   email varchar(256) UNIQUE,
   name TEXT,
   birthday DATE,
   address TEXT,
   tel TEXT
);
-- 注文テーブル
CREATE TABLE orders
(
   id SERIAL PRIMARY KEY,
   customer_id INTEGER,
   ordered_on DATE,
   total_price INTEGER
   
);
-- 注文明細テーブル
CREATE TABLE order_details
(
   id SERIAL PRIMARY KEY,
   order_id INTEGER,
   item_id INTEGER,
   quantity INTEGER
);
-- 注文明細ビュー
CREATE VIEW v_order_details AS
(
   SELECT
      d.id,
      d.order_id,
      d.item_id,
      d.quantity,
      i.name,
      i.price
   FROM order_details d
   JOIN items i
      ON d.item_id = i.id
);

-- ユーザーテーブル
CREATE TABLE users
(
   id SERIAL PRIMARY KEY,
   name TEXT,
   password TEXT,
   email TEXT
);