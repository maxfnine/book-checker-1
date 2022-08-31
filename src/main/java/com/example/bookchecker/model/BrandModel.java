package com.example.bookchecker.model;

public class BrandModel {
    private Boolean canSkip;
    private String brandName;
    private String baseURL;
    private String logoPath;
    private CarModel[] models;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public CarModel[] getModels() {
        return models;
    }

    public void setModels(CarModel[] models) {
        this.models = models;
    }

    public BrandModel() {
    }

    public Boolean getCanSkip() {
        return canSkip;
    }

    public void setCanSkip(Boolean canSkip) {
        this.canSkip = canSkip;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public BrandModel(String brandName, String baseURL, CarModel[] models, Boolean canSkip) {
        this.brandName = brandName;
        this.baseURL = baseURL;
        this.models = models;
        this.canSkip=canSkip;
    }
}
