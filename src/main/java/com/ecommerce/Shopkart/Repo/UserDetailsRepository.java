package com.ecommerce.Shopkart.Repo;

import com.ecommerce.Shopkart.Dto.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {
    public UserDetails findByUserId(String userId);
}
