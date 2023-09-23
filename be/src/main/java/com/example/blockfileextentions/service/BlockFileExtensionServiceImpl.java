package com.example.blockfileextentions.service;

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
            
            // 고정 확장자 리스트에서 체크 해제했을 경우
            if (dto.getType() == 0) {
                return false;
            }
            // 커스텀 확장자를 이용해 고정 확장자 체크 표시를 해제하는 경우
            else {
                throw new RuntimeException("해당 확장자는 이미 차단되어 있습니다. 차단 해제되었습니다.");
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
    public boolean addBlockCustomFileExtension(BlockFileExtensionDTO dto) {
        Optional<BlockFileExtension> blockExtension = repository.findById(dto.getExtension());

        if (blockExtension.isPresent()) {
            if (blockExtension.get().getType() == 0) {
                throw new RuntimeException("고정 확장자로 등록되어 있습니다.");
            }
            return false;
        }

        repository.save(new BlockFileExtension(dto.getExtension(), dto.getType()));
        return true;
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
