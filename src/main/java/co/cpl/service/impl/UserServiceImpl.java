package co.cpl.service.impl;

import co.cpl.api.generated.model.SimpleResponse;
import co.cpl.api.generated.model.User;
import co.cpl.service.APIManager;
import co.cpl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    APIManager apiManager;



    @Override
    public List<User> getUsers(Long offset, Long limit) {

        return apiManager.getUsers(offset, limit);
    }

    @Override
    public User getUserById(String userId) {

        User userById = apiManager.getUserById(userId);
        return apiManager.getUserById(userId);
    }

    @Override
    public SimpleResponse deleteUser(String userId) {

        return  apiManager.deleteUser(userId);
    }

    @Override
    public SimpleResponse createUser(User newUser) {

        return apiManager.createUser(newUser);
    }

    @Override
    public SimpleResponse updateUser(User newUser) {

        return apiManager.updateUser(newUser);
    }
}
