package controllers;

import data.models.Entry;
import dto.*;
import exceptions.DiaryAppException;
import services.DiaryService;
import services.DiaryServiceImpl;
import services.EntryService;
import services.EntryServiceImpl;

import java.util.List;

public class DiaryController {
    private DiaryService diaryService = new DiaryServiceImpl();
    private EntryService entryService = new EntryServiceImpl();


    public String register(RegisterRequest registerRequest) {
        try {
            diaryService.register(registerRequest);
            return "Registration Successful";
        } catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    public String login(LoginRequest loginRequest) {
        try {
            diaryService.login(loginRequest);
            return "Login Successful";
        } catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    public String logout(LogoutRequest logoutRequest) {
        try {
            diaryService.logout(logoutRequest);
            return "Logout Successful";
        } catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    public String addEntry(CreateEntryRequest createEntryRequest) {
        try {
            diaryService.createEntry(createEntryRequest);
            return "Entry added successfully";
        } catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    public String deleteEntry(DeleteEntryRequest deleteEntryRequest) {
        try {
            diaryService.deleteEntry(deleteEntryRequest);
            return "Entry deleted successfully";
        } catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    public List<Entry> getAllEntries(String username) {
        try {
            return entryService.findEntriesByUsername(username);
        } catch (DiaryAppException e){
            return List.of();
        }
    }
}
