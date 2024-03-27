package services;

import data.models.Diary;
import data.models.Entry;
import data.repositories.DiaryRepository;
import data.repositories.DiaryRepositoryImpl;
import dto.*;
import exceptions.*;

import java.util.List;

public class DiaryServiceImpl implements DiaryService{
    private DiaryRepository diaryRepository = new DiaryRepositoryImpl();
    private static EntryService entryService = new EntryServiceImpl();


    @Override
    public void register(RegisterRequest registerRequest) {
        validateUsername(registerRequest);
        
        Diary diary = new Diary();
        diary.setUsername(registerRequest.getUsername());
        diary.setPassword(registerRequest.getPassword());
        diaryRepository.save(diary);
    }

    private void validateUsername(RegisterRequest registerRequest) {
        Diary diary = diaryRepository.findByUsername(registerRequest.getUsername());
        if (diary != null) throw new UsernameAlreadyExist(registerRequest.getUsername() + " username already exist");
    }

    @Override
    public long getNoOfUsers() {
        return diaryRepository.count();
    }

    @Override
    public void login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        Diary diary = diaryRepository.findByUsername(username);
        if (diary == null) throw new DiaryNotFoundException("User not found");
        validatePassword(diary, password);
        diary.setLocked(false);
        diaryRepository.save(diary);
    }

    private static void validatePassword(Diary diary, String password) {
        if(diary.getPassword().equals(password)) diary.setLocked(false);
        else throw new InvalidPassword("Password incorrect");
    }

    @Override
    public Diary findDiaryByUsername(String username) {
        Diary diary = diaryRepository.findByUsername(username);
        if (diary == null) throw new DiaryNotFoundException("Diary not found");
        return diary;

    }

    @Override
    public void logout(LogoutRequest logoutRequest) {
        String username = logoutRequest.getUsername();
        Diary diary = diaryRepository.findByUsername(username);
        if (diary == null) throw new DiaryNotFoundException("User not found");
        diary.setLocked(true);
        diaryRepository.save(diary);
    }

    @Override
    public void deleteUser(DeleteUserRequest deleteDiaryRequest) {
        String username = deleteDiaryRequest.getUsername();
        String password = deleteDiaryRequest.getPassword();
        Diary diary = diaryRepository.findByUsername(username);
        validatePassword(diary, password);
        diaryRepository.delete(diary);
    }

    @Override
    public void createEntry(CreateEntryRequest createEntryRequest) {
        String author = createEntryRequest.getAuthor();
        Diary diary = findDiaryByUsername(author);
        isLockedStatus(diary);

        validateEntry(createEntryRequest);

        Entry entry = new Entry();
        entry.setAuthor(author);
        entry.setTitle(createEntryRequest.getTitle());
        entry.setBody(createEntryRequest.getBody());
        entryService.save(entry);
    }

    @Override
    public void deleteEntry(DeleteEntryRequest deleteEntryRequest) {
       Diary diary = findDiaryByUsername(deleteEntryRequest.getAuthor());
       isLockedStatus(diary);

       entryService.deleteEntrybyId(deleteEntryRequest.getId());
    }

//    @Override
//    public void updateEntry(UpdateEntryRequest updateEntryRequest) {
////        String author = updateEntryRequest.getAuthor();
////        Diary diary = findDiaryByUsername(author);
////        isLockedStatus(diary);
//
//        List<Entry> userEntries = entryService.findEntriesByUsername(updateEntryRequest.getAuthor());
//        for(Entry entry : userEntries){
//            if (entry.getTitle().equals(updateEntryRequest.getTitle())) {
//                entry.setTitle(updateEntryRequest.getTitle());
//                entry.setBody(updateEntryRequest.getBody());
//                entryService.save(entry);
//            }
//        }
//    }

    private static void validateEntry(CreateEntryRequest createEntryRequest) {
        List<Entry> userEntries = entryService.findEntriesByUsername(createEntryRequest.getAuthor());
        for(Entry entry : userEntries){
            if (entry.getTitle().equals(createEntryRequest.getTitle())) {
                throw new EntryTitleAlreadyCreated("Entry title already created");
            }
        }
    }

    private void isLockedStatus(Diary diary) {
        if(diary.isLocked()) throw new DiaryLockedException("You need to login to create Entry");
    }
}
