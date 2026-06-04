package com.autofinance.pricingengine.repo;

import com.autofinance.pricingengine.model.RateMaster;
import com.autofinance.pricingengine.model.VehicleMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateMasterRepository extends JpaRepository<RateMaster, Long> {

    @Query("""
            SELECT DISTINCT r FROM RateMaster r
            JOIN VehicleMaster v ON r.dealerId = v.dealerId
                                AND r.make = v.make
                                AND r.model = v.model
                                AND r.vehicleYear = v.modelYear
                                AND (r.trim = v.trim OR (r.trim IS NULL AND v.trim IS NULL))
            WHERE r.creditTier = :creditTier
              AND r.financeType = 'Retail'
              AND v IN :vehicles
            """)
    List<RateMaster> findRetailRatesForVehicles(@Param("vehicles") List<VehicleMaster> vehicles,
                                                @Param("creditTier") String creditTier);
}
