package com.safalifter.jobservice.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.List;

@Entity(name = "categories")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category extends BaseEntity {
    private String name;
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Job> jobs;

    @ElementCollection
    private List<String> imagesId = Collections.emptyList();
}