package com.example.bookchecker.model;

public class BookUrl {
     private Boolean required;
     private String url;
     private String cssQuery;

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCssQuery() {
        return cssQuery;
    }

    public void setCssQuery(String cssQuery) {
        this.cssQuery = cssQuery;
    }

    public BookUrl() {
    }

    public BookUrl(Boolean required, String url, String cssQuery) {
        this.required = required;
        this.url = url;
        this.cssQuery = cssQuery;
    }
}
