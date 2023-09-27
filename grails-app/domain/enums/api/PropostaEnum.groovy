package enums.api

enum PropostaEnum {
    NOVA("Nova"),
    PENDENTE("Pendente"),
    ILEGITIMO("Ilegitimo"),
    COMITE("Comite"),
    FORMALIZADA("Formalizada"),
    PENDENTE_FORMALIZACAO("Pendente Formalizacao"),
    PENDENTE_INSTITUICAO_FINANCEIRA("Pendente Instituicao Financeira"),
    APROVADA_INSTITUICAO_FINANCEIRA("Aprovada Instituicao Financeira"),
    REPROVADA_INSTITUICAO_FINANCEIRA("Reprovada Instituicao Financeira"),
    CANCELADA("Cancelada"),
    APROVADA("Aprovada")

    private final String value

    PropostaEnum(String value) {
        this.value = value
    }

    String value() {
        return value
    }
}