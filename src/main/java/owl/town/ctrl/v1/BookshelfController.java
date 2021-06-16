package owl.town.ctrl.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import owl.town.domain.Bookshelf;
import owl.town.domain.CollectBookshelf;
import owl.town.domain.Press;
import owl.town.utils.R;

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

    @PostMapping(value = "/get/bookshelf/by/pressId/{pressId}")
    public R getBookshelfByPressId(@PathVariable String pressId) {
        List<Bookshelf> result = mongoTemplate.find(
                new Query(
                        Criteria.where("press_id").in(pressId)
                ), Bookshelf.class);
        return new R().ok(result);
    }

    @PostMapping(value = "/get/press/by/id/{id}")
    public R getPressById(@PathVariable String id) {
        Press press = mongoTemplate.findOne(
                new Query(
                        Criteria.where("_id").in(id)
                ), Press.class);
        return new R().ok(press);
    }

    @PostMapping(value = "/get/bookshelf/{id}")
    public Bookshelf getBookshelf(@PathVariable String id) {
        return mongoTemplate.findById(id, Bookshelf.class);
    }

    @PostMapping(value = "/set/collectBookshelf")
    public void setCollectBookshelf(@RequestBody CollectBookshelf collectBookshelf) {
        mongoTemplate.save(collectBookshelf);
    }

    @PostMapping(value = "/get/collectBookshelfs")
    public List<CollectBookshelf> getCollectBookshelfs(@RequestBody CollectBookshelf collectBookshelf) {
        return mongoTemplate.find(new Query(
                Criteria.where("user_id").in(collectBookshelf.getUserId())
        ), CollectBookshelf.class);
    }
}
