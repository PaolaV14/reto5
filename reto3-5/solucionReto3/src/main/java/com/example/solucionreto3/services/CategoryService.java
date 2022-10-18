package com.example.solucionreto3.services;

import com.example.solucionreto3.entities.Category;
import com.example.solucionreto3.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int id){
        return categoryRepository.getCategory(id);
    }

    public Category save(Category p){
        if(p.getId()==null){
            return  categoryRepository.save(p);
        }else{
            Optional<Category> e = categoryRepository.getCategory(p.getId());
            if(e.isPresent()){
                return p;
            }else{
                return  categoryRepository.save(p);
            }
        }
    }

   //Codigo
   public Category update(Category category){
       if(category.getId()!=null){
           Optional<Category>g= categoryRepository.getCategory(category.getId());
           if(!g.isEmpty()){
               if(category.getDescription()!=null){
                   g.get().setDescription(category.getDescription());
               }
               if(category.getName()!=null){
                   g.get().setName(category.getName());
               }
               return categoryRepository.save(g.get());
           }
       }
       return category;
   }

    public boolean delete(int id){
            boolean flag = false;
            Optional<Category> p = categoryRepository.getCategory(id);
            if (p.isPresent()) {
                categoryRepository.delete(p.get());
                flag = true;
            }
            return flag;
        }

}
