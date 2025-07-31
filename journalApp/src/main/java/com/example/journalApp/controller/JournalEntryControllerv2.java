package com.example.journalApp.controller;

import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.entity.User;
import com.example.journalApp.service.JournalEntryService;
import com.example.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping("{username}")
    public ResponseEntity<?> getAllJournalEntriesofUser(@PathVariable String username) {
        User user = userService.findByUserName(username);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        List<JournalEntry> all = user.getJournalEntryList();
        if(all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{username}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry , @PathVariable String username) {
        try{
            journalEntryService.saveEntry( myEntry ,username);
            return new ResponseEntity<>(myEntry , HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
    public ResponseEntity<String> deleteJournalEntryById(@PathVariable ObjectId myId) {
        // Only remove errors: check if entry exists before deleting
        Optional<JournalEntry> entry = journalEntryService.findById(myId);
        if (!entry.isPresent()) {
            return new ResponseEntity<>("Journal entry not found", HttpStatus.NOT_FOUND);
        }
        try {
            journalEntryService.deleteById(myId);
            return new ResponseEntity<>("Journal entry deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting journal entry", HttpStatus.INTERNAL_SERVER_ERROR);
        }
   }
   
   @PutMapping("/id/{id}/{username}")
    public ResponseEntity<JournalEntry> updateJournalEntry(@PathVariable ObjectId id, @PathVariable String username, @RequestBody JournalEntry myEntry) {
        // Only remove errors: check if entry exists before updating
        JournalEntry old = journalEntryService.findById(id).orElse(null);
        if(old == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        old.setTitle(myEntry.getTitle() != null && !myEntry.getTitle().isEmpty() ? myEntry.getTitle() : old.getTitle());
        old.setContent(myEntry.getContent() != null && !myEntry.getContent().isEmpty() ? myEntry.getContent() : old.getContent());
        journalEntryService.saveEntry(old, username);
        return new ResponseEntity<>(old, HttpStatus.OK);
   }
}
