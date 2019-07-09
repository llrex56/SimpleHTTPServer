package springboot.demo.service;

import springboot.demo.domain.IntAuthToken;

public interface UserService {

    public IntAuthToken findByIntAuthToken(String intAuthToken);
}
