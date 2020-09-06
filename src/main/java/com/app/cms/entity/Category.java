package com.app.cms.entity;

import com.app.cms.valueobject.category.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Category  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private Name name;
}
