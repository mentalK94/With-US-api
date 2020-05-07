package kr.co.mentalK94.withus.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.mentalK94.withus.applications.CartItemService;
import kr.co.mentalK94.withus.applications.CartService;
import kr.co.mentalK94.withus.applications.ProductService;
import kr.co.mentalK94.withus.applications.UserService;
import kr.co.mentalK94.withus.domains.Cart;
import kr.co.mentalK94.withus.domains.CartItem;
import kr.co.mentalK94.withus.domains.Product;
import kr.co.mentalK94.withus.domains.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    Logger logger = LoggerFactory.getLogger(CartController.class);

    @GetMapping("/cart")
    public ResponseEntity<?> getCart(Authentication authentication) throws Exception {

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);
        User user = userService.getMyUser(userId);
        Cart userCart = cartService.getCartById(user.getCartId());

        if(userCart == null) { // 사용자의 장바구니가 존재하지 않는 경우
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // response 204
        }

        return new ResponseEntity<Cart>(userCart, HttpStatus.OK);
    }

    @DeleteMapping("/cart")
    public ResponseEntity<?> delete(Authentication authentication) throws Exception {

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);
        User user = userService.getMyUser(userId);
        Cart userCart = cartService.getCartById(user.getCartId());

        cartItemService.removeAllCartItems(userCart);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/cart/{productId}")
    public ResponseEntity<?> deleteItem(Authentication authentication,
                                        @PathVariable("productId") Long productId)
                                        throws Exception {

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);
        User user = userService.getMyUser(userId);
        Cart userCart = cartService.getCartById(user.getCartId());

        cartItemService.removeCartItem(userCart, productId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/cart/add/{productId}")
    public ResponseEntity<?> addItem(@PathVariable("productId") Long productId,
                                    Authentication authentication) throws Exception {

        Product product = productService.getProduct(productId);

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);
        User user = userService.getMyUser(userId);
        Cart userCart = cartService.getCartById(user.getCartId());

        if(userCart == null) { // 사용자에게 Cart가 없는 경우

            userCart = Cart.builder().grandTotalPrice(product.getPrice()).build();
            cartService.addCart(userCart);
            user.setCartId(userCart.getId());
            userService.updateCartMyUser(user);
            logger.info("userCart Id: " + userCart.getId());
        }

        List<CartItem> cartItems = cartItemService.getCartItems(userCart.getId());

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
                .cartId(userCart.getId())
                .build();

        // userCart.getCartItems().add(cartItem);
        cartItemService.addCartItem(cartItem);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
