create table tb_category (
    created_at TIMESTAMP WITHOUT TIME ZONE,
    deleted_at TIMESTAMP WITHOUT TIME ZONE,
    id bigint not null,
    name varchar(255) not null unique,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    primary key (id)
);