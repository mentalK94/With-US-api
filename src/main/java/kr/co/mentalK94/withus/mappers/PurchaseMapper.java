package kr.co.mentalK94.withus.mappers;

import kr.co.mentalK94.withus.domains.Purchase;
import kr.co.mentalK94.withus.domains.PurchaseItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PurchaseMapper {

    void insertPurchase(Purchase purchase);

    void insertPurchaseItem(@Param("item")PurchaseItem purchaseItem, @Param("purchaseId")Long purchaseId);
}
