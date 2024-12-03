create table tb_product (
    price float(53) not null,
    category_id bigint not null,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    deleted_at TIMESTAMP WITHOUT TIME ZONE,
    description TEXT not null,
    id bigint not null,
    img_url varchar(255) not null,
    name varchar(255) not null,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    primary key (id)
);
