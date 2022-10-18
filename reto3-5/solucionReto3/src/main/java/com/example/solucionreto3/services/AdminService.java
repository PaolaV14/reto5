package com.example.solucionreto3.services;

import com.example.solucionreto3.entities.Admin;
import com.example.solucionreto3.entities.Client;
import com.example.solucionreto3.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAll(){
        return adminRepository.getAll();
    }

    public Optional<Admin> getAdmin (int id){
        return adminRepository.getAdmin(id);
    }

    public Admin save(Admin admin){
        if(admin.getIdAdmin()==null){
            return  adminRepository.save(admin);
        }else{
            Optional<Admin> e = adminRepository.getAdmin(admin.getIdAdmin());
            if(e.isPresent()){
                return admin;
            }else{
                return  adminRepository.save(admin);
            }
        }
    }

    public Admin update (Admin admin) {
        if (admin.getIdAdmin() != null) {
            Optional<Admin> q = adminRepository.getAdmin(admin.getIdAdmin());
            if (!q.isEmpty()) {
                if (admin.getName() != null) {
                    q.get().setName(admin.getName());
                }
                if(admin.getPassword()!= null){
                    q.get().setPassword(admin.getPassword());
                }
                return adminRepository.save(q.get());
            }
        }
        return admin;
    }

    public boolean delete(int id){
            boolean flag = false;
            Optional<Admin> p = adminRepository.getAdmin(id);
            if (p.isPresent()) {
                adminRepository.delete(p.get());
                flag = true;
            }
            return flag;
        }

}
