package enums.api

enum StatusEnum {
    EM_ANALISE("Em Analise"),
    APROVADO("Aprovado"),
    NEGADO("Negado"),
    PENDENTE("Pendente"),
    ILEGITIMO("Ilegitimo"),
    COMITE("Comite"),
    LOTE("Lote")

    private final String value

    StatusEnum(String value) {
        this.value = value
    }

    String value() {
        return value
    }
}