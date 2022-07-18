CREATE TABLE IF NOT EXISTS public.users
(
    id text  NOT NULL,
    email text ,
    first_name text ,
    last_name text ,
    password text ,
    username text ,
    mobile numeric,
    user_pub_key text ,
    user_private_key text ,
    user_wallet_id text,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT email_unique_key UNIQUE (email),
    CONSTRAINT username_unique_key UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS public.refresh_token
(
    id text  NOT NULL,
    user_id text  NOT NULL,
    token text ,
    expiry_date time without time zone,
    CONSTRAINT refresh_token_pkey PRIMARY KEY (id),
    CONSTRAINT userkey FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.roles
(
    id text  NOT NULL,
    name text ,
    CONSTRAINT roles_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.user_roles
(
    user_id text  NOT NULL,
    role_id text  NOT NULL,
    CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id),
    CONSTRAINT role_foreign_key FOREIGN KEY (role_id)
        REFERENCES public.roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_foreign_key FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.nft_image_info
(
 	 id text,     
	 user_id text  NOT NULL,
	 img_name text,
	 creator_name text,
	 wallet_id text,
	 private_key text,
	 fixed_price numeric,
	 royalty_fee numeric,
	 ipfs_url text,
	 previous_owner text,
	 current_owner text,
	 token_name text,
	 token_symbol text,
	 nft_id text,
	 created_date timestamp without time zone,
    CONSTRAINT img_pkey PRIMARY KEY (id),
    CONSTRAINT fk_user_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
