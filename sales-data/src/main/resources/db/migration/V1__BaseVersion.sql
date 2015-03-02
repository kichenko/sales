set statement_timeout = 0;
set client_encoding = 'utf8';
set standard_conforming_strings = on;
set check_function_bodies = false;
set client_min_messages = warning;
set search_path = public, pg_catalog;
set default_tablespace = '';
set default_with_oids = false;

--product
drop table if exists product cascade;
create table product
(
  id bigint not null,
  product_name varchar(255),
  product_price bigint,
  constraint pk_product_pkey primary key (id)
);

--seq_product
drop sequence if exists seq_product;
create sequence seq_product;

--sale
drop table if exists sale cascade;
create table sale
(
  id bigint not null,
  product_id bigint not null,
  sale_quantity integer,
  sale_cost bigint,
  sale_date timestamp without time zone,
  constraint pk_sale_pkey primary key (id),

  constraint fk_product_id foreign key (product_id)
      references product (id) match simple
      on update cascade on delete cascade
);

--idx_fk_product_id
drop index if exists idx_fk_product_id;
create index idx_fk_product_id
    on sale
    using btree
    (product_id);


--seq_sale
drop sequence if exists seq_sale;
create sequence seq_sale;
