package model.enums;

public enum CourseName {
    QA1("Manual testing"),
    QA2("Automation testing"),
    QA3("Advanced testing"),
    JAVA("Web project development");

    private String name;

    CourseName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
