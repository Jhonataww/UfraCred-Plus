package enums.api

enum ClienteEnum {
    CADASTRADO("Cadastrado"),
    CADASTRO("Cadastro"),
    SIM("Sim"),
    NAO("Nao")

    private final String value

    ClienteEnum(String value) {
        this.value = value
    }

    String value() {
        return value
    }
}