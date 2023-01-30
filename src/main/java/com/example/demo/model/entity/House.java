package com.example.demo.model.entity;

import com.example.demo.model.enums.HouseStatus;
import com.example.demo.model.enums.MaterialType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "houses")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class House {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    MaterialType material;

    @Column(name = "built_date")
    String builtDate;

    @Column(name = "floors_count")
    Integer floorsCount;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    LocalDateTime createdAt;

    @JsonIgnore
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    User user;

    String name;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    HouseStatus status = HouseStatus.CREATED;

}
