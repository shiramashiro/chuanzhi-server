package owl.town.ctrl.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import owl.town.domain.User;
import owl.town.utils.R;

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
    public R signin(@RequestBody User user) {
        User result = mongoTemplate.findOne(
                new Query(
                        Criteria
                                .where("username")
                                .in(user.getUsername())
                                .and("password")
                                .in(user.getPassword())
                ), User.class);
        if (result == null) {
            return new R().fail("登陆失败，未找到该用户。");
        } else {
            return new R().ok(result, "登陆成功");
        }
    }

    @PostMapping(value = "signup")
    public void signup(@RequestBody User user) {

    }

}
