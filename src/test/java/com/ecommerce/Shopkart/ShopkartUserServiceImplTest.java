package com.ecommerce.Shopkart;

import com.ecommerce.Shopkart.Dto.GeneralResponse;
import com.ecommerce.Shopkart.Dto.UserDetails;
import com.ecommerce.Shopkart.Repo.UserDetailsRepository;
import com.ecommerce.Shopkart.ServiceImpl.ShopkartUserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShopkartUserServiceImplTest
{
    @InjectMocks
    ShopkartUserServiceImpl shopkartUserService;

    @Mock
    UserDetailsRepository userDetailsRepository;

    UserDetails userDetails=new UserDetails();

    @Test
    public  void userLoginTest()
    {
       GeneralResponse response= shopkartUserService.userLogin(null);
       GeneralResponse expected=GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Incorrect Entry").build();
        Assertions.assertEquals(expected.getStatusCode(),response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(),response.getStatusDesc());
        Assertions.assertEquals(expected.getProductList(),response.getProductList() );
    }

    @Test
    public  void userLoginNotNullTest()
    {
        userDetails.setUserId("Kush24");
        userDetails.setPassword("sana27");
        when(userDetailsRepository.findByUserId(any())).thenReturn(null);
        GeneralResponse response= shopkartUserService.userLogin(userDetails);
        GeneralResponse expected=GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Login UnSuccessful").build();
        Assertions.assertEquals(expected.getStatusCode(),response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(),response.getStatusDesc());
        Assertions.assertEquals(expected.getProductList(),response.getProductList() );
    }

    @Test
    public  void userLoginRepoReturnsNotNullTest()
    {
        UserDetails userDetailsDb=new UserDetails();
        userDetailsDb.setPassword("sana27");
        userDetails.setUserId("Kush24");
        userDetails.setPassword("sana27");
        when(userDetailsRepository.findByUserId(any())).thenReturn(userDetailsDb);
        GeneralResponse response= shopkartUserService.userLogin(userDetails);
        GeneralResponse expected=GeneralResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Login Successful").build();
        Assertions.assertEquals(expected.getStatusCode(),response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(),response.getStatusDesc());
        Assertions.assertEquals(expected.getProductList(),response.getProductList() );
    }

    @Test
    public  void userLoginExceptionTest()
    {
        userDetails.setUserId("Kush24");
        userDetails.setPassword("sana27");
        when(userDetailsRepository.findByUserId(any())).thenThrow(new RuntimeException());
        GeneralResponse response= shopkartUserService.userLogin(userDetails);
        GeneralResponse expected=GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Something went wrong").build();
        Assertions.assertEquals(expected.getStatusCode(),response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(),response.getStatusDesc());
        Assertions.assertEquals(expected.getProductList(),response.getProductList() );
    }

    //userRegister
    @Test
    public void userRegisterTest()
    {
        GeneralResponse response=shopkartUserService.userRegister(null);
        GeneralResponse expected=GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Registration Failed").build();
        Assertions.assertEquals(expected.getStatusCode(),response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(),response.getStatusDesc());
    }

    @Test
    public void userRegisterNotNullTest()
    {
        userDetails.setName("sana");
        userDetails.setUserId("sana27");
        userDetails.setPassword("kush24");
        userDetails.setEmailId("sana@gmail.com");
        userDetails.setPhoneNo("24325985");
        GeneralResponse response=shopkartUserService.userRegister(userDetails);
        GeneralResponse expected=GeneralResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Registered Successfully").build();
        Assertions.assertEquals(expected.getStatusCode(),response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(),response.getStatusDesc());
    }

    @Test
    public void userRegisterExceptionTest()
    {
        when(userDetailsRepository.save(any())).thenThrow(new RuntimeException());
        userDetails.setName("sana");
        userDetails.setUserId("sana27");
        userDetails.setPassword("kush24");
        userDetails.setEmailId("sana@gmail.com");
        userDetails.setPhoneNo("24325985");
        GeneralResponse response=shopkartUserService.userRegister(userDetails);
        GeneralResponse expected=GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Something went wrong").build();
        Assertions.assertEquals(expected.getStatusCode(),response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(),response.getStatusDesc());
    }

}
