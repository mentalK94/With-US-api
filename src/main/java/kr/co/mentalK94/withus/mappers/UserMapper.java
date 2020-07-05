package kr.co.mentalK94.withus.mappers;

import kr.co.mentalK94.withus.domains.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    void insertUser(User user);

    User selectByUserEmail(String email);

    User selectByUserId(Long userId);

    int selectPointByUserId(Long userId);

    void updateCartByUserId(User user);

    void updatePointByUserId(@Param("userId")Long userId, @Param("point")int point);

    void updateAuth(@Param("userId")Long userId, @Param("status")int status);
}
