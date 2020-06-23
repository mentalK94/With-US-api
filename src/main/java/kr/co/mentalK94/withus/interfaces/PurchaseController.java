package kr.co.mentalK94.withus.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.mentalK94.withus.applications.CartItemService;
import kr.co.mentalK94.withus.applications.PurchaseService;
import kr.co.mentalK94.withus.applications.UserService;
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

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/purchase")
    public ResponseEntity<?> create(@RequestBody Purchase resource)
            throws URISyntaxException {

        Purchase purchase = Purchase.builder().payAmount(resource.getPayAmount())
                .totalPrice(resource.getTotalPrice())
                .usingPoint(resource.getUsingPoint())
                .shippingAddress(resource.getShippingAddress())
                .shippingMemo(resource.getShippingMemo())
                .purchaseItems(resource.getPurchaseItems())
                .phone(resource.getPhone())
                .userId(resource.getUserId())
                .build();

        // 구매 정보 추가
        purchaseService.addPurchase(purchase);

        // 구매 상품목록 추가
        purchaseService.addPurchaseItem(purchase.getPurchaseItems(), purchase.getId());

        // user 포인트 갱신
        userService.updateUsingPoint(purchase.getUserId(), purchase.getUsingPoint());

        // user 장바구니 목록 삭제
        cartItemService.removeAllCartItems(purchase.getUserId());

        URI location = new URI("/purchase/"+purchase.getId());
        return ResponseEntity.created(location).body(1);
    }
}
