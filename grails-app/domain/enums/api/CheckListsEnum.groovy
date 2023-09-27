package enums.api

enum CheckListsEnum {
    APROVADO("Aprovado"),
    APROVADO_COMITE("Aprovado Comite"),
    NEGADO_COMITE("Negado Comite"),
    NEGADO("Negado"),
    SIMULACAO("Simulacao"),
    ATUALIZACAO("Atualizacao"),
    PENDENTE_INTEGRACAO("Pendente Integracao")

    private final String value

    CheckListsEnum(String value) {
        this.value = value
    }

    String value() {
        return value
    }
}