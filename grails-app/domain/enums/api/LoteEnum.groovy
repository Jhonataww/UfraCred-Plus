package enums.api

enum LoteEnum {
    CRIADO("Criado"),
    PROCESSANDO("Processando"),
    FINALIZADO("Finalizado")


    private final String value

    LoteEnum(String value) {
        this.value = value
    }

    String value() {
        return value
    }
}