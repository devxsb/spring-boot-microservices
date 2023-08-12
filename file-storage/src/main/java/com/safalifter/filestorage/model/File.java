package com.safalifter.filestorage.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "files")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class File {
    @Id
    private String id;
    private String type;
    private String filePath;
}