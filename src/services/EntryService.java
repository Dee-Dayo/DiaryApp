package services;

import data.models.Entry;

import java.util.List;

public interface EntryService {
    void save(Entry entry);

    List<Entry> findEntriesByUsername(String username);

    void deleteEntrybyId(int id);
}
