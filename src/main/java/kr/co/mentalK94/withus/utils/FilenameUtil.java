package kr.co.mentalK94.withus.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FilenameUtil {

    public static String make(String filename) {

        StringBuilder sb = new StringBuilder();

        // 현재 시간가져오기
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HHmmss ");

        String time = format.format(System.currentTimeMillis());

        sb.append(time);
        sb.append(filename);

        return sb.toString();
    }
}
