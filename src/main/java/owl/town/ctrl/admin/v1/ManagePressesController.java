package owl.town.ctrl.admin.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;
import owl.town.domain.Press;
import owl.town.utils.Result;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/admin/v1")
public class ManagePressesController {

    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @PostMapping(value = "/get/presses")
    public Result getPresses() {
        List<Press> presses = mongoTemplate.findAll(Press.class);
        return new Result().correct(presses);
    }

    @PostMapping(value = "/update/press")
    public void updatePress(@RequestBody Press reqBody) {
        mongoTemplate.updateMulti(
                new Query(Criteria.where("_id").is(reqBody.getId())),
                new Update()
                        .set("cover", reqBody.getCover())
                        .set("profile", reqBody.getProfile())
                        .set("name", reqBody.getName())
                , Press.class);
    }

    @PostMapping(value = "/cutoff/press")
    public void cutoffPress(@RequestBody Map<String, Object> reqBody) {
        mongoTemplate.remove(
                new Query(
                        Criteria.where("_id").is(reqBody.get("id"))
                ), Press.class);
    }

    @PostMapping(value = "/append/press")
    public void appendPress(@RequestBody Press reqBody) {
        mongoTemplate.save(reqBody);
    }

}
