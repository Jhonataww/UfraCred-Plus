package enums.api

enum StatusEnum {
    EM_ANALISE("Em Analise"),
    PENDENTE("Pendente"),
    ILEGITIMO("Ilegitimo"),
    COMITE("Comite")

    private final String value

    StatusEnum(String value) {
        this.value = value
    }

    String value() {
        return value
    }
}