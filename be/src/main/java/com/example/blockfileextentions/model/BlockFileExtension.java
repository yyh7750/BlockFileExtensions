package com.example.blockfileextentions.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BlockFileExtension {

    @Id
    private String extension;

    // 0 : fix
    // 1 : custom
    private int type;
}
