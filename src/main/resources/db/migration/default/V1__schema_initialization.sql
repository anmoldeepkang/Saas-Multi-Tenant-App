create table tenants (
    tenant_id varchar(255) primary key,
    tenant_name varchar(255),
    username varchar(255),
    password varchar(255),
    created_at timestamp,
    updated_at timestamp,
    constraint UC_TENANT_NAME unique (username)
);

create table roles(
	username varchar(255),
    authorities varchar(255)
);

INSERT INTO tenants(tenant_id,tenant_name,username,password)
VALUES ('48fd125b-126c-44d8-a65c-cebf3606f5a2','anmoldeep', 'anmoldeep','$2a$10$/ns.CwZ9sdhQaVjw/bwBQeelnmTZTI19trLtyY/bjbIVUokAckX8y');

INSERT INTO roles(username,authorities)
VALUES ('anmoldeep','ROLE_USER');

INSERT INTO roles(username,authorities)
VALUES ('anmoldeep','ROLE_ADMIN');