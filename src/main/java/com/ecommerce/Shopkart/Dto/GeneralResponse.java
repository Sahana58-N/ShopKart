package com.ecommerce.Shopkart.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GeneralResponse {
    private String statusCode;
    private String statusDesc;

    private List<ProductInfo> productList;
}
