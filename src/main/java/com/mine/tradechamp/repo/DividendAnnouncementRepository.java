package com.mine.tradechamp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mine.tradechamp.model.DividendAnnouncement;

// all the DB methods are already created automatically from this class
public interface DividendAnnouncementRepository extends JpaRepository<DividendAnnouncement, Long> {

}
