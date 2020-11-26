package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDaoImpl();
    /**
     * 注册
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        //根据用户查询对象
        User u = dao.findbyUsername(user.getUsername());
        //判断u是否为null
        if(u != null){
            return false;
        }
        //设置激活码
        user.setCode(UuidUtil.getUuid());
        //设置激活状态
        user.setStatus("N");
        //保存用户信息
        dao.save(user);

        //发送邮件
        System.out.println("--------发送邮件-------");
        String content = "<a href='http://localhost/travel/ActiveUserServlet?code="+user.getCode()+"'>点击激活</a>";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        return true;

    }

    /**
     * 激活用户
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        //根据激活码查询对象
       User user =  dao.findbyCode(code);
        //修改激活码状态
        if (user!=null){
            dao.updateStatus(user);
            return true;
        }else {
            return false;
        }

    }
}
