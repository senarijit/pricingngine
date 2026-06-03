package com.autofinance.pricingengine.repo;

import com.autofinance.pricingengine.model.RateMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateMasterRepository extends JpaRepository<RateMaster, Long> {

    @Query(value = """
            SELECT r.*
            FROM public.standard_rates r
            JOIN public.vehicle_master v
              ON r.dealer_id = v.dealer_id
             AND r.make = v.make
             AND r.model = v.model
             AND r.model_year = v.model_year
             AND ((r.series = v.trim) OR (r.series IS NULL AND v.trim IS NULL))
            WHERE r.tier = :creditTier
              AND r.type = 'Retail'
              AND r.dealer_id IN :dealerIds
            """, nativeQuery = true)
    List<RateMaster> findRetailRatesForVehicles(@Param("dealerIds") List<String> dealerIds,
                                                @Param("creditTier") String creditTier);
}
