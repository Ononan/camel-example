package ru.neoflex.camalexample.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ContentDTO {

    private Long productID;
    private String productName;
    private Integer quantity;

}
