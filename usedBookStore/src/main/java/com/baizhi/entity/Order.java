package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Order {
    private Integer id;
    private Integer bookId;
    private String bookName;
    private Integer buyerId;
    private String buyerName;
    private Integer sellerId;
    private String sellerName;
    private Integer num;
    private boolean active;
    private boolean contactBuyer;
    private boolean received;
    private boolean sendOut;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") //@DateTimeFormat来控制入参，@JsonFormat来控制出参
    private Date createDate;
}
