CREATE TABLE public.customer_master (
	phone_number varchar(8000) NOT NULL,
	first_name varchar(8000) NULL,
	last_name varchar(8000) NULL,
	email varchar(8000) NULL,
	address_line1 varchar(8000) NULL,
	address_line2 varchar(8000) NULL,
	city varchar(8000) NULL,
	state varchar(8000) NULL,
	zip_code varchar(8000) NULL,
	credit_score varchar(8000) NULL,
	credit_tier varchar(8000) NULL,
	created_at timestamp(6) DEFAULT CURRENT_TIMESTAMP NULL,
	credit_tier_desc varchar(8000) NULL,
	CONSTRAINT chk_phone_number_format CHECK (((phone_number)::text ~ '^[0-9]{10}$'::text)),
	CONSTRAINT customer_master_pkey PRIMARY KEY (phone_number)
);

CREATE TABLE public.dealer_master (
	dealer_id varchar(8000) NOT NULL,
	dealer_name varchar(8000) NULL,
	address_line1 varchar(8000) NULL,
	address_line2 varchar(8000) NULL,
	city varchar(8000) NULL,
	state varchar(8000) NULL,
	zip_code varchar(8000) NULL,
	timezone varchar(8000) NULL,
	phone_number varchar(8000) NULL,
	email varchar(8000) NULL,
	created_at timestamp(6) DEFAULT CURRENT_TIMESTAMP NULL,
	CONSTRAINT dealer_master_pkey PRIMARY KEY (dealer_id)
);

CREATE TABLE public.vehicle_master (
	inventory_id varchar(8000) NOT NULL,
	dealer_id varchar(8000) NOT NULL,
	vin varchar(8000) NULL,
	vehicle_type varchar(8000) NULL,
	make varchar(8000) NULL,
	model varchar(8000) NULL,
	model_year varchar(8000) NULL,
	trim varchar(8000) NULL,
	miles varchar(8000) NULL,
	msrp varchar(8000) NULL,
	color_interior varchar(8000) NULL,
	color_exterior varchar(8000) NULL,
	image varchar(8000) NULL,
	created_at timestamp(6) DEFAULT CURRENT_TIMESTAMP NULL,
	CONSTRAINT vehicle_master_pkey PRIMARY KEY (inventory_id),
	CONSTRAINT fk_vehicle_dealer FOREIGN KEY (dealer_id) REFERENCES public.dealer_master(dealer_id)
);
CREATE INDEX vehicle_master_dealer_id_idx ON public.vehicle_master USING btree (dealer_id);
CREATE INDEX vehicle_master_vin_idx ON public.vehicle_master USING btree (vin);

CREATE SEQUENCE rate_master_rate_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE public.rate_master (
	rate_id bigserial NOT NULL,
	dealer_id varchar(100) NOT NULL,
	make varchar(100) NOT NULL,
	model varchar(100) NOT NULL,
	"year" varchar(10) NOT NULL,
	trim varchar(100) NULL,
	term_months int4 NOT NULL,
	finance_type varchar(50) NOT NULL,
	credit_tier varchar(50) NOT NULL,
	rate numeric(5, 2) NOT NULL,
	created_at timestamp DEFAULT CURRENT_TIMESTAMP NULL,
	updated_at timestamp DEFAULT CURRENT_TIMESTAMP NULL,
	CONSTRAINT rate_master_pkey PRIMARY KEY (rate_id)
);
CREATE INDEX idx_rate_master_dealer ON public.rate_master USING btree (dealer_id);
CREATE INDEX idx_rate_master_rate_lookup ON public.rate_master USING btree (dealer_id, make, model, year, term_months, finance_type, credit_tier);
CREATE INDEX idx_rate_master_vehicle ON public.rate_master USING btree (make, model, year, "trim");

CREATE TABLE public.standard_rates (
	standard_rate_id serial4 NOT NULL,
	state varchar(8000) NULL,
	dealer_id varchar(8000) NOT NULL,
	"type" varchar(8000) NULL,
	make varchar(8000) NULL,
	model varchar(8000) NULL,
	series varchar(8000) NULL,
	model_year varchar(8000) NULL,
	term int4 NULL,
	tier varchar(8000) NULL,
	rate varchar(8000) NULL,
	subvented bool NULL,
	CONSTRAINT chk_rate_type CHECK (((type)::text = ANY ((ARRAY['Retail'::character varying, 'Lease'::character varying])::text[]))),
	CONSTRAINT standard_rates_pkey PRIMARY KEY (standard_rate_id),
	CONSTRAINT fk_rates_dealer FOREIGN KEY (dealer_id) REFERENCES public.dealer_master(dealer_id)
);
CREATE INDEX idx_standard_rates_dealer ON public.standard_rates USING btree (dealer_id);