package com.ecommerce.Shopkart.Controller;

import com.ecommerce.Shopkart.Dto.GeneralResponse;
import com.ecommerce.Shopkart.Dto.UserDetails;
import com.ecommerce.Shopkart.Service.ShopkartUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopkartUserController
{
    @Autowired
    ShopkartUserService shopkartService;
    @PostMapping("/userLogin")
    public GeneralResponse userLogin(@RequestBody UserDetails userDetails)
    {
       return shopkartService.userLogin(userDetails);
    }

    @PostMapping("/userRegister")
    public GeneralResponse userRegister(@RequestBody UserDetails userDetails)
    {
        return shopkartService.userRegister(userDetails);
    }

}
