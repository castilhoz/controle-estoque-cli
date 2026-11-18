package com.vitor.estoque.service;

import com.vitor.estoque.model.Produto;
import com.vitor.estoque.repository.ProdutoRepository;

import java.util.List;

public class ProdutoService {
    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }

        if (repository.existePorId(produto.getId())) {
            throw new IllegalArgumentException("Já existe um produto cadastrado com este ID.");
        }

        if (repository.existePorNome(produto.getNomeProduto())) {
            throw new IllegalArgumentException("Já existe um produto cadastrado com este nome.");
        }

        repository.adicionar(produto);
    }

    public Produto buscarPorId(int id) {
        Produto produto = repository.buscarPorId(id);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado para o ID informado.");
        }
        return produto;
    }

    public List<Produto> listar() {
        return repository.listar();
    }

    public boolean remover(int id) {
        return repository.removerPorId(id);
    }

    public void atualizarNomePorId(int id, String novoNome) {

        if (novoNome == null || novoNome.isBlank()) {
            throw new IllegalArgumentException("Novo nome não pode ser nulo ou vazio.");
        }

        Produto produto = buscarPorId(id);

        String novoNomeTratado = novoNome.trim();

        if (repository.existePorNome(novoNomeTratado)) {
            throw new IllegalArgumentException("Nome em conflito com outro produto.");
        }

        Produto produtoAtualizado = new Produto(
                produto.getId(),
                novoNomeTratado,
                produto.getDescricao(),
                produto.getCategoria(),
                produto.getPreco(),
                produto.getQuantidade(),
                produto.getEstoqueMinimo());

        repository.atualizar(produtoAtualizado);

    }
}
