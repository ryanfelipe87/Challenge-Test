package com.org.ecm.challengetest.enums;

import com.org.ecm.challengetest.exceptions.BadRequestException;

public enum ClassificacaoIndicativa {

    LIVRE("L", "Classificação Livre"),
    DEZ("10", "Não recomendado para menores de 10 anos"),
    DOZE("12", "Não recomendado para menores de 12 anos"),
    CATORZE("14", "Não recomendado para menores de 14 anos"),
    DEZESSEIS("16", "Não recomendado para menores de 16 anos"),
    DEZOITO("18", "Não recomendado para menores de 18 anos");

    private final String codigo;
    private final String descricao;

    ClassificacaoIndicativa(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static ClassificacaoIndicativa getByCodigo(String codigo) {
        for (ClassificacaoIndicativa value : ClassificacaoIndicativa.values()) {
            if (value.codigo.equals(codigo)) {
                return value;
            }
        }
        throw new BadRequestException("Código de classificação indicativa inválido: " + codigo);
    }
}
