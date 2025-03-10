package org.example.homework4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PetControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PetService petService;

    @InjectMocks
    private PetController petController;

    private Pet pet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();


        Category category = new Category(1L, "Dog");
        Tag tag = new Tag(1L, "Friendly");


        pet = new Pet(1L, "Dog", category, "available", List.of(tag));
    }

    @Test
    public void testAddPet() throws Exception {

        when(petService.addPet(any(Pet.class))).thenReturn(pet);

        mockMvc.perform(post("/pet")
                        .contentType("application/json")
                        .content("{\"name\":\"Dog\",\"category\":{\"id\":1,\"name\":\"Dog\"},\"status\":\"available\",\"tags\":[{\"id\":1,\"name\":\"Friendly\"}]}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Dog"))
                .andExpect(jsonPath("$.tags[0].name").value("Friendly"));
    }

    @Test
    public void testGetPetById() throws Exception {

        when(petService.getPetById(1L)).thenReturn(Optional.of(pet));

        mockMvc.perform(get("/pet/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dog"))
                .andExpect(jsonPath("$.tags[0].name").value("Friendly"));
    }

    @Test
    public void testDeletePet() throws Exception {
        when(petService.deletePet(1L)).thenReturn(true);

        mockMvc.perform(delete("/pet/1"))
                .andExpect(status().isNoContent());

        verify(petService, times(1)).deletePet(1L);
    }

    @Test
    public void testGetPetByIdNotFound() throws Exception {
        when(petService.getPetById(2L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/pet/2"))
                .andExpect(status().isNotFound());
    }
}
