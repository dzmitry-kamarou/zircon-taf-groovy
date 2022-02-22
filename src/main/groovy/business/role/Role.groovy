package business.role

enum Role {
    USER('USER'),
    ADMIN('ADMIN')

    private final String value

    Role(String value) {
        this.value = value
    }

    String getValue() {
        return value
    }
}
