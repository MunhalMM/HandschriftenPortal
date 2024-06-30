package com.example.handschriftenportal.controller;

import com.example.handschriftenportal.entity.Manuscript;
import com.example.handschriftenportal.service.ManuscriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("/api/manuscripts")
public class ManuscriptController {

    private final ManuscriptService manuscriptService;

    @Autowired
    public ManuscriptController(ManuscriptService manuscriptService) {
        this.manuscriptService = manuscriptService;
    }

    @PostMapping
    public ResponseEntity<String> registerManuscript(@RequestBody Manuscript manuscript) {
        try {
            Manuscript savedManuscript = manuscriptService.saveManuscript(manuscript);
            return ResponseEntity.status(HttpStatus.CREATED).body("Manuscript registered successfully with ID: " + savedManuscript.getId());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering manuscript: " + exception.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Manuscript>> getAllManuscripts() {
        List<Manuscript> manuscripts = manuscriptService.getAllManuscripts();
        return ResponseEntity.ok(manuscripts);
    }
}
