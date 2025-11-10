package com.kicklite.app.controller;

import com.kicklite.app.model.Project;
import com.kicklite.app.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService service;
    public ProjectController(ProjectService service) { this.service = service; }

    @GetMapping
    public List<Project> list() { return service.all(); }

    @PostMapping
    public Project create(@RequestBody Project p) { return service.create(p); }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
