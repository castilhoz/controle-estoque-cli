package com.vitor.estoque.model;

import java.math.BigDecimal;

public class Produto {
    private final int id;
    private final String nomeProduto;
    private final String descricao;
    private final Categoria categoria;
    private final BigDecimal preco;
    private int quantidade;
    private final int estoqueMinimo;


    public Produto(int id, String nomeProduto, String descricao, Categoria categoria, BigDecimal preco, int quantidade, int estoqueMinimo) {

        if (id <= 0) {
            throw new IllegalArgumentException("O ID deve ser maior que zero.");
        }

        if (nomeProduto == null || nomeProduto.isBlank()) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio.");
        }

        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("A descrição não pode ser vazia.");
        }

        if (categoria == null) {
            throw new IllegalArgumentException("A categoria não pode ser vazia.");
        }

        if (preco == null || preco.signum() < 0) {
            throw new IllegalArgumentException("O preço não pode ser nulo ou negativo.");
        }

        if (quantidade < 0) {
            throw new IllegalArgumentException("A quantidade não pode ser negativa.");
        }

        if (estoqueMinimo < 0) {
            throw new IllegalArgumentException("Estoque mínimo não pode ser negativo.");
        }



        this.id = id;
        this.nomeProduto = nomeProduto.trim();
        this.descricao = descricao.trim();
        this.categoria = categoria;
        this.preco = preco;
        this.quantidade = quantidade;
        this.estoqueMinimo = estoqueMinimo;


    }

    public int getId() {
        return id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }


}
