package com.ishaan.EliteEcom.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "dd-mm-yy")
    private Date releaseDate;
    private boolean  productAvailable;
    private int stockQuantity;

    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageData;

    public void setImageName(String imageName) {
        this.imageName=imageName;
    }

    public void setImageType(String imageType) {
        this.imageType=imageType;
    }

    public void setImageData(byte[] imageData) {
        this.imageData=imageData;
    }


    public byte[] getImage() {
        return imageData;
    }

    public String getImageType() {
        return imageType;
    }
}
