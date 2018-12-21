package com.example.financialmanagement.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicRecordRepository extends JpaRepository<BasicRecord, Integer> {

}