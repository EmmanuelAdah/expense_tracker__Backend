package com.expensetracker.services;

import com.expensetracker.data.models.Type;
import com.expensetracker.dtos.requests.AddExpenseRequest;
import com.expensetracker.dtos.requests.RegistrationRequest;
import com.expensetracker.dtos.response.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import java.time.LocalDate;

@SpringBootTest
class ExpenseServiceImplTest {

    @Mock
    public UserServiceImpl userServiceImpl;

    @MockitoBean
    private AuthenticationService authenticationService;

    @InjectMocks
    private ExpenseServiceImpl expenseServiceImpl;


    @BeforeEach
    void setUp() {
        expenseServiceImpl.deleteAll();
    }

    @Test
    void saveExpense() {
        RegistrationRequest request = new RegistrationRequest();
        request.setFirstname("firstname");
        request.setLastname("lastname");
        request.setEmail("email");
        request.setUsername("username");
        request.setPassword("password");

        authenticationService.register(request);
        UserResponse response = userServiceImpl.findByUsername("username");

        AddExpenseRequest request1 = new AddExpenseRequest();
        request1.setUserId(response.getUserId());
        request1.setName("Milk");
        request1.setAmount(1000);
        request1.setCategory("Food");
        request1.setType(Type.DIRECT);
        request1.setCreatedAt(LocalDate.now());
        request1.setDueDate(LocalDate.now().plusMonths(1));
        expenseServiceImpl.saveExpense(request1);
//        assertThat(expenseServiceImpl.findByUserId(response.getUserId()).size())
//                .isEqualTo(1);
    }

}