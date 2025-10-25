package com.expensetracker.services;

import com.expensetracker.data.models.Type;
import com.expensetracker.dtos.requests.AddExpenseRequest;
import com.expensetracker.dtos.requests.RegistrationRequest;
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
    public UserServiceImpl userServiceImpl;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ExpenseServiceImpl expenseServiceImpl;

    @BeforeEach
    void setUp() {
        expenseServiceImpl.deleteAll();
    }

    @BeforeEach
    void setup() {
        userServiceImpl.deleteAll();
    }

    @Test
    void saveExpense() {
        RegistrationRequest request = new RegistrationRequest();
        request.setFirstname("firstname");
        request.setLastname("lastname");
        request.setEmail("email@gmail.com");
        request.setUsername("username");
        request.setPassword("password");

        authenticationService.register(request);

        UserResponse response = userServiceImpl.findByUsername("username");

        AddExpenseRequest request1 = new AddExpenseRequest();
        request1.setName("Milk");
        request1.setAmount(1000);
        request1.setCategory("Food");
        request1.setType(Type.DIRECT);
        request1.setCreatedAt(LocalDate.now());
        request1.setDueDate(LocalDate.now().plusMonths(1));
        expenseServiceImpl.saveExpense(request1, response.getUsername());

        assertThat(expenseServiceImpl.findByUserId(response.getUserId()).size())
                .isEqualTo(1);
    }

}