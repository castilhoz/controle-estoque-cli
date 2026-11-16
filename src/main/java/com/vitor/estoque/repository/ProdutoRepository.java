package com.vitor.estoque.repository;

import com.vitor.estoque.model.Produto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ProdutoRepository {
    private final List<Produto> produtos = new ArrayList<>();

    public void adicionar(Produto produto) {
        produtos.add(produto);
    }

    public List<Produto> listar() {
        return Collections.unmodifiableList(produtos);
    }

    public Produto buscarPorId(int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    public boolean existePorId(int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean removerPorId(int id) {
        Produto produtoEncontrado = null;
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                produtoEncontrado = produto;
                break;
            }
        }

        if (produtoEncontrado != null) {
            produtos.remove(produtoEncontrado);
            return true;
        }

        return false;
    }

    public boolean existePorNome(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("Nome não pode ser nulo.");
        }

        String nomeBuscado = nome.trim().toLowerCase();
        for (Produto produto : produtos) {
            String nomeProdutoAtual = produto.getNomeProduto();
            if (nomeProdutoAtual != null && nomeProdutoAtual.trim().toLowerCase().equals(nomeBuscado)) {
                return true;
            }
        }
        return false;
    }

    public boolean atualizar(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo.");
        }

        int indice = -1;
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == produto.getId()) {
                indice = i;
                break;
            }
        }

        if (indice == -1) {
            return false;
        }

        produtos.set(indice, produto);
        return true;
    }
}