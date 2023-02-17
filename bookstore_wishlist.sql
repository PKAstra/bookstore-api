-- Table: public.wishlist
-- DROP TABLE IF EXISTS public.wishlist;

CREATE TABLE IF NOT EXISTS public.wishlist
(
    id integer NOT NULL DEFAULT nextval('wishlist_id_seq'::regclass),
    user_id integer NOT NULL,
    wishlist_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT wishlist_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.wishlist
    OWNER to postgres;

-- Table: public.wishlist_books
-- DROP TABLE IF EXISTS public.wishlist_books;

CREATE TABLE IF NOT EXISTS public.wishlist_books
(
    id integer NOT NULL DEFAULT nextval('wishlist_books_id_seq'::regclass),
    wishlist_id integer NOT NULL,
    book_id integer NOT NULL,
    CONSTRAINT wishlist_books_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.wishlist_books
    OWNER to postgres;