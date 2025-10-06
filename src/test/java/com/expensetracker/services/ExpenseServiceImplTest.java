package com.expensetracker.services;

import com.expensetracker.data.models.Type;
import com.expensetracker.dtos.requests.AddExpenseRequest;
import com.expensetracker.dtos.requests.RegisterRequest;
import com.expensetracker.dtos.response.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ExpenseServiceImplTest {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private ExpenseServiceImpl expenseServiceImpl;

    @BeforeEach
    void setUp() {
        expenseServiceImpl.deleteAll();
    }

    @Test
    void saveExpense() {
        RegisterRequest request = new RegisterRequest();
        request.setFirstname("firstname");
        request.setLastname("lastname");
        request.setEmail("email");
        request.setUsername("username");
        request.setPassword("password");

        UserResponse response = userServiceImpl.registerUser(request);

        AddExpenseRequest request1 = new AddExpenseRequest();
        request1.setUserId(response.getUserId());
        request1.setName("Milk");
        request1.setAmount(1000);
        request1.setCategory("Food");
        request1.setType(Type.DIRECT);
        request1.setCreatedAt(LocalDate.now());
        request1.setDueDate(LocalDate.now().plusMonths(1));
        expenseServiceImpl.saveExpense(request1);
        assertThat(expenseServiceImpl.findByUserId(response.getUserId()).size())
                .isEqualTo(1);
    }

    @Test
    void findAll() {
    }

    @Test
    void findByUserId() {
    }

    @Test
    void findById() {
    }
}