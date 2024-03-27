package data.repositories;

import data.models.Diary;

import java.util.List;

public interface DiaryRepository {
    void save(Diary diary);
    Diary findByUsername(String username);
    long count();
    List<Diary> findAll();
    void delete(String username);
    void delete(Diary diary);
}
