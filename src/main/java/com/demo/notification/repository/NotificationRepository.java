package com.demo.notification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.notification.model.Notification;


@Repository
@Transactional
public interface NotificationRepository extends CrudRepository<Notification, String> {

	@Query("Select n from Notification n join n.dispositionSet d where "
			+ "(:channel = d.channel ) ")
	public List<Notification> getByChannel(@Param("channel") String channel);

	@Query("Select n from Notification n join n.dispositionSet d where "
			+ "(:deliveredDate = d.deliveredDate ) ")
	public List<Notification> getByDeliveredDate(@Param("deliveredDate") String deliveredDate);

	@Query("Select n from Notification n where "
			+ "(:notificationType = n.notificationType) ")
	public List<Notification> getByType(@Param("notificationType") String notificationType);

}
