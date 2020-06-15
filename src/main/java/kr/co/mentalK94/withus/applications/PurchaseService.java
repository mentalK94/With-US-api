package kr.co.mentalK94.withus.applications;

import kr.co.mentalK94.withus.domains.Purchase;
import kr.co.mentalK94.withus.domains.PurchaseItem;
import kr.co.mentalK94.withus.mappers.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    private PurchaseMapper purchaseMapper;

    @Autowired
    public PurchaseService(PurchaseMapper purchaseMapper) {
        this.purchaseMapper = purchaseMapper;
    }

    public void addPurchase(Purchase purchase) {
        // TODO: addPurchase 구현

        // 구매상품 목록 저장
        for(PurchaseItem purchaseItem : purchase.getPurchaseItems()) {
            purchaseMapper.insertPurchaseItem(purchaseItem);
        }

        // 구매내용 저장
        purchaseMapper.insertPurchase(purchase);
    }
}
