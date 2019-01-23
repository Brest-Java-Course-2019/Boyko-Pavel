package com.epam.brest.course.createParams;

import java.util.Objects;

public  abstract class CreateDeliveryKoef {

    public abstract void setKoef();

    public abstract double getKoef();

    public String filePath (String fileName){
        return  Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getPath();
    }
}
