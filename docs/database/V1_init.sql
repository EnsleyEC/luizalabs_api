/************ Add: Sequences ***************/

/* Sequences */

CREATE SEQUENCE public.client_cli_id_seq INCREMENT BY 1 START 1;
CREATE SEQUENCE public.product_pro_id_seq INCREMENT BY 1 START 1;
CREATE SEQUENCE public.account_acc_id_seq INCREMENT BY 1 START 1;

/************ Update: Tables ***************/

/******************** Add Table: public.client ************************/

/* Build Table Structure */
CREATE TABLE public.client
(
	cli_id INTEGER DEFAULT nextval('public.client_cli_id_seq'::regclass) NOT NULL,
	cli_name VARCHAR(50) NULL,
	cli_email VARCHAR(60) UNIQUE NOT NULL
);

/* Add Comments */
COMMENT ON COLUMN public.client.cli_id IS 'The client id';
COMMENT ON COLUMN public.client.cli_name IS 'Client name';
COMMENT ON COLUMN public.client.cli_email IS 'Client email';

/* Add Primary Key */
ALTER TABLE public.client ADD CONSTRAINT pkclient
	PRIMARY KEY (cli_id);
	

/******************** Add Table: public.product ************************/

/* Build Table Structure */
CREATE TABLE public.product
(
	pro_id INTEGER DEFAULT nextval('public.product_pro_id_seq'::regclass) NOT NULL,
	pro_price real NULL,
	pro_image text NULL,
	pro_brand VARCHAR(50) NULL,
	pro_title VARCHAR(50) NULL,
	pro_review_score real NULL 
);

/* Add Comments */
COMMENT ON COLUMN public.product.pro_id IS 'The product id';
COMMENT ON COLUMN public.product.pro_price IS 'Product price';
COMMENT ON COLUMN public.product.pro_image IS 'Product URL image';
COMMENT ON COLUMN public.product.pro_brand IS 'Product brand';
COMMENT ON COLUMN public.product.pro_title IS 'Product name';
COMMENT ON COLUMN public.product.pro_review_score IS 'Average reviews for this product';

/* Add Primary Key */
ALTER TABLE public.product ADD CONSTRAINT pkproduct
	PRIMARY KEY (pro_id);
	
/******************** Add Table: public.client_has_product ************************/

/* Build Table Structure */
CREATE TABLE public.client_has_product
(
	chp_cli_id INTEGER NULL,
	chp_pro_id INTEGER NULL
);

/* Add Primary Key */
ALTER TABLE public.client_has_product ADD CONSTRAINT pkclient_has_product
	PRIMARY KEY (chp_cli_id,chp_pro_id);
	
/* Add Comments */
COMMENT ON COLUMN public.client_has_product.chp_cli_id IS 'Chave estrangeira que conecta na tabela: client';
COMMENT ON COLUMN public.client_has_product.chp_pro_id IS 'Chave estrangeira que conecta na tabela: product';

/******************** Add Table: public.account ************************/

CREATE TABLE public.account
(
	acc_id INTEGER DEFAULT nextval('public.account_acc_id_seq'::regclass) NOT NULL,
	acc_username varchar(20) UNIQUE NULL,
	acc_password varchar(80) NULL
);
/* Add Comments */
COMMENT ON COLUMN public.account.acc_id IS 'Account id';
COMMENT ON COLUMN public.account.acc_username IS 'Account username';
COMMENT ON COLUMN public.account.acc_password IS 'Account password';

/* Add Primary Key */
ALTER TABLE public.account ADD CONSTRAINT pkaccount
	PRIMARY KEY (acc_id);
	
/************ Add Foreign Keys ***************/

/* Add Foreign Key: fk_chp_cli_id */
ALTER TABLE public.client_has_product ADD CONSTRAINT fk_chp_cli_id
	FOREIGN KEY (chp_cli_id) REFERENCES public.client (cli_id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;

/* Add Foreign Key: chp_pro_id */
ALTER TABLE public.client_has_product ADD CONSTRAINT fk_chp_pro_id
	FOREIGN KEY (chp_pro_id) REFERENCES public.product (pro_id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;

