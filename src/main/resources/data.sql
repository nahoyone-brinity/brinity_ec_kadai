INSERT INTO categories(name) VALUES('本');
INSERT INTO categories(name) VALUES('DVD');
INSERT INTO categories(name) VALUES('ゲーム');

INSERT INTO items(category_id, name, price) VALUES(1, 'Javaの基本', 2500);
INSERT INTO items(category_id, name, price) VALUES(1, 'MLB Fun', 980);
INSERT INTO items(category_id, name, price) VALUES(1, '料理BOOK!', 1200);

INSERT INTO items(category_id, name, price) VALUES(2, 'なつかしのアニメシリーズ', 2000);
INSERT INTO items(category_id, name, price) VALUES(2, 'The Racer', 1000);
INSERT INTO items(category_id, name, price) VALUES(2, 'Space Wars 3', 1800);

INSERT INTO items(category_id, name, price) VALUES(3, 'パズルゲーム', 780);
INSERT INTO items(category_id, name, price) VALUES(3, 'Invader Fighter', 3400);
INSERT INTO items(category_id, name, price) VALUES(3, 'Play the BascketBall', 2200);

-- 注文で発生するテストデータの追加
INSERT INTO customers(name, address, tel, email) VALUES('田中太郎', '東京', '090-1111-1111', 'tanaka@aaa.com');
INSERT INTO orders(customer_id, ordered_on, total_price) VALUES(1, '2020-04-10', 5980);
INSERT INTO order_details(order_id, item_id, quantity) VALUES(1, 1, 2);
INSERT INTO order_details(order_id, item_id, quantity) VALUES(1, 2, 1);

INSERT INTO customers(name, address, tel, email) VALUES('鈴木一郎', '大阪', '090-2222-2222', 'suzuki@aaa.com');
INSERT INTO orders(customer_id, ordered_on, total_price) VALUES(2, '2020-04-11', 2500);
INSERT INTO order_details(order_id, item_id, quantity) VALUES(2, 1, 1);