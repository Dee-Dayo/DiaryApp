package services;

import data.models.Diary;
import dto.*;

public interface DiaryService {
    void register(RegisterRequest registerRequest);
    long getNoOfUsers();
    void login(LoginRequest loginRequest);
    Diary findDiaryByUsername(String username);
    void logout(LogoutRequest logoutRequest);
    void deleteUser(DeleteUserRequest deleteDiaryRequest);
    void createEntry(CreateEntryRequest createEntryRequest);

    void deleteEntry(DeleteEntryRequest deleteEntryRequest);

//    void updateEntry(UpdateEntryRequest updateEntryRequest);

}
