package com.vitor.estoque.repository;

import com.vitor.estoque.model.Produto;
import com.vitor.estoque.model.Categoria;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoRepositoryTest {

    @Test
    void deveAdicionarProduto() {

        //criar repository
        ProdutoRepository repository = new ProdutoRepository();

        //criar produto
        Produto produto = new Produto(
                1,
                "Cimento 2-A",
                "Cimento para acabamento",
                Categoria.CIMENTO,
                new BigDecimal("49.90"),
                10,
                5
        );

        repository.adicionar(produto);

        assertEquals(1, repository.listar().size());
    }
}
