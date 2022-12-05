INSERT INTO public.usuario(nome, login, senha, ativo)
VALUES('Leonardo', 'leonardo', '{argon2}$argon2id$v=19$m=4096,t=3,p=1$3y5L1pkoFQgQGKkzAcGzjA$PUUe4q7M1Yot5VgK2Sa8mQ0oSlzekovvgkCiebN1LHo', true),
('Gustavo', 'gustavo', '{argon2}$argon2id$v=19$m=4096,t=3,p=1$3y5L1pkoFQgQGKkzAcGzjA$PUUe4q7M1Yot5VgK2Sa8mQ0oSlzekovvgkCiebN1LHo', true),
('Maria', 'maria', '{argon2}$argon2id$v=19$m=4096,t=3,p=1$3y5L1pkoFQgQGKkzAcGzjA$PUUe4q7M1Yot5VgK2Sa8mQ0oSlzekovvgkCiebN1LHo', true),
('Carla', 'carla', '{argon2}$argon2id$v=19$m=4096,t=3,p=1$3y5L1pkoFQgQGKkzAcGzjA$PUUe4q7M1Yot5VgK2Sa8mQ0oSlzekovvgkCiebN1LHo', true);

INSERT INTO public.usuario_papel(codigo_usuario,codigo_papel)
VALUES(1,1),
(2,1),
(3,2),
(4,2);