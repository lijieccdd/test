package com.jay.test.springboot.util;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author : lijie
 * @Description :
 * @Date : Create in 2018/7/2 11:26
 * @Modified by :
 */
public class FileUtil {
    public static void exportToFile(String fileName,Workbook workbook){
        try {
            FileOutputStream outputStream = new FileOutputStream("e://data//"+fileName);
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
