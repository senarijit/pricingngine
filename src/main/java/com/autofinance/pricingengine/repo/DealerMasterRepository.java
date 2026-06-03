package com.autofinance.pricingengine.repo;

import com.autofinance.pricingengine.model.DealerMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealerMasterRepository extends JpaRepository<DealerMaster, String> {
    @Query("select d.dealerId from DealerMaster d where d.zipCode = :zipCode")
    List<String> findDealerIdByZipCode(@Param("zipCode") String zipCode);
}

