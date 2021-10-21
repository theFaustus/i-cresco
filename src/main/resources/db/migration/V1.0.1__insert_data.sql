INSERT INTO public.users (id, created_date, last_modified_date, email_address, enabled, first_name, gender, last_name,
                          password, username)
VALUES ('f3379620-9a83-4d1f-aee4-746046ce3a65', '2021-10-20 22:44:00.891779', '2021-10-20 22:44:00.891779',
        'bdylan@gmail.com', true, 'Bob', 'MALE', 'Dylan',
        '$2a$10$WhVZgha9ervL9qk45rCfE.QJidJgDbE6H4lSCfQV9kM9ISqWGyCki', 'bdylan');
INSERT INTO public.users (id, created_date, last_modified_date, email_address, enabled, first_name, gender, last_name,
                          password, username)
VALUES ('1e733974-f67d-434b-a4da-df6bc9458b72', '2021-10-20 23:22:30.574212', '2021-10-20 23:22:30.574212',
        'fmercury@evil-inc.com', true, 'Freddie', 'MALE', 'Mercury',
        '$2a$10$WhVZgha9ervL9qk45rCfE.QJidJgDbE6H4lSCfQV9kM9ISqWGyCki', 'fmercury');
INSERT INTO public.users (id, created_date, last_modified_date, email_address, enabled, first_name, gender, last_name,
                          password, username)
VALUES ('dd38a713-7ebd-46b8-a513-e161ca998b4a', '2021-10-20 23:25:05.376674', '2021-10-20 23:25:05.376674',
        'jmorrison@gmail.com', true, 'Jim', 'MALE', 'Morrison',
        '$2a$10$WhVZgha9ervL9qk45rCfE.QJidJgDbE6H4lSCfQV9kM9ISqWGyCki','jmorrison');
INSERT INTO public.users (id, created_date, last_modified_date, email_address, enabled, first_name, gender, last_name,
                          password, username)
VALUES ('aca009c1-28a5-4897-8648-b26cc9de06df', '2021-10-20 23:29:11.357230', '2021-10-20 23:29:11.357230',
        'kcobain@gmail.com', true, 'Kurt', 'MALE', 'Cobain',
        '$2a$10$WhVZgha9ervL9qk45rCfE.QJidJgDbE6H4lSCfQV9kM9ISqWGyCki', 'kcobain');
INSERT INTO public.users (id, created_date, last_modified_date, email_address, enabled, first_name, gender, last_name,
                          password, username)
VALUES ('c96cc113-45e3-4a09-9884-318536169a4b', '2021-10-20 23:32:16.504954', '2021-10-20 23:32:16.504954',
        'epresley@gmail.com', true, 'Elvis', 'MALE', 'Presley',
        '$2a$10$WhVZgha9ervL9qk45rCfE.QJidJgDbE6H4lSCfQV9kM9ISqWGyCki', 'epresley');

INSERT INTO public.user_authorities (id, created_date, last_modified_date, authority, user_id)
VALUES ('37141c0a-1696-4530-93ca-9a1262dd0e36', '2021-10-20 22:44:00.928850', '2021-10-20 22:44:00.928850',
        'POWER_USER', 'f3379620-9a83-4d1f-aee4-746046ce3a65');
INSERT INTO public.user_authorities (id, created_date, last_modified_date, authority, user_id)
VALUES ('f308bb8b-d1f9-4eb4-a205-b86cd1785dab', '2021-10-20 23:22:30.574212', '2021-10-20 23:22:30.574212',
        'SIMPLE_USER', '1e733974-f67d-434b-a4da-df6bc9458b72');
INSERT INTO public.user_authorities (id, created_date, last_modified_date, authority, user_id)
VALUES ('0a015414-f5e7-471b-88ff-5eb2c7f99def', '2021-10-20 23:25:05.377680', '2021-10-20 23:25:05.377680',
        'SIMPLE_USER', 'dd38a713-7ebd-46b8-a513-e161ca998b4a');
INSERT INTO public.user_authorities (id, created_date, last_modified_date, authority, user_id)
VALUES ('6fc9a74e-da29-4972-afbc-3932ec68bcc7', '2021-10-20 23:29:11.358236', '2021-10-20 23:29:11.358236',
        'SIMPLE_USER', 'aca009c1-28a5-4897-8648-b26cc9de06df');
INSERT INTO public.user_authorities (id, created_date, last_modified_date, authority, user_id)
VALUES ('de68ee27-2e6f-4b99-8ced-cac2e98c9696', '2021-10-20 23:32:16.505913', '2021-10-20 23:32:16.505913',
        'POWER_USER', 'c96cc113-45e3-4a09-9884-318536169a4b');