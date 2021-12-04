package com.example.ScaleableServer.ControllerLego;

import com.example.ScaleableServer.Entity.Lego;
import com.example.ScaleableServer.Service.ServiceLego;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/")
public class ControllerLego {

    ServiceLego ServiceLego;

    public ControllerLego(ServiceLego ServiceLego) {
        this.ServiceLego = ServiceLego;
    }


    @GetMapping("all")
    public Collection<Lego> getAllLego() {
        System.out.println(" ");
        return ServiceLego.getAllLego();
    }

    @GetMapping("{name}")
    public Collection<Lego> getNamedLego(@PathVariable("name") String name){
        System.out.println(" ");
        if (name.isBlank()){
            return null;
        }
        return ServiceLego.getChoosenLego(name);
    }
}
