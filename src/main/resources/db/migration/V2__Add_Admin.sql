insert into users (id, username, password, active)
    values (1, 'admin', '$2a$08$HAdC/uBneEZ4tWjTMol7oOlXWgMoN.A.yyvk3B0zcT6Q6Y9UW0cKu', true);

insert into user_role (user_id, roles)
    values (1,'USER'), (1, 'ADMIN');