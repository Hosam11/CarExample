package com.example.carexample.data.model;

/*
 {
        "id": 21,
        "brand": "AUDI_3",
        "constractionYear": "01.2015",
        "isUsed": true,
        "imageUrl": "http://i.imgur.com/FG2eSjW.jpg"
        }
 */
public class CarData {

     int id;
     String brand;
      String constractionYear;
     boolean isUsed;
     String imageUrl;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getConstractionYear() {
        return constractionYear;
    }

    public void setConstractionYear(String constractionYear) {
        this.constractionYear = constractionYear;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    @Override
    public String toString() {
        return "CarData{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", constractionYear='" + constractionYear + '\'' +
                ", isUsed=" + isUsed +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
