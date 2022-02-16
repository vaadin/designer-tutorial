package com.example.application.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, String> {

    List<Contact> findByFirstNameOrLastNameContainsIgnoreCase(String firstName, String lastName);

    @Query("SELECT DISTINCT c.company FROM Contact c")
    List<String> findDistinctCompanies();

    @Query("SELECT DISTINCT c.status FROM Contact c")
    List<String> findDistinctStatuses();
}