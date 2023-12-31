# Controle Financeiro

## Visão Geral

Um aplicativo para controle financeiro pessoal, permitindo que os usuários gerenciem suas finanças mensal, semestral ou anualmente. O aplicativo oferece funcionalidades para inserir informações de salário, contas mensais e apresentar um resultado final. Além disso, o sistema prevê a estimativa de despesas para os meses seguintes.

## Funcionalidades

### Controle Mensal

- **Inserção de Salário:**
  - Os usuários podem inserir informações sobre seus salários mensais.

- **Gestão de Contas:**
  - Permite aos usuários adicionar suas contas mensais.
  - Mostra o resultado final após dedução das despesas.

- **Estimativa de Contas Futuras:**
  - Oferece um parâmetro para estimar o total de contas a pagar no próximo mês.

### Perfil do Usuário

- **Informações Pessoais:**
  - Nome Completo
  - Data de Nascimento
  - E-mail (com confirmação //TODO)
  - Senha
  - Cargo (opcional)
  - Data de vencimento do cartão de crédito
  - Data em que vira o cartão de crédito

- **Login:**
  - Autenticação por e-mail e senha.
  - Opção para login através do Google(//TODO).

### Gestão de Contas

- **Detalhes da Conta:**
  - Valor da conta.
  - Tipo de pagamento:
    - Único
    - Constante
      - Periodicidade (3 opções):
        - Mensal
        - Semestral
        - Anual
    - Parcelado
      - Número de parcelas.
      - Data de Início(Opcional, caso não escolha o sistema definirá que inicia a partir do mês atual).
  - Descrição da conta.
  - Observações adicionais (Opcional).
  - Recebedor da conta (Opcional).

- **Gerenciamento de Contas:**
  - Adicionar nova conta.
  - Visualizar e editar conta existente.
  - Excluir conta existente.
  - Definir se a conta foi paga ou não.
  - Definir se foi feita a partir do cartão de crédito ou débito.
    - Caso seja cartão de crédito, o sistema irá adicionar à conta do cartão.
    - Caso seja cartão de débito, o sistema irá subtrair do dinheiro do mês atual (não pode ficar negativo).

  
### Ações do Usuário

- **Adicionar Salário:**
  - Valor do salário.
  - Data de recebimento.
  - Definir se o salário já foi recebido
  
- **Incuir valor adicional no dinheiro do mês:**
  - Valor adicional.
  - Data de recebimento.

- **Incuir se há valor a receber no mês:**
  - Valor a receber.
  - Data de recebimento.
  - Pessoa que irá pagar(Opcional).