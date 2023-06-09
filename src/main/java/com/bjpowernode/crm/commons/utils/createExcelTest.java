package com.bjpowernode.crm.commons.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Classname createExcelTest
 * @Date 2023/3/7
 * @Created by YQ
 * 使用apache-opi生成excel文件
 */
public class createExcelTest {
    public static void main(String[] args) throws IOException {
        //创建HSSFWorkbook对象，对应一个excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //使用wb创建HSSFSheet对象,对应wb文件中的一页
        HSSFSheet sheet = wb.createSheet("学生列表");
        //使用sheet创建HSSFRow对象，对应wb文件中的一行
        HSSFRow row = sheet.createRow(0);//行号从0开始，以此增加
        //使用row创建HSSFCell对象，对应row中的一列
        HSSFCell cell = row.createCell(0);//列的编号，从0开始以此增加


        cell.setCellValue("学号");
        cell = row.createCell(1);
        cell.setCellValue("姓名");
        cell = row.createCell(2);
        cell.setCellValue("年龄");
        //创建样式
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        //使用sheet创建10个HSSFRow对象，对应sheet中的10行
        for (int i = 1; i <=10 ; i++) {
            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(100+i);
            cell = row.createCell(1);
            cell.setCellValue("NAME"+i);
            cell = row.createCell(2);
            //加入样式
            cell.setCellStyle(style);
            cell.setCellValue(20+i);
        }

        //调用工具函数生成excel
        OutputStream os = new FileOutputStream("E:\\JavaCreateExcelTest\\studentList.xls");
        wb.write(os);

        //释放资源
        os.close();

        wb.close();
    }
}
