package com.example.bookchecker.model.dto;

import com.example.bookchecker.model.BookUrl;

public class CarModelOutput {
    private String name;
    private String licenceNumber;
    private BookStatus fullBook;
    private BookStatus shortBook;
    private BookStatus multimediaBook;
    private BookStatus maintenanceBook;
    private BookStatus guarantyBook;
    private BookStatus serviceListBook;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public BookStatus getFullBook() {
        return fullBook;
    }

    public void setFullBook(BookStatus fullBook) {
        this.fullBook = fullBook;
    }

    public BookStatus getShortBook() {
        return shortBook;
    }

    public void setShortBook(BookStatus shortBook) {
        this.shortBook = shortBook;
    }

    public BookStatus getMultimediaBook() {
        return multimediaBook;
    }

    public void setMultimediaBook(BookStatus multimediaBook) {
        this.multimediaBook = multimediaBook;
    }

    public BookStatus getMaintenanceBook() {
        return maintenanceBook;
    }

    public void setMaintenanceBook(BookStatus maintenanceBook) {
        this.maintenanceBook = maintenanceBook;
    }

    public BookStatus getGuarantyBook() {
        return guarantyBook;
    }

    public void setGuarantyBook(BookStatus guarantyBook) {
        this.guarantyBook = guarantyBook;
    }

    public BookStatus getServiceListBook() {
        return serviceListBook;
    }

    public void setServiceListBook(BookStatus serviceListBook) {
        this.serviceListBook = serviceListBook;
    }

    public CarModelOutput() {
        this.name = "Not provided";
        this.licenceNumber = "Not provided";
        this.fullBook=BookStatus.NOT_REQUIRED;
        this.shortBook=BookStatus.NOT_REQUIRED;
        this.multimediaBook=BookStatus.NOT_REQUIRED;
        this.maintenanceBook=BookStatus.NOT_REQUIRED;
        this.guarantyBook=BookStatus.NOT_REQUIRED;
        this.serviceListBook=BookStatus.NOT_REQUIRED;
    }

    public CarModelOutput(String name, String licenceNumber) {
        this.name = name;
        this.licenceNumber = licenceNumber;
        this.fullBook=BookStatus.NOT_REQUIRED;
        this.shortBook=BookStatus.NOT_REQUIRED;
        this.multimediaBook=BookStatus.NOT_REQUIRED;
        this.maintenanceBook=BookStatus.NOT_REQUIRED;
        this.guarantyBook=BookStatus.NOT_REQUIRED;
        this.serviceListBook=BookStatus.NOT_REQUIRED;

    }
}
