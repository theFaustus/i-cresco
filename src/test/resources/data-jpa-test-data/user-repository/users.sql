DELETE FROM USER_AUTHORITIES;
DELETE FROM USERS;

INSERT INTO users (id, created_date, last_modified_date, email_address, enabled, first_name, gender, last_name,
                   password, username)
VALUES ('-4', '2021-10-20 23:32:16.504954', '2021-10-20 23:32:16.504954',
        'etestley@gmail.com', true, 'Elvis', 'MALE', 'Testley',
        '$2a$10$WhVZgha9ervL9qk45rCfE.QJidJgDbE6H4lSCfQV9kM9ISqWGyCki', 'etestley');
INSERT INTO user_authorities (id, created_date, last_modified_date, authority, user_id)
VALUES ('-4', '2021-10-20 23:32:16.505913', '2021-10-20 23:32:16.505913',
        'POWER_USER', '-4');
