package kr.co.mentalK94.withus.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.mentalK94.withus.applications.PurchaseService;
import kr.co.mentalK94.withus.domains.Purchase;
import kr.co.mentalK94.withus.utils.ExcelGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
public class ExcelController {

    @Autowired
    PurchaseService purchaseService;

    @GetMapping("/downloadExcelFile")
    public ResponseEntity<?> excelPurchaseHistory(Authentication authentication) throws Exception {
        Claims claims = (Claims) authentication.getPrincipal();

        String username = claims.get("name", String.class);

        Long userId = claims.get("userId", Long.class);

        List<Purchase> purchaseList = purchaseService.getPurchaseList(userId);

        ByteArrayInputStream inputStream = ExcelGeneratorUtil.purchaseHistoryToExcel(purchaseList, username);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment; filename=purchase-history.xlsx");

        return ResponseEntity.ok().headers(httpHeaders).body(new InputStreamResource(inputStream));
    }
}
