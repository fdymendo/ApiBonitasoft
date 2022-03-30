package com.fdymendo.apibonitasoft.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Publicar {

  private String tipodevehiculo;
  private String marca;
  private String ano;
  private String placa;
  private String ciudadVenta;
  private String kms;
  private Double precio;
  private String color;
  private String usuario;
  private String plan;
  private Boolean aprobada;
  private Boolean pagada;
  private Integer tipousuario;
  private Integer tipoplan;
  private Integer calificacion;

}
