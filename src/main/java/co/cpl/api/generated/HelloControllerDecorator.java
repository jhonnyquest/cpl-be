
package co.cpl.api.generated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Retrieve a basic test message from backend
 * (Generated with springmvc-raml-parser v.2.0.3)
 * 
 */
@RestController
@RequestMapping("/v1/hello")
@Validated
public class HelloControllerDecorator
    implements HelloController
{

    @Autowired
    private HelloController helloControllerDelegate;

    /**
     * No description
     * 
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<String> getStringByName(
        @RequestParam
        java.lang.String name) {
        return this.helloControllerDelegate.getStringByName(name);
    }

}
