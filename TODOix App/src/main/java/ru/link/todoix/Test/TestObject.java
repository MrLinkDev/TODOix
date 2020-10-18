package ru.link.todoix.Test;

public class TestObject {
    private final long id;
    private final String text;

    public TestObject(long id, String text){
        this.id = id;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
