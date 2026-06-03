package com.autofinance.pricingengine.repo;

import com.autofinance.pricingengine.model.VehicleMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VehicleMasterRepository extends JpaRepository<VehicleMaster, String> {

    List<VehicleMaster> findByDealerIdIn(List<String> dealerIds);
}
