/************ Update: Schemas ***************/

/* Drop Schema if exists: luizalabs */
DROP SCHEMA IF EXISTS luizalabs CASCADE;
/* Add Schema: luizalabs */
CREATE SCHEMA luizalabs;

/************ Add: Sequences ***************/

/* Sequences */

CREATE SEQUENCE luizalabs.client_cli_id_seq INCREMENT BY 1 START 1;
CREATE SEQUENCE luizalabs.product_pro_id_seq INCREMENT BY 1 START 1;

/************ Update: Tables ***************/

/******************** Add Table: luizalabs.client ************************/

/* Build Table Structure */
CREATE TABLE luizalabs.client
(
	cli_id INTEGER DEFAULT nextval('luizalabs.client_cli_id_seq'::regclass) NOT NULL,
	cli_name VARCHAR(50) NULL,
	cli_email VARCHAR(60) UNIQUE NOT NULL
);

/* Add Comments */
COMMENT ON COLUMN luizalabs.client.cli_id IS 'The client id';
COMMENT ON COLUMN luizalabs.client.cli_name IS 'Client name';
COMMENT ON COLUMN luizalabs.client.cli_email IS 'Client email';

/* Add Primary Key */
ALTER TABLE luizalabs.client ADD CONSTRAINT pkclient
	PRIMARY KEY (cli_id);
	

/******************** Add Table: luizalabs.product ************************/

/* Build Table Structure */
CREATE TABLE luizalabs.product
(
	pro_id INTEGER DEFAULT nextval('luizalabs.product_pro_id_seq'::regclass) NOT NULL,
	pro_price real NULL,
	pro_image text NULL,
	pro_brand VARCHAR(50) NULL,
	pro_title VARCHAR(50) NULL,
	pro_review_score real NULL 
);

/* Add Comments */
COMMENT ON COLUMN luizalabs.product.pro_id IS 'The product id';
COMMENT ON COLUMN luizalabs.product.pro_price IS 'Product price';
COMMENT ON COLUMN luizalabs.product.pro_image IS 'Product URL image';
COMMENT ON COLUMN luizalabs.product.pro_brand IS 'Product brand';
COMMENT ON COLUMN luizalabs.product.pro_title IS 'Product name';
COMMENT ON COLUMN luizalabs.product.pro_review_score IS 'Average reviews for this product';

/* Add Primary Key */
ALTER TABLE luizalabs.product ADD CONSTRAINT pkproduct
	PRIMARY KEY (pro_id);

/******************** Add Table: luizalabs.client_has_product ************************/

/* Build Table Structure */
CREATE TABLE luizalabs.client_has_product
(
	chp_cli_id INTEGER NULL,
	chp_pro_id INTEGER NULL
);

/* Add Primary Key */
ALTER TABLE luizalabs.client_has_product ADD CONSTRAINT pkclient_has_product
	PRIMARY KEY (chp_cli_id,chp_pro_id);

/* Add Comments */
COMMENT ON COLUMN luizalabs.client_has_product.chp_cli_id IS 'Chave estrangeira que conecta na tabela: client';
COMMENT ON COLUMN luizalabs.client_has_product.chp_pro_id IS 'Chave estrangeira que conecta na tabela: product';;

/************ Add Foreign Keys ***************/

/* Add Foreign Key: fk_chp_cli_id */
ALTER TABLE luizalabs.client_has_product ADD CONSTRAINT fk_chp_cli_id
	FOREIGN KEY (chp_cli_id) REFERENCES luizalabs.client (cli_id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;

/* Add Foreign Key: chp_pro_id */
ALTER TABLE luizalabs.client_has_product ADD CONSTRAINT fk_chp_pro_id
	FOREIGN KEY (chp_pro_id) REFERENCES luizalabs.product (pro_id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;

