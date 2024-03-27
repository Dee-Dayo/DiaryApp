package services;

import dto.*;
import exceptions.EntryTitleAlreadyCreated;
import exceptions.InvalidPassword;
import exceptions.UsernameAlreadyExist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryServiceImplTest {

    private DiaryService diaryService;
    private RegisterRequest registerRequest;
    private EntryService entryService;

    @BeforeEach
    public void setUp() {
        diaryService = new DiaryServiceImpl();
        registerRequest = new RegisterRequest();
        entryService = new EntryServiceImpl();

        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
    }

    @Test
    public void registerOneUser_getUserIsOne(){
        diaryService.register(registerRequest);
        assertEquals(1, diaryService.getNoOfUsers());
    }

    @Test
    public void cantRegisterTwoUsersWithSameUsername_throwsException(){
        diaryService.register(registerRequest);
        assertEquals(1, diaryService.getNoOfUsers());

        RegisterRequest registerRequest2 = new RegisterRequest();
        registerRequest2.setUsername("username");
        registerRequest2.setPassword("password");
        assertThrows(UsernameAlreadyExist.class, ()->diaryService.register(registerRequest2));
    }

    @Test
    public void canRegisterTwoUsersWithDifferentUsername(){
        diaryService.register(registerRequest);

        RegisterRequest registerRequest2 = new RegisterRequest();
        registerRequest2.setUsername("username2");
        registerRequest2.setPassword("password2");
        diaryService.register(registerRequest2);

        assertEquals(2, diaryService.getNoOfUsers());
    }

    @Test
    public void registerOneUser_userCanLoginWithCorrectDetails(){
        diaryService.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");

        diaryService.login(loginRequest);
        assertFalse(diaryService.findDiaryByUsername("USERname").isLocked());
    }

    @Test
    public void registerOneUser_userCantLoginWithinCorrectDetails(){
        diaryService.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("incorrectPassword");

        assertThrows(InvalidPassword.class, ()-> diaryService.login(loginRequest));
        assertTrue(diaryService.findDiaryByUsername("USERname").isLocked());
    }

    @Test
    public void resgisterOneUser_userCanLoginCanLogout(){
        diaryService.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
        diaryService.login(loginRequest);
        assertFalse(diaryService.findDiaryByUsername("USERname").isLocked());

        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.setUsername("USERname");
        diaryService.logout(logoutRequest);
        assertTrue(diaryService.findDiaryByUsername("USERname").isLocked());
    }

    @Test
    public void registerTwoUser_canDeleteUser(){
        diaryService.register(registerRequest);

        RegisterRequest registerRequest2 = new RegisterRequest();
        registerRequest2.setUsername("username2");
        registerRequest2.setPassword("password2");
        diaryService.register(registerRequest2);

        assertEquals(2, diaryService.getNoOfUsers());

        DeleteUserRequest deleteDiaryRequest = new DeleteUserRequest();
        deleteDiaryRequest.setUsername("username");
        deleteDiaryRequest.setPassword("password");

        diaryService.deleteUser(deleteDiaryRequest);
        assertEquals(1, diaryService.getNoOfUsers());
    }

    @Test
    public void registerOneUser_userCanCreateEntry(){
        diaryService.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
        diaryService.login(loginRequest);
        assertFalse(diaryService.findDiaryByUsername("USERname").isLocked());

        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setBody("body");
        createEntryRequest.setTitle("title");
        createEntryRequest.setAuthor("username");
        diaryService.createEntry(createEntryRequest);

        assertEquals(1, entryService.findEntriesByUsername("username").size());
    }

    @Test
    public void registerOneUser_userCanCreateTwoEntry(){
        diaryService.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
        diaryService.login(loginRequest);
        assertFalse(diaryService.findDiaryByUsername("USERname").isLocked());

        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setBody("body");
        createEntryRequest.setTitle("title");
        createEntryRequest.setAuthor("username");
        diaryService.createEntry(createEntryRequest);

        CreateEntryRequest createEntryRequest2 = new CreateEntryRequest();
        createEntryRequest2.setBody("body2");
        createEntryRequest2.setTitle("title2");
        createEntryRequest2.setAuthor("username");
        diaryService.createEntry(createEntryRequest2);

        assertEquals(2, entryService.findEntriesByUsername("username").size());
    }

     @Test
    public void registerOneUser_userCantCreateTwoEntryWithSameTitle(){
        diaryService.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
        diaryService.login(loginRequest);
        assertFalse(diaryService.findDiaryByUsername("USERname").isLocked());

        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setBody("body");
        createEntryRequest.setTitle("title");
        createEntryRequest.setAuthor("username");
        diaryService.createEntry(createEntryRequest);

        CreateEntryRequest createEntryRequest2 = new CreateEntryRequest();
        createEntryRequest2.setBody("body");
        createEntryRequest2.setTitle("title");
        createEntryRequest2.setAuthor("username");
        assertThrows(EntryTitleAlreadyCreated.class, ()->diaryService.createEntry(createEntryRequest2));

        assertEquals(1, entryService.findEntriesByUsername("username").size());
    }

    @Test
    public void registerOneUser_userCanCreateTwoEntry_userDeleteOne() {
        diaryService.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
        diaryService.login(loginRequest);
        assertFalse(diaryService.findDiaryByUsername("USERname").isLocked());

        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setBody("body");
        createEntryRequest.setTitle("title");
        createEntryRequest.setAuthor("username");
        diaryService.createEntry(createEntryRequest);

        CreateEntryRequest createEntryRequest2 = new CreateEntryRequest();
        createEntryRequest2.setBody("body2");
        createEntryRequest2.setTitle("title2");
        createEntryRequest2.setAuthor("username");
        diaryService.createEntry(createEntryRequest2);
        assertEquals(2, entryService.findEntriesByUsername("username").size());

        DeleteEntryRequest deleteEntryRequest = new DeleteEntryRequest();
        deleteEntryRequest.setId(1);
        deleteEntryRequest.setAuthor("username");

        diaryService.deleteEntry(deleteEntryRequest);
        assertEquals(1, entryService.findEntriesByUsername("username").size());
    }
}