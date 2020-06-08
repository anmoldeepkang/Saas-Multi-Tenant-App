create table users (
	id varchar(255) primary key,
    email varchar(255),
    username varchar(255),
    password varchar(255),
    created_at timestamp,
    updated_at timestamp,
    provider varchar(255),
    constraint UC_TENANT_NAME unique (email)
);