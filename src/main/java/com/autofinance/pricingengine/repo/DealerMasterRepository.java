package com.autofinance.pricingengine.repo;

import com.autofinance.pricingengine.model.DealerMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealerMasterRepository extends JpaRepository<DealerMaster, String> {
    // Standard CRUD methods are automatically inherited
}

