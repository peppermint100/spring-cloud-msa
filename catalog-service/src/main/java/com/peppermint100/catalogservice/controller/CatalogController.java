package com.peppermint100.catalogservice.controller;

import com.peppermint100.catalogservice.jpa.CatalogEntity;
import com.peppermint100.catalogservice.service.CatalogService;
import com.peppermint100.catalogservice.vo.ResponseCatalog;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/catalog-service")
public class CatalogController {

    private final Environment env;
    private final CatalogService service;

    public CatalogController(Environment env, CatalogService service) {
        this.env = env;
        this.service = service;
    }

    @GetMapping("/health-check")
    public String status() {
        return String.format("User Service is Working on PORT : %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getUsers() {
        Iterable<CatalogEntity> userList = service.getAllCatalogs();

        List<ResponseCatalog> result = new ArrayList<>();

        userList.forEach(c -> {
            result.add(new ModelMapper().map(c, ResponseCatalog.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}
