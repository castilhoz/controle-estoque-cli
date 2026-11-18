## Decisões abertas

| ID | Decisão | Resolução |
|----|---------|--|
| D1 | `atualizarNomePorId` retorna `void` (lança exceção se o ID não existir) ou `boolean`? | Retorna void |
| D2 | Manter o próprio nome na renomeação: `buscarPorNome` + comparar IDs, ou método no repository que ignora um ID? |  |
| D3 | "Abaixo do mínimo" é `<` ou `<=`? |  |
| D4 | O histórico fica no `ProdutoService` ou em um service próprio? |  |
| D5 | O ID é digitado pelo usuário ou gerado pelo sistema? |  |
| D6 | A quantidade muda alterando o objeto buscado ou criando um novo `Produto` e salvando pelo repository? |  |
| D7 | `Movimentacao` guarda o `Produto` ou só o `produtoId`? |  |
