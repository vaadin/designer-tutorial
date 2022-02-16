package com.example.application.data;

import com.vaadin.exampledata.ChanceStringType;
import com.vaadin.exampledata.DataType;
import com.vaadin.exampledata.ExampleDataGenerator;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(ContactRepository contactRepository) {
        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            int seed = 123;

            logger.info("Generating demo data");

            logger.info("... generating random Contact entities...");
            DataType<String> company = new ChanceStringType("company", "{ full: true }");
            ExampleDataGenerator<Contact> contactRepositoryGenerator = new ExampleDataGenerator<>(Contact.class,
                    LocalDateTime.of(2022, 2, 11, 0, 0, 0));
            contactRepositoryGenerator.setData(Contact::setFirstName, DataType.FIRST_NAME);
            contactRepositoryGenerator.setData(Contact::setLastName, DataType.LAST_NAME);
            contactRepositoryGenerator.setData(Contact::setEmail, DataType.EMAIL);
            contactRepositoryGenerator.setData(Contact::setCompany, company);
            contactRepositoryGenerator.setData(Contact::setStatus, DataType.TRANSACTION_STATUS);
            List<Contact> contacts = contactRepositoryGenerator.create(100, seed);
            contacts.forEach(contact -> {if(contact.getEmail()==null) contacts.remove(contact);});
            contactRepository.saveAll(contacts);

            logger.info("Generated demo data");
        };
    }

}