package com.example.blockfileextentions.service;

import com.example.blockfileextentions.model.BlockFileExtension;
import com.example.blockfileextentions.model.BlockFileExtensionDTO;
import com.example.blockfileextentions.model.CustomExtensionDTO;

import java.util.List;

public interface BlockFileExtensionService {

    boolean addFixedFileExtension(BlockFileExtensionDTO dto);

    List<BlockFileExtensionDTO> getFixedFileExtensions();

    CustomExtensionDTO getCustomFileExtensions();

    boolean addBlockCustomFileExtension(BlockFileExtensionDTO dto);

    void deleteBlockFileExtension(BlockFileExtensionDTO dto);
}
