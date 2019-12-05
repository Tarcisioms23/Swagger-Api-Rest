/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.10).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Cliente;
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

@Api(value = "cliente", description = "the cliente API")
public interface ClienteApi {

    @ApiOperation(value = "Altera um cliente existente", nickname = "alteraExistente", notes = "Esta operação tem por objetivo alterar um cliente.", response = Cliente.class, tags={ "Manutencao", })
    @ApiResponses(value = { 
        @ApiResponse(code = 202, message = "Sucesso ao alterar um cliente existente.", response = Cliente.class),
        @ApiResponse(code = 400, message = "Alteração inválida."),
        @ApiResponse(code = 401, message = "Alteração não autorizada."),
        @ApiResponse(code = 500, message = "Erro interno ao tentar realizar a alteração.") })
    @RequestMapping(value = "/cliente/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Cliente> alteraExistente(@ApiParam(value = "Id do cliente.",required=true) @PathVariable("id") Integer id,@ApiParam(value = "" ,required=true )  @Valid @RequestBody Cliente cliente);


    @ApiOperation(value = "Alteração de status de cliente por id.", nickname = "alteraStatusPorId", notes = "Esta operacao tem por objetivo alterar o status do cliente por id.", response = Cliente.class, tags={ "Manutencao", })
    @ApiResponses(value = { 
        @ApiResponse(code = 202, message = "Sucesso ao alterar o status de um cliente existente.", response = Cliente.class),
        @ApiResponse(code = 400, message = "Alteração de status invalida."),
        @ApiResponse(code = 401, message = "Alteração de status não autorizada."),
        @ApiResponse(code = 500, message = "Erro interno ao tentar realizar alteração do status.") })
    @RequestMapping(value = "/cliente/{status}/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Cliente> alteraStatusPorId(@ApiParam(value = "Status do cliente.",required=true, allowableValues = "\"ativo\", \"inativo\"") @PathVariable("status") String status,@ApiParam(value = "Numero do id do cliente.",required=true) @PathVariable("id") Integer id);


    @ApiOperation(value = "Cadastro de um novo cliente.", nickname = "cadastraNovo", notes = "Esta operacao tem por objetivo cadastrar um novo cliente", response = Cliente.class, tags={ "Cadastro", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Sucesso ao cadastrar um novo cliente", response = Cliente.class),
        @ApiResponse(code = 400, message = "Cadastro invalido"),
        @ApiResponse(code = 401, message = "Cadastro nao autorizado"),
        @ApiResponse(code = 500, message = "Erro ao realizar o cadastro") })
    @RequestMapping(value = "/cliente",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Cliente> cadastraNovo(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Cliente cliente);


    @ApiOperation(value = "Consulta um cliente existe por id.", nickname = "consultaPorId", notes = "Esta consulta tem por objetivo consultar um cliente existente por id", response = Cliente.class, tags={ "Consulta", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Sucesso ao consultar cliente por id.", response = Cliente.class),
        @ApiResponse(code = 400, message = "Consulta invalida"),
        @ApiResponse(code = 401, message = "Consulta nao autorizada"),
        @ApiResponse(code = 404, message = "Nenhum cliente encontrado para a consulta por id."),
        @ApiResponse(code = 500, message = "Erro interno.") })
    @RequestMapping(value = "/cliente/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<Cliente> consultaPorId(@ApiParam(value = "Numero do id do Cliente",required=true) @PathVariable("id") Integer id);


    @ApiOperation(value = "Consulta cliente existente por sobrenome.", nickname = "consultaPorSobrenome", notes = "Esta operação tem por objetivo consultar um cliente existente por sobrenome.", response = Cliente.class, tags={ "Consulta", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Sucesso ao consultar cliente por sobrenome.", response = Clientes.class),
        @ApiResponse(code = 400, message = "Consulta inválida."),
        @ApiResponse(code = 401, message = "Consulta não autorizada."),
        @ApiResponse(code = 404, message = "Nenhum cliente encontrado para pesquiza."),
        @ApiResponse(code = 500, message = "Erro interno ao tentar realizar a consulta por sobrenome.") })
    @RequestMapping(value = "/cliente/consulta/{sobrenome}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<Clientes> consultaPorSobrenome(@ApiParam(value = "Sobrenome do Cliente.",required=true) @PathVariable("sobrenome") String sobrenome);


    @ApiOperation(value = "Exclui um cliente existente.", nickname = "excluiExistente", notes = "Esta operação tem por objetivo excluir um cliente existente.", tags={ "Exclusao", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Sucesso ao excluir um cliente existente."),
        @ApiResponse(code = 400, message = "Exclusão inválida."),
        @ApiResponse(code = 401, message = "Exclusão não autorizada."),
        @ApiResponse(code = 500, message = "Erro interno ao tentar realizar a exclusão.") })
    @RequestMapping(value = "/cliente/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.DELETE)
    ResponseEntity<Void> excluiExistente(@ApiParam(value = "",required=true) @PathVariable("id") Integer id);

}
