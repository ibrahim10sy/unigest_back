package gestion.scolaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import gestion.scolaire.model.Admin;
import gestion.scolaire.service.AdminService;
import jakarta.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping
    public Admin creer(@Valid @RequestBody Admin admin){
        return adminService.creer(admin);
    }

    @GetMapping
    public List<Admin> liste(){
        return adminService.liste();
    }

    @GetMapping("/{id}")
    public Admin get(@PathVariable Long id){
        return adminService.getById(id);
    }

    @PutMapping("/{id}")
    public Admin update(@PathVariable Long id,
                        @Valid @RequestBody Admin admin){
        return adminService.update(id, admin);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        adminService.delete(id);
    }
}