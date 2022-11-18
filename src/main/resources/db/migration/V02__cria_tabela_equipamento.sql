CREATE TABLE public.equipamento
(
    codigo bigserial NOT NULL,
    nome text,
    marca text,
    status text,
    PRIMARY KEY (codigo)
);
