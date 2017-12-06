package com.demo.notification.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.notification.datafetcher.AllNotificationDataFetcher;
import com.demo.notification.datafetcher.DispositionDataFetcher;
import com.demo.notification.datafetcher.NotificationDataFetcher;
import com.demo.notification.model.Disposition;
import com.demo.notification.model.Greeting;
import com.demo.notification.repository.DispositionRepository;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;


@RestController
public class QueryController {

    private final Logger logger = LoggerFactory.getLogger(QueryController.class);

    @Value(value="classpath:notification.graphqls")
    private Resource schemaResource;

    private GraphQL graphQL;
    
    @Autowired
    private DispositionRepository dispositionRepository;

    @Autowired
    private AllNotificationDataFetcher allNotificationDataFetcher;

    @Autowired
    private NotificationDataFetcher notificationDataFetcher;

    @Autowired
    private DispositionDataFetcher dispositionDataFetcher;
    
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    @PostConstruct
    public void loadSchema() throws IOException {
        File schemaFile = schemaResource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring  = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry,wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {

        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allNotification", allNotificationDataFetcher)
                        .dataFetcher("notification", notificationDataFetcher))
                .type("Notification", typeWiring-> typeWiring
                        .dataFetcher("dispositions", dispositionDataFetcher))
                .build();
    }

    @RequestMapping(value="/query", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody String query){

        ExecutionResult result = graphQL.execute(query);
        logger.info(String.valueOf(result.getErrors()));
        return ResponseEntity.ok(result.getData());
    }

    @RequestMapping(value="/dispositions", method= RequestMethod.GET)
    public @ResponseBody List<Disposition> getAll(){
        return dispositionRepository.getDisposByNotification("19851D66-9A9F-43C5-9B15-2E621181BE9B");
    }
    
 
}
