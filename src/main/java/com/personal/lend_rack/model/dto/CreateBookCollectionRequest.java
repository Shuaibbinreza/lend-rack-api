package com.personal.lend_rack.model.dto;

public record CreateBookCollectionRequest(String title, String author, String publisher, Long category_id, Long language_id, Long collection_id, String description, int page_count) {

    public String location() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'location'");
    }

    public String collection_name() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'collection_name'");
    }

}
