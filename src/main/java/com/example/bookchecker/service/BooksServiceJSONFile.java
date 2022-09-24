package com.example.bookchecker.service;

import com.example.bookchecker.model.BrandModel;
import com.example.bookchecker.model.CarModel;
import com.example.bookchecker.model.dto.BrandModelOutput;
import com.example.bookchecker.model.dto.CarModelOutput;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


@Service
public class BooksServiceJSONFile {
    private final String BOOKS_JSON_PATH="/data/books_data.json";
    private List<BrandModel> brandModels;
    private ExecutorService executorService = Executors.newFixedThreadPool(10);



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

    @PreDestroy
    private void shutdown(){
        System.out.println("Shutting down");
        executorService.shutdown();
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
                    BrandModelOutput brandResults = validateBrand(brandModel);
                    result.add(brandResults);
                }
            }
        }
        return result;
    }

    private BrandModelOutput validateBrand(BrandModel brandModel) {
        BrandModelOutput brandModelOutput = new BrandModelOutput(brandModel.getBrandName(),brandModel.getLogoPath(), brandModel.getBaseURL());
        List<CarModelCheckerTask> modelsToCheck=new ArrayList<>();
        for(CarModel model:brandModel.getModels()){
            if(model.getCanBeSkipped()){
                continue;
            }else{
                CarModelOutput outputModel = new CarModelOutput(model.getName(),model.getLicenceNumber());
                modelsToCheck.add(new CarModelCheckerTask(brandModel.getBaseURL(),model,outputModel));
            }
        }

        try {
          List<Future<CarModelOutput>> result =  executorService.invokeAll(modelsToCheck);
            result.forEach((item)->{
                try {
                    brandModelOutput.getCarModels().add(item.get());
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException when getting results");
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    System.out.println("ExecutionException when getting results");
                    e.printStackTrace();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (CancellationException e) {
            e.printStackTrace();
        }
        return brandModelOutput;
    }





}
