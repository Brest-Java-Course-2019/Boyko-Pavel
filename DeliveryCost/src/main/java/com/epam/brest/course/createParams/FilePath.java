package com.epam.brest.course.createParams;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.Objects;

public class FilePath {
    private final static Logger LOGGER = LogManager.getLogger();

    public String getFilePath(String fileName) throws FileNotFoundException {


        LOGGER.log(Level.DEBUG, fileName);
        String pathFile = Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getPath();
        if (pathFile == null) {
            LOGGER.error("File don't found {}");
            throw new FileNotFoundException();
        }
        return pathFile;
    }
}


