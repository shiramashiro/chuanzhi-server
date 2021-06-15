package owl.town.ctrl.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import owl.town.domain.Bookshelf;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1")
public class BookshelfController {

    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping(value = "/get/bookshelfs/by/types/{types}")
    public List<Bookshelf> getBookshelfsByTypes(@PathVariable String types) {
        if (types.equals("all")) {
            return mongoTemplate.findAll(Bookshelf.class);
        } else {
            return mongoTemplate.find(new Query(Criteria.where("types").in(types)), Bookshelf.class);
        }
    }

    @PostMapping(value = "/get/bookshelf/{id}")
    public Bookshelf getBookshelf(@PathVariable String id) {
        return mongoTemplate.findById(id, Bookshelf.class);
    }

}