package enums.api

enum StatusEnum {
    EmAnalise("Em Analise"),
    Pendente("Pendente"),
    Ilegitimo("Ilegitimo"),
    Comite("Comite")

    private final String value

    StatusEnum(String value) {
        this.value = value
    }

    String value() {
        return value
    }
}