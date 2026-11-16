package com.vitor.estoque.service;

import com.vitor.estoque.model.Categoria;
import com.vitor.estoque.model.Produto;
import com.vitor.estoque.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoServiceTest {

    private ProdutoRepository repository;
    private ProdutoService service;

    @BeforeEach
    void setUp() {
        repository = new ProdutoRepository();
        service = new ProdutoService(repository);
    }

    private Produto novoProduto(int id, String nome) {
        return new Produto(id, nome, "Descrição", Categoria.CIMENTO, new BigDecimal("10.00"), 5, 1);
    }

    @Test
    void deveCadastrarProdutoValido() {
        Produto produto = novoProduto(1, "Cimento CP-II");

        service.cadastrar(produto);

        List<Produto> produtos = service.listar();
        assertEquals(1, produtos.size());
        assertEquals(produto, produtos.get(0));
    }

    @Test
    void naoDeveCadastrarProdutoNulo() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> service.cadastrar(null));
        assertEquals("Produto não pode ser nulo.", ex.getMessage());
    }

    @Test
    void naoDeveCadastrarProdutoComIdDuplicado() {
        service.cadastrar(novoProduto(1, "Cimento CP-II"));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> service.cadastrar(novoProduto(1, "Areia Média")));

        assertEquals("Já existe um produto cadastrado com este ID.", ex.getMessage());
    }

    @Test
    void naoDeveCadastrarProdutoComNomeDuplicado() {
        service.cadastrar(novoProduto(1, "Cimento CP-II"));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> service.cadastrar(novoProduto(2, "  cimento cp-ii  ")));

        assertEquals("Já existe um produto cadastrado com este nome.", ex.getMessage());
    }

    @Test
    void deveBuscarProdutoPorIdQuandoExistir() {
        Produto produto = novoProduto(1, "Cimento CP-II");
        service.cadastrar(produto);

        Produto encontrado = service.buscarPorId(1);

        assertSame(produto, encontrado);
    }

    @Test
    void deveLancarExcecaoAoBuscarIdInexistente() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> service.buscarPorId(99));
        assertEquals("Produto não encontrado para o ID informado.", ex.getMessage());
    }

    @Test
    void deveListarProdutosCadastrados() {
        service.cadastrar(novoProduto(1, "Cimento CP-II"));
        service.cadastrar(novoProduto(2, "Areia Média"));

        List<Produto> produtos = service.listar();

        assertEquals(2, produtos.size());
    }

    @Test
    void deveRemoverProdutoExistente() {
        service.cadastrar(novoProduto(1, "Cimento CP-II"));

        boolean removido = service.remover(1);

        assertTrue(removido);
        assertTrue(service.listar().isEmpty());
    }

    @Test
    void deveRetornarFalseAoRemoverProdutoInexistente() {
        assertFalse(service.remover(99));
    }
}
