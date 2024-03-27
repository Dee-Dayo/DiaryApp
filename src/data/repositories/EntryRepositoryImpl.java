package data.repositories;

import data.models.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntryRepositoryImpl implements EntryRepository {
    private List<Entry> entries = new ArrayList<>();
    private int countEntry;

    @Override
    public void save(Entry entry) {
        if (isNew(entry)) addIdTo(entry);
        else update(entry);

        entries.add(entry);
    }

    private void update(Entry entry) {
        Entry oldEntry = findById(entry.getId());
        if (oldEntry != null) {
            entries.remove(oldEntry);
        }
    }

    private void addIdTo(Entry entry) {
        entry.setId(generateID());
    }

    private int generateID() {
        return ++countEntry;
    }

    private boolean isNew(Entry entry) {
        return entry.getId() == 0;
    }

    @Override
    public List<Entry> findByAuthor(String username) {
        List<Entry> result = new ArrayList<>();
        for (Entry entry : entries) {
            if (entry.getAuthor().equals(username)) {
                result.add(entry);
            }
        }
        return result;
    }

    @Override
    public Entry findById(int id) {
        for (Entry entry : entries) {
            if (entry.getId() == id) {
                return entry;
            }
        }
        return null;
    }

    @Override
    public long count() {
        return entries.size();
    }

    @Override
    public List<Entry> findAll() {
        return null;
    }

    @Override
    public void delete(String username) {

    }

    @Override
    public void deleteById(int id) {
        Entry entry = findById(id);
        entries.remove(entry);

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void delete(Entry entry) {
        entries.remove(entry);
    }
}
