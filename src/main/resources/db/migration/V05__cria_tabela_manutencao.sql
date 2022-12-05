CREATE TABLE public.manutencao
(
    codigo bigserial NOT NULL,
    obs_admin text,
    obs_usuario text,
    prioridade text,
    situacao text,
    codigo_usuario bigint,
    codigo_equipamento bigint,
    PRIMARY KEY (codigo)
);

ALTER TABLE public.manutencao
    ADD FOREIGN KEY (codigo_usuario)
    REFERENCES public.usuario (codigo)
    NOT VALID;

ALTER TABLE public.manutencao
    ADD FOREIGN KEY (codigo_equipamento)
    REFERENCES public.equipamento (codigo)
    NOT VALID;
