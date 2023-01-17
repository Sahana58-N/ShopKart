package com.ecommerce.Shopkart;

import com.ecommerce.Shopkart.Dto.GeneralResponse;
import com.ecommerce.Shopkart.Dto.ProductInfo;
import com.ecommerce.Shopkart.Repo.ProductInfoRepository;
import com.ecommerce.Shopkart.ServiceImpl.ShopkartProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShopkartProductServiceImplTest
{
    @InjectMocks
    ShopkartProductServiceImpl shopkartProductService;

    @Mock
    ProductInfoRepository productInfoRepository;

    ProductInfo productInfo=new ProductInfo();

    @Test
    public void addProductNullTest()
    {
        GeneralResponse response=shopkartProductService.addProduct(null);
        GeneralResponse expected=GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Insufficient Data").build();
        Assertions.assertEquals(expected.getStatusCode(),response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(),response.getStatusDesc());
        Assertions.assertEquals(expected.getProductList(),response.getProductList() );
    }

    @Test
    public void addProductNotNullTest()
    {

        productInfo.setNoOfProducts(2);
        productInfo.setProductCost(90.87);
        productInfo.setProductColor("red");
        productInfo.setDescription("fdg");
        productInfo.setProductName("pen");
        GeneralResponse response=shopkartProductService.addProduct(productInfo);
        GeneralResponse expected=GeneralResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Product Added Successfully").build();
         Assertions.assertEquals(expected.getStatusCode(),response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(),response.getStatusDesc());
        Assertions.assertEquals(expected.getProductList(),response.getProductList() );

    }

    @Test
    public void addProductExceptionTest()
    {
        when(productInfoRepository.save(any())).thenReturn(new RuntimeException());
        productInfo.setNoOfProducts(2);
        productInfo.setProductCost(90.87);
        productInfo.setProductColor("red");
        productInfo.setDescription("fdg");
        productInfo.setProductName("pen");
        GeneralResponse response=shopkartProductService.addProduct(productInfo);
        GeneralResponse expected=GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Something went wrong").build();
        Assertions.assertEquals(expected.getStatusCode(),response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(),response.getStatusDesc());
        Assertions.assertEquals(expected.getProductList(),response.getProductList() );
    }

    //Delete Product
    @Test
    public void deleteProductNullTest()
    {
        GeneralResponse response=shopkartProductService.deleteProduct(null);
        GeneralResponse expected=GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Insufficient Data").build();
        Assertions.assertEquals(expected.getStatusCode(),response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(),response.getStatusDesc());
        Assertions.assertEquals(expected.getProductList(),response.getProductList() );
    }

    @Test
    public void deleteProductNotNullTest()
    {
        when(productInfoRepository.existsById(any())).thenReturn(Boolean.TRUE);
        GeneralResponse response=shopkartProductService.deleteProduct(productInfo);
        GeneralResponse expected=GeneralResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Product Deleted Successfully").build();
        Assertions.assertEquals(expected.getStatusCode(),response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(),response.getStatusDesc());
        Assertions.assertEquals(expected.getProductList(),response.getProductList() );
    }

    @Test
    public void deleteProductExistByIdIsFalseTest()
    {
        when(productInfoRepository.existsById(any())).thenReturn(Boolean.FALSE);
        GeneralResponse response=shopkartProductService.deleteProduct(productInfo);
        GeneralResponse expected=GeneralResponse.builder().statusCode(HttpStatus.NOT_FOUND.value() + "").statusDesc("Product with Id:"+productInfo.getProductId()+" does not exist").build();
        Assertions.assertEquals(expected.getStatusCode(),response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(),response.getStatusDesc());
        Assertions.assertEquals(expected.getProductList(),response.getProductList() );
    }

    @Test
    public void deleteProductExceptionTest()
    {
      //  when(productInfoRepository.deleteById(any())).thenThrow(new RuntimeException());
        when(productInfoRepository.existsById(any())).thenThrow(new RuntimeException());
        GeneralResponse response=shopkartProductService.deleteProduct(productInfo);
        GeneralResponse expected=GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Something went wrong").build();
        Assertions.assertEquals(expected.getStatusCode(),response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(),response.getStatusDesc());
        Assertions.assertEquals(expected.getProductList(),response.getProductList() );
    }

    //Fetch All Products
    @Test
    public void fetchAllProducts()
    {
        List<ProductInfo> productInfoList=null;
        when(productInfoRepository.findAll()).thenReturn(productInfoList);
        GeneralResponse response=shopkartProductService.fectchAllProducts();
        GeneralResponse expected=GeneralResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Fetch UnSuccessful").build();
        Assertions.assertEquals(expected.getStatusCode(),response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(),response.getStatusDesc());
        Assertions.assertEquals(expected.getProductList(),response.getProductList() );

    }

    @Test
    public void fetchAllNotNullProducts()
    {
        List<ProductInfo> productInfoList=new ArrayList<>();
        when(productInfoRepository.findAll()).thenReturn(productInfoList);
        GeneralResponse response=shopkartProductService.fectchAllProducts();
        GeneralResponse expected=GeneralResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Products fetched Successfully").productList(productInfoList).build();
        Assertions.assertEquals(expected.getStatusCode(),response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(),response.getStatusDesc());
        Assertions.assertEquals(expected.getProductList(),response.getProductList() );

    }

    @Test
    public void fetchAllExceptionProducts()
    {
        List<ProductInfo> productInfoList=new ArrayList<>();
        when(productInfoRepository.findAll()).thenThrow(new RuntimeException());
        GeneralResponse response=shopkartProductService.fectchAllProducts();
        GeneralResponse expected=GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Something went wrong").build();
        Assertions.assertEquals(expected.getStatusCode(),response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(),response.getStatusDesc());
        Assertions.assertEquals(expected.getProductList(),response.getProductList() );

    }

    //Edit Products

    @Test
    public void editProductTest()
    {
        GeneralResponse response=shopkartProductService.editProducts(null);
        GeneralResponse expected=GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("No product Id Entered").build();
        Assertions.assertEquals(expected.getStatusCode(),response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(),response.getStatusDesc());
        Assertions.assertEquals(expected.getProductList(),response.getProductList() );
    }

    @Test
    public void editProductNotNullTest()
    {
        when(productInfoRepository.existsById(any())).thenReturn(Boolean.FALSE);
        productInfo.setProductId(3);
        GeneralResponse response=shopkartProductService.editProducts(productInfo);
        GeneralResponse expected=GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("No product exists with Id " + productInfo.getProductId()).build();
        Assertions.assertEquals(expected.getStatusCode(),response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(),response.getStatusDesc());
        Assertions.assertEquals(expected.getProductList(),response.getProductList() );
    }

    @Test
    public void editProductExistByIdIsTrueTest()
    {
        when(productInfoRepository.existsById(any())).thenReturn(Boolean.TRUE);
        productInfo.setProductId(3);
        productInfo.setProductCost(45.33);
        productInfo.setProductName("pen");
        productInfo.setProductColor("red");
        productInfo.setNoOfProducts(3);
        GeneralResponse response=shopkartProductService.editProducts(productInfo);
        GeneralResponse expected=GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Insufficient Data").build();
        Assertions.assertEquals(expected.getStatusCode(),response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(),response.getStatusDesc());
        Assertions.assertEquals(expected.getProductList(),response.getProductList() );
    }

    @Test
    public void editProductValidInpuutTest()
    {
        when(productInfoRepository.save(any())).thenReturn(new ProductInfo());
        when(productInfoRepository.existsById(any())).thenReturn(Boolean.TRUE);
        productInfo.setProductId(3);
        productInfo.setProductCost(45.33);
        productInfo.setProductName("pen");
        productInfo.setProductColor("red");
        productInfo.setNoOfProducts(3);
        productInfo.setDescription("dfdg");
        GeneralResponse response=shopkartProductService.editProducts(productInfo);
        GeneralResponse expected=GeneralResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Products Updated Successfully").build();
        Assertions.assertEquals(expected.getStatusCode(),response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(),response.getStatusDesc());
        Assertions.assertEquals(expected.getProductList(),response.getProductList() );
    }

    @Test
    public void editProductInValidInpuutTest()
    {
        when(productInfoRepository.save(any())).thenReturn(null);
        when(productInfoRepository.existsById(any())).thenReturn(Boolean.TRUE);
        productInfo.setProductId(3);
        productInfo.setProductCost(45.33);
        productInfo.setProductName("pen");
        productInfo.setProductColor("red");
        productInfo.setNoOfProducts(3);
        productInfo.setDescription("dfdg");
        GeneralResponse response=shopkartProductService.editProducts(productInfo);
        GeneralResponse expected=GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Product Update UnSuccessful").build();
        Assertions.assertEquals(expected.getStatusCode(),response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(),response.getStatusDesc());
        Assertions.assertEquals(expected.getProductList(),response.getProductList() );
    }

    @Test
    public void editProductExceptionTest()
    {
        when(productInfoRepository.existsById(any())).thenThrow(new RuntimeException());
        productInfo.setProductId(3);
        GeneralResponse response=shopkartProductService.editProducts(productInfo);
        GeneralResponse expected=GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Something went wrong").build();
        Assertions.assertEquals(expected.getStatusCode(),response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(),response.getStatusDesc());
        Assertions.assertEquals(expected.getProductList(),response.getProductList() );
    }

}
