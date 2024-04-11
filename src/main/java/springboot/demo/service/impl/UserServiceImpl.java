package springboot.demo.service.impl;

import org.springframework.stereotype.Service;
import springboot.demo.dao.IntAuthTokenDao;
import springboot.demo.domain.IntAuthToken;
import springboot.demo.service.UserService;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private IntAuthTokenDao intAuthTokenDao;

    @Override
    public IntAuthToken findByIntAuthToken(String intAuthToken) {
        return intAuthTokenDao.findByIntAuthToken(intAuthToken);
    }
}
