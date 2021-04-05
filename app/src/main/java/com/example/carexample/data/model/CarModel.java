package com.example.carexample.data.model;

/*
{
    "status": 1,
    "data": [
        {
        "id": 21,
        "brand": "AUDI_3",
        "constractionYear": "01.2015",
        "isUsed": true, "imageUrl": "http://i.imgur.com/FG2eSjW.jpg"
        }
        ]
}
 */

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarModel {

    int status;
    @SerializedName("data")
    List<CarData> carDetailsList;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<CarData> getCarDetailsList() {
        return carDetailsList;
    }

    public void setCarDetailsList(List<CarData> carDetailsList) {
        this.carDetailsList = carDetailsList;
    }


    @Override
    public String toString() {
        return "CarModel{" +
                "status=" + status +
                ", carDetailsList=" + carDetailsList +
                '}';
    }
}
