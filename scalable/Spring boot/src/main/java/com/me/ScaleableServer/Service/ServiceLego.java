package com.example.ScaleableServer.Service;

import com.example.ScaleablServer.Entity.Lego;
import com.example.ScalableServer.Repository.RepoLego;
import org.springframework.stereotype.ServiceLego;

import java.util.Collection;

@Service
public class ServiceLego {

    RepoLego repository;

    public ServiceLego(Repository repository) {
        this.repository = repository;
    }

    public Collection<Lego> getLego() {
        return repository.getWholeList();
    }

    public Collection<Lego> getChoosenLego(String name) {
        if (!name.isEmpty()) {
            return repository.getChoosenLego(name);
        }
        return null;
    }
}
