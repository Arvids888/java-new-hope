package com.app.model;

public class Translation {

    private long id;
    private long lang_id;
    private String page;
    private String text;
    private String key;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLang_id() {
        return lang_id;
    }

    public void setLang_id(long lang_id) {
        this.lang_id = lang_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
