package com.ecommerce.Shopkart.Controller;

import com.ecommerce.Shopkart.Dto.GeneralResponse;
import com.ecommerce.Shopkart.Dto.ProductInfo;
import com.ecommerce.Shopkart.Service.ShopkartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopkartProductController
{
    @Autowired
    ShopkartProductService shopkartProductService;
    @PostMapping("/addProduct")
    public GeneralResponse addProduct(@RequestBody ProductInfo productInfo){
        return shopkartProductService.addProduct(productInfo);
    }

    @PostMapping("/deleteProduct")
    public GeneralResponse deleteProduct(@RequestBody ProductInfo productInfo)
    {
        return shopkartProductService.deleteProduct(productInfo);
    }

    @GetMapping("/getProducts")
    public GeneralResponse FetchAllProducts()
    {
        return shopkartProductService.fectchAllProducts();
    }

    @PostMapping("/editProduct")
    public GeneralResponse editProducts(@RequestBody ProductInfo productInfo)
    {
      return shopkartProductService.editProducts(productInfo);
    }
}
