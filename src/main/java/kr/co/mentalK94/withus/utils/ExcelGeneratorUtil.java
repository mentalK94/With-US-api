package kr.co.mentalK94.withus.utils;

import kr.co.mentalK94.withus.domains.Purchase;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

public class ExcelGeneratorUtil {

    static Logger logger = LoggerFactory.getLogger(ExcelGeneratorUtil.class);

    // 구매내역 -> 엑셀파일 다운로드
    public static ByteArrayInputStream purchaseHistoryToExcel(List<Purchase> purchaseList, String username) throws IOException {
        String[] Columns = {"구매번호", "구매상품 명", "총 금액", "사용 포인트", "결제 금액", "주문자", "배송지", "배송 메모", "연락처", "구매일시"};
        // String[] Columns = {"id", "productName", "totalPrice", "usingPoint", "payAmount", "customer", "shippingAddress", "shippingMemo", "phone", "date"};

        try(Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("aa");

//            Font headerFont = workbook.createFont();
//            headerFont.setFontName("맑은고딕");
//            headerFont.setBold(true);

            CellStyle headerCellStyle = workbook.createCellStyle();
            // headerCellStyle.setFont(headerFont);

            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < Columns.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(Columns[col]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowIdx = 1;
            for (Purchase purchase : purchaseList) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(purchase.getId());
                if (purchase.getPurchaseItems().size() == 1) {
                    row.createCell(1).setCellValue(purchase.getPurchaseItems().get(0).getProduct().getName());
                } else {
                    row.createCell(1).setCellValue(purchase.getPurchaseItems().get(0).getProduct().getName()
                            + "외 " + (purchase.getPurchaseItems().size() - 1) + "개 상품");
                }
                row.createCell(2).setCellValue(purchase.getTotalPrice());
                row.createCell(3).setCellValue(purchase.getUsingPoint());
                row.createCell(4).setCellValue(purchase.getPayAmount());
                row.createCell(5).setCellValue(username);
                row.createCell(6).setCellValue(purchase.getShippingAddress());
                row.createCell(7).setCellValue(purchase.getShippingMemo());
                row.createCell(8).setCellValue(purchase.getPhone());
                row.createCell(9).setCellValue(purchase.getDateTime());
            }

        // excel 파일 저장
        try {
            String path = "D:/"; //경로
            String fileName = "구매내역.xlsx"; //파일명

            File xlsFile = new File(path+fileName); //저장경로 설정
            FileOutputStream fileOut = new FileOutputStream(xlsFile);
            workbook.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);


            return new ByteArrayInputStream(outputStream.toByteArray());
        }
    }
}
