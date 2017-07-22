--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: address; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE address (
    id bigint NOT NULL,
    apartament character varying(255),
    city character varying(255),
    house character varying(255),
    street character varying(255)
);


ALTER TABLE address OWNER TO postgres;

--
-- Name: coordinate; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE coordinate (
    id bigint NOT NULL,
    latitude bigint,
    longitude bigint
);


ALTER TABLE coordinate OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO postgres;

--
-- Name: parameter; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE parameter (
    id bigint NOT NULL,
    name character varying(255),
    unit character varying(255),
    parameter_id character varying(255)
);


ALTER TABLE parameter OWNER TO postgres;

--
-- Name: sensor; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE sensor (
    id bigint NOT NULL,
    name character varying(255),
    address_id bigint,
    coordinate_id bigint
);


ALTER TABLE sensor OWNER TO postgres;

--
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY address (id, apartament, city, house, street) FROM stdin;
192	apartament1	city1	house1	street1
196	apartament2	city2	house2	street2
200	apartament3	city3	house3	street3
\.


--
-- Data for Name: coordinate; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY coordinate (id, latitude, longitude) FROM stdin;
193	440442347	110101001
197	441232347	11451001
201	3452345	467467
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 282, true);


--
-- Data for Name: parameter; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY parameter (id, name, unit, parameter_id) FROM stdin;
194	температура	градус цельсия	temperature
202	влажность	г/m3	humidity
\.


--
-- Data for Name: sensor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY sensor (id, name, address_id, coordinate_id) FROM stdin;
191	устройство 1	192	193
195	устройство 2	196	197
199	устройство 3	200	201
\.


--
-- Name: address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


--
-- Name: coordinate_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY coordinate
    ADD CONSTRAINT coordinate_pkey PRIMARY KEY (id);


--
-- Name: parameter_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY parameter
    ADD CONSTRAINT parameter_pkey PRIMARY KEY (id);


--
-- Name: sensor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY sensor
    ADD CONSTRAINT sensor_pkey PRIMARY KEY (id);


--
-- Name: fk43x40y917ls15npi13en4ve7p; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sensor
    ADD CONSTRAINT fk43x40y917ls15npi13en4ve7p FOREIGN KEY (coordinate_id) REFERENCES coordinate(id);


--
-- Name: fk6qemmiymosq68oe1wqx8fokn1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sensor
    ADD CONSTRAINT fk6qemmiymosq68oe1wqx8fokn1 FOREIGN KEY (address_id) REFERENCES address(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

