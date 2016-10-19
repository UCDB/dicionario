package br.ucdb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Dicionario {

    @Id
    @GeneratedValue
    private Integer id;

    private String palavra;
    private String significado;








}
