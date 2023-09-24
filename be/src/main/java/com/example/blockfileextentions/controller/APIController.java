package com.example.blockfileextentions.controller;

import com.example.blockfileextentions.model.BlockFileExtensionDTO;
import com.example.blockfileextentions.model.CustomExtensionDTO;
import com.example.blockfileextentions.service.BlockFileExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class APIController {

    private final BlockFileExtensionService service;

    @PostMapping("/fix-extension")
    public ResponseEntity<Void> addFixedFileExtension(@RequestBody BlockFileExtensionDTO dto) {
        if (service.addFixedFileExtension(dto)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/fix-extension")
    public ResponseEntity<List<BlockFileExtensionDTO>> getFixedFileExtensions() {
        return new ResponseEntity<>(service.getFixedFileExtensions(), HttpStatus.OK);
    }

    @PostMapping("/custom-extension")
    public ResponseEntity<Void> addCustomFileExtension(@RequestBody BlockFileExtensionDTO dto) {
        service.addBlockCustomFileExtension(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/custom-extension")
    public ResponseEntity<CustomExtensionDTO> getCustomFileExtensions() {
        return new ResponseEntity<>(service.getCustomFileExtensions(), HttpStatus.OK);
    }

    @DeleteMapping("/custom-extension")
    public ResponseEntity<Void> deleteCustomFileExtension(@RequestBody BlockFileExtensionDTO dto) {
        service.deleteBlockFileExtension(dto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
