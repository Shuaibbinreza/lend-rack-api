package com.personal.lend_rack.controller.rest;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.lend_rack.model.domain.BookCollection;
import com.personal.lend_rack.model.dto.CreateBookCollectionRequest;
import com.personal.lend_rack.model.dto.UpdateBookCollectionRequest;
import com.personal.lend_rack.persistance.entity.BookCollectionEntity;
import com.personal.lend_rack.service.BookCollectionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Collection Resource", description = "API for managing collections")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/book-collection")
public class BookCollectionRestController {
    private final BookCollectionService collectionService;

    @Operation(summary = "Get all collections")
    @GetMapping
    public List<BookCollection> getAllCollections(@ParameterObject Pageable pageable) {
        return collectionService.getAllCollections(pageable);
    }

    @Operation(summary = "Create a new collection")
    @PostMapping
    public void createCollection(CreateBookCollectionRequest createCollectionRequest) {
        collectionService.create(createCollectionRequest);
    }

    @Operation(summary = "Get Collection by ID")
    @GetMapping("{id}")
    public BookCollectionEntity getCollectionById(@PathVariable Long id) throws NotFoundException {
        BookCollectionEntity collectionEntity = collectionService.findEntityById(id);
        return collectionEntity;
    }

    @Operation(summary = "Update a collection by id")
    @PutMapping("{id}")
    public void updateCollection(@PathVariable Long id, @RequestBody UpdateBookCollectionRequest request) throws NotFoundException {
        collectionService.update(id, request);
    }

    @Operation(summary = "Delete Collection by id")
    @DeleteMapping("{id}")
    public void deleteCollection(@PathVariable Long id) throws NotFoundException {
        collectionService.delete(id);
    }
}
