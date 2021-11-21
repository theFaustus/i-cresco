INSERT INTO users (id, created_date, last_modified_date, email_address, enabled, first_name, gender, last_name,
                   password, username)
VALUES ('-1', '2021-10-20 22:44:00.891779', '2021-10-20 22:44:00.891779',
        'bdtest@gmail.com', true, 'Bob', 'MALE', 'Dtest',
        '$2a$10$WhVZgha9ervL9qk45rCfE.QJidJgDbE6H4lSCfQV9kM9ISqWGyCki', 'bdtest');
INSERT INTO users (id, created_date, last_modified_date, email_address, enabled, first_name, gender, last_name,
                   password, username)
VALUES ('-2', '2021-10-20 23:22:30.574212', '2021-10-20 23:22:30.574212',
        'fmtest@evil-inc.com', true, 'Freddie', 'MALE', 'Mtest',
        '$2a$10$WhVZgha9ervL9qk45rCfE.QJidJgDbE6H4lSCfQV9kM9ISqWGyCki', 'fmtest');
INSERT INTO users (id, created_date, last_modified_date, email_address, enabled, first_name, gender, last_name,
                   password, username)
VALUES ('-3', '2021-10-20 23:25:05.376674', '2021-10-20 23:25:05.376674',
        'jmtest@gmail.com', true, 'Jim', 'MALE', 'Mtest',
        '$2a$10$WhVZgha9ervL9qk45rCfE.QJidJgDbE6H4lSCfQV9kM9ISqWGyCki','jmtest');

INSERT INTO user_authorities (id, created_date, last_modified_date, authority, user_id)
VALUES ('-1', '2021-10-20 22:44:00.928850', '2021-10-20 22:44:00.928850',
        'POWER_USER', '-1');
INSERT INTO user_authorities (id, created_date, last_modified_date, authority, user_id)
VALUES ('-2', '2021-10-20 23:22:30.574212', '2021-10-20 23:22:30.574212',
        'SIMPLE_USER', '-2');
INSERT INTO user_authorities (id, created_date, last_modified_date, authority, user_id)
VALUES ('-3', '2021-10-20 23:25:05.377680', '2021-10-20 23:25:05.377680',
        'SIMPLE_USER', '-3');
