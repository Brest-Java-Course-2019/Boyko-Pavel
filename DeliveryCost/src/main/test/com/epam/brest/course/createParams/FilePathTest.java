package com.epam.brest.course.createParams;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FilePathTest {
    private final static String WRONG_PATH = "home/coefficient.json";
    private final static String RIGHT_PATH = "coefficient.json";
    private FilePath file = new FilePath();


    @Test
    void testInvalidFilePath() {
        Assertions.assertThrows(NullPointerException.class, () -> file.getFilePath(WRONG_PATH));
    }

    @Test
    void testCorrectFilePath() throws IOException {
        Assertions.assertNotNull(file.getFilePath(RIGHT_PATH));
    }
}