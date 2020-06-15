package kr.co.mentalK94.withus.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.mentalK94.withus.applications.PurchaseService;
import kr.co.mentalK94.withus.domains.Product;
import kr.co.mentalK94.withus.domains.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/purchase")
    public ResponseEntity<?> create(@RequestBody Purchase resource)
            throws URISyntaxException {

//        Claims claims = (Claims) authentication.getPrincipal();
//
//        Long userId = claims.get("userId", Long.class);
//
        Long userId = 3L;

        Purchase purchase = Purchase.builder().payAmount(resource.getPayAmount())
                .totalPrice(resource.getTotalPrice())
                .usingPoint(resource.getUsingPoint())
                .shippingAddress(resource.getShippingAddress())
                .shippingMemo(resource.getShippingMemo())
                .userId(userId)
                .build();

        purchaseService.addPurchase(purchase);

        URI location = new URI("/purchase/1");
        return ResponseEntity.created(location).body(1);
    }
}
