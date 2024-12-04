ALTER TABLE tb_category DROP CONSTRAINT IF EXISTS tb_category_pkey;

CREATE SEQUENCE tb_category_id_seq START 1;

ALTER TABLE tb_category
    ALTER COLUMN id SET DEFAULT nextval('tb_category_id_seq');

ALTER TABLE tb_category
    ALTER COLUMN id SET NOT NULL;

ALTER TABLE tb_category
    ADD PRIMARY KEY (id);