CREATE TABLE public.usuario
(
    codigo bigserial NOT NULL,
    nome text,
    login text,
    senha text,
    ativo boolean,
    PRIMARY KEY (codigo)
);
