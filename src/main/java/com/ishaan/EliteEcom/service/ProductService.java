package com.ishaan.EliteEcom.service;


import com.ishaan.EliteEcom.model.Product;
import com.ishaan.EliteEcom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public List<Product> getAllProducts(){
        return productRepo.findAll();

    }

    public Optional<Product> getProductById(int id) {

        return productRepo.findById(id);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
         return productRepo.save(product);


    }
    public Optional<Product> getProduct(Long id){
        return productRepo.findById(Math.toIntExact(id));
    }


    public Product updateProduct(int id, Product product, MultipartFile imageFile) {
        try {
            product.setImageData(imageFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return productRepo.save(product);
    }

    public void deleteProduct(int id) {
        productRepo.deleteById(id);
    }
    public List<Product> searchProducts(String keyword) {
        return productRepo.searchProducts(keyword);
    }
}
