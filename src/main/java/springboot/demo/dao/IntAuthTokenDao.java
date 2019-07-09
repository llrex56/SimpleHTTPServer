package springboot.demo.dao;

import springboot.demo.domain.IntAuthToken;

public interface IntAuthTokenDao {

    IntAuthToken findByIntAuthToken(String intAuthToken);
}
