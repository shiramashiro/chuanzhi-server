package owl.town.ctrl.admin.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;
import owl.town.domain.Bookshelf;
import owl.town.utils.R;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/admin/v1")
public class ManageBookshelfController {

    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping(value = "/get/bookshelf")
    public R getBookshelf() {
        return new R().ok(mongoTemplate.findAll(Bookshelf.class));
    }

    @PostMapping(value = "/update/bookshelf")
    public void updateBookshelf(@RequestBody Bookshelf reqBody) {
        mongoTemplate.updateMulti(
                new Query(Criteria.where("_id").is(reqBody.getId())),
                new Update()
                        .set("previews", reqBody.getPreviews())
                        .set("title", reqBody.getTitle())
                        .set("profile", reqBody.getProfile())
                        .set("press", reqBody.getPress())
                        .set("press_date", reqBody.getPressDate())
                        .set("price", reqBody.getPrice())
                        .set("discount", reqBody.getDiscount())
                        .set("cover", reqBody.getCover())
                        .set("press_id", reqBody.getPressId())
                        .set("tags", reqBody.getTags())
                        .set("authors", reqBody.getAuthors())
                        .set("types", reqBody.getTypes())
                , Bookshelf.class);
    }

    @PostMapping(value = "/append/bookshelf")
    public void appendBookshelf(@RequestBody Bookshelf reqBody) {
        mongoTemplate.save(reqBody);
    }

    @PostMapping(value = "/cutoff/bookshelf")
    public void cutoffBookshelf(@RequestBody Map<String, Object> reqBody) {
        mongoTemplate.remove(
                new Query(
                        Criteria.where("_id").is(reqBody.get("id"))
                ), Bookshelf.class);
    }

}
