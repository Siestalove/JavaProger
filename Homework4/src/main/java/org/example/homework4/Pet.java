package org.example.homework4;

import java.util.List;

public class Pet {
    private Long id;
    private String name;
    private Category category;
    private String status;
    private List<Tag> tags;  // Добавляем поле для тегов

    public Pet(Long id, String name, Category category, String status, List<Tag> tags) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.status = status;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
