package com.example.blockfileextentions.repository;

import com.example.blockfileextentions.model.BlockFileExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlockFileExtensionRepository extends JpaRepository<BlockFileExtension, String> {

    List<BlockFileExtension> findAllByType(int type);

    Optional<BlockFileExtension> findByExtensionAndType(String extension, int type);
}
