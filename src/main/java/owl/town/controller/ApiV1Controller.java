package owl.town.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import owl.town.domain.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1")
public class ApiV1Controller {

    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping(value = "/get/books/by/types/{types}")
    public List<Book> getBooksByTypes(@PathVariable String types) {
        if (types.equals("all")) {
            return mongoTemplate.findAll(Book.class);
        } else {
            return mongoTemplate.find(new Query(Criteria.where("types").in(types)), Book.class);
        }
    }

    @PostMapping(value = "/get/book/by/id/{id}")
    public Book getBookById(@PathVariable String id) {
        return mongoTemplate.findById(id, Book.class);
    }


    @PostMapping(value = "/get/comments")
    public List<Comment> getComments(@RequestBody Map<String, Object> params) {
        if (params.get("type").equals("all")) {
            return mongoTemplate.find(new Query(Criteria.where("book_id").in(params.get("id"))), Comment.class);
        } else {
            return mongoTemplate.find(new Query(Criteria.where("book_id").in(params.get("id")).and("type").in(params.get("type"))), Comment.class);
        }
    }

    @PostMapping(value = "/set/trolley/row")
    public void setTrolleyRow(@RequestBody Trolley trolley) {
        System.out.println(trolley);
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
                )
        , Trolley.class);
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

    @PostMapping(value = "/set/indent")
    public void setIndent(@RequestBody Indent indent) {
        indent.setDate(new SimpleDateFormat("yyyy-MM-dd mm:hh").format(new Date()));
        mongoTemplate.save(indent);
    }

    @PostMapping(value = "/get/indents")
    public List<Indent> getIndents(@RequestBody Indent indent) {
        return null;
    }

    @PostMapping(value = "/delete/indent")
    public void deleteIndent(@RequestBody Indent indent) {
        System.out.println(indent);
    }
}
