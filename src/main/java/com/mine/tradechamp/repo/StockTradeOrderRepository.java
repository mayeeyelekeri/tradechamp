package com.mine.tradechamp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mine.tradechamp.model.StockTradeOrder;

// all the DB methods are already created automatically from this class
public interface StockTradeOrderRepository extends JpaRepository<StockTradeOrder, Long> {

}
