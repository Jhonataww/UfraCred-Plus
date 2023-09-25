package enums.api

enum CheckListsEnum {
    Aprovado("Aprovado"),
    Negado("Negado")

    private final String value

    CheckListsEnum(String value) {
        this.value = value
    }

    String value() {
        return value
    }
}