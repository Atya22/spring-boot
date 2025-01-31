package com.sp.store.dao.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@Builder
@Table(name = "discount")
@NoArgsConstructor
public class DiscountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Double discountPercentage;
    private Date start_date;
    private Date end_date;
}