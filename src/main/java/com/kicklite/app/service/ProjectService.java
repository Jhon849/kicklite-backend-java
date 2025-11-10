package com.kicklite.app.service;

import com.kicklite.app.model.Project;
import com.kicklite.app.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository repo;
    public ProjectService(ProjectRepository repo) { this.repo = repo; }

    public List<Project> all() { return repo.findAll(); }
    public Project create(Project p) { return repo.save(p); }
    public void delete(Long id) { repo.deleteById(id); }
}
