package com.example.blockfileextentions.service;

import com.example.blockfileextentions.model.BlockFileExtensionDTO;
import com.example.blockfileextentions.model.CustomExtensionDTO;

import java.util.List;

public interface BlockFileExtensionService {

    void addFixedFileExtension(BlockFileExtensionDTO dto);

    List<BlockFileExtensionDTO> getFixedFileExtensions();

    CustomExtensionDTO getCustomFileExtensions();

    void addBlockCustomFileExtension(BlockFileExtensionDTO dto);

    void deleteBlockFileExtension(BlockFileExtensionDTO dto);
}
