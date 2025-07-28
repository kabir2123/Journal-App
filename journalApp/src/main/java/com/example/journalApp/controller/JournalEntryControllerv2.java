package com.example.journalApp.controller;

import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.repository.JournalEntryRepository;
import com.example.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }

   


   @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now());
           journalEntryService.saveEntry( myEntry);
       return true;
   }

   @GetMapping("id/{myId}")
   public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId myId) {
       Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
       if(journalEntry.isPresent()) {
           return new ResponseEntity<>(journalEntry.get() , HttpStatus.OK);
           }
       else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
   }




   @DeleteMapping("id/{myId}")
    public boolean  deleteJournalEntryById(@PathVariable ObjectId myId) {
           journalEntryService.deleteById(myId);
           return true;
   }
   @PutMapping("/id/{id}")
    public JournalEntry updateJournalEntry(@PathVariable ObjectId id,@RequestBody JournalEntry myEntry) {
       JournalEntry old = journalEntryService.findById(id).orElse(null);
       if(old != null) {
           old.setTitle(myEntry.getTitle() != null && !myEntry.getTitle().isEmpty() ? myEntry.getTitle() : old.getTitle());
           old.setContent(myEntry.getContent() != null && !myEntry.getContent().isEmpty() ? myEntry.getContent() : old.getContent());

       }
       journalEntryService.saveEntry(old);
       return old;
   }


}
