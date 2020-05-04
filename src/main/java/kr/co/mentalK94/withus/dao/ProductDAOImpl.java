package kr.co.mentalK94.withus.dao;

import kr.co.mentalK94.withus.daoInterfaces.ProductDAO;
import kr.co.mentalK94.withus.domains.Product;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    @Qualifier("sqlSessionTemplate")
    private SqlSession sqlSession;

    private static final String insertProduct = "ProductMapper.insertProduct";
    private static final String selectProduct = "ProductMapper.selectProduct";
    private static final String selectProductList = "ProductMapper.selectProductList";

    @Override
    public void insertProduct(Product product) {
        sqlSession.insert(insertProduct, product);
    }

    @Override
    public Product selectProduct(Long id) {
        return sqlSession.selectOne(selectProduct, id);
    }

    @Override
    public List<Product> selectProductList() {
        return sqlSession.selectList(selectProductList);
    }
}
