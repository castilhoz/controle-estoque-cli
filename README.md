# Controle de Estoque

Aplicação de console em Java para uma loja de materiais de construção acompanhar produtos, quantidades e movimentações de estoque.

Projeto de aprendizagem. O que falta fazer está em [`BACKLOG.md`](BACKLOG.md).

## O que o sistema faz

- Cadastrar, listar, buscar (por ID), renomear e remover produtos
- Registrar entrada e saída de estoque
- Consultar a quantidade atual de um produto
- Listar produtos abaixo do estoque mínimo
- Consultar o histórico de movimentações de um produto
- Filtrar produtos por categoria

## Regras de negócio

- Cada produto tem um ID único.
- O nome do produto é único (sem diferenciar maiúsculas de minúsculas e ignorando espaços nas pontas).
- Nome, descrição e categoria são obrigatórios.
- O preço não pode ser nulo nem negativo.
- O estoque mínimo não pode ser negativo.
- A quantidade em estoque nunca pode ficar negativa.
- A quantidade de uma entrada ou saída deve ser maior que zero.
- Só é possível movimentar produtos cadastrados.
- Toda entrada ou saída gera um registro com data e hora.

## Escopo atual

- Dados guardados apenas em memória (somem ao fechar o programa).
- Interface somente no terminal, sem login nem múltiplos usuários.
- Banco de dados fica para uma etapa futura.

## Como executar

Requisitos: JDK 26 (ou ajuste `maven.compiler.release` no `pom.xml` para a versão instalada) e Maven.

```bash
mvn clean compile   # compila
mvn test            # roda os testes
```

## Estrutura

```text
src/main/java/com/vitor/estoque/
├── model/        # entidades e validações (Produto, Categoria)
├── repository/   # guarda e busca dados, sem regra de negócio
├── service/      # regras de negócio
├── ui/           # interface no terminal
└── Main.java     # iniciar o estoque e chamar ui
```

A interface (ui) chama o `service`, que aplica as regras e usa o `repository`. A interface não contém regra de negócio.

## Mais informações

- Tarefas e decisões em aberto: [`BACKLOG.md`](BACKLOG.md)
- Decisões de design já tomadas: pasta [`docs/`](docs/)