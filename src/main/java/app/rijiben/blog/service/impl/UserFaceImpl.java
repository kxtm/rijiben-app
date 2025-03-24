package app.rijiben.blog.service.impl;

import app.rijiben.blog.data.entity.User;
import app.rijiben.blog.data.mapper.UserMapper;
import app.rijiben.blog.service.IUserFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userFace")
public class UserFaceImpl implements IUserFace {


    UserMapper userMapper;

    @Override
    public User getUser(String account) {
        return userMapper.selectByPrimaryKey(1L);
    }
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
