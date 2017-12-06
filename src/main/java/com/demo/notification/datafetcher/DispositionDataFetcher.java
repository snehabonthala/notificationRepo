package com.demo.notification.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.notification.model.Disposition;
import com.demo.notification.model.Notification;
import com.demo.notification.repository.DispositionRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class DispositionDataFetcher implements DataFetcher<List<Disposition>>{

    @Autowired
    private DispositionRepository dispositionRepository;

    @Override
    public List<Disposition> get(DataFetchingEnvironment env) {

        Notification notification = env.getSource();
        return dispositionRepository.getDisposByNotification(notification.getId());

    }
}
