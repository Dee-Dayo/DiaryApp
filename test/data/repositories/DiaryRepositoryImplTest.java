package data.repositories;

import data.models.Diary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiaryRepositoryImplTest {

    DiaryRepository diaryRepository;

    @BeforeEach
    public void setUp() {
        diaryRepository = new DiaryRepositoryImpl();
    }

    @Test
    public void diaryIsAdded_countIsOne(){
        Diary diary = new Diary();
        diary.setUsername("username");
        diary.setPassword("password");
        diaryRepository.save(diary);
        assertEquals(1, diaryRepository.count());
    }

    @Test
    public void twoDiaryWithSameUsernameCantBeAdded(){
        Diary diary = new Diary();
        diary.setUsername("username");
        diary.setPassword("password");
        diaryRepository.save(diary);
        diaryRepository.save(diary);
        assertEquals(1, diaryRepository.count());
    }

    @Test
    public void twoDiaryCanBeCreated(){
        Diary diary = new Diary();
        diary.setUsername("username");
        diary.setPassword("password");
        diaryRepository.save(diary);

        Diary diary2 = new Diary();
        diary2.setUsername("username2");
        diary2.setPassword("password2");
        diaryRepository.save(diary2);
        assertEquals(2, diaryRepository.count());
    }

    @Test
    public void canFindDiaryByUsername(){
        Diary diary = new Diary();
        diary.setUsername("username");
        diary.setPassword("password");
        diaryRepository.save(diary);

        Diary foundDiary = diaryRepository.findByUsername("username");
        assertEquals(foundDiary, diary);
    }

    @Test
    public void threeDiaryCreated_canFindAll(){
        Diary diary = new Diary();
        diary.setUsername("username");
        diary.setPassword("password");
        diaryRepository.save(diary);

        Diary diary2 = new Diary();
        diary2.setUsername("username2");
        diary2.setPassword("password2");
        diaryRepository.save(diary2);

        Diary diary3 = new Diary();
        diary3.setUsername("username3");
        diary3.setPassword("password3");
        diaryRepository.save(diary3);
        assertEquals(3, diaryRepository.count());

        List<Diary> foundDiaries = diaryRepository.findAll();
        assertEquals(3, foundDiaries.size());
    }

    @Test
    public void diaryIsCreated_canDeleteByUsername(){
        Diary diary = new Diary();
        diary.setUsername("username");
        diary.setPassword("password");
        diaryRepository.save(diary);
        assertEquals(1, diaryRepository.count());

        diaryRepository.delete("username");
        assertEquals(0, diaryRepository.count());
    }

     @Test
    public void diaryIsCreated_cantDeleteByWrongUsernameOrIncorrectCase(){
        Diary diary = new Diary();
        diary.setUsername("username");
        diary.setPassword("password");
        diaryRepository.save(diary);
        assertEquals(1, diaryRepository.count());

        diaryRepository.delete("userNAME");
        assertEquals(1, diaryRepository.count());
    }

     @Test
    public void diaryIsCreated_canDeleteByDiary(){
        Diary diary = new Diary();
        diary.setUsername("username");
        diary.setPassword("password");
        diaryRepository.save(diary);
        assertEquals(1, diaryRepository.count());

        diaryRepository.delete(diary );
        assertEquals(0, diaryRepository.count());
    }
}