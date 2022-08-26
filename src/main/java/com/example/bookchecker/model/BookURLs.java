package com.example.bookchecker.model;

public class BookURLs {
    private BookUrl fullBook;
    private BookUrl shortBook;
    private BookUrl multimediaBook;
    private BookUrl maintenanceBook;
    private BookUrl guarantyBook;
    private BookUrl serviceListBook;

    public BookUrl getFullBook() {
        return fullBook;
    }

    public void setFullBook(BookUrl fullBook) {
        this.fullBook = fullBook;
    }

    public BookUrl getShortBook() {
        return shortBook;
    }

    public void setShortBook(BookUrl shortBook) {
        this.shortBook = shortBook;
    }

    public BookUrl getMultimediaBook() {
        return multimediaBook;
    }

    public void setMultimediaBook(BookUrl multimediaBook) {
        this.multimediaBook = multimediaBook;
    }

    public BookUrl getMaintenanceBook() {
        return maintenanceBook;
    }

    public void setMaintenanceBook(BookUrl maintenanceBook) {
        this.maintenanceBook = maintenanceBook;
    }

    public BookUrl getGuarantyBook() {
        return guarantyBook;
    }

    public void setGuarantyBook(BookUrl guarantyBook) {
        this.guarantyBook = guarantyBook;
    }

    public BookUrl getServiceListBook() {
        return serviceListBook;
    }

    public void setServiceListBook(BookUrl serviceListBook) {
        this.serviceListBook = serviceListBook;
    }

    public BookURLs() {
    }

    public BookURLs(BookUrl fullBook, BookUrl shortBook, BookUrl multimediaBook, BookUrl maintenanceBook, BookUrl guarantyBook, BookUrl serviceListBook) {
        this.fullBook = fullBook;
        this.shortBook = shortBook;
        this.multimediaBook = multimediaBook;
        this.maintenanceBook = maintenanceBook;
        this.guarantyBook = guarantyBook;
        this.serviceListBook = serviceListBook;
    }
}
