//package kr.co.mentalK94.withus.applications;
//
//import kr.co.mentalK94.withus.domains.Cart;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CartService {
//
//    @Autowired
//    CartMapper cartMapper;
//
//    public Cart getCartById(Long cartId) throws Exception { // id에 따른 cart 조회
//        return cartMapper.selectCartById(cartId);
//    }
//
//    public void addCart(Cart cart) { // 카트 추가
//        cartMapper.insertCart(cart);
//    }
//}
