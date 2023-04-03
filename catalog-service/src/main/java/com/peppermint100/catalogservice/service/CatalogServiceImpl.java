package com.peppermint100.catalogservice.service;

import com.peppermint100.catalogservice.jpa.CatalogEntity;
import com.peppermint100.catalogservice.jpa.CatalogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository repository;

    public CatalogServiceImpl(CatalogRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<CatalogEntity> getAllCatalogs() {
        return repository.findAll();
    }
}
