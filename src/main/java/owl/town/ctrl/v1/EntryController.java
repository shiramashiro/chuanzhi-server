package owl.town.ctrl.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import owl.town.domain.User;
import owl.town.utils.Result;

import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1")
public class EntryController {

    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping(value = "/signin")
    public Result signin(@RequestBody User user) {
        User result = mongoTemplate.findOne(
                new Query(Criteria.where("username").is(user.getUsername())), User.class);
        if (result == null) {
            return new Result().fail("登陆失败，未找到该用户。");
        } else {
            if (!user.getPassword().equals(result.getPassword())) {
                return new Result().fail("登陆失败，密码错误");
            } else {
                return new Result().correct(result, "登陆成功");
            }
        }
    }

    @PostMapping(value = "signup")
    public Result signup(@RequestBody User user) {
        User result = mongoTemplate.findOne(
                new Query(
                    Criteria
                            .where("username")
                            .in(user.getUsername())
        ), User.class);
        if (result == null) {
            user.setProfilePhoto("https://owl-town.oss-cn-chengdu.aliyuncs.com/img/user/default-profile-photo.png");
            user.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
            mongoTemplate.save(user);
            return new Result().correct("注册成功");
        } else {
            return new Result().fail("已存在用户名");
        }
    }

}
