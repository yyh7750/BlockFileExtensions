package com.example.blockfileextentions.service;

import com.example.blockfileextentions.exception.CustomException;
import com.example.blockfileextentions.exception.ErrorCode;
import com.example.blockfileextentions.model.BlockFileExtension;
import com.example.blockfileextentions.model.BlockFileExtensionDTO;
import com.example.blockfileextentions.model.CustomExtensionDTO;
import com.example.blockfileextentions.repository.BlockFileExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlockFileExtensionServiceImpl implements BlockFileExtensionService {

    private final BlockFileExtensionRepository repository;

    @Override
    public boolean addFixedFileExtension(BlockFileExtensionDTO dto) {
        Optional<BlockFileExtension> blockExtension = repository.findById(dto.getExtension());

        if (blockExtension.isPresent()) {
            repository.delete(blockExtension.get());

            if (dto.getType() == 1) {
                throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
            } //
            else {
                return false;
            }
        }

        repository.save(new BlockFileExtension(dto.getExtension(), dto.getType()));
        return true;
    }

    @Override
    public List<BlockFileExtensionDTO> getFixedFileExtensions() {
        List<BlockFileExtension> blockExtensions = repository.findAllByType(0);
        return blockExtensions.stream().map(BlockFileExtensionDTO::of).collect(Collectors.toList());
    }

    @Override
    public CustomExtensionDTO getCustomFileExtensions() {
        List<BlockFileExtension> blockExtensions = repository.findAllByType(1);
        List<BlockFileExtensionDTO> list = blockExtensions.stream().map(BlockFileExtensionDTO::of).collect(Collectors.toList());
        return new CustomExtensionDTO(list, list.size());
    }

    @Override
    public void addBlockCustomFileExtension(BlockFileExtensionDTO dto) {
        CustomExtensionDTO customExtensions = getCustomFileExtensions();

        if (customExtensions.getCnt() < 200) {
            Optional<BlockFileExtension> blockExtension = repository.findById(dto.getExtension());

            if (blockExtension.isPresent()) {
                throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
            }

            repository.save(new BlockFileExtension(dto.getExtension(), dto.getType()));
        } //
        else {
            throw new CustomException(ErrorCode.OVER_LENGTH);
        }
    }

    @Override
    public void deleteBlockFileExtension(BlockFileExtensionDTO dto) {
        Optional<BlockFileExtension> blockExtension = repository.findByExtensionAndType(dto.getExtension(), dto.getType());
        if (blockExtension.isPresent()) {
            repository.delete(blockExtension.get());
            return;
        }
    }
}
