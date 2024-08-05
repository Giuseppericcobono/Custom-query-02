package co.develhope.exercise2_custom_query.enums;

public enum Status {
    ONTIME("ONTIME"),
    DELAYED("DELAYED"),
    CANCELLED("CANCELLED");

    private final String description;

    Status(String description) {

        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
