# Projeto-Consórcio-IP-II

## Integrantes do Grupo
* Emanuel José Tenório Rodrigues
* Gustavo Henrique Evangelista da Silva
* João Ricardo Ferreira de Andrade Barbosa
* Josué Costa das Silva
* Lucas Cavalcanti Ramos de Albuquerque

## Descrição do Projeto
O projeto tem por objetivo criar um sistema de aquisição de bens por meio da formação de uma poupança mensal coletiva. O sistema é destinado a usuários que possuem interesse nesse meio de aquisição mais simples e de fácil acesso ao mais variado público. O sistema deve permitir para os clientes a comprar a assinatura do consórcio desejado, visualizar a quantidade de parcelas restantes do consórcio, além de também ver as contemplações mensais. O usuário (vendedor do consóricio) deve ser permitido ver a quantidade de pessoas que assinaram seu produto, emitir relatórios sobre os assinantes, a simulação das contemplações mensais.

## Requisitos do Projeto
* REQ01: Gerenciamento de clientes, incluindo informações como nome, CPF, telefone e e-mail.
* REQ02: Gerenciamento de grupos de consórcio, com informações como nome do grupo, valor total do consórcio, número de participantes e taxa de administração.
* REQ03: Gerenciamento de contratos de participação, vinculando clientes a grupos com informações sobre parcelas pagas, saldo devedor e status (ativo, contemplado ou encerrado).
* REQ04: Cálculo do valor das parcelas com base no valor total do consórcio, número de participantes e taxa de administração.
* REQ05: Registro e controle dos pagamentos realizados por cada cliente, atualizando automaticamente o saldo devedor.
* REQ06: Simulação de sorteios mensais para contemplação, escolhendo aleatoriamente um cliente ativo no grupo.
* REQ07: Atualização automática do status do contrato para "contemplado" quando um cliente é sorteado.
* REQ08: Consulta ao histórico de contemplações de um grupo, incluindo data, cliente contemplado e status do grupo.
* REQ09: Relatório detalhado do saldo devedor de todos os clientes de um grupo.
* REQ10: Exibição de estatísticas financeiras, como valor total arrecadado e valor pendente em um grupo.
* REQ11: Geração de boletos fictícios para pagamento das parcelas (ex.: exportar um arquivo com informações de pagamento).
* REQ12: Emissão de relatórios em formato texto simples (ex.: .txt ou .csv) contendo o resumo das informações de um grupo.
* REQ13: Validação automática de dados de entrada, como CPF válido, e-mail correto e valor positivo para parcelas.
* REQ14: Bloqueio de operações financeiras (ex.: pagamentos e sorteios) caso o grupo esteja encerrado.
* REQ15: Permitir alteração da taxa de administração de um grupo com atualização automática das parcelas futuras.
