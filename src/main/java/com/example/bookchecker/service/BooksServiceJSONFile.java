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

    private interface GetValue<T>{
        T getValue();
    }

    private interface SetValue<T>{
        void setValue(T value);
    }

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
        handleBook( doc,bookUrls.getFullBook(),outputModel::getFullBook,outputModel::setFullBook);
        handleBook( doc,bookUrls.getShortBook(),outputModel::getShortBook,outputModel::setShortBook);
        handleBook( doc,bookUrls.getMultimediaBook(),outputModel::getMultimediaBook,outputModel::setMultimediaBook);
        handleBook(doc,bookUrls.getMaintenanceBook(),outputModel::getMaintenanceBook,outputModel::setMaintenanceBook);
        handleBook(doc,bookUrls.getGuarantyBook(),outputModel::getGuarantyBook,outputModel::setGuarantyBook);
        handleBook(doc,bookUrls.getServiceListBook(),outputModel::getServiceListBook,outputModel::setServiceListBook);
        return;
    }

    private void handleBook( Document doc, BookUrl bookUrl, GetValue<BookStatus> getter, SetValue<BookStatus> setter) {
        Elements bookElements;
        if(!bookUrl.getRequired()){
            setter.setValue(BookStatus.NOT_REQUIRED);
        }else{
            bookElements = doc.select(bookUrl.getCssQuery());
            if(bookElements.isEmpty()){
                setter.setValue(BookStatus.MISSING);
            }else{
                for(Element element:bookElements){
                    if(element.attr("href").equals(bookUrl.getUrl())){
                        setter.setValue(BookStatus.PRESENT);
                        break;
                    }
                }
                if(getter.getValue()==BookStatus.NOT_REQUIRED){
                    setter.setValue(BookStatus.CHANGED);
                }
            }
        }
    }

}
