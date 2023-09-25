package enums.api

enum PropostaEnum {
    NOVA("Nova"),
    PENDENTE("Pendente"),
    ILEGITIMO("Ilegitimo"),
    COMITE("Comite"),
    FORMALIZADA("Formalizada"),
    PENDENTE_FORMALIZACAO("Pendente Formalizacao"),
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