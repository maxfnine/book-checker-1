package com.example.bookchecker.model.dto;

import java.util.ArrayList;
import java.util.List;

public class BrandModelOutput {
    private String brandName;
    private String logoPath;
    private String baseUrl;
    private List<CarModelOutput> carModels=new ArrayList<>();

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public List<CarModelOutput> getCarModels() {
        return carModels;
    }

    public void setCarModels(List<CarModelOutput> carModels) {
        this.carModels = carModels;
    }

    public BrandModelOutput(String brandName) {
        this.brandName = brandName;
    }

    public BrandModelOutput(String brandName, String logoPath, String baseUrl) {
        this.brandName = brandName;
        this.logoPath = logoPath;
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public BrandModelOutput() {
    }
}
