package com.example.bookchecker.model;

public class CarModel {
    private String name;
    private Boolean canBeSkipped;
    private String licenceNumber;
    private BookURLs bookUrls;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCanBeSkipped() {
        return canBeSkipped;
    }

    public void setCanBeSkipped(Boolean canBeSkipped) {
        this.canBeSkipped = canBeSkipped;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public BookURLs getBookUrls() {
        return bookUrls;
    }

    public void setBookUrls(BookURLs bookUrls) {
        this.bookUrls = bookUrls;
    }

    public CarModel() {
    }

    public CarModel(String name, Boolean canBeSkipped, String licenceNumber, BookURLs bookUrls) {
        this.name = name;
        this.canBeSkipped = canBeSkipped;
        this.licenceNumber = licenceNumber;
        this.bookUrls = bookUrls;
    }
}
