package com.example.blockfileextentions.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BlockFileExtensionDTO {

    private String extension;

    private int type;

    public static BlockFileExtensionDTO of(BlockFileExtension entity) {
        return new BlockFileExtensionDTO(entity.getExtension(), entity.getType());
    }
}
