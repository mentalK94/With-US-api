package kr.co.mentalK94.withus.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

class FilenameUtilTest {

    @Test
    void make() {
        String str = "2020-07-13 정보처리기사.jpg";

        assertEquals(str, "2020-07-13 정보처리기사.jpg");

    }
}