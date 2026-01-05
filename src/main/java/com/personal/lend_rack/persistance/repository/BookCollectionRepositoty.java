package com.personal.lend_rack.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.lend_rack.persistance.entity.BookCollectionEntity;

public interface BookCollectionRepositoty extends JpaRepository<BookCollectionEntity, Long> {

}
