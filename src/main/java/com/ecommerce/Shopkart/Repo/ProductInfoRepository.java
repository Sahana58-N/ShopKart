package com.ecommerce.Shopkart.Repo;

import com.ecommerce.Shopkart.Dto.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer> {
    ProductInfo findByProductId(int id);
}
