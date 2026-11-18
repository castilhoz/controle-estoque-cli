# Backlog: Controle de Estoque

> Regra: no máximo 2 itens em "Fazendo".
> O que o sistema é e suas regras ficam no [README](README.md). Aqui só entra o trabalho.
> Só as primeiras tarefas têm critério de pronto. As demais são detalhadas quando chegar a vez.

## Fazendo

### Renomear produto (`atualizarNomePorId`)
- **Por quê:** o projeto não compila enquanto este método estiver incompleto, e isso trava todos os testes.
- **Pronto quando:**
  - `mvn clean compile` e `mvn test` passam
  - ID inexistente tem comportamento definido (ver D1)
  - nome vazio ou nulo é rejeitado
  - nome já usado por *outro* produto é rejeitado
  - manter o próprio nome não dá erro (ver D2)
  - os cenários acima têm teste (repository e service)
- **Fora do escopo:** menu no console, histórico.
- **Antes de codar:** decidir D1 e D2.

## Próximas (em ordem)

### 1. Testes do `Produto`
- **Por quê:** é a classe com mais validações e nenhum teste.
- **Pronto quando:** cada regra inválida do construtor (nome, descrição, categoria, preço, estoque mínimo, quantidade) tem um teste que espera a exceção, e há um teste de construção válida.

### 2. Menu mínimo no console: cadastrar, listar, buscar por ID, remover
- **Por quê:** primeira fatia vertical. Faz o sistema rodar de ponta a ponta e dá feedback cedo.
- **Pronto quando:**
  - `Main` só monta as peças (repository, service, ui) e inicia o menu
  - a `ui` só lê o teclado e exibe, sem regra de negócio
  - os leitores de teclado repetem a pergunta se a entrada for inválida (número, texto, preço, categoria)
  - as quatro opções funcionam e o menu tem opção de sair
- **Fora do escopo:** renomear, estoque, histórico.
- **Antes de codar:** decidir D5.

### 3. Exceções específicas e mensagens claras
- **Por quê:** a `ui` precisa distinguir "não encontrado", "duplicado" e "entrada inválida" para mostrar mensagem certa.
- **Pronto quando:**
  - existem exceções próprias para pelo menos "produto não encontrado" e "nome duplicado"
  - service e repository usam essas exceções no lugar de `IllegalArgumentException` genérica
  - os testes existentes foram ajustados e passam
  - a `ui` mostra a mensagem de cada caso sem encerrar o programa

### 4. Entrada e saída de estoque
- **Por quê:** é o coração do controle de estoque.
- **Pronto quando:**
  - entrada e saída aumentam e diminuem a quantidade corretamente
  - quantidade zero ou negativa é rejeitada
  - saída maior que o saldo é rejeitada e o estoque não muda
  - ID inexistente é tratado
  - há testes de `Produto` e de `service` para cada caso acima
  - as duas opções aparecem no menu
- **Antes de codar:** decidir D6.

### 5. Consultar quantidade e listar abaixo do mínimo
- Pronto quando: consultar a quantidade por ID e listar produtos abaixo do estoque mínimo, com teste no limite, abaixo e acima, e opções no menu. Decidir D3.

### 6. Histórico de movimentações
- Cada entrada e saída gera registro com tipo, quantidade e data/hora; consulta por produto no menu. Decidir D4 e D7.

### 7. Filtro por categoria
- Listar produtos de uma categoria, inclusive categoria sem produtos, com opção no menu.

### 8. Persistência (arquivo ou banco)
- Detalhar só ao chegar aqui. Antes, rever D6 e a interface do repository.

### 9. Refinamento
- `equals`, `hashCode` e `toString` em `Produto`, formatação de valores monetários, revisão de README, `docs/` e cobertura de testes.

## Decisões abertas

| ID | Decisão (pergunta + opções) | Bloqueia |
|----|-----------------------------|----------|
| D1 | `atualizarNomePorId` retorna `void` (lança exceção se o ID não existir) ou `boolean`? | Renomear produto |
| D2 | Manter o próprio nome na renomeação: `buscarPorNome` + comparar IDs, ou método no repository que ignora um ID? | Renomear produto |
| D3 | "Abaixo do mínimo" é `<` ou `<=`? | Item 5 |
| D4 | O histórico fica no `ProdutoService` ou em um service próprio? | Item 6 |
| D5 | O ID é digitado pelo usuário ou gerado pelo sistema? | Item 2 |
| D6 | A quantidade muda alterando o objeto buscado ou criando um novo `Produto` e salvando pelo repository? | Item 4 |
| D7 | `Movimentacao` guarda o `Produto` ou só o `produtoId`? | Item 6 |

## Feito

- Estrutura Maven, JUnit e organização em pacotes
- `Produto` com validações e `Categoria`
- `ProdutoRepository` completo (adicionar, listar, buscar, existir, remover, atualizar)
- `ProdutoService`: cadastrar, buscar, listar e remover, com testes
