package data.repositories;

import data.models.Entry;

import java.util.List;

public interface EntryRepository {
    void save(Entry entry);
    List<Entry> findByAuthor(String username);
    Entry findById(int id);
    long count();
    List<Entry> findAll();
    void delete(String username);
    void deleteById(int id);
    void deleteAll();
    void delete(Entry entry);
}
