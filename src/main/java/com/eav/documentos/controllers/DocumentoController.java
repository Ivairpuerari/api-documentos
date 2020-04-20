/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eav.documentos.controllers;

import com.eav.documentos.business.DocumentoBusiness;
import com.eav.documentos.entities.Documento;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ivaai
 */
@RestController
@RequestMapping( value = "/api-documentos" )
@Scope( ConfigurableBeanFactory.SCOPE_PROTOTYPE )
public class DocumentoController {

    @Autowired
    private DocumentoBusiness documentoBusiness;
    
    @RequestMapping(value = "/documentos", method = RequestMethod.GET )
    public ResponseEntity< List<Documento> > listaDocumentos(){
       return new ResponseEntity<>( documentoBusiness.listaDocumentos(), HttpStatus.OK );
    }
    
    @RequestMapping(value = "/documento/{id}", method = RequestMethod.GET )
    public ResponseEntity< Documento > listaDocumento( @PathVariable("id") String id ){
       return new ResponseEntity<>( documentoBusiness.listaDocumento(id) , HttpStatus.OK );
    }
    
    @RequestMapping( value = "/documentos/inserir", method = RequestMethod.POST )
    public ResponseEntity< List<Documento> > salvarDocumentos(@RequestBody List<Documento> documentos ){
       return new ResponseEntity<>( documentoBusiness.salvarDocumentos( documentos ), HttpStatus.OK );
    }
    
    @RequestMapping( value = "/documento/inserir", method = RequestMethod.POST )
    public ResponseEntity<Documento> salvarDocumento(@RequestBody Documento documento ){
       return new ResponseEntity<>( documentoBusiness.salvarDocumento( documento ) , HttpStatus.OK );
    }
    
    @RequestMapping( value = "/documento/editar", method = RequestMethod.PUT )
    public ResponseEntity<Documento> editarDocumento(@RequestBody Documento documento ){
       return new ResponseEntity<>( documentoBusiness.atualizaDocumento( documento ), HttpStatus.OK );
    }
    
    
}
