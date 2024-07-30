package com.ishaan.EliteEcom.controller;


import com.ishaan.EliteEcom.model.Product;
import com.ishaan.EliteEcom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

//    @RequestMapping("/")
//    public String greet(){
//        return "hello aliens";

    //}
    @Autowired
    ProductService productService;
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/product/{id}")
    public Optional<Product> getProductById(@PathVariable int id){
        return productService.getProductById(id);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile){
        try {
            System.out.println(product);
            Product product1 = productService.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


@GetMapping("/product/{productId}/image")
public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){

    Optional<Product> product = productService.getProductById(productId);
    byte[] imageFile = product.get().getImage();

    return ResponseEntity.ok()
            .contentType(MediaType.valueOf(product.get().getImageType()))
            .body(imageFile);

}

@PutMapping("/product/{id}")
public ResponseEntity<String> updateProduct(@PathVariable int id,@RequestPart Product product,
                                            @RequestPart MultipartFile imageFile ){

Product product1 = productService.updateProduct(id,product,imageFile);
if(product1 != null){
    return new ResponseEntity<>("Updated",HttpStatus.OK);
}
else{
    return new ResponseEntity<>("failed",HttpStatus.BAD_REQUEST);
}
}

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Optional<Product> product = productService.getProductById(id);
        if(product.isPresent()) {
            productService.deleteProduct(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);

    }


    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
        List<Product> products = productService.searchProducts(keyword);
        System.out.println("searching with " + keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }





}
