package com.medicare.medsystem.utils;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import com.medicare.medsystem.utils.Paginador;

class PaginadorTest {

    @Test
    void paginar_quandoPaginaValida_retornaItensEsperados() {
        List<String> itens = Arrays.asList("A", "B", "C", "D", "E");
        List<String> resultado = Paginador.paginar(itens, 2, 2); // página 2, 2 por página

        assertEquals(List.of("C", "D"), resultado);
    }

    @Test
    void paginar_quandoPaginaEhZero_lancaExcecao() {
        List<String> itens = List.of("A", "B");

        Exception excecao = assertThrows(IllegalArgumentException.class, () ->
                Paginador.paginar(itens, 0, 1));

        assertEquals("Número da página deve ser maior que zero", excecao.getMessage());
    }

    @Test
    void paginar_quandoItensPorPaginaEhZero_lancaExcecao() {
        List<String> itens = List.of("A", "B");

        Exception excecao = assertThrows(IllegalArgumentException.class, () ->
                Paginador.paginar(itens, 1, 0));

        assertEquals("Quantidade de itens por página deve ser maior que zero", excecao.getMessage());
    }

    @Test
    void paginar_quandoPaginaNaoExiste_lancaExcecao() {
        List<String> itens = List.of("A", "B");

        Exception excecao = assertThrows(IllegalArgumentException.class, () ->
                Paginador.paginar(itens, 3, 2)); // página 3 não existe com 2 itens

        assertEquals("Nenhum registro encontrado para a página solicitada", excecao.getMessage());
    }
}
