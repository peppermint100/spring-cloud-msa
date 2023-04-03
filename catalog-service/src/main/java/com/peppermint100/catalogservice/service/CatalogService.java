package com.peppermint100.catalogservice.service;

import com.peppermint100.catalogservice.jpa.CatalogEntity;

public interface CatalogService {
    Iterable<CatalogEntity> getAllCatalogs();
}
