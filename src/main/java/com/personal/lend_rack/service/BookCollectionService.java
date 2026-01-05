package com.personal.lend_rack.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.personal.lend_rack.maper.BookCollectionMapper;
import com.personal.lend_rack.model.domain.BookCollection;
import com.personal.lend_rack.model.dto.CreateBookCollectionRequest;
import com.personal.lend_rack.model.dto.UpdateBookCollectionRequest;
import com.personal.lend_rack.persistance.entity.BookCollectionEntity;
import com.personal.lend_rack.persistance.repository.BookCollectionRepositoty;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookCollectionService {
    private final BookCollectionRepositoty collectionRepository;
    private final BookCollectionMapper collectionMapper;
    // private final UserRepository userRepository;

    public List<BookCollection> getAllCollections(Pageable pageable) {
        List<BookCollectionEntity> collectionEntities = collectionRepository.findAll(pageable).getContent();
        return collectionEntities.stream().map(collectionMapper::EntityToDomain).toList();
    }

    public List<BookCollection> getAllCollections() {
        List<BookCollectionEntity> collectionEntities = collectionRepository.findAll();
        return collectionEntities.stream().map(collectionMapper::EntityToDomain).toList();
    }

    public Long create(CreateBookCollectionRequest createCollectionRequest) {
        // if (createCollectionRequest.created_by() == null) {
        //     throw new IllegalArgumentException("created_by must not be null");
        // }

        // // Fetch UserEntity from DB
        // User user = userRepository.findById(createCollectionRequest.created_by())
        //         .orElseThrow(() -> new RuntimeException("User not found"));

        // Map request to CollectionEntity (without user)
        BookCollectionEntity collectionToSave = collectionMapper.createRequestToEntity(createCollectionRequest);

        // Manually set the user entity
        // collectionToSave.setCreated_by(user);

        // Save collection with user
        BookCollectionEntity savedCollection = collectionRepository.save(collectionToSave);
        return savedCollection.getId();
    }

    public BookCollection getById(Long id) throws NotFoundException {
        var entity = collectionRepository.findById(id).orElse(null);
        return collectionMapper.EntityToDomain(entity);
    }

    public void update(Long id, UpdateBookCollectionRequest updateCollectionRequest) throws NotFoundException {
        BookCollectionEntity entity = this.findEntityById(id);
        BookCollectionEntity updateCollectionEntity = collectionMapper.updateRequestToEntity(updateCollectionRequest, entity);
        collectionRepository.save(updateCollectionEntity);
    }

    public BookCollectionEntity findEntityById(Long id) throws NotFoundException {
        var entity = collectionRepository.findById(id).orElseThrow(() -> new NotFoundException());
        return entity;
    }

    public void delete(Long id) throws NotFoundException {
        this.findEntityById(id);
        collectionRepository.deleteById(id);
    }
}
