package com.example.blockfileextentions.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomExtensionDTO {

    private List<BlockFileExtensionDTO> list;
    private int cnt;
}
