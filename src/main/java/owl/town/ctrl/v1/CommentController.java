package owl.town.ctrl.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import owl.town.domain.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1")
public class CommentController {

    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping(value = "/get/comments")
    public List<Comment> getComments(@RequestBody Map<String, Object> params) {
        if (params.get("type").equals("all")) {
            return mongoTemplate.find(new Query(Criteria.where("bookshelf_id").in(params.get("bookshelfId"))), Comment.class);
        } else {
            return mongoTemplate.find(new Query(Criteria.where("bookshelf_id").in(params.get("bookshelfId")).and("type").in(params.get("type"))), Comment.class);
        }
    }

}
