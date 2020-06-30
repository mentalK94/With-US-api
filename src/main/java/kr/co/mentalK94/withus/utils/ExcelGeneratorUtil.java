package kr.co.mentalK94.withus.utils;

import kr.co.mentalK94.withus.domains.Purchase;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGeneratorUtil {

    // 구매내역 -> 엑셀파일 다운로드
    public static ByteArrayInputStream purchaseHistoryToExcel(List<Purchase> purchaseList, String username) throws IOException {
        String[] Columns = {"구매번호", "구매상품 명", "총 금액", "사용 포인트", "결제 금액", "주문자", "배송지", "배송 메모", "연락처", "구매일시"};

        try(
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
         ) {
            CreationHelper creationHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("purchaseHistory");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            Row headerRow = sheet.createRow(0);

            for(int col=0; col<Columns.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(Columns[col]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowIdx = 1;
            for(Purchase purchase : purchaseList) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(purchase.getId());
                if(purchase.getPurchaseItems().size() == 1) {
                    row.createCell(1).setCellValue(purchase.getPurchaseItems().get(0).getProduct().getName());
                } else {
                    row.createCell(1).setCellValue(purchase.getPurchaseItems().get(0).getProduct().getName()
                            + "외 " + (purchase.getPurchaseItems().size()-1) +"개 상품");
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

            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        }
    }
}
