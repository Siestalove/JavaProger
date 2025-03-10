package org.example.homework4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPet() {

        Pet pet = new Pet(null, "Dog", new Category(1L, "Dog"), "available", new ArrayList<>());


        when(petRepository.save(any(Pet.class))).thenReturn(pet);


        Pet savedPet = petService.addPet(pet);


        assertNotNull(savedPet);
        assertEquals("Dog", savedPet.getName());


        verify(petRepository, times(1)).save(any(Pet.class));
    }

    @Test
    public void testGetPetById() {

        Pet pet = new Pet(1L, "Dog", new Category(1L, "Dog"), "available", new ArrayList<>());


        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));


        Optional<Pet> foundPet = petService.getPetById(1L);


        assertTrue(foundPet.isPresent());
        assertEquals("Dog", foundPet.get().getName());
    }

    @Test
    public void testGetPetByIdNotFound() {

        when(petRepository.findById(2L)).thenReturn(Optional.empty());


        Optional<Pet> foundPet = petService.getPetById(2L);


        assertFalse(foundPet.isPresent());
    }
}
