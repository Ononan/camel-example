package ru.neoflex.camalexample.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class OrderDTO {

    private Long orderID;
    private String shopperName;
    private String shopperEmail;
    private List<ContentDTO> contents;
    private Boolean orderCompleted;
}
