package com.vitor.estoque.repository;

import com.vitor.estoque.model.Produto;
import com.vitor.estoque.model.Categoria;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoRepositoryTest {

    private Produto criarProduto(int id, String nome) {
        return new Produto(
                id,
                nome,
                "descricao teste",
                Categoria.CIMENTO,
                new BigDecimal("49.90"),
                10,
                5
        );
    }

    @Test
    void deveAdicionarProduto() {

        //criar repository
        ProdutoRepository repository = new ProdutoRepository();

        //criar produto
        Produto produto = criarProduto(1, "Cimento 2-A");

        repository.adicionar(produto);

        assertEquals(1, repository.listar().size());
    }

    @Test
    void deveBuscarId() {

        ProdutoRepository repository = new ProdutoRepository();

        //criar produto
        Produto produto = criarProduto(1, "Cimento 2-A");

        repository.adicionar(produto);

        assertSame(produto,repository.buscarPorId(1));

    }

    @Test
    void deveRetornarTrueQuandoProdutoExistir() {

        ProdutoRepository repository = new ProdutoRepository();

        //criar produto
        Produto produto = criarProduto(1, "Cimento 2-A");

        repository.adicionar(produto);

        assertTrue(repository.existePorId(1));
    }

    @Test
    void deveRetornarFalseQuandoProdutoNaoExistir() {

        ProdutoRepository repository = new ProdutoRepository();

        //criar produto1
        Produto produto1 = criarProduto(1, "Cimento 2-A");

        repository.adicionar(produto1);


        //criar produto2
        Produto produto2 = criarProduto(2, "Cimento 2-A");

        repository.adicionar(produto2);


        assertFalse(repository.existePorId(3));
        assertFalse(repository.removerPorId(99));
        assertEquals(2, repository.listar().size());

    }

    @Test
    void deveRemoverPorId() {

        ProdutoRepository repository = new ProdutoRepository();

        //criar produto1
        Produto produto1 = criarProduto(1, "Cimento 2-A");
        repository.adicionar(produto1);


        //criar produto2
        Produto produto2 = criarProduto(2, "Cimento 2-A");

        repository.adicionar(produto2);


        assertTrue(repository.removerPorId(1));
        assertEquals(1, repository.listar().size());
        assertFalse(repository.existePorId(1));
    }


}
