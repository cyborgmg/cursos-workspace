package com.devsuperior.hruserfeinggatway.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Worker implements Serializable {

    private static final long serialVesionUID = 1L;

    private Long id;
    private String name;
    private Double dailyIncome;

}
