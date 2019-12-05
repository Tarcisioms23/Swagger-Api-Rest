/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.10).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Clientes;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-03T15:14:39.861Z")

@Api(value = "clientes", description = "the clientes API")
public interface ClientesApi {

    @ApiOperation(value = "Consulta todos clientes", nickname = "consultaTodos", notes = "Essa operação tem como objetivo consultar todos os clientes", response = Clientes.class, tags={ "Consulta", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Sucesso ao consultar todos clientes.", response = Clientes.class),
        @ApiResponse(code = 400, message = "Consulta invalida"),
        @ApiResponse(code = 401, message = "Consulta nao autorizada"),
        @ApiResponse(code = 404, message = "Nenhum cliente encontrado para a consulta"),
        @ApiResponse(code = 500, message = "Erro interno ao tentar realizar a consulta") })
    @RequestMapping(value = "/clientes",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<Clientes> consultaTodos();

}
