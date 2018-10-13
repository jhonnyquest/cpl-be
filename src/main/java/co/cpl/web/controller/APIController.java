/******************************************************************
 *
 * This code is for the Complaints service project.
 *
 *
 * © 2018, Complaints Management All rights reserved.
 *
 *
 ******************************************************************/

package co.cpl.web.controller;

import co.cpl.api.generated.ConsoleController;
import co.cpl.api.generated.HelloController;
import co.cpl.enums.ResponseKeyName;
import co.cpl.service.HelloService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static co.cpl.utilities.Constants.RAML_URL;

/**
 * Main API controller that manage all endpoints functionalities
 *
 * @author jmunoz
 * @since 25/09/2018
 * @version 1.0
 */
@Component
public class APIController extends BaseRestController implements HelloController, ConsoleController {

    private HelloService helloService;

    public APIController(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public ResponseEntity<String> getStringByName(String name) {
        String hello = helloService.sayHello(name);
        return new ResponseEntity<>(hello, HttpStatus.OK);
//        return successResponse(hello, HttpStatus.OK);
    }

    private ResponseEntity<Object> successResponse(Object body, HttpStatus status) {
        ResponseEntity<Object> responseEntity;
        if(status.value() == 201) {
            responseEntity = ResponseEntity.created(null)
                    .body(createSuccessResponse(ResponseKeyName.CREATED_RESPONSE, body));
        } else {
            responseEntity = ResponseEntity.ok(createSuccessResponse(ResponseKeyName.OK_RESPONSE, body));
        }
        return responseEntity;
    }

    private ResponseEntity<Object> errorResponse(HttpClientErrorException ex) {
        HashMap<String, Object> map = new HashMap<>();
        HttpStatus status;
        switch (ex.getStatusCode().value()) {
            case 404:
                map.put("message", "Not Found");
                status = HttpStatus.NOT_FOUND;
                break;
            case 401:
                map.put("message", "Access denied");
                status = HttpStatus.UNAUTHORIZED;
                break;
            case 400:
                map.put("message", "bad request");
                status = HttpStatus.BAD_REQUEST;
                break;
            case 406:
                map.put("message", "invalid parameter");
                map.put("detail", ex.getMessage());
                status = HttpStatus.NOT_ACCEPTABLE;
                break;
            case 412:
                map.put("message", "invalid parameter");
                map.put("detail", ex.getMessage());
                status = HttpStatus.PRECONDITION_FAILED;
                break;
            case 500:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
            case 503:
                map.put("message", "Service unavailable");
                status = HttpStatus.SERVICE_UNAVAILABLE;
                break;
            default:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                map.put("message", "There was a problem trying to resolve the request");
        }
        return  ResponseEntity.status(status)
                .body(createExeptionResponse(ResponseKeyName.EXCEPTION_RESPONSE, map, ex));

    }

    @Override
    public ResponseEntity<?> getObject() {
        try {
            return new ResponseEntity<>(Files.readAllBytes(Paths.get(RAML_URL)), HttpStatus.OK);
        } catch (IOException e) {
            return errorResponse(new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE));
        }
    }
}
