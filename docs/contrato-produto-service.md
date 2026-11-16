# Contrato ProdutoService (Sessão 1)

## Decisões registradas

| Item | Decisão |
|------|---------|
| **RN01 (ID único)** | Exigida. `cadastrar` rejeita se `existePorId(id)` for verdadeiro. |
| **Nome único (regra extra)** | **SIM** — regra oficial. `cadastrar` rejeita se `existePorNome(nome)` for verdadeiro. Comparação normalizada: `trim().toLowerCase()`. |
| **`buscarPorId(int id)` quando não encontrado** | **Lança exceção** (cancelando a operação). |
| **Exceção usada** | `IllegalArgumentException` com mensagem descritiva. |
| **Comportamento de nulidade** | `existePorNome` lança `IllegalArgumentException` se `nome` for `null`. Nomes armazenados `null` são ignorados na comparação (não causam NPE). |

## Contratos de método (resumo)

| Método | Assinatura | Comportamento esperado | Erros lançados |
|--------|------------|------------------------|----------------|
| `ProdutoService(ProdutoRepository repository)` | Construtor guarda referência. | Nenhum. | — |
| `void cadastrar(Produto produto)` | Valida ID duplicado (RN01); valida nome duplicado (regra extra); se válido, chama `repository.adicionar(produto)`. | `IllegalArgumentException` se `produto` for nulo; se ID duplicado; se nome duplicado. | `IllegalArgumentException` |
| `Produto buscarPorId(int id)` | Delega a `repository.buscarPorId(id)`. | **Lança `IllegalArgumentException`** se o ID não existir (cancelando operação). | `IllegalArgumentException` |
| `List<Produto> listar()` | Delega a `repository.listar()`. | Nenhum. | — |
| `boolean remover(int id)` | Delega a `repository.removerPorId(id)`. | Retorna `true` se removido, `false` se não encontrado (sem exceção). | — |

## Decisão futura opcional

| Item | Status | Observação |
|------|--------|------------|
| `Locale.ROOT` em `existePorNome` | **Adiar** para depois, se necessário. | Comparação atual usa `toLowerCase()` sem locale; comportamento consistente em qualquer JVM. |