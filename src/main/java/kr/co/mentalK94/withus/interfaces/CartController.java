package kr.co.mentalK94.withus.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.mentalK94.withus.applications.CartItemService;
import kr.co.mentalK94.withus.applications.ProductService;
import kr.co.mentalK94.withus.applications.UserService;
import kr.co.mentalK94.withus.domains.Cart;
import kr.co.mentalK94.withus.domains.CartItem;
import kr.co.mentalK94.withus.domains.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    Logger logger = LoggerFactory.getLogger(CartController.class);

    @PostMapping("/cart")
    public ResponseEntity<?> getCart(Authentication authentication) throws Exception {

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);
        // User user = userService.getMyUser(userId);

        List<CartItem> cartItems = cartItemService.getCartItems(userId);

        if(cartItems == null) { // 사용자의 장바구니가 존재하지 않는 경우
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // response 204
        }

        for(int i=0; i<cartItems.size(); i++) { // 사용자가 장바구니에 추가한 상품들의 총 가격
            Product product = productService.getProduct(cartItems.get(i).getProductId());
            cartItems.get(i).setProduct(product);
        }

        int grandTotalPrice = 0;
        for(int i=0; i<cartItems.size(); i++) { // 사용자가 장바구니에 추가한 상품들의 총 가격
            grandTotalPrice += cartItems.get(i).getTotalPrice();
        }
        // Cart에 필요한 것 : CartItems
        Cart userCart = Cart.builder().cartItems(cartItems).grandTotalPrice(grandTotalPrice).build();

        return new ResponseEntity<>(userCart, HttpStatus.OK);
    }

    @DeleteMapping("/cart") // 전부 삭제
    public ResponseEntity<?> delete(Authentication authentication) throws Exception {

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);
        // User user = userService.getMyUser(userId);

        cartItemService.removeAllCartItems(userId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/cart/{productId}") // 해당 상품 삭제
    public ResponseEntity<?> deleteItem(Authentication authentication,
                                        @PathVariable("productId") Long productId)
                                        throws Exception {

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);
        // User user = userService.getMyUser(userId);
        // Cart userCart = cartService.getCartById(user.getCartId());

        cartItemService.removeCartItem(userId, productId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/cart/add/{productId}") // 장바구니에 상품 추가
    public ResponseEntity<?> addItem(@PathVariable("productId") Long productId,
                                    Authentication authentication) throws Exception {

        Product product = productService.getProduct(productId);

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);
        // User user = userService.getMyUser(userId);

        List<CartItem> cartItems = cartItemService.getCartItems(userId);

        // 추가할 상품이 장바구니에 존재하는지 여부 확인
        if(cartItems != null) {
            for(int i=0; i<cartItems.size(); i++) {
                if(product.getId() == cartItems.get(i).getProductId()) {  // 추가할 상품이 이미 장바구니에 존재하는 경우 -> 수량 증가
                    CartItem cartItem = cartItems.get(i);
                    cartItem.setQuantity(cartItem.getQuantity()+1);
                    cartItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());
                    cartItemService.updateCartItem(cartItem);

                    return new ResponseEntity<>(HttpStatus.OK);
                }
            }
        }

        // 장바구니에 없는 경우 -> 새로 추가
        CartItem cartItem = CartItem.builder().quantity(1)
                .totalPrice(product.getPrice())
                .productId(product.getId())
                .userId(userId)
                .build();

        // userCart.getCartItems().add(cartItem);
        cartItemService.addCartItem(cartItem);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
