package org.builtinquality.pos.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class ItemEntity {
    @Id
    @JsonProperty
    private String code;

    @JsonProperty
    private String name;

    @JsonProperty
    private double price;

    @JsonProperty
    private String unit;

    public ItemEntity() {
    }

    public ItemEntity(String code, String name, String unit, double price) {
        this.code = code;
        this.name = name;
        this.unit = unit;
        this.price = price;
    }
}
