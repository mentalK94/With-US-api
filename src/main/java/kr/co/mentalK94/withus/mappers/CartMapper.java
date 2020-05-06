package kr.co.mentalK94.withus.mappers;

import kr.co.mentalK94.withus.domains.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CartMapper {

    Cart selectCartById(Long id) throws Exception; // id 따른 cart 조회

    void insertCart(Cart cart); // cart 추가

    void updateCart(@Param("cart") Cart cart, @Param("id")Long id); // cart 수정

    int deleteCartById(Long id); // 해당 cart 삭제
}
