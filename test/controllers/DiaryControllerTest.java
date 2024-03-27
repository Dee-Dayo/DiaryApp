package controllers;

import dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiaryControllerTest {

    private DiaryController controller;

    @BeforeEach
    public void setUp() {
        controller = new DiaryController();
    }

    @Test
    public void appCanRegisterUser(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");

        assertEquals("Registration Successful", controller.register(registerRequest));
    }

    @Test
    public void appCantRegisterTwoUserWithSameUsername(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("dee");
        registerRequest.setPassword("password");

        assertEquals("Registration Successful", controller.register(registerRequest));
        assertEquals("dee username already exist", controller.register(registerRequest));
    }

    @Test
    public void appCanLoginUserIntoAccount(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("dee");
        registerRequest.setPassword("password");

        assertEquals("Registration Successful", controller.register(registerRequest));

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("dee");
        loginRequest.setPassword("password");
        assertEquals("Login Successful", controller.login(loginRequest));
    }

    @Test
    public void userCantLoginIfNotRegistered(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("dee");
        loginRequest.setPassword("password");
        assertEquals("User not found", controller.login(loginRequest));
    }

    @Test
    public void userCantLoginWithIncorrectPassword(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Dee");
        registerRequest.setPassword("password");

        assertEquals("Registration Successful", controller.register(registerRequest));

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("DEE");
        loginRequest.setPassword("Password");
        assertEquals("Password incorrect", controller.login(loginRequest));
    }

    @Test
    public void userCanLogin_userCanLogout(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("dee");
        registerRequest.setPassword("password");

        assertEquals("Registration Successful", controller.register(registerRequest));

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("dee");
        loginRequest.setPassword("password");
        assertEquals("Login Successful", controller.login(loginRequest));

        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.setUsername("DEe");
        assertEquals("Logout Successful", controller.logout(logoutRequest));
    }

    @Test
    public void userCantLogoutAccountNotCreated(){
        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.setUsername("DEe");
        assertEquals("User not found", controller.logout(logoutRequest));
    }

    @Test
    public void userCanLogin_userCanCreateEntry(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("dee");
        registerRequest.setPassword("password");

        assertEquals("Registration Successful", controller.register(registerRequest));

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("dee");
        loginRequest.setPassword("password");
        assertEquals("Login Successful", controller.login(loginRequest));

        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setAuthor("dee");
        createEntryRequest.setTitle("First day in semicolon");
        createEntryRequest.setBody("I am dee");
        assertEquals("Entry added successfully", controller.addEntry(createEntryRequest));
    }

    @Test
    public void userCantCreateEntryIfNotRegistered(){
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setAuthor("dee");
        createEntryRequest.setTitle("First day in semicolon");
        createEntryRequest.setBody("I am dee");
        assertEquals("Diary not found", controller.addEntry(createEntryRequest));
    }

    @Test
    public void userCantCreateEntryIfNotLoggedIn(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("dee");
        registerRequest.setPassword("password");
        assertEquals("Registration Successful", controller.register(registerRequest));

        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setAuthor("dee");
        createEntryRequest.setTitle("First day in semicolon");
        createEntryRequest.setBody("I am dee");
        assertEquals("You need to login to create Entry", controller.addEntry(createEntryRequest));
    }

     @Test
    public void userCanLoginWithApp_userCanCreateEntry(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("dee");
        registerRequest.setPassword("password");

        assertEquals("Registration Successful", controller.register(registerRequest));

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("dee");
        loginRequest.setPassword("password");
        assertEquals("Login Successful", controller.login(loginRequest));

        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setAuthor("dee");
        createEntryRequest.setTitle("First day in semicolon");
        createEntryRequest.setBody("I am dee");
        assertEquals("Entry added successfully", controller.addEntry(createEntryRequest));

        DeleteEntryRequest deleteEntryRequest = new DeleteEntryRequest();
        deleteEntryRequest.setId(1);
        deleteEntryRequest.setAuthor("dee");
        assertEquals("Entry deleted successfully", controller.deleteEntry(deleteEntryRequest));
    }

    @Test
    public void userCanGetAllEntriesAdded(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("dee");
        registerRequest.setPassword("password");

        assertEquals("Registration Successful", controller.register(registerRequest));

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("dee");
        loginRequest.setPassword("password");
        assertEquals("Login Successful", controller.login(loginRequest));

        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setAuthor("dee");
        createEntryRequest.setTitle("First day in semicolon");
        createEntryRequest.setBody("I am dee");
        assertEquals("Entry added successfully", controller.addEntry(createEntryRequest));
        String result = "[ID=1, Title='First day in semicolon', Author='dee', Body='I am dee', Date Created=2024-03-24T23:01:40.463014900']";

        assertEquals(result, controller.getAllEntries("dee"));
    }
}