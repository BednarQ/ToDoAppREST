package com.ToDoApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ToDoApp.model.ColumnAggr;

import org.springframework.stereotype.Repository;

@Repository
public interface ColumnAggrRepository extends JpaRepository<ColumnAggr, Long> {

}
