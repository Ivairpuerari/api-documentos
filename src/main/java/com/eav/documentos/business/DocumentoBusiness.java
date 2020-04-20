/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eav.documentos.business;

import com.eav.documentos.entities.Documento;
import com.eav.documentos.repositories.DocumentoRepository;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ivaai
 */

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DocumentoBusiness {
    
    @Autowired
    private DocumentoRepository documentoRepository;
     
    public List<Documento> listaDocumentos(){
        List<Documento> processos = documentoRepository.findAll();
        
        return processos;
    }  
    
    public Documento listaDocumento( String id ) {
        Optional<Documento> documento = documentoRepository.findById( id );
        
        return documento.orElse( null );
    }
    
    public List<Documento> salvarDocumentos( List<Documento> documentos ) {
        documentoRepository.saveAll( documentos );
       
        return documentos;
    } 
    
    public Documento salvarDocumento( Documento documento ) {
        documentoRepository.save( documento );

        return documento;
    }
    
    private Documento copyData(@NonNull Documento documento, Documento novoDocumento) {
       documento.setTexto(novoDocumento.getTexto());
       documento.setUsername(novoDocumento.getUsername()); 
       
       return documento;
    }
    
    public Documento atualizaDocumento(@NonNull Documento novoDocumento ) {
        Documento documento = documentoRepository.findById( novoDocumento.getId() ).get();
        
        copyData(documento, novoDocumento);
        
        documentoRepository.save(documento);
        
        return documento;
    }
}
