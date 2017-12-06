package com.demo.notification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.notification.model.Disposition;

@Repository
@Transactional
public interface DispositionRepository extends CrudRepository<Disposition, String> {

    @Query("Select d from Disposition d where (d.notification.id = :notifId)")
    List<Disposition> getDisposByNotification(@Param("notifId")String notifId);
}
