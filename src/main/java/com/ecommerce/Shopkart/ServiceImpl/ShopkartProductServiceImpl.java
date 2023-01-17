package com.ecommerce.Shopkart.ServiceImpl;

import com.ecommerce.Shopkart.Dto.GeneralResponse;
import com.ecommerce.Shopkart.Dto.ProductInfo;
import com.ecommerce.Shopkart.Repo.ProductInfoRepository;
import com.ecommerce.Shopkart.Service.ShopkartProductService;
import com.ecommerce.Shopkart.Utility.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ShopkartProductServiceImpl implements ShopkartProductService
{

    @Autowired
    ProductInfoRepository productInfoRepository;
    @Override
    public GeneralResponse addProduct(ProductInfo productInfo) {
        String methodName=new Object(){
        }.getClass().getEnclosingMethod().getName();
        try {
            log.info(methodName+"has invoked");
            if (productInfo != null && Util.stringChecker(productInfo.getProductName()) && Util.stringChecker(productInfo.getProductColor()) && Util.stringChecker(productInfo.getDescription()) && productInfo.getProductCost() > 0.0) {
                productInfoRepository.save(productInfo);
                log.info("Product Added Successfully");
                return GeneralResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Product Added Successfully").build();
            } else {
                log.info("Insufficient Data");
                return GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Insufficient Data").build();
            }
        }catch (Exception e)
        {
            log.error(methodName,e+"error has occurred");
        }
        return GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Something went wrong").build();
    }

    @Override
    public GeneralResponse deleteProduct(ProductInfo productInfo) {
        String methodName=new Object(){
        }.getClass().getEnclosingMethod().getName();
        try {
            log.info(methodName+"has invoked");
            if (productInfo != null) {
                boolean b = productInfoRepository.existsById(productInfo.getProductId());
                if (b) {
                    productInfoRepository.deleteById(productInfo.getProductId());
                    log.info("Product Deleted Successfully");
                    return GeneralResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Product Deleted Successfully").build();
                } else{
                    log.info("Product with Id:"+productInfo.getProductId()+"does not exist");
                    return GeneralResponse.builder().statusCode(HttpStatus.NOT_FOUND.value() + "").statusDesc("Product with Id:"+productInfo.getProductId()+" does not exist").build();
                }
            }else {
                log.info("Insufficient Data");
                return GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Insufficient Data").build();
            }
        }catch (Exception e)
        {
            log.error(methodName,e+"error has occurred");
        }
        return GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Something went wrong").build();
    }

    @Override
    public GeneralResponse fectchAllProducts() {
        String methodName=new Object(){
        }.getClass().getEnclosingMethod().getName();
        try {
            log.info(methodName+"has invoked");
            List<ProductInfo> productInfoList = productInfoRepository.findAll();
            if (productInfoList != null) {
                log.info("Products fetched Successfully");
                return GeneralResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Products fetched Successfully").productList(productInfoList).build();
            } else {
                log.info("Fetch UnSuccessful");
                return GeneralResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Fetch UnSuccessful").build();
            }
        }catch (Exception e)
        {
            log.error(methodName,e+"error has occurred");
        }
        return GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Something went wrong").build();
    }

    @Override
    public GeneralResponse editProducts(ProductInfo productInfo) {
        String methodName=new Object(){
        }.getClass().getEnclosingMethod().getName();
        try {
            log.info(methodName+"has invoked");
            if (productInfo != null && productInfo.getProductId()!=null && productInfo.getProductId()>0 && productInfoRepository.existsById(productInfo.getProductId())) {
                if(Util.stringChecker(productInfo.getProductName()) && Util.stringChecker(productInfo.getProductColor()) && Util.stringChecker(productInfo.getDescription()) && productInfo.getNoOfProducts()!=null && productInfo.getProductCost()!=null && productInfo.getProductCost()>0.0)
                {
                    ProductInfo save = productInfoRepository.save(productInfo);
                    if(save!=null)
                    {
                        log.info("Products Updated Successfully");
                        return GeneralResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Products Updated Successfully").build();
                    }else {
                        log.info("Product Update UnSuccessful");
                        return GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Product Update UnSuccessful").build();
                    }
                }else {
                    log.info("Insufficient Data");
                    return GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Insufficient Data").build();
                }
            } else {
                if(productInfo!=null && productInfo.getProductId()!=null && productInfo.getProductId()!=0) {
                    log.info("No product exists with Id " + productInfo.getProductId());
                    return GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("No product exists with Id " + productInfo.getProductId()).build();
                }else{
                    log.info("No product Id Entered");
                    return GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("No product Id Entered").build();
                }
            }
        }catch (Exception e)
        {
            log.error(methodName,e+"error has occurred");
        }
        return GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Something went wrong").build();
    }
}

