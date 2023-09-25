package enums.api

enum PropostaEnum {
    Nova("Nova"),
    Pendente("Pendente"),
    Ilegitimo("Ilegitimo"),
    Comite("Comite")

    private final String value

    PropostaEnum(String value) {
        this.value = value
    }

    String value() {
        return value
    }
}