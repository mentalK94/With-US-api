package kr.co.mentalK94.withus.applications;

import kr.co.mentalK94.withus.domains.Cart;
import kr.co.mentalK94.withus.domains.CartItem;
import kr.co.mentalK94.withus.mappers.CartItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    CartItemMapper cartItemMapper;

    // 장바구니 상품추가
    public void addCartItem(CartItem cartItem) {
        cartItemMapper.insertCartItem(cartItem);
    }

    // 장바구니 상품 업데이트
    public void updateCartItem(CartItem cartItem) {
        cartItemMapper.updateCartItem(cartItem);
    }

    // 장바구니 상품 전체삭제
    public void removeAllCartItems(Cart cart) {
        cartItemMapper.deleteCartItemByCartId(cart.getId()); // cart id에 해당하는 상품목록 모두 제거
    }

    // 장바구니 선택 상품 삭제
    public void removeCartItem(Cart cart, Long productId) {
        cartItemMapper.deleteCartItemByProductId(cart.getId(), productId);
    }

    public List<CartItem> getCartItems(Long cartId) throws Exception {
        return cartItemMapper.selectCartItemByCartId(cartId);
    }
}
