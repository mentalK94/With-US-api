package kr.co.mentalK94.withus.mappers;

import kr.co.mentalK94.withus.domains.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    void insertUser(Customer customer);

    Customer selectByUserEmail(String email);

    Customer selectByUserId(Long userId);

    int selectPointByUserId(Long userId);

    void updateCartByUserId(Customer customer);

    void updatePointByUserId(@Param("userId")Long userId, @Param("point")int point);

    void updateAuth(@Param("userId")Long userId, @Param("auth")int auth, @Param("initKey")String initKey);
}
