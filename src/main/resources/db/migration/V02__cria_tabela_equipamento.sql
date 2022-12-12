CREATE TABLE public.equipamento
(
    codigo bigserial NOT NULL,
    nome text,
    marca text,
    status text,
    PRIMARY KEY (codigo)
);

INSERT INTO public.equipamento(nome, marca, status) VALUES 
('Maçarico', 'Fogo e fé', 'DISPONIVEL'),
('Britadeira', 'WATT', 'DISPONIVEL'),
('Gerador', 'Ferrari', 'DISPONIVEL'),
('Escora', 'Suporte ltda', 'DISPONIVEL'),
('Moedor', 'Smash', 'DISPONIVEL'),
('Picareta', 'Minecraft', 'DISPONIVEL'),
('Bomba de água', 'Waterway', 'DISPONIVEL'),
('Painel metalico', 'SHIELD', 'DISPONIVEL'),
('Betoneira', 'Menegotti', 'DISPONIVEL');