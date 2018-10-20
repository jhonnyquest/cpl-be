package co.cpl.service;

import co.cpl.api.generated.model.SimpleResponse;
import co.cpl.api.generated.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<User> getUsers(Long offset, Long limit);

    User getUserById(String userId);

    SimpleResponse deleteUser(String userId);

    SimpleResponse createUser(User newUser);

    SimpleResponse updateUser(User newUser);
}
