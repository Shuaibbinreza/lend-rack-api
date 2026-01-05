package com.personal.lend_rack.maper;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.personal.lend_rack.model.domain.BookCollection;
import com.personal.lend_rack.model.dto.CreateBookCollectionRequest;
import com.personal.lend_rack.model.dto.UpdateBookCollectionRequest;
import com.personal.lend_rack.persistance.entity.BookCollectionEntity;

@Component
public class BookCollectionMapper {
    public BookCollection EntityToDomain(BookCollectionEntity collectionEntity) {
        BookCollection collection = new BookCollection();
        BeanUtils.copyProperties(collectionEntity, collection);
        // if (collectionEntity.getCreated_by() != null) {
        //     collection.setCreatedByName(collectionEntity.getCreated_by().getName());
        // }
        return collection;
    }

    public BookCollectionEntity createRequestToEntity(CreateBookCollectionRequest request) {
        BookCollectionEntity collectionEntity = new BookCollectionEntity();
        collectionEntity.setCollection_name(request.collection_name());
        collectionEntity.setLocation(request.location());
        collectionEntity.setCreated_at(LocalDateTime.now());
        return collectionEntity;
    }

    public BookCollectionEntity updateRequestToEntity(UpdateBookCollectionRequest request, BookCollectionEntity collectionEntity) {
        collectionEntity.setCollection_name(request.collection_name());
        collectionEntity.setLocation(request.location());
        return collectionEntity;
    }
}
