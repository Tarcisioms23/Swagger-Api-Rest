package io.swagger.api;

import io.swagger.model.Cliente;
import io.swagger.model.Clientes;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.api.dao.ClienteDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-03T15:14:39.861Z")

@Controller
public class ClientesApiController implements ClientesApi {

    private static final Logger log = LoggerFactory.getLogger(ClientesApiController.class);

    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;
    private ClienteDAO clienteDAO;

    @org.springframework.beans.factory.annotation.Autowired
    public ClientesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.clienteDAO = new ClienteDAO();
    }

    /**
     * 501  NAO DESENVOLVIDO
     * return new ResponseEntity<Clientes>(HttpStatus.NOT_IMPLEMETED)
     * 
     * 400 REQUISICAO INVALIDA
     * return new ResponseEntity<Clientes>(HttpStatus.BAD_REQUEST)
     * 
     *  401 REQUISICAO INVALIDA
     * return new ResponseEntity<Clientes>(HttpStatus.UNUATHORIZED)
     * 
     */
    
    public ResponseEntity<Clientes> consultaTodos() {
    	ResponseEntity<Clientes> responseEntity = null;
    	
    	try {
			
    		List<Cliente> clientes = clienteDAO.todos();
    		
    		//404 nenhum registro encontrado.
    		if(clientes != null) {
    			if(clientes.size() <= 0){
    				responseEntity = new ResponseEntity<Clientes>(objectMapper.readValue(objectMapper.writeValueAsString(clientes), Clientes.class), HttpStatus.NOT_FOUND);
    			}else {
    				//200 Sucessoao consultar  				
    				responseEntity = new ResponseEntity<Clientes>(objectMapper.readValue(objectMapper.writeValueAsString(clientes), Clientes.class), HttpStatus.OK);
    				
    			}

    		}
    		
		} catch (Exception e) {
			log.error("Erro ao tentar consultar clientes.");
			responseEntity = new ResponseEntity<Clientes>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	return responseEntity;
    	
    }

}
