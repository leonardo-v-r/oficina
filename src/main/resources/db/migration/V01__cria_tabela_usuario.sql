CREATE TABLE public.usuario
(
    codigo bigserial NOT NULL,
    nome text,
    admin boolean,
    login text,
    senha text,
    PRIMARY KEY (codigo)
);
