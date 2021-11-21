create table users
(
    id varchar(255) not null
        constraint users_pkey
            primary key,
    created_date timestamp,
    last_modified_date timestamp,
    version bigint,
    email_address varchar(255) not null,
    enabled boolean not null,
    first_name varchar(255) not null,
    gender varchar(255) not null,
    last_name varchar(255) not null,
    password varchar(255) not null,
    username varchar(255) not null
        constraint uk_r43af9ap4edm43mmtq01oddj6
            unique
        constraint users_username
            unique
);

create table growth_plans
(
    id varchar(255) not null
        constraint growth_plans_pkey
            primary key,
    created_date timestamp,
    last_modified_date timestamp,
    version bigint,
    description varchar(3000) not null,
    title varchar(255) not null,
    user_id varchar(255)
        constraint fkd1aye97k8skjo57mdd7pfowc6
            references users
);

create table article_records
(
    id varchar(255) not null
        constraint article_records_pkey
            primary key,
    created_date timestamp,
    last_modified_date timestamp,
    version bigint,
    description varchar(3000) not null,
    title varchar(255) not null,
    url varchar(255) not null,
    growth_plan_id varchar(255)
        constraint fkrxah51wwvqrbxtjs6ny8u2c9x
            references growth_plans
);

create table book_records
(
    id varchar(255) not null
        constraint book_records_pkey
            primary key,
    created_date timestamp,
    last_modified_date timestamp,
    version bigint,
    author varchar(255) not null,
    description varchar(3000) not null,
    title varchar(255) not null,
    growth_plan_id varchar(255)
        constraint fka5dt20t4eimjlq6myjhx5yeem
            references growth_plans,
    page_count integer,
    thumbnail varchar(255)
);

create table course_records
(
    id varchar(255) not null
        constraint course_records_pkey
            primary key,
    created_date timestamp,
    last_modified_date timestamp,
    version bigint,
    description varchar(3000) not null,
    title varchar(255) not null,
    url varchar(255) not null,
    growth_plan_id varchar(255)
        constraint fk8b7peai5qsl3x0cew2enmpxv1
            references growth_plans
);

create table exercise_records
(
    id varchar(255) not null
        constraint exercise_records_pkey
            primary key,
    created_date timestamp,
    last_modified_date timestamp,
    version bigint,
    calories double precision not null,
    duration bigint not null,
    exercise_type varchar(255) not null,
    growth_plan_id varchar(255)
        constraint fkk8fkx6s2eubfhk45mnbjnuv9t
            references growth_plans
);

create table presentation_records
(
    id varchar(255) not null
        constraint presentation_records_pkey
            primary key,
    created_date timestamp,
    last_modified_date timestamp,
    version bigint,
    description varchar(3000) not null,
    title varchar(255) not null,
    url varchar(255) not null,
    growth_plan_id varchar(255)
        constraint fkgyeigqgo328xfuqed409agbd3
            references growth_plans
);

create table sleep_records
(
    id varchar(255) not null
        constraint sleep_records_pkey
            primary key,
    created_date timestamp,
    last_modified_date timestamp,
    version bigint,
    amount_of_sleep bigint not null,
    growth_plan_id varchar(255)
        constraint fk9j9ya7phw56o3yn5e4h0jb697
            references growth_plans
);

create table user_authorities
(
    id varchar(255) not null
        constraint user_authorities_pkey
            primary key,
    created_date timestamp,
    last_modified_date timestamp,
    version bigint,
    authority varchar(255) not null,
    user_id varchar(255) not null
        constraint fkhiiib540jf74gksgb87oofni
            references users
);

create table water_intake_records
(
    id varchar(255) not null
        constraint water_intake_records_pkey
            primary key,
    created_date timestamp,
    last_modified_date timestamp,
    version bigint,
    number_of_glasses double precision not null,
    growth_plan_id varchar(255)
        constraint fkqgysbp8ulrkl43uodo75cdj0n
            references growth_plans
);

create table weight_records
(
    id varchar(255) not null
        constraint weight_records_pkey
            primary key,
    created_date timestamp,
    last_modified_date timestamp,
    version bigint,
    value double precision not null,
    growth_plan_id varchar(255)
        constraint fkaq0ygqppfbqy974ywkvfifsw1
            references growth_plans
);
