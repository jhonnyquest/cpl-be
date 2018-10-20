
package co.cpl.api.generated;

import java.util.List;
import javax.validation.Valid;
import co.cpl.api.generated.model.SimpleResponse;
import co.cpl.api.generated.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Users management
 * (Generated with springmvc-raml-parser v.2.0.3)
 * 
 */
@RestController
@RequestMapping("/v1/users")
@Validated
public class UserControllerDecorator
    implements UserController
{

    @Autowired
    private UserController userControllerDelegate;

    /**
     * No description
     * 
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers(
        @RequestParam(required = false, defaultValue = "0")
        Long offset,
        @RequestParam(required = false, defaultValue = "1000000")
        Long limit) {
        return this.userControllerDelegate.getUsers(offset, limit);
    }

    /**
     * No description
     * 
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<SimpleResponse> createUser(
        @Valid
        @RequestBody
        User user) {
        return this.userControllerDelegate.createUser(user);
    }

    /**
     * No description
     * 
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<SimpleResponse> updateUser(
        @Valid
        @RequestBody
        User user) {
        return this.userControllerDelegate.updateUser(user);
    }

    /**
     * No description
     * 
     */
    @RequestMapping(value = "/{user-id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByUserId(
        @PathVariable("user-id")
        String userId) {
        return this.userControllerDelegate.getUserByUserId(userId);
    }

    /**
     * No description
     * 
     */
    @RequestMapping(value = "/{user-id}", method = RequestMethod.DELETE)
    public ResponseEntity<SimpleResponse> deleteUserByUserId(
        @PathVariable("user-id")
        String userId) {
        return this.userControllerDelegate.deleteUserByUserId(userId);
    }

}
