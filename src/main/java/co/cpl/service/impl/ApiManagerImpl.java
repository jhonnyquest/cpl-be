package co.cpl.service.impl;

import co.cpl.api.generated.model.SimpleResponse;
import co.cpl.api.generated.model.User;
import co.cpl.service.APIManager;
import co.cpl.utilities.PropertyManager;
import co.cpl.utilities.RestTemplateHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Component
public class ApiManagerImpl implements APIManager {

	private RestTemplateHelper restTemplateHelper;
	private PropertyManager pm;


	public ApiManagerImpl(PropertyManager pm) {
		super();
		this.restTemplateHelper = new RestTemplateHelper(new RestTemplate());
		this.pm = pm;
	}

	@Override
	public List<User> getUsers(Long offset, Long limit) {

		Map<String,String> params = new HashMap<>();
		params.put("offset", String.valueOf(offset));
		params.put("limit", String.valueOf(limit));

        String property = pm.getProperty("cpl.users.base.endpoint");
        ResponseEntity<User[]> response = restTemplateHelper.processRequestGet(
				pm.getProperty("cpl.users.base.endpoint"), null, User[].class, params);

		if (response != null && response.getStatusCode().equals(HttpStatus.OK)) {
			return Arrays.asList(Objects.requireNonNull(response.getBody()));
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public User getUserById(String userId) {

        ResponseEntity<User> response = restTemplateHelper.processRequestGet(
				pm.getProperty("cpl.users.base.endpoint") + "/" + userId, User.class);

		if (response != null && response.getStatusCode().equals(HttpStatus.OK)) {
			return Objects.requireNonNull(response.getBody());
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

    @Override
    public SimpleResponse deleteUser(String userId) {

        ResponseEntity<SimpleResponse> response = restTemplateHelper.processRequestGet(
                pm.getProperty("cpl.users.base.endpoint") + "/" + userId, SimpleResponse.class);

        if (response != null && response.getStatusCode().equals(HttpStatus.OK)) {
            return response.getBody();
        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public SimpleResponse createUser(User newUser) {

        Map<String,String> params = new HashMap<>();
        params.put("name", newUser.getName());
        params.put("status", String.valueOf(newUser.getStatus()));
        params.put("phone", newUser.getPhone());

        ResponseEntity<SimpleResponse> response = restTemplateHelper.processRequestPost(
                pm.getProperty("cpl.users.base.endpoint"), params, SimpleResponse.class);

        if (response != null && response.getStatusCode().equals(HttpStatus.CREATED)) {
            return response.getBody();
        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_MODIFIED);
        }
    }

    @Override
    public SimpleResponse updateUser(User newUser) {

        Map<String,String> params = new HashMap<>();
        params.put("id", newUser.getId());
        params.put("name", newUser.getName());
        params.put("status", String.valueOf(newUser.getStatus()));
        params.put("phone", newUser.getPhone());

        ResponseEntity<SimpleResponse> response = restTemplateHelper.processRequestPut(
                pm.getProperty("cpl.users.base.endpoint"), params, SimpleResponse.class);

        if (response != null && response.getStatusCode().equals(HttpStatus.OK)) {
            return response.getBody();
        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_MODIFIED);
        }
    }
}
