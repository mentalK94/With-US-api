package kr.co.mentalK94.withus.mappers;

import kr.co.mentalK94.withus.domains.CartItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CartItemMapper {

    List<CartItem> selectCartItemByUserId(Long userId) throws Exception; // cartId에 따른 cartItem리스트 조회

    void insertCartItem(CartItem cartItem); // cartItem 추가

    void updateCartItem(CartItem cartItem); // cartItem 수정

    int deleteCartItemByUserId(Long userId); // 해당 cartItems 모두 삭제

    int deleteCartItemByProductIdAndUserId(@Param("userId") Long userId, @Param("productId")Long productId); // 해당 상품 삭제

}
