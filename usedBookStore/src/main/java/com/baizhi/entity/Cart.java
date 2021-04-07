package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Cart implements Serializable {
    private Integer id;
    private Integer buyerId;
    private Integer ownerId;
    private Integer bookId;
    private String bookName;
    private String smallImg;
    private float price;
    private Integer num;
}
