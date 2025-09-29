package com.example.myapp.controller;

import com.example.myapp.MyApp;
import com.example.myapp.controller.config.TestContainersConfig;
import com.example.myapp.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static com.example.myapp.controller.config.TestContainersConfig.postgres;
import static org.junit.Assert.assertEquals;

@SpringBootTest(
        //webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = { MyApp.class, TestContainersConfig.class})
@AutoConfigureWebMvc
@Testcontainers
class UserControllerTest {
    @Autowired
    private UserController controller;
    
    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }
    
    @AfterAll
    static void afterAll() {
        postgres.stop();
    }
    
    @Test
    void testBgetAllUsers() {
        var users = controller.getAllUsers();
        assertEquals(200, users.getStatusCode().value());
        assertEquals(0, users.getBody().size());
    }

    @Test
    void getUserById() {
        var resp = controller.getUserById(1L);
        assertEquals(200, resp.getStatusCode());
        assertEquals("Nmae", resp.getBody().getUserName());
    }

//    @Test
//    void testACreate() {
//        User user = new User();
//        var resp = controller.createUser(user);
//        assertEquals(200, resp.getStatusCode());
//        assertEquals(user, resp.getBody());
//    }

    @Test
    void updateUser() {
        User user = new User();
        var resp = controller.updateUser(1L,user);
        assertEquals(200, resp.getStatusCode());
        assertEquals(user, resp.getBody());
    }

    @Test
    void deleteUser() {
        controller.deleteUser(1L);
        var resp = controller.getAllUsers();
        assertEquals(0, resp.getBody().size());
    }
}