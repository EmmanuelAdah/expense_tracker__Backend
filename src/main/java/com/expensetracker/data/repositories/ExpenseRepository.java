package com.expensetracker.data.repositories;

import com.expensetracker.data.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, UUID> {

    List<Expense> findByUserId(UUID userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Expense e WHERE e.id = :id")
    void deleteExpenseById(@Param("id") UUID id);
}
