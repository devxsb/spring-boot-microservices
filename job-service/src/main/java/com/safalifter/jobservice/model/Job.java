package com.safalifter.jobservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity(name = "jobs")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Job extends BaseEntity {
    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<Advert> adverts;

    @ElementCollection
    private List<String> keys = Collections.emptyList();

    @ElementCollection
    private List<String> imagesId = Collections.emptyList();
}
