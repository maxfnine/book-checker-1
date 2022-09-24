package com.example.bookchecker.service;

import com.example.bookchecker.model.BookURLs;
import com.example.bookchecker.model.BookUrl;
import com.example.bookchecker.model.CarModel;
import com.example.bookchecker.model.dto.BookStatus;
import com.example.bookchecker.model.dto.CarModelOutput;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.Callable;

public class CarModelCheckerTask implements Callable<CarModelOutput> {
    private interface GetValue<T>{
        T getValue();
    }

    private interface SetValue<T>{
        void setValue(T value);
    }

    private String baseURL;
    private CarModel model;
    private CarModelOutput outputModel;

    public CarModelCheckerTask(String baseURL, CarModel model, CarModelOutput outputModel) {
        this.baseURL = baseURL;
        this.model = model;
        this.outputModel = outputModel;
    }

    @Override
    public CarModelOutput call() throws Exception {
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

    private void handleBook(Document doc, BookUrl bookUrl, GetValue<BookStatus> getter, SetValue<BookStatus> setter) {
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
