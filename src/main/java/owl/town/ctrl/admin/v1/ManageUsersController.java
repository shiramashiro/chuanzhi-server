package owl.town.ctrl.admin.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;
import owl.town.domain.User;
import owl.town.utils.R;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/admin/v1")
public class ManageUsersController {

    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping(value = "/get/users")
    public R getUsers() {
        List<User> users = mongoTemplate.findAll(User.class);
        return new R().ok(users);
    }

    @PostMapping(value = "/update/user")
    public void updateUser(@RequestBody User reqBody) {
        mongoTemplate.updateMulti(
                new Query(Criteria.where("_id").is(reqBody.getId())),
                new Update()
                        .set("username", reqBody.getUsername())
                        .set("password", reqBody.getPassword())
                        .set("profile_photo", reqBody.getProfilePhoto())
                        .set("level", reqBody.getLevel())
                        .set("email", reqBody.getEmail())
                        .set("phone", reqBody.getPhone())
                        .set("profile", reqBody.getProfile())
                        .set("sex", reqBody.getSex())
                        .set("date", reqBody.getDate())
                , User.class);
    }

    @PostMapping(value = "/cutoff/user")
    public void cutoffUser(@RequestBody Map<String, Object> reqBody) {
        mongoTemplate.remove(
                new Query(
                        Criteria
                                .where("_id")
                                .is(reqBody.get("id"))
                ), User.class);
    }

}
