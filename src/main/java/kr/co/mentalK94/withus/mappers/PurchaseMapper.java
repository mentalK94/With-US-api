//package kr.co.mentalK94.withus.mappers;
//
//import kr.co.mentalK94.withus.domains.Purchase;
//import kr.co.mentalK94.withus.domains.PurchaseItem;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Mapper
//@Repository
//public interface PurchaseMapper {
//
//    void insertPurchase(Purchase purchase);
//
//    void insertPurchaseItem(@Param("item")PurchaseItem purchaseItem, @Param("purchaseId")Long purchaseId);
//
//    Purchase selectByPurchaseId(@Param("purchaseId")Long purchaseId, @Param("userId")Long userId);
//
//    List<Purchase> selectByUserId(Long userId);
//
//    List<PurchaseItem> selectItemByPurchaseId(Long purchaseId);
//}
