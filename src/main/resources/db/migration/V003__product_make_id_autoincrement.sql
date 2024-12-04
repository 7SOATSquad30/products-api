ALTER TABLE tb_product DROP CONSTRAINT IF EXISTS tb_product_pkey;

CREATE SEQUENCE tb_product_id_seq START 1;

ALTER TABLE tb_product
    ALTER COLUMN id SET DEFAULT nextval('tb_product_id_seq');

ALTER TABLE tb_product
    ALTER COLUMN id SET NOT NULL;

ALTER TABLE tb_product
    ADD PRIMARY KEY (id);