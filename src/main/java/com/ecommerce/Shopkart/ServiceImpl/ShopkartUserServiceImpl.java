package com.ecommerce.Shopkart.ServiceImpl;

import com.ecommerce.Shopkart.Dto.GeneralResponse;
import com.ecommerce.Shopkart.Dto.UserDetails;
import com.ecommerce.Shopkart.Repo.UserDetailsRepository;
import com.ecommerce.Shopkart.Service.ShopkartUserService;
import com.ecommerce.Shopkart.Utility.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;



/***
 * @author Sahana N
 */

/**
 * The ShopkartServiceImpl is a class which implements ShopkartService interface
 *
 */
@Service
@Slf4j
public class ShopkartUserServiceImpl implements ShopkartUserService {
    @Autowired
    UserDetailsRepository userDetailsRepository;

    //GeneralResponse generalResponse=new GeneralResponse();

    /**
     * The userLogin is a method which will authenticate the user.
     * @param userDetails
     * @return
     */
    @Override
    public GeneralResponse userLogin(UserDetails userDetails) {
        String methodName=new Object(){
        }.getClass().getEnclosingMethod().getName();

        try {
            log.info(methodName+"has invoked");
            if (userDetails != null && userDetails.getUserId() != null && !userDetails.getUserId().isEmpty() && userDetails.getPassword() != null && !userDetails.getPassword().isEmpty() && !userDetails.getUserId().equalsIgnoreCase("null") && !userDetails.getUserId().equalsIgnoreCase("na") && !userDetails.getPassword().equalsIgnoreCase("null") && !userDetails.getPassword().equalsIgnoreCase("na")) {
                UserDetails userDetailsRepo = userDetailsRepository.findByUserId(userDetails.getUserId());
                if (userDetailsRepo != null && userDetailsRepo.getPassword().equals(userDetails.getPassword())) {

                    log.info("Login Successful");
                    return GeneralResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Login Successful").build();
                } else {
                    log.info("Login UnSuccessful");
                    return GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Login UnSuccessful").build();
                }
            } else {
                log.info("Incorrect Entry");
                return GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Incorrect Entry").build();
            }
        }catch(Exception e)
        {
            log.error(methodName,e+"error has occured");
        }
        return GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Something went wrong").build();
    }

    @Override
    public GeneralResponse userRegister(UserDetails userDetails) {
        String methodName=new Object(){
        }.getClass().getEnclosingMethod().getName();
        try {
            log.info(methodName+"has invoked");
            if (userDetails!=null && Util.stringChecker(userDetails.getName()) && Util.stringChecker(userDetails.getUserId()) && Util.stringChecker(userDetails.getPassword()) && Util.stringChecker(userDetails.getEmailId()) && Util.stringChecker(userDetails.getPhoneNo())) {
                userDetailsRepository.save(userDetails);
                log.info("Registered Successfully");
                return GeneralResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Registered Successfully").build();
            } else {
                log.info("Registration Failed");
                return GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Registration Failed").build();
            }
        }catch (Exception e)
        {
            log.error(methodName,e+"error has occured");
        }
        return GeneralResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Something went wrong").build();
    }


}
