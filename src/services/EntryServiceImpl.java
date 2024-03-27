package services;

import data.models.Entry;
import data.repositories.EntryRepository;
import data.repositories.EntryRepositoryImpl;

import java.util.List;

public class EntryServiceImpl implements EntryService{
    private static EntryRepository entryRepository = new EntryRepositoryImpl();
    @Override
    public void save(Entry entry) {
        entryRepository.save(entry);
    }

    @Override
    public List<Entry> findEntriesByUsername(String username) {
        return entryRepository.findByAuthor(username);
    }

    @Override
    public void deleteEntrybyId(int id) {
        entryRepository.deleteById(id);
    }

}
