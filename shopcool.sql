--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.12
-- Dumped by pg_dump version 9.5.12

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE IF EXISTS shopcool;
--
-- Name: shopcool; Type: DATABASE; Schema: -; Owner: sylenz
--

CREATE DATABASE shopcool WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE shopcool OWNER TO sylenz;

\connect shopcool

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: customer_cart; Type: TABLE; Schema: public; Owner: sylenz
--

CREATE TABLE public.customer_cart (
    customer_id integer NOT NULL,
    cart_id integer NOT NULL
);


ALTER TABLE public.customer_cart OWNER TO sylenz;

--
-- Name: customers; Type: TABLE; Schema: public; Owner: sylenz
--

CREATE TABLE public.customers (
    id integer NOT NULL,
    first_name character varying NOT NULL,
    last_name character varying NOT NULL,
    hashed_password character varying NOT NULL,
    email character varying NOT NULL,
    phone_number integer NOT NULL,
    zip_code character varying,
    city character varying,
    address character varying,
    billing_zip_code character varying NOT NULL,
    billing_city character varying NOT NULL,
    billing_address character varying NOT NULL,
    username character varying NOT NULL
);


ALTER TABLE public.customers OWNER TO sylenz;

--
-- Name: customers_id_seq; Type: SEQUENCE; Schema: public; Owner: sylenz
--

CREATE SEQUENCE public.customers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customers_id_seq OWNER TO sylenz;

--
-- Name: customers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sylenz
--

ALTER SEQUENCE public.customers_id_seq OWNED BY public.customers.id;


--
-- Name: product_categories; Type: TABLE; Schema: public; Owner: sylenz
--

CREATE TABLE public.product_categories (
    id integer NOT NULL,
    name character varying NOT NULL,
    department character varying NOT NULL,
    description character varying
);


ALTER TABLE public.product_categories OWNER TO sylenz;

--
-- Name: product_categories_id_seq; Type: SEQUENCE; Schema: public; Owner: sylenz
--

CREATE SEQUENCE public.product_categories_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_categories_id_seq OWNER TO sylenz;

--
-- Name: product_categories_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sylenz
--

ALTER SEQUENCE public.product_categories_id_seq OWNED BY public.product_categories.id;


--
-- Name: product_statuses; Type: TABLE; Schema: public; Owner: sylenz
--

CREATE TABLE public.product_statuses (
    id integer NOT NULL,
    name character varying NOT NULL
);


ALTER TABLE public.product_statuses OWNER TO sylenz;

--
-- Name: product_statuses_id_seq; Type: SEQUENCE; Schema: public; Owner: sylenz
--

CREATE SEQUENCE public.product_statuses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_statuses_id_seq OWNER TO sylenz;

--
-- Name: product_statuses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sylenz
--

ALTER SEQUENCE public.product_statuses_id_seq OWNED BY public.product_statuses.id;


--
-- Name: products; Type: TABLE; Schema: public; Owner: sylenz
--

CREATE TABLE public.products (
    name character varying NOT NULL,
    id integer NOT NULL,
    defaultprice integer NOT NULL,
    currency character varying NOT NULL,
    description character varying,
    size character varying NOT NULL,
    color character varying NOT NULL,
    category_id integer NOT NULL,
    supplier_id integer NOT NULL,
    image character varying,
    status_id integer NOT NULL
);


ALTER TABLE public.products OWNER TO sylenz;

--
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: sylenz
--

CREATE SEQUENCE public.products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.products_id_seq OWNER TO sylenz;

--
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sylenz
--

ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;


--
-- Name: shopping_cart_content; Type: TABLE; Schema: public; Owner: sylenz
--

CREATE TABLE public.shopping_cart_content (
    id integer NOT NULL,
    shopping_cart_id integer NOT NULL,
    product_id integer NOT NULL,
    quantity integer NOT NULL
);


ALTER TABLE public.shopping_cart_content OWNER TO sylenz;

--
-- Name: shopping_cart_content_id_seq; Type: SEQUENCE; Schema: public; Owner: sylenz
--

CREATE SEQUENCE public.shopping_cart_content_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.shopping_cart_content_id_seq OWNER TO sylenz;

--
-- Name: shopping_cart_content_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sylenz
--

ALTER SEQUENCE public.shopping_cart_content_id_seq OWNED BY public.shopping_cart_content.id;


--
-- Name: shopping_cart_statuses; Type: TABLE; Schema: public; Owner: sylenz
--

CREATE TABLE public.shopping_cart_statuses (
    id integer NOT NULL,
    name character varying NOT NULL
);


ALTER TABLE public.shopping_cart_statuses OWNER TO sylenz;

--
-- Name: shopping_carts; Type: TABLE; Schema: public; Owner: sylenz
--

CREATE TABLE public.shopping_carts (
    id integer NOT NULL,
    status_id integer NOT NULL
);


ALTER TABLE public.shopping_carts OWNER TO sylenz;

--
-- Name: shopping_carts_id_seq; Type: SEQUENCE; Schema: public; Owner: sylenz
--

CREATE SEQUENCE public.shopping_carts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.shopping_carts_id_seq OWNER TO sylenz;

--
-- Name: shopping_carts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sylenz
--

ALTER SEQUENCE public.shopping_carts_id_seq OWNED BY public.shopping_carts.id;


--
-- Name: statuses_id_seq; Type: SEQUENCE; Schema: public; Owner: sylenz
--

CREATE SEQUENCE public.statuses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.statuses_id_seq OWNER TO sylenz;

--
-- Name: statuses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sylenz
--

ALTER SEQUENCE public.statuses_id_seq OWNED BY public.shopping_cart_statuses.id;


--
-- Name: suppliers; Type: TABLE; Schema: public; Owner: sylenz
--

CREATE TABLE public.suppliers (
    id integer NOT NULL,
    name character varying NOT NULL,
    description character varying
);


ALTER TABLE public.suppliers OWNER TO sylenz;

--
-- Name: suppliers_id_seq; Type: SEQUENCE; Schema: public; Owner: sylenz
--

CREATE SEQUENCE public.suppliers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.suppliers_id_seq OWNER TO sylenz;

--
-- Name: suppliers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sylenz
--

ALTER SEQUENCE public.suppliers_id_seq OWNED BY public.suppliers.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: sylenz
--

ALTER TABLE ONLY public.customers ALTER COLUMN id SET DEFAULT nextval('public.customers_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: sylenz
--

ALTER TABLE ONLY public.product_categories ALTER COLUMN id SET DEFAULT nextval('public.product_categories_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: sylenz
--

ALTER TABLE ONLY public.product_statuses ALTER COLUMN id SET DEFAULT nextval('public.product_statuses_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: sylenz
--

ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: sylenz
--

ALTER TABLE ONLY public.shopping_cart_content ALTER COLUMN id SET DEFAULT nextval('public.shopping_cart_content_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: sylenz
--

ALTER TABLE ONLY public.shopping_cart_statuses ALTER COLUMN id SET DEFAULT nextval('public.statuses_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: sylenz
--

ALTER TABLE ONLY public.shopping_carts ALTER COLUMN id SET DEFAULT nextval('public.shopping_carts_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: sylenz
--

ALTER TABLE ONLY public.suppliers ALTER COLUMN id SET DEFAULT nextval('public.suppliers_id_seq'::regclass);


--
-- Data for Name: customer_cart; Type: TABLE DATA; Schema: public; Owner: sylenz
--



--
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: sylenz
--



--
-- Name: customers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sylenz
--

SELECT pg_catalog.setval('public.customers_id_seq', 9, true);


--
-- Data for Name: product_categories; Type: TABLE DATA; Schema: public; Owner: sylenz
--



--
-- Name: product_categories_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sylenz
--

SELECT pg_catalog.setval('public.product_categories_id_seq', 7, true);


--
-- Data for Name: product_statuses; Type: TABLE DATA; Schema: public; Owner: sylenz
--

INSERT INTO public.product_statuses (id, name) VALUES (1, 'active');
INSERT INTO public.product_statuses (id, name) VALUES (2, 'passive');


--
-- Name: product_statuses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sylenz
--

SELECT pg_catalog.setval('public.product_statuses_id_seq', 2, true);


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: sylenz
--



--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sylenz
--

SELECT pg_catalog.setval('public.products_id_seq', 1, false);


--
-- Data for Name: shopping_cart_content; Type: TABLE DATA; Schema: public; Owner: sylenz
--



--
-- Name: shopping_cart_content_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sylenz
--

SELECT pg_catalog.setval('public.shopping_cart_content_id_seq', 1, false);


--
-- Data for Name: shopping_cart_statuses; Type: TABLE DATA; Schema: public; Owner: sylenz
--

INSERT INTO public.shopping_cart_statuses (id, name) VALUES (1, 'in progress');
INSERT INTO public.shopping_cart_statuses (id, name) VALUES (2, 'checked');
INSERT INTO public.shopping_cart_statuses (id, name) VALUES (3, 'paid');
INSERT INTO public.shopping_cart_statuses (id, name) VALUES (4, 'confirmed');
INSERT INTO public.shopping_cart_statuses (id, name) VALUES (5, 'shipped');


--
-- Data for Name: shopping_carts; Type: TABLE DATA; Schema: public; Owner: sylenz
--



--
-- Name: shopping_carts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sylenz
--

SELECT pg_catalog.setval('public.shopping_carts_id_seq', 1, false);


--
-- Name: statuses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sylenz
--

SELECT pg_catalog.setval('public.statuses_id_seq', 5, true);


--
-- Data for Name: suppliers; Type: TABLE DATA; Schema: public; Owner: sylenz
--



--
-- Name: suppliers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sylenz
--

SELECT pg_catalog.setval('public.suppliers_id_seq', 44, true);


--
-- Name: customers_id_pk; Type: CONSTRAINT; Schema: public; Owner: sylenz
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_id_pk PRIMARY KEY (id);


--
-- Name: product_categories_id_pk; Type: CONSTRAINT; Schema: public; Owner: sylenz
--

ALTER TABLE ONLY public.product_categories
    ADD CONSTRAINT product_categories_id_pk PRIMARY KEY (id);


--
-- Name: product_statuses_pkey; Type: CONSTRAINT; Schema: public; Owner: sylenz
--

ALTER TABLE ONLY public.product_statuses
    ADD CONSTRAINT product_statuses_pkey PRIMARY KEY (id);


--
-- Name: products_id_pk; Type: CONSTRAINT; Schema: public; Owner: sylenz
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_id_pk PRIMARY KEY (id);


--
-- Name: shopping_cart_content_pkey; Type: CONSTRAINT; Schema: public; Owner: sylenz
--

ALTER TABLE ONLY public.shopping_cart_content
    ADD CONSTRAINT shopping_cart_content_pkey PRIMARY KEY (id);


--
-- Name: shopping_carts_pkey; Type: CONSTRAINT; Schema: public; Owner: sylenz
--

ALTER TABLE ONLY public.shopping_carts
    ADD CONSTRAINT shopping_carts_pkey PRIMARY KEY (id);


--
-- Name: statuses_pkey; Type: CONSTRAINT; Schema: public; Owner: sylenz
--

ALTER TABLE ONLY public.shopping_cart_statuses
    ADD CONSTRAINT statuses_pkey PRIMARY KEY (id);


--
-- Name: suppliers_id_pk; Type: CONSTRAINT; Schema: public; Owner: sylenz
--

ALTER TABLE ONLY public.suppliers
    ADD CONSTRAINT suppliers_id_pk PRIMARY KEY (id);


--
-- Name: customers_id_uindex; Type: INDEX; Schema: public; Owner: sylenz
--

CREATE UNIQUE INDEX customers_id_uindex ON public.customers USING btree (id);


--
-- Name: customers_username_uindex; Type: INDEX; Schema: public; Owner: sylenz
--

CREATE UNIQUE INDEX customers_username_uindex ON public.customers USING btree (username);


--
-- Name: product_categories_id_uindex; Type: INDEX; Schema: public; Owner: sylenz
--

CREATE UNIQUE INDEX product_categories_id_uindex ON public.product_categories USING btree (id);


--
-- Name: product_statuses_id_uindex; Type: INDEX; Schema: public; Owner: sylenz
--

CREATE UNIQUE INDEX product_statuses_id_uindex ON public.product_statuses USING btree (id);


--
-- Name: product_statuses_name_uindex; Type: INDEX; Schema: public; Owner: sylenz
--

CREATE UNIQUE INDEX product_statuses_name_uindex ON public.product_statuses USING btree (name);


--
-- Name: products_id_uindex; Type: INDEX; Schema: public; Owner: sylenz
--

CREATE UNIQUE INDEX products_id_uindex ON public.products USING btree (id);


--
-- Name: shopping_cart_content_id_uindex; Type: INDEX; Schema: public; Owner: sylenz
--

CREATE UNIQUE INDEX shopping_cart_content_id_uindex ON public.shopping_cart_content USING btree (id);


--
-- Name: statuses_id_uindex; Type: INDEX; Schema: public; Owner: sylenz
--

CREATE UNIQUE INDEX statuses_id_uindex ON public.shopping_cart_statuses USING btree (id);


--
-- Name: statuses_name_uindex; Type: INDEX; Schema: public; Owner: sylenz
--

CREATE UNIQUE INDEX statuses_name_uindex ON public.shopping_cart_statuses USING btree (name);


--
-- Name: suppliers_id_uindex; Type: INDEX; Schema: public; Owner: sylenz
--

CREATE UNIQUE INDEX suppliers_id_uindex ON public.suppliers USING btree (id);


--
-- Name: customer_cart_shopping_carts_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: sylenz
--

ALTER TABLE ONLY public.customer_cart
    ADD CONSTRAINT customer_cart_shopping_carts_id_fk FOREIGN KEY (cart_id) REFERENCES public.shopping_carts(id);


--
-- Name: products_product_categories_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: sylenz
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_product_categories_id_fk FOREIGN KEY (category_id) REFERENCES public.product_categories(id);


--
-- Name: products_product_statuses_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: sylenz
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_product_statuses_id_fk FOREIGN KEY (status_id) REFERENCES public.product_statuses(id);


--
-- Name: products_suppliers_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: sylenz
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_suppliers_id_fk FOREIGN KEY (supplier_id) REFERENCES public.suppliers(id);


--
-- Name: shopping_cart_content_products_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: sylenz
--

ALTER TABLE ONLY public.shopping_cart_content
    ADD CONSTRAINT shopping_cart_content_products_id_fk FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- Name: shopping_cart_content_shopping_carts_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: sylenz
--

ALTER TABLE ONLY public.shopping_cart_content
    ADD CONSTRAINT shopping_cart_content_shopping_carts_id_fk FOREIGN KEY (shopping_cart_id) REFERENCES public.shopping_carts(id);


--
-- Name: shopping_carts_statuses_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: sylenz
--

ALTER TABLE ONLY public.shopping_carts
    ADD CONSTRAINT shopping_carts_statuses_id_fk FOREIGN KEY (status_id) REFERENCES public.shopping_cart_statuses(id);


--
-- Name: user_cart_customers_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: sylenz
--

ALTER TABLE ONLY public.customer_cart
    ADD CONSTRAINT user_cart_customers_id_fk FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
SET search_path = 'public';

INSERT INTO suppliers (id,name,description) VALUES ('1','Rapanui','Rapanui ruhák');
INSERT INTO suppliers (id,name,description) VALUES ('2','Bam','Bam ruhák');

INSERT INTO product_categories (id,name,department,description) VALUES ('1','Női','Ruha','Bambusz ruha');
INSERT INTO product_categories (id,name,department,description) VALUES ('2','Férfi','Ruha','Bambusz ruha');
INSERT INTO product_categories (id,name,department,description) VALUES ('3','Unisex','Ruha','Bambusz ruha');

INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('1','Aktív Zip','15990','HUF','Olyan kényelmes, hogy le sem akarod majd venni. Puha, jól lélegzik és melegen tart.','1','2','M','Kék','img/product_1.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('2','Aktív Zip','15990','HUF','Olyan kényelmes, hogy le sem akarod majd venni. Puha, jól lélegzik és melegen tart.','1','2','M','Fekete','img/product_2.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('3','Bamboo Horizon','15990','HUF','Stílusos tölcsérnyakú pulóver.','1','1','M','Acélkék','img/product_3.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('4','Bamboo Horizon','15990','HUF','Stílusos tölcsérnyakú pulóver.','1','1','M','Lila','img/product_4.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('5','Női hosszú ujjú pulóver - narancs','12990','HUF','Mindennapi viselethez.','1','2','M','Narancs','img/product_5.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('6','Női hosszú ujjú pulóver - fenyő','12990','HUF','Mindennapi viselethez.','1','2','S','Zöld','img/product_6.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('7','Női hosszú ujjú pulóver - fenyő','12990','HUF','Mindennapi viselethez.','1','2','M','Zöld','img/product_7.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('8','Női hosszú ujjú pulóver - szürke','12990','HUF','Mindennapi viselethez.','1','2','M','Szürke','img/product_8.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('9','Henley pulóver bambuszból','12990','HUF','Könnyed, stílusos és bársonyosan puha Henley felso férfiaknak.','2','1','L','Füge','img/product_9.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('10','Bambusz póló','5990','HUF','Kényelmes és puha bambusz póló, hétköznapi használatra.','2','1','L','Fekete','img/product_10.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('11','Bambusz póló','5990','HUF','Kényelmes és puha bambusz póló, hétköznapi használatra.','2','1','M','Fekete','img/product_11.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('12','Bambusz póló','5990','HUF','Kényelmes és puha bambusz póló, hétköznapi használatra.','2','1','XL','Fekete','img/product_12.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('13','Bambusz póló','5990','HUF','Kényelmes és puha bambusz póló, hétköznapi használatra.','2','1','L','Fehér','img/product_13.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('14','Biopamut póló','8990','HUF','Galléros póló biopamutból - hogy az irodában is zöld maradhass!','2','1','M','Fekete','img/product_14.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('15','Biopamut póló','8990','HUF','Galléros póló biopamutból - hogy az irodában is zöld maradhass!','2','1','L','Kék','img/product_15.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('16','Bambusz póló','5070','HUF','Vékony bambusz póló, hétköznapi viseletre.','1','2','S','Lila','img/product_16.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('17','Bambusz póló','5070','HUF','Vékony bambusz póló, hétköznapi viseletre.','1','2','M','Lila','img/product_17.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('18','Bambusz póló','5070','HUF','Vékony bambusz póló, hétköznapi viseletre.','1','2','S','Kék','img/product_18.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('19','Bambusz póló','5070','HUF','Vékony bambusz póló, hétköznapi viseletre.','1','2','M','Kék','img/product_19.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('20','Biopamut pulcsi','12990','HUF','Cipzáras-kapucnis pulcsi, biopamutból.','2','1','L','Zöld','img/product_20.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('21','Biopamut pulcsi','12990','HUF','Cipzáras-kapucnis pulcsi, biopamutból.','2','1','M','Zöld','img/product_21.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('22','Biopamut pulcsi','12990','HUF','Kapucnis pulcsi biopamutból.','2','1','L','Kék','img/product_22.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('23','Fekete biopamut pulcsi','12990','HUF','Kapucnis pulcsi biopamutból.','3','1','L','Fekete','img/product_23.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('24','Fekete biopamut pulcsi','12990','HUF','Kapucnis pulcsi biopamutból.','3','1','M','Fekete','img/product_24.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('25','Bambusz póló','12990','HUF','Kapucnis pulcsi biopamutból.','3','1','L','Kék','img/product_25.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('26','Bambusz póló','12990','HUF','Kapucnis pulcsi biopamutból.','3','1','S','Kék','img/product_26.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('27','Bambusz póló','12990','HUF','Kapucnis pulcsi biopamutból.','3','1','S','Fekete','img/product_27.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('28','Biopamut pulcsi','11990','HUF','Cipzáras kapucnis pulcsi biopamutból.','1','1','S','Bordó','img/product_28.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('29','Női biopamut pulcsi','11990','HUF','Cipzáras kapucnis pulcsi biopamutból.','1','1','M','Bordó','img/product_29.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('30','Női biopamut pulcsi','9990','HUF','Cipzáras kapucnis pulcsi biopamutból.','1','1','S','Zöld','img/product_30.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('31','Biopamut pulcsi','9990','HUF','Cipzáras kapucnis pulcsi biopamutból.','1','1','M','Zöld','img/product_31.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('32','Biopamut póló','3990','HUF','Kíváló minőségű biopamutból készült póló - környezetbarát választás.','2','2','L','Fekete','img/product_32.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('33','Biopamut póló','3990','HUF','Kíváló minőségű biopamutból készült póló - környezetbarát választás.','2','2','XL','Kék','img/product_33.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('34','Biopamut póló','3990','HUF','Kíváló minoség, biopamutból készült póló - környezetbarát választás.','2','2','XL','Sötétkék','img/product_34.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('35','Biopamut póló','3990','HUF','Kíváló minoség, biopamutból készült póló - környezetbarát választás.','2','2','L','Zöld','img/product_35.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('36','Biopamut póló','3990','HUF','Kíváló minoség, biopamutból készült póló - környezetbarát választás.','2','2','M','Zöld','img/product_36.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('37','Biopamut póló','3990','HUF','Kíváló minoség, biopamutból készült póló - környezetbarát választás.','2','2','L','Szürke','img/product_37.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('38','Biopamut póló','3990','HUF','Kíváló minőségű biopamutból készült póló - környezetbarát választás.','2','2','L','Sárga','img/product_38.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('39','Biopamut póló','3990','HUF','Kíváló minoség, biopamutból készült póló - környezetbarát választás.','2','2','XL','Piros','img/product_39.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('40','Biopamut póló','3990','HUF','Kíváló minoség, biopamutból készült póló - környezetbarát választás.','2','2','L','Fehér','img/product_40.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('41','Biopamut póló','3990','HUF','Kíváló minoség, biopamutból készült póló - környezetbarát választás.','2','2','M','Fehér','img/product_41.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('42','Biopamut póló','3990','HUF','Kíváló minoség, biopamutból készült póló - környezetbarát választás.','2','2','XL','Fehér','img/product_42.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('43','Biopamut póló','3990','HUF','Kíváló minoség, biopamutból készült póló - környezetbarát választás.','1','2','S','Fekete','img/product_43.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('44','Biopamut póló','3990','HUF','Kíváló minoség, biopamutból készült póló - környezetbarát választás.','1','2','M','Fekete','img/product_44.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('45','Biopamut póló','3990','HUF','Kíváló minoség, biopamutból készült póló - környezetbarát választás.','1','2','S','Rózsaszín','img/product_45.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('46','Biopamut póló','3990','HUF','Kíváló minoség, biopamutból készült póló - környezetbarát választás.','1','2','M','Rózsaszín','img/product_46.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('47','Biopamut póló','3990','HUF','Kíváló minoség, biopamutból készült póló - környezetbarát választás.','1','2','S','Fehér','img/product_47.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('48','Biopamut póló','3990','HUF','Kíváló minoség, biopamutból készült póló - környezetbarát választás.','1','2','M','Fehér','img/product_48.jpg','1');
INSERT INTO products (id,name,defaultprice,currency,description,category_id,supplier_id,size,color,image,status_id) VALUES ('49','Biopamut póló','3990','HUF','Kíváló minoség, biopamutból készült póló - környezetbarát választás.','1','2','M','Sárga','img/product_49.jpg','1');

--
-- PostgreSQL database dump complete
--

