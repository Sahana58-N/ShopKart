package com.ecommerce.Shopkart.Service;

import com.ecommerce.Shopkart.Dto.GeneralResponse;
import com.ecommerce.Shopkart.Dto.ProductInfo;

public interface ShopkartProductService {

    GeneralResponse addProduct(ProductInfo productInfo);

    GeneralResponse deleteProduct(ProductInfo productInfo);

    GeneralResponse fectchAllProducts();

    GeneralResponse editProducts(ProductInfo productInfo);
}
