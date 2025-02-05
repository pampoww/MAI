insert into tb_mai_usuario(id_usuario, login, role, salt, senha) values ('31165323-9655-43ab-aaef-5a1e752e1d22', 'THEJoeAnd@gmail.com', 'ADMIN', 'b4e077f7c68887b5', '$2a$10$P5rJN/YBNjfl16zL.ze63ub07e3PYBANdLifmbJcyN4HEObzro..6');
insert into tb_mai_funcionario(id_funcionario, nm_cargo, nm_email, img_imagem_perfil, nm_funcionario, id_usuario) values ('5f623c11-e308-4f78-b00b-c06f252f3e2f', 'Não mexa com ele', 'THEJoeAnd@gmail.com', 'Ainda não', 'THE Joe Anderson', '31165323-9655-43ab-aaef-5a1e752e1d22');

insert into tb_mai_usuario (id_usuario, login, role, salt, senha) values ('9b0975d5-d653-4234-be21-4ece573d0ab3', 'mchagas@gmail.com', 'ADMIN', '04a2fb5784993c7e', '$2a$10$EzTXjZEalfc4W1tX.qGcbeqLbx.R5NMrIx.204b9C3DH7x0hoCVeC');
insert into tb_mai_funcionario (id_funcionario, nm_cargo, nm_email, img_imagem_perfil, nm_funcionario, id_usuario) values ('24f7f9d9-45df-409a-b8bf-d8454d73ab07', 'Analista Empresarial', 'mchagas@gmail.com', '', 'Mariana Chagas de Oliveira', '9b0975d5-d653-4234-be21-4ece573d0ab3');

-- Empresa Tiktak

-- Inserindo o Estado
INSERT INTO tb_mai_estado (id_estado, sg_estado, nm_estado) VALUES ('3e08966a-09c3-4747-b972-3a0072962cac', 'SP', 'São Paulo');
-- Inserindo a Cidade
INSERT INTO tb_mai_cidade (id_cidade, nm_cidade, id_estado) VALUES ('2068d17c-c0f3-42b4-8b7e-1991bddc99b6', 'São Paulo', '3e08966a-09c3-4747-b972-3a0072962cac');
-- Inserindo o Logradouro
INSERT INTO tb_mai_logradouro (id_logradouro, nr_cep, id_cidade) VALUES ('e50f7aa7-9971-400c-83f0-43390b8f8a88', '98635100', '2068d17c-c0f3-42b4-8b7e-1991bddc99b6');
-- Inserindo o Usuário
INSERT INTO tb_mai_usuario (id_usuario, login, senha, salt, role) VALUES ('aaec524a-3de7-416c-9a89-f73b58b5f229', 'tik@gmail.com', '$2a$10$kw37ua4BzL0vzyTzqsKB6.cSb5F1wAM2AeEjRt3leBZPE3mVFZSF.', '28a58e7451b95e3f', 'USER');
-- Inserindo a Empresa
INSERT INTO tb_mai_empresa (id_empresa, nm_empresa, nr_cnpj, tp_tipo_plano, bl_pagamento, id_usuario, id_logradouro) VALUES ('9d19c0fc-cef3-475f-90f1-2b5ca888e2b9', 'Tiktak', '98765432109876', 'BASIC', true, 'aaec524a-3de7-416c-9a89-f73b58b5f229', 'e50f7aa7-9971-400c-83f0-43390b8f8a88');

-- Empresa Goggle

-- Inserindo o Estado
INSERT INTO tb_mai_estado (id_estado, sg_estado, nm_estado) VALUES ('3d216c37-240e-453b-a6c2-d3012281a9bb', 'NY', 'Nova York');
-- Inserindo a Cidade
INSERT INTO tb_mai_cidade (id_cidade, nm_cidade, id_estado) VALUES ('ed1bde33-fa24-457b-be9c-fdedf700796e', 'Manhattan', '3d216c37-240e-453b-a6c2-d3012281a9bb');
-- Inserindo o Logradouro
INSERT INTO tb_mai_logradouro (id_logradouro, nr_cep, id_cidade) VALUES ('3655fa36-d1ff-467b-81ad-dd36da9e8c49', '98635129', 'ed1bde33-fa24-457b-be9c-fdedf700796e');
-- Inserindo o Usuário
INSERT INTO tb_mai_usuario (id_usuario, login, senha, salt, role) VALUES ('cb27ed44-4967-4255-8399-30d9c1ca5b30', 'gg@gmail.com', '$2a$10$UpmgE8.bHQ7SW4skAgSYRu97GPp72cc7rNtf5tTrxBbdq1QqMd5U2', 'f0a6bb57d4a52a82', 'USER');
-- Inserindo a Empresa
INSERT INTO tb_mai_empresa (id_empresa, nm_empresa, nr_cnpj, tp_tipo_plano, bl_pagamento, id_usuario, id_logradouro) VALUES ('359280df-f598-4df6-8139-3cf343b66846', 'Goggle', '98765432109432', 'PREMIUM', false, 'cb27ed44-4967-4255-8399-30d9c1ca5b30', '3655fa36-d1ff-467b-81ad-dd36da9e8c49');

-- Empresa Macrosoft

-- Inserindo o Estado
INSERT INTO tb_mai_estado (id_estado, sg_estado, nm_estado) VALUES ('e4443bb0-469d-4c10-9d69-4e4e1a0c689e', 'NY', 'Nova York');
-- Inserindo a Cidade
INSERT INTO tb_mai_cidade (id_cidade, nm_cidade, id_estado) VALUES ('fad7abe8-9869-44f9-8f81-106d20ad8823', 'Manhattan', 'e4443bb0-469d-4c10-9d69-4e4e1a0c689e');
-- Inserindo o Logradouro
INSERT INTO tb_mai_logradouro (id_logradouro, nr_cep, id_cidade) VALUES ('e901821c-bc4a-4975-ac9a-b6cf7b0298d2', '98698100', 'fad7abe8-9869-44f9-8f81-106d20ad8823');
-- Inserindo o Usuario
INSERT INTO tb_mai_usuario (id_usuario, login, senha, salt, role) VALUES ('067c7c43-db0d-47c9-998a-721c1d52f99b', 'ms@gmail.com', '$2a$10$gn6wzOWFccyqd7Tg3NV2keA3qN.UExxXahPDXSio3Y9YmiRgqYcGi', 'eeefe7b23a7d5af3', 'USER');
-- Inserindo a Empresa
INSERT INTO tb_mai_empresa (id_empresa, nm_empresa, nr_cnpj, tp_tipo_plano, bl_pagamento, id_usuario, id_logradouro) VALUES ('b50a41f2-600f-455e-ba39-bd4f574fce4f', 'Macrosoft', '98765345212348', 'BASIC', false, '067c7c43-db0d-47c9-998a-721c1d52f99b', 'e901821c-bc4a-4975-ac9a-b6cf7b0298d2');

-- Empresa PAIF

-- Inserindo o Estado
INSERT INTO tb_mai_estado (id_estado, sg_estado, nm_estado) VALUES ('9188374d-9057-4d42-9184-ff3a0d69300d', 'SP', 'São Paulo');
-- Inserindo a Cidade
INSERT INTO tb_mai_cidade (id_cidade, nm_cidade, id_estado) VALUES ('4d13fec4-567a-459c-bff2-8f3f983cf1b8', 'São Paulo', '9188374d-9057-4d42-9184-ff3a0d69300d');
-- Inserindo o Logradouro
INSERT INTO tb_mai_logradouro (id_logradouro, nr_cep, id_cidade) VALUES ('ec2e1b85-1b4c-4c3d-ba52-6b1f1761cf26', '42645100', '4d13fec4-567a-459c-bff2-8f3f983cf1b8');
-- Inserindo o Usuario
INSERT INTO tb_mai_usuario (id_usuario, login, senha, salt, role) VALUES ( 'f2dc43be-3073-4715-8501-d96b092dbd91', 'pf@gmail.com', '$2a$10$Kal7gVBs0RP2lolN1lRwwONgDXftq.XnOGB9lFIPmm8ImUJcEIf/6', '7e1ddf15943c9968', 'USER');
-- Inserindo a Empresa
INSERT INTO tb_mai_empresa (id_empresa, nm_empresa, ct_telefone, st_site, nr_cnpj, tp_tipo_plano, bl_pagamento, id_usuario, id_logradouro) VALUES ( '898c5a08-87b7-4279-9671-ab54d97f030f', 'PAIF', null, null, '98765345212532', 'BASIC', true, 'f2dc43be-3073-4715-8501-d96b092dbd91', 'ec2e1b85-1b4c-4c3d-ba52-6b1f1761cf26');
