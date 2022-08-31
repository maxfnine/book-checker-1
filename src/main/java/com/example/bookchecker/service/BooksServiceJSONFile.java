package com.example.bookchecker.service;

import com.example.bookchecker.model.BookURLs;
import com.example.bookchecker.model.BookUrl;
import com.example.bookchecker.model.BrandModel;
import com.example.bookchecker.model.CarModel;
import com.example.bookchecker.model.dto.BookStatus;
import com.example.bookchecker.model.dto.BrandModelOutput;
import com.example.bookchecker.model.dto.CarModelOutput;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class BooksServiceJSONFile {
    private final String BOOKS_JSON_PATH="/data/books_data.json";
    private List<BrandModel> brandModels;

    @PostConstruct
    private void initData() throws IOException {
        System.out.println("Getting data");
        TypeReference<List<BrandModel>> typeReference = new TypeReference<List<BrandModel>>() {};
        InputStream inputStream = typeReference.getClass().getResourceAsStream(BOOKS_JSON_PATH);
        List<BrandModel> data = new ObjectMapper().readValue(inputStream,typeReference);
        if(data!=null && !data.isEmpty()){
            System.out.println("Data loaded");
            this.brandModels=data;
        }
    }

    public List<BrandModelOutput> checkBooks(){
        List<BrandModelOutput> result = new ArrayList<BrandModelOutput>();
        for(BrandModel brandModel:brandModels){
            if(brandModel.getCanSkip()){
                continue;
            }else {
                if(brandModel.getCanSkip()){
                    continue;
                }else{
                    BrandModelOutput brandResults= validateBrand(brandModel);
                    result.add(brandResults);
                }
            }
        }
        return result;
    }

    private BrandModelOutput validateBrand(BrandModel brandModel) {
        BrandModelOutput brandModelOutput = new BrandModelOutput(brandModel.getBrandName(),brandModel.getLogoPath(), brandModel.getBaseURL());
        for(CarModel model:brandModel.getModels()){
            if(model.getCanBeSkipped()){
                continue;
            }else{
                CarModelOutput modelOutput= checkCarModel(brandModel.getBaseURL(),model);
                brandModelOutput.getCarModels().add(modelOutput);
            }

        }
        return brandModelOutput;
    }

    private CarModelOutput checkCarModel(String baseURL, CarModel model) {
        CarModelOutput outputModel = new CarModelOutput(model.getName(),model.getLicenceNumber());
        try {
            Document doc = Jsoup.connect(baseURL+model.getLicenceNumber()).get();
            processModelUrls(model,outputModel,doc);
            return outputModel;
        } catch (IOException e) {
            e.printStackTrace();
            return outputModel;
        }
    }

    private void processModelUrls(CarModel model, CarModelOutput outputModel, Document doc) {
        BookURLs bookUrls = model.getBookUrls();
        BookUrl bookUrl;
        Elements bookElements;
        String bookUrlString;
        bookUrl=bookUrls.getFullBook();
        handleFullBook(outputModel, doc, bookUrl);
        bookUrl=bookUrls.getShortBook();
        handleShortBook(outputModel, doc, bookUrl);
        bookUrl=bookUrls.getMultimediaBook();
        handleMultimediaBook(outputModel, doc, bookUrl);
        bookUrl=bookUrls.getMaintenanceBook();
        handleMaintenanceBook(outputModel, doc, bookUrl);
        bookUrl=bookUrls.getGuarantyBook();
        handleGuarantyBook(outputModel, doc, bookUrl);
        bookUrl=bookUrls.getServiceListBook();
        handleServiceListBook(outputModel, doc, bookUrl);
        return;
    }

    private void handleServiceListBook(CarModelOutput outputModel, Document doc, BookUrl bookUrl) {
        Elements bookElements;
        if(!bookUrl.getRequired()){
            outputModel.setServiceListBook(BookStatus.NOT_REQUIRED);
        }else{
            bookElements = doc.select(bookUrl.getCssQuery());
            if(bookElements.isEmpty()){
                outputModel.setServiceListBook(BookStatus.MISSING);
            }else{
                for(Element element:bookElements){
                    if(element.attr("href").equals(bookUrl.getUrl())){
                        outputModel.setServiceListBook(BookStatus.PRESENT);
                        break;
                    }
                }
                if(outputModel.getServiceListBook()==BookStatus.NOT_REQUIRED){
                    outputModel.setServiceListBook(BookStatus.CHANGED);
                }
            }
        }
    }

    private void handleGuarantyBook(CarModelOutput outputModel, Document doc, BookUrl bookUrl) {
        Elements bookElements;
        if(!bookUrl.getRequired()){
            outputModel.setGuarantyBook(BookStatus.NOT_REQUIRED);
        }else{
            bookElements = doc.select(bookUrl.getCssQuery());
            if(bookElements.isEmpty()){
                outputModel.setGuarantyBook(BookStatus.MISSING);
            }else{
                for(Element element:bookElements){
                    if(element.attr("href").equals(bookUrl.getUrl())){
                        outputModel.setGuarantyBook(BookStatus.PRESENT);
                        break;
                    }
                }
                if(outputModel.getGuarantyBook()==BookStatus.NOT_REQUIRED){
                    outputModel.setGuarantyBook(BookStatus.CHANGED);
                }
            }
        }
    }

    private void handleMaintenanceBook(CarModelOutput outputModel, Document doc, BookUrl bookUrl) {
        Elements bookElements;
        if(!bookUrl.getRequired()){
            outputModel.setMaintenanceBook(BookStatus.NOT_REQUIRED);
        }else{
            bookElements = doc.select(bookUrl.getCssQuery());
            if(bookElements.isEmpty()){
                outputModel.setMaintenanceBook(BookStatus.MISSING);
            }else{
                for(Element element:bookElements){
                    if(element.attr("href").equals(bookUrl.getUrl())){
                        outputModel.setMaintenanceBook(BookStatus.PRESENT);
                        break;
                    }
                }
                if(outputModel.getMaintenanceBook()==BookStatus.NOT_REQUIRED){
                    outputModel.setMaintenanceBook(BookStatus.CHANGED);
                }
            }
        }
    }

    private void handleFullBook(CarModelOutput outputModel, Document doc, BookUrl bookUrl) {
        Elements bookElements;
        if(!bookUrl.getRequired()){
            outputModel.setFullBook(BookStatus.NOT_REQUIRED);
        }else{
            bookElements = doc.select(bookUrl.getCssQuery());
            if(bookElements.isEmpty()){
                outputModel.setFullBook(BookStatus.MISSING);
            }else{
               for(Element element:bookElements){
                   if(element.attr("href").equals(bookUrl.getUrl())){
                       outputModel.setFullBook(BookStatus.PRESENT);
                       break;
                   }
               }
               if(outputModel.getFullBook()==BookStatus.NOT_REQUIRED){
                   outputModel.setFullBook(BookStatus.CHANGED);
               }
            }
        }
    }

    private void handleShortBook(CarModelOutput outputModel, Document doc, BookUrl bookUrl) {
        Elements bookElements;
        if(!bookUrl.getRequired()){
            outputModel.setShortBook(BookStatus.NOT_REQUIRED);
        }else{
            bookElements = doc.select(bookUrl.getCssQuery());
            if(bookElements.isEmpty()){
                outputModel.setShortBook(BookStatus.MISSING);
            }else{
                for(Element element:bookElements){
                    if(element.attr("href").equals(bookUrl.getUrl())){
                        outputModel.setShortBook(BookStatus.PRESENT);
                        break;
                    }
                }
                if(outputModel.getFullBook()==BookStatus.NOT_REQUIRED){
                    outputModel.setShortBook(BookStatus.CHANGED);
                }
            }
        }
    }

    private void handleMultimediaBook(CarModelOutput outputModel, Document doc, BookUrl bookUrl) {
        Elements bookElements;
        if(!bookUrl.getRequired()){
            outputModel.setMultimediaBook(BookStatus.NOT_REQUIRED);
        }else{
            bookElements = doc.select(bookUrl.getCssQuery());
            if(bookElements.isEmpty()){
                outputModel.setMultimediaBook(BookStatus.MISSING);
            }else{
                for(Element element:bookElements){
                    if(element.attr("href").equals(bookUrl.getUrl())){
                        outputModel.setMultimediaBook(BookStatus.PRESENT);
                        break;
                    }
                }
                if(outputModel.getMultimediaBook()==BookStatus.NOT_REQUIRED){
                    outputModel.setMultimediaBook(BookStatus.CHANGED);
                }
            }
        }
    }


    public void getDoc(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");
    }
}
