insert into Users (id, login, password, email) values (-1, 'osadmin', 'osadmin', 'osadmin@gmail.com');
insert into Users (id, login, password, email) values (-2, 'ossystem', 'ossystem', 'ossystem@gmail.com');

insert into Category (id, name) values (-1, 'Kategoria glowna');
insert into Category (id, name) values (-2, 'Kategoria glowna 2');

insert into Article (id, title, content, creation_date, user_id, category_id) values (-1, 'Article 1 title', 'Article 1 content',NOW(), -1,-1);
insert into Article (id, title, content, creation_date, user_id, category_id) values (-2, 'Article 2 title', 'Article 1 content',NOW(), -1,-1);
insert into Article (id, title, content, creation_date, user_id, category_id) values (-3, 'Article 3 title', 'Article 1 content',NOW(), -1,-1);

