package data.repositories;

import data.models.Diary;

import java.util.ArrayList;
import java.util.List;

public class DiaryRepositoryImpl implements DiaryRepository {
    private List<Diary> diaries = new ArrayList<>();
    @Override
    public void save(Diary diary) {
        for (Diary d : diaries){
            if (d.getUsername().equalsIgnoreCase(diary.getUsername())){
                return;
            }
        }
        diaries.add(diary);
    }

    @Override
    public Diary findByUsername(String username) {
        for (Diary diary: diaries){
            if (diary.getUsername().equalsIgnoreCase(username)){
                return diary;
            }
        }
        return null;
    }

    @Override
    public long count() {
        return diaries.size();
    }

    @Override
    public List<Diary> findAll() {
        return diaries;
    }

    @Override
    public void delete(String username) {
        Diary diary = findByUsername(username);
        diaries.remove(diary);
    }

    @Override
    public void delete(Diary diary) {
        diaries.remove(diary);
    }
}
