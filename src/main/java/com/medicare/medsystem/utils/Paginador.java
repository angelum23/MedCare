package com.medicare.medsystem.utils;

import java.util.List;

public class Paginador {
    
    private Paginador() {
        // Construtor privado para evitar instanciação
    }

    public static <T> List<T> paginar(List<T> itens, int pagina, int itensPorPagina) {
        if (pagina < 1) {
            throw new IllegalArgumentException("Número da página deve ser maior que zero");
        }
        
        if (itensPorPagina < 1) {
            throw new IllegalArgumentException("Quantidade de itens por página deve ser maior que zero");
        }
        
        int inicio = (pagina - 1) * itensPorPagina;
        
        if (itens.size() <= inicio) {
            throw new IllegalArgumentException("Nenhum registro encontrado para a página solicitada");
        }
        
        return itens.stream()
                .skip(inicio)
                .limit(itensPorPagina)
                .toList();
    }
}
