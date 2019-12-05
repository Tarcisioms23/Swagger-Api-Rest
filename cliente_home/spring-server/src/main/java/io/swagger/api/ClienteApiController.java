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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-03T15:14:39.861Z")

@Controller
public class ClienteApiController implements ClienteApi {

	private static final Logger log = LoggerFactory.getLogger(ClienteApiController.class);

	private final ObjectMapper objectMapper;
	private final HttpServletRequest request;
	private ClienteDAO clienteDAO;

	@org.springframework.beans.factory.annotation.Autowired
	public ClienteApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
		this.clienteDAO = new ClienteDAO();
	}

	public ResponseEntity<Cliente> alteraExistente(
			@ApiParam(value = "Id do cliente.", required = true) @PathVariable("id") Integer id,
			@ApiParam(value = "", required = true) @Valid @RequestBody Cliente cliente) {
		ResponseEntity<Cliente> responseEntity = null;

		try {

			cliente.setId(id);
			Cliente clienteUpdate = clienteDAO.altera(cliente);

			if (clienteUpdate == null) {
				throw new RuntimeException("Erro ao tentar alterar cliente.");
			}

			responseEntity = new ResponseEntity<Cliente>(clienteUpdate, getHeaderLocation(clienteUpdate.getId()),
					HttpStatus.ACCEPTED);

		} catch (Exception e) {
			log.error("Falha ao tentar alterar o cliente");
			responseEntity = new ResponseEntity<Cliente>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;

	}

	private MultiValueMap<String, String> getHeaderLocation(Integer id) {

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri();

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("location", location.getPath());

		return headers;
	}

	public ResponseEntity<Cliente> alteraStatusPorId(
			@ApiParam(value = "Status do cliente.", required = true, allowableValues = "\"ativo\", \"inativo\"") @PathVariable("status") String status,
			@ApiParam(value = "Numero do id do cliente.", required = true) @PathVariable("id") Integer id) {

		ResponseEntity<Cliente> responseEntity = null;

		try {

			
			Cliente clienteUpdateStatus = clienteDAO.alteraStatusPorId(id, status);

			if (clienteUpdateStatus == null) {
				throw new RuntimeException("Erro ao tentar alterar o cliente.");
			}

			responseEntity = new ResponseEntity<Cliente>(clienteUpdateStatus, getHeaderLocation(clienteUpdateStatus.getId()),
					HttpStatus.ACCEPTED);

		} catch (Exception e) {
			log.error("Falha ao tentar alterar status do cliente");
			responseEntity = new ResponseEntity<Cliente>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;
	}

	public ResponseEntity<Cliente> cadastraNovo(
			@ApiParam(value = "", required = true) @Valid @RequestBody Cliente cliente) {

		ResponseEntity<Cliente> responseEntity = null;

		try {
			if (ehValido(cliente)) {

				Cliente clienteNew = clienteDAO.salva(cliente);

				if (clienteNew == null) {
					throw new RuntimeException("Erro ao tentar cadastrar no novo cliente");
				}

				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
						.buildAndExpand(clienteNew.getId()).toUri();

				MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
				headers.add("location", location.getPath());

				responseEntity = new ResponseEntity<Cliente>(clienteNew, headers, HttpStatus.CREATED);
			} else {

				responseEntity = new ResponseEntity<Cliente>(HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			log.error("Falha ao tentar cadastrar cliente", e);
			responseEntity = new ResponseEntity<Cliente>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}

	private boolean ehValido(Cliente cliente) {

		if (cliente != null) {
			return true;
		}

		return false;
	}

	public ResponseEntity<Cliente> consultaPorId(@ApiParam(value = "Numero do id do Cliente",required=true) @PathVariable("id") Integer id) {
		ResponseEntity<Cliente> responseEntity = null;
		
		try {
			
			Cliente cliente = clienteDAO.consultaPorId(id);
			
			if (cliente == null) {
				responseEntity = new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
			}else {
				responseEntity = new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			log.error("Falha ao tentar consultar cliente por id", e);
			return new ResponseEntity<Cliente>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
		
		
		

	}

	public ResponseEntity<Clientes> consultaPorSobrenome(
			@ApiParam(value = "Sobrenome do Cliente.", required = true) @PathVariable("sobrenome") String sobrenome) {

		ResponseEntity<Clientes> responseEntity = null;
		
		
		try {
			List<Cliente> clienteslz = clienteDAO.consultaPorSobrenome(sobrenome);
			if (clienteslz == null) {
				responseEntity = new ResponseEntity<Clientes>(HttpStatus.NOT_FOUND);
			}else {
				responseEntity = new ResponseEntity<Clientes>(objectMapper.readValue(objectMapper.writeValueAsString(clienteslz), Clientes.class), HttpStatus.OK);
			}
			
		} catch (Exception e) {
			log.error("Falha ao tentar consultar cliente por sobrenome", e);
			return new ResponseEntity<Clientes>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}

	public ResponseEntity<Void> excluiExistente(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {


		ResponseEntity<Void> responseEntity = null;
		
		try {
			
			boolean excluido = clienteDAO.exclui(id);
			
			if (excluido) {
				responseEntity = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}else {
				throw new RuntimeException("Erro ao excluir cliente.");
			}
			
		} catch (Exception e) {
			log.error("Falha ao tentar consultar cliente por sobrenome", e);
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}

}
