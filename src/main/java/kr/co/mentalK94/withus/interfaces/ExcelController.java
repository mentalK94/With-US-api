package kr.co.mentalK94.withus.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.mentalK94.withus.applications.PurchaseService;
import kr.co.mentalK94.withus.domains.Purchase;
import kr.co.mentalK94.withus.utils.ExcelGeneratorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.util.List;

@RestController
public class ExcelController {

    @Autowired
    PurchaseService purchaseService;

    Logger logger = LoggerFactory.getLogger(ExcelController.class);

    @GetMapping("/downloadExcelFile/purchaseHistory.xls")
    public ResponseEntity<InputStreamResource> excelPurchaseHistory(Authentication authentication) throws Exception {
        Claims claims = (Claims) authentication.getPrincipal();

        String username = claims.get("name", String.class);

        Long userId = claims.get("userId", Long.class);

        List<Purchase> purchaseList = purchaseService.getPurchaseList(userId);

        ByteArrayInputStream inputStream = ExcelGeneratorUtil.purchaseHistoryToExcel(purchaseList, username);

        logger.info("istream" + inputStream);

        HttpHeaders httpHeaders = new HttpHeaders();


        httpHeaders.set("Content-Disposition", "attachment; filename=구매내역.xlsx");
        httpHeaders.set("Content-Type", "application/vnd.ms-excel");

        return ResponseEntity.ok().headers(httpHeaders).body(new InputStreamResource(inputStream));
    }
}
