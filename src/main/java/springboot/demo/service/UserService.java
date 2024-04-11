package springboot.demo.service;

import springboot.demo.domain.IntAuthToken;

public interface UserService {

    IntAuthToken findByIntAuthToken(String intAuthToken);
}
