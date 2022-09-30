INSERT INTO restaurante (id, cep, complemento, nome) VALUES
(1, '0000001', 'Complemento Endereço Restaurante 1', 'Restaurante 1'),
(2, '0000002', 'Complemento Endereço Restaurante 2', 'Restaurante 2');

INSERT INTO cliente (id, cep, complemento, nome) VALUES
(1, '0000001', 'Complemento Endereço Cliente 1', 'Cliente 1');

INSERT INTO produto (id, disponivel, nome, valor_unitario, restaurante_id) VALUES
(1, true, 'Produto 1', 5.0, 1),
(2, true, 'Produto 2', 6.0, 1),
(3, true, 'Produto 3', 7.0, 2);

INSERT INTO sacola (id, forma_pagamento, fechada, valor_total, cliente_id) VALUES
(2, 0, false, 0.0, 1);