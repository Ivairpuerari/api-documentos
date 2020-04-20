/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eav.documentos.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Ivaai
 */
@Document(collection = "documento")
@Getter
@Setter
@NoArgsConstructor
public class Documento {
    @Id
    private String id;
    
    private String username;
    
    private String texto;
      
}
