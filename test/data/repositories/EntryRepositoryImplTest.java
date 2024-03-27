package data.repositories;

import data.models.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntryRepositoryImplTest {
    EntryRepository entryRepository;

    @BeforeEach
    public void setUp(){
        entryRepository = new EntryRepositoryImpl();
    }

    @Test
    public void EntryCanBeCreated(){
        Entry entry = new Entry();
        entry.setId(1);
        entry.setAuthor("author");
        entry.setTitle("title");
        entry.setBody("body");
        entryRepository.save(entry);
        assertEquals(1, entryRepository.count());
    }

    @Test
    public void EntryIsCreated_canBeFound(){
         Entry entry = new Entry();
        entry.setId(1);
        entry.setAuthor("author");
        entry.setTitle("title");
        entry.setBody("body");
        entryRepository.save(entry);

        List<Entry> foundEntry = entryRepository.findByAuthor("author");
        assertEquals(1, foundEntry.size());
    }

    @Test
    public void entryCanBeUpdated_canBeFound(){
        Entry entry = new Entry();
        entry.setId(1);
        entry.setAuthor("author");
        entry.setTitle("title");
        entry.setBody("body");
        entryRepository.save(entry);
        assertEquals(1, entryRepository.count());

        Entry updatedEntry = new Entry();
        updatedEntry.setId(1);
        updatedEntry.setAuthor("author");
        updatedEntry.setTitle("NEW title");
        updatedEntry.setBody("NEW body");
        entryRepository.save(updatedEntry);
        assertEquals(1, entryRepository.count());
    }
}