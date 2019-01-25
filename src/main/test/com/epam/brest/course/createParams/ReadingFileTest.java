package com.epam.brest.course.createParams;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;


class ReadingFileTest {
    private final static String WRONG_PATH = "home/koef.json";
    private final static String RIGHT_PATH = "koef.json";
    private ReadingFile file = new ReadingFile();

    @Test
    void testInvalidFilePath() {
        Assertions.assertThrows(NullPointerException.class, () -> file.filePath(WRONG_PATH));
    }

    @Test
    void testCorrectFilePath() throws IOException {
        Assertions.assertNotNull(file.filePath(RIGHT_PATH));
    }
}
