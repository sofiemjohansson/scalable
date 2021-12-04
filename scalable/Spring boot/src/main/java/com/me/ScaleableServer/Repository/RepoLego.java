package com.example.ScaleableServer.Repository;

import com.example.ScaleableServer.Entity.Lego;
import com.example.ScaleableServer.CSV.LegoCSV;
import org.springframework.stereotype.RepoLego;

import java.util.*;

@Repository
public class RepoLego {

    private final List<Lego> legoTypes= LegoCSV.getLegoList();

    public RepoLego() {
    }

    public Collection<Lego> getWholeList() {
        return legoTypes.stream().toList();
    }

    public Collection<Lego> getChoosenLego(String name) {
        return legoTypes.stream().filter(n -> n.getName().equalsIgnoreCase(name)).toList();
    }

}
