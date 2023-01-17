package com.ecommerce.Shopkart.Service;

import com.ecommerce.Shopkart.Dto.GeneralResponse;
import com.ecommerce.Shopkart.Dto.UserDetails;

public interface ShopkartUserService {
    GeneralResponse userLogin(UserDetails userDetails);

    GeneralResponse userRegister(UserDetails userDetails);
}
