
package co.cpl.api.generated;

import java.util.List;
import javax.validation.Valid;
import co.cpl.api.generated.model.SimpleResponse;
import co.cpl.api.generated.model.User;
import org.springframework.http.ResponseEntity;


/**
 * Users management
 * (Generated with springmvc-raml-parser v.2.0.3)
 * 
 */
public interface UserController {


    /**
     * No description
     * 
     */
    public ResponseEntity<List<User>> getUsers(Long offset, Long limit);

    /**
     * No description
     * 
     */
    public ResponseEntity<SimpleResponse> createUser(
        @Valid
        User user);

    /**
     * No description
     * 
     */
    public ResponseEntity<SimpleResponse> updateUser(
        @Valid
        User user);

    /**
     * No description
     * 
     */
    public ResponseEntity<User> getUserByUserId(String userId);

    /**
     * No description
     * 
     */
    public ResponseEntity<SimpleResponse> deleteUserByUserId(String userId);

}
