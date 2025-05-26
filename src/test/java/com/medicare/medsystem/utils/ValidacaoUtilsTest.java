package com.medicare.medsystem.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.medicare.medsystem.utils.ValidacaoUtils;

class ValidacaoUtilsTest {

    @Test
    void validarNaoNulo_quandoObjetoNaoNulo_naoLancaExcecao() {
        assertDoesNotThrow(() -> ValidacaoUtils.validarNaoNulo("teste", "Erro: nulo"));
    }

    @Test
    void validarNaoNulo_quandoObjetoNulo_lancaExcecao() {
        Exception excecao = assertThrows(Exception.class, () ->
                ValidacaoUtils.validarNaoNulo(null, "Erro esperado"));
        assertEquals("Erro esperado", excecao.getMessage());
    }

    @Test
    void validarNaoVazio_quandoStringValida_naoLancaExcecao() {
        assertDoesNotThrow(() -> ValidacaoUtils.validarNaoVazio("algo", "Mensagem de erro"));
    }

    @Test
    void validarNaoVazio_quandoStringVazia_lancaExcecao() {
        Exception excecao = assertThrows(Exception.class, () ->
                ValidacaoUtils.validarNaoVazio("   ", "Campo obrigatório"));
        assertEquals("Campo obrigatório", excecao.getMessage());
    }

    @Test
    void validarMaiorQueZero_quandoNumeroValido_naoLancaExcecao() {
        assertDoesNotThrow(() -> ValidacaoUtils.validarMaiorQueZero(1, "Erro"));
    }

    @Test
    void validarMaiorQueZero_quandoNumeroInvalido_lancaExcecao() {
        Exception excecao = assertThrows(Exception.class, () ->
                ValidacaoUtils.validarMaiorQueZero(0, "Valor inválido"));
        assertEquals("Valor inválido", excecao.getMessage());
    }
}
