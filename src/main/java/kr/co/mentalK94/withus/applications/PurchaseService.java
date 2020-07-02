package kr.co.mentalK94.withus.applications;

import kr.co.mentalK94.withus.domains.Product;
import kr.co.mentalK94.withus.domains.Purchase;
import kr.co.mentalK94.withus.domains.PurchaseItem;
import kr.co.mentalK94.withus.mappers.ProductMapper;
import kr.co.mentalK94.withus.mappers.PurchaseMapper;
import kr.co.mentalK94.withus.mappers.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService {

    private PurchaseMapper purchaseMapper;

    private ProductMapper productMapper;

    private Logger logger = LoggerFactory.getLogger(PurchaseService.class);

    @Autowired
    public PurchaseService(PurchaseMapper purchaseMapper, ProductMapper productMapper) {
        this.purchaseMapper = purchaseMapper;
        this.productMapper = productMapper;
    }

    public void addPurchase(Purchase purchase) {
        // 구매내용 저장
        purchaseMapper.insertPurchase(purchase);
    }

    public void addPurchaseItem(List<PurchaseItem> purchaseItems, Long purchaseId) {
        // 구매상품 목록 저장
        for(PurchaseItem purchaseItem : purchaseItems) {

            int stock = productMapper.selectProductStock(purchaseItem.getProductId());
            // 구매한 상품 재고 갱신
            productMapper.updateProductStock(purchaseItem.getProductId(),
                    stock-purchaseItem.getQuantity());

            purchaseMapper.insertPurchaseItem(purchaseItem, purchaseId);
        }
    }

    public Purchase getPurchaseInfo(Long purchaseId, Long userId) {
        return purchaseMapper.selectByPurchaseId(purchaseId, userId);
    }

    public List<Purchase> getPurchaseList(Long userId) throws Exception {
        List<Purchase> purchaseList = purchaseMapper.selectByUserId(userId);

        // 구매내역이 존재하는 경우
        for(Purchase p : purchaseList) {
            ArrayList<PurchaseItem> purchaseItemList = (ArrayList<PurchaseItem>) getPurchaseItemList(p.getId());

            for(PurchaseItem purchaseItem : purchaseItemList) {
                Product product = productMapper.selectProduct(purchaseItem.getProductId());
                purchaseItem.setProduct(product);
            }

            p.setPurchaseItems(purchaseItemList);
        }

        return purchaseList;

    }

    public List<PurchaseItem> getPurchaseItemList(Long purchaseId) {
        return purchaseMapper.selectItemByPurchaseId(purchaseId);
    }
}
