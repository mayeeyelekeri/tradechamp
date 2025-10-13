package com.mine.tradechamp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mine.tradechamp.model.DividendPay;

// all the DB methods are already created automatically from this class
public interface DividendPayRepository extends JpaRepository<DividendPay, Long> {

}
