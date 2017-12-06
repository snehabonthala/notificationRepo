package com.demo.notification.datafetcher;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.notification.model.Notification;
import com.demo.notification.repository.NotificationRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllNotificationDataFetcher implements DataFetcher<List<Notification>> {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<Notification> get(DataFetchingEnvironment env) {
    	
    	List<Notification> notifList = new ArrayList<>();
    	String channel = null, deliveredDate = null, notificationType = null;
    	Map<String, Object> argMap = env.getArguments();
    	LinkedHashMap<String, Object> filterMap = (LinkedHashMap<String, Object>) argMap.get("filter");
    	
    	if(filterMap!=null){
    		channel = (String) filterMap.get("channel");
    		deliveredDate = (String) filterMap.get("deliveredDate");
    		notificationType = (String)filterMap.get("notificationType");
    	}
    	
    	if(channel!=null){
    		notifList.addAll(notificationRepository.getByChannel(channel));
    	}else if(deliveredDate!=null){
    		notifList.addAll(notificationRepository.getByDeliveredDate(deliveredDate));
    	}else if(notificationType!=null){
    		notifList.addAll(notificationRepository.getByType(notificationType));
    	}else{
    		notifList.addAll((List<Notification>) notificationRepository.findAll());
    	}
    	
    	return notifList;
    }
}
