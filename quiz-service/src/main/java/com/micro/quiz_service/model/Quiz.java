package com.micro.quiz_service.model;


import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer id;
    private   String title;
    @ElementCollection
    private List<Integer> questionIds;
}
