package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Book implements Serializable {
    private Integer id;
    private String name;
    private Integer storage;
    private String describes;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") //@DateTimeFormat来控制入参，@JsonFormat来控制出参
    private Date buyDate ;
    private float price;
    private String type_;
    private Integer userId;
    private String pic;
    private Boolean saleNow;
    private String author;
}
