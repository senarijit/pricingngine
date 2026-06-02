package com.autofinance.pricingengine.repo;

import com.autofinance.pricingengine.model.CustomerMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerMasterRepository extends JpaRepository<CustomerMaster, String> {
    // Custom query methods can be defined here if needed
}

