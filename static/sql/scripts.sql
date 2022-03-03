--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE rickandmorty_farmatodo;
--
-- TOC entry 3012 (class 1262 OID 25876)
-- Name: rickandmorty_farmatodo; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE rickandmorty_farmatodo WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Colombia.1252';


ALTER DATABASE rickandmorty_farmatodo OWNER TO postgres;

\connect rickandmorty_farmatodo

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3 (class 2615 OID 26064)
-- Name: farmatodo; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA farmatodo;


ALTER SCHEMA farmatodo OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 200 (class 1259 OID 26065)
-- Name: character; Type: TABLE; Schema: farmatodo; Owner: postgres
--

CREATE TABLE farmatodo."character" (
    id integer NOT NULL,
    gender character varying(250) NOT NULL,
    image character varying(250) NOT NULL,
    name character varying(250) NOT NULL,
    species character varying(250) NOT NULL,
    location_id integer
);


ALTER TABLE farmatodo."character" OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 26071)
-- Name: episode; Type: TABLE; Schema: farmatodo; Owner: postgres
--

CREATE TABLE farmatodo.episode (
    id integer NOT NULL,
    name character varying(250) NOT NULL
);


ALTER TABLE farmatodo.episode OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 26074)
-- Name: episode_character; Type: TABLE; Schema: farmatodo; Owner: postgres
--

CREATE TABLE farmatodo.episode_character (
    episode_id integer NOT NULL,
    character_id integer NOT NULL
);


ALTER TABLE farmatodo.episode_character OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 26077)
-- Name: location; Type: TABLE; Schema: farmatodo; Owner: postgres
--

CREATE TABLE farmatodo.location (
    id integer NOT NULL,
    dimension character varying(250) NOT NULL,
    name character varying(250) NOT NULL,
    type character varying(250) NOT NULL
);


ALTER TABLE farmatodo.location OWNER TO postgres;


--
-- TOC entry 2863 (class 2606 OID 26084)
-- Name: character character_pkey; Type: CONSTRAINT; Schema: farmatodo; Owner: postgres
--

ALTER TABLE ONLY farmatodo."character"
    ADD CONSTRAINT character_pkey PRIMARY KEY (id);


--
-- TOC entry 2867 (class 2606 OID 26086)
-- Name: episode_character episode_character_pkey; Type: CONSTRAINT; Schema: farmatodo; Owner: postgres
--

ALTER TABLE ONLY farmatodo.episode_character
    ADD CONSTRAINT episode_character_pkey PRIMARY KEY (episode_id, character_id);


--
-- TOC entry 2865 (class 2606 OID 26088)
-- Name: episode episode_pkey; Type: CONSTRAINT; Schema: farmatodo; Owner: postgres
--

ALTER TABLE ONLY farmatodo.episode
    ADD CONSTRAINT episode_pkey PRIMARY KEY (id);


--
-- TOC entry 2869 (class 2606 OID 26090)
-- Name: location location_pkey; Type: CONSTRAINT; Schema: farmatodo; Owner: postgres
--

ALTER TABLE ONLY farmatodo.location
    ADD CONSTRAINT location_pkey PRIMARY KEY (id);


--
-- TOC entry 2871 (class 2606 OID 26091)
-- Name: episode_character fk22fkbr1r5q4o6q15qkhjipyf9; Type: FK CONSTRAINT; Schema: farmatodo; Owner: postgres
--

ALTER TABLE ONLY farmatodo.episode_character
    ADD CONSTRAINT fk22fkbr1r5q4o6q15qkhjipyf9 FOREIGN KEY (episode_id) REFERENCES farmatodo.episode(id);


--
-- TOC entry 2872 (class 2606 OID 26096)
-- Name: episode_character fkbs2evhtydowb65yt1qvdcks7a; Type: FK CONSTRAINT; Schema: farmatodo; Owner: postgres
--

ALTER TABLE ONLY farmatodo.episode_character
    ADD CONSTRAINT fkbs2evhtydowb65yt1qvdcks7a FOREIGN KEY (character_id) REFERENCES farmatodo."character"(id);


--
-- TOC entry 2870 (class 2606 OID 26101)
-- Name: character fkhkhaccxwfjjas7gs7tel4h8xf; Type: FK CONSTRAINT; Schema: farmatodo; Owner: postgres
--

ALTER TABLE ONLY farmatodo."character"
    ADD CONSTRAINT fkhkhaccxwfjjas7gs7tel4h8xf FOREIGN KEY (location_id) REFERENCES farmatodo.location(id);

--
-- PostgreSQL database dump complete
--

