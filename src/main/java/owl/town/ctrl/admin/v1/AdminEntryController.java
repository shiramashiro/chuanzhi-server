package owl.town.ctrl.admin.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import owl.town.domain.User;
import owl.town.utils.Result;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/admin/v1")
public class AdminEntryController {

    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 1. 根据用户名查询该用户的所有信息，否则提示不存在该用户；
     * 2. 再根据信息对比密码是否正确，否则提示密码错误；
     * 3. 根据信息再对比是否为管理用户，否则提示非管理用户。
     *
     * @param reqBody body
     * @return user
     */
    @PostMapping(value = "/signin")
    public Result signin(@RequestBody User reqBody) {
        User user = mongoTemplate.findOne(new Query(Criteria.where("username").is(reqBody.getUsername())), User.class);
        if (user == null) {
            return new Result().fail("登陆失败，用户未注册");
        } else {
            if (!reqBody.getPassword().equals(user.getPassword())) {
                return new Result().fail("登陆失败，密码错误");
            } else if (!user.isAdmin()) {
                return new Result().fail("登陆失败，非管理员用户");
            } else {
                return new Result().correct(user, "登陆成功");
            }
        }
    }

}
