package com.demo.notification.datafetcher;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.notification.model.Notification;
import com.demo.notification.repository.NotificationRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class NotificationDataFetcher implements DataFetcher<Notification>{

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notification get(DataFetchingEnvironment env) {
        Map arguments = env.getArguments();
        return notificationRepository.findOne((String) arguments.get("id"));
    }
}
