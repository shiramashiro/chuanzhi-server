package owl.town.ctrl.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import owl.town.domain.Indent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1")
public class IndentController {

    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping(value = "/set/indent")
    public void setIndent(@RequestBody Indent indent) {
        indent.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        mongoTemplate.save(indent);
    }

    @PostMapping(value = "/get/indents")
    public List<Indent> getIndents(@RequestBody Indent indent) {
        return mongoTemplate.find(new Query(
                Criteria
                        .where("user_id")
                        .in(indent.getUserId())
        ), Indent.class);
    }

    @PostMapping(value = "/delete/indent")
    public void deleteIndent(@RequestBody Indent indent) {
        mongoTemplate.remove(
            new Query(
                    Criteria
                            .where("_id")
                            .in(indent.getId())
            ), Indent.class);
    }

}
