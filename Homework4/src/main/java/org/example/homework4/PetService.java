package org.example.homework4;

import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @PostConstruct
    public void init() {
        List<Tag> tags1 = new ArrayList<>();
        tags1.add(new Tag(1L, "friendly"));
        tags1.add(new Tag(2L, "playful"));

        List<Tag> tags2 = new ArrayList<>();
        tags2.add(new Tag(3L, "calm"));
        tags2.add(new Tag(4L, "independent"));

        Pet pet1 = new Pet(1L, "Dog", new Category(1L, "Dog"), "available", tags1);
        Pet pet2 = new Pet(2L, "Cat", new Category(2L, "Cat"), "sold", tags2);

        petRepository.save(pet1);
        petRepository.save(pet2);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Optional<Pet> getPetById(Long id) {
        return petRepository.findById(id);
    }

    public Pet addPet(Pet pet) {
        return petRepository.save(pet);
    }

    public Optional<Pet> updatePet(Long id, Pet pet) {
        if (petRepository.findById(id).isPresent()) {
            pet.setId(id);
            return Optional.of(petRepository.save(pet));
        }
        return Optional.empty();
    }

    public boolean deletePet(Long id) {
        if (petRepository.findById(id).isPresent()) {
            petRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
