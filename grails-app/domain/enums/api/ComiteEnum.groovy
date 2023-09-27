package enums.api

enum ComiteEnum {
    ASSESSOR("assessor"),
    COORDENADOR("coordenador"),

    private final String value

    ComiteEnum(String value) {
        this.value = value
    }

    String value() {
        return value
    }
}