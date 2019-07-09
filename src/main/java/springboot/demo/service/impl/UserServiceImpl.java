package springboot.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.demo.dao.IntAuthTokenDao;
import springboot.demo.domain.IntAuthToken;
import springboot.demo.domain.User;
import springboot.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private IntAuthTokenDao intAuthTokenDao;

    @Override
    public IntAuthToken findByIntAuthToken(String intAuthToken) {
        return intAuthTokenDao.findByIntAuthToken(intAuthToken);
    }
}
