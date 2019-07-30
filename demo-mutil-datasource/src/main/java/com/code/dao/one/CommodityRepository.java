package com.code.dao.one;

import com.code.domain.one.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update t_commodity set stock = stock-?2 where stock>0 and id = ?1", nativeQuery = true)
    int decrStock(Integer commodityId, Integer num);
}