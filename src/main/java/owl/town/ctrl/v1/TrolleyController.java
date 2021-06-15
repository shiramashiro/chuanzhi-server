package owl.town.ctrl.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import owl.town.domain.Trolley;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1")
public class TrolleyController {

    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping(value = "/set/trolley/row")
    public void setTrolleyRow(@RequestBody Trolley trolley) {
        mongoTemplate.save(trolley);
    }

    @PostMapping(value = "/get/trolley/rows")
    public List<Trolley> getTrolleyRows(@RequestBody Trolley trolley) {
        return mongoTemplate.find(
                new Query(
                        Criteria
                                .where("user_id")
                                .in(trolley.getUserId())
                ), Trolley.class);
    }

    @PostMapping(value = "/delete/trolley/row")
    public void deleteTrolleyRow(@RequestBody Trolley trolley) {
        mongoTemplate.remove(
            new Query(
                    Criteria
                            .where("_id")
                            .in(trolley.getId())
            ), Trolley.class);
    }

    @PostMapping(value = "/delete/trolley/rows")
    public void deleteTrolleyRows(@RequestBody Trolley trolley) {
        mongoTemplate.remove(
                new Query(
                        Criteria
                                .where("user_id")
                                .in(trolley.getUserId())
                )
                , Trolley.class);
    }

}
