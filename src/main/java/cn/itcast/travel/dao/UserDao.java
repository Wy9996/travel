package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {

    public User findbyUsername(String username);

    public void save(User user);


    User findbyCode(String code);

    void updateStatus(User user);
}
