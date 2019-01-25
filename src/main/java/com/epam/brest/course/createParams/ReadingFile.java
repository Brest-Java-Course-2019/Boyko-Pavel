package com.epam.brest.course.createParams;

import java.io.IOException;
import java.util.Objects;

public class ReadingFile {

    public String filePath (String fileName) throws IOException {
        return  Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getPath();
    }
}
