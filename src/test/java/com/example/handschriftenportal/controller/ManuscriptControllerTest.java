package com.example.handschriftenportal.controller;

import com.example.handschriftenportal.entity.Manuscript;
import com.example.handschriftenportal.service.ManuscriptService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;


public class ManuscriptControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ManuscriptService manuscriptService;

    @InjectMocks
    private ManuscriptController manuscriptController;

    @BeforeEach
    public void setUp() {
        // Initialisierung der Mock-Objekte und des MockMvc
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(manuscriptController).build();
    }

    @Test
    public void testRegisterManuscript() throws Exception {
        // Erstellen von Manuskripten für den Test
        Manuscript manuscriptOne = new Manuscript();
        manuscriptOne.setId(1L);
        manuscriptOne.setStorageLocation("Location one");
        manuscriptOne.setStorageLocationGndId("GND1");
        manuscriptOne.setOwner("Owner one");
        manuscriptOne.setOwnerGndId("GND2");
        manuscriptOne.setCreationYear(2024);
        manuscriptOne.setTitle("Manuscript one");
        manuscriptOne.setDescription("Description of manuscript one");
        manuscriptOne.setResearcherEmail("researcher@example.com");

        // Mock für die saveManuscript-Methode
        when(manuscriptService.saveManuscript(any(Manuscript.class))).thenReturn(manuscriptOne);

        // Ausführen eines POST-Requests zur Registrierung eines Manuskripts
        mockMvc.perform(post("/api/manuscripts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"storageLocation\": \"Location one\", " +
                                "\"storageLocationGndId\": \"GND1\", " +
                                "\"owner\": \"Owner one\", " +
                                "\"ownerGndId\": \"GND2\", " +
                                "\"creationYear\": \"2024\", " +
                                "\"title\": \"Manuscript one\", " +
                                "\"description\": \"Description of manuscript one\", " +
                                "\"researcherEmail\": \"researcher@example.com\" }"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Manuscript registered successfully with ID: 1"));
    }

    @Test
    public void testGetAllManuscripts() throws Exception {
        Manuscript manuscriptOne = new Manuscript();
        manuscriptOne.setId(1L);
        manuscriptOne.setTitle("Manuscript one");

        Manuscript manuscriptTwo = new Manuscript();
        manuscriptTwo.setId(2L);
        manuscriptTwo.setTitle("Manuscript two");

        List<Manuscript> manuscripts = Arrays.asList(manuscriptOne, manuscriptTwo);

        // Mock für die getAllManuscripts-Methode
        when(manuscriptService.getAllManuscripts()).thenReturn(manuscripts);

        mockMvc.perform(get("/api/manuscripts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Manuscript one"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Manuscript two"));
    }
}
