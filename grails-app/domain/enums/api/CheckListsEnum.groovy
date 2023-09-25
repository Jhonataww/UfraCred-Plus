package enums.api

enum CheckListsEnum {
    APROVADO("Aprovado"),
    NEGADO("Negado"),
    SIMULACAO("Simulacao"),
    ATUALIZACAO("Atualizacao")

    private final String value

    CheckListsEnum(String value) {
        this.value = value
    }

    String value() {
        return value
    }
}