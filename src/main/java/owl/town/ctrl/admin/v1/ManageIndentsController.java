package owl.town.ctrl.admin.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;
import owl.town.domain.Indent;
import owl.town.utils.Result;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/admin/v1")
public class ManageIndentsController {

    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping(value = "/get/indents")
    public Result getIndents(@RequestBody Map<String, Object> reqBody) {
        List<Indent> indents;
        if (reqBody.get("statusType").equals("noStatusType")) { // noStatusType代表查询所有的订单，包括正在发货和未发货的订单
            indents = mongoTemplate.findAll(Indent.class);
        } else { // 否者查询对应类型的订单，如delivering查询正在发货的订单
            indents = mongoTemplate.find(
                    new Query(
                            Criteria
                                    .where("status_type")
                                    .is(reqBody.get("statusType"))
            ), Indent.class);
        }
        return new Result().correct(indents);
    }

    @PostMapping(value = "/cutoff/indent")
    public void cutoffIndent(@RequestBody Map<String, Object> reqBody) {
        mongoTemplate.remove(
                new Query(
                        Criteria
                                .where("_id")
                                .is(reqBody.get("id"))
                ), Indent.class);
    }

    @PostMapping(value = "/update/indent")
    public void updateIndent(@RequestBody Indent reqBody) {
        mongoTemplate.updateMulti(
                new Query(Criteria.where("_id").is(reqBody.getId())),
                new Update()
                            .set("date", reqBody.getDate())
                            .set("total", reqBody.getTotal())
                            .set("receive_location", reqBody.getReceiveLocation())
                            .set("receive_phone", reqBody.getReceivePhone())
                            .set("receive_name", reqBody.getReceiveName())
                            .set("status", reqBody.getStatus())
                            .set("status_type", reqBody.getStatusType())
                            .set("pay_way", reqBody.getPayWay())
        , Indent.class);
    }

}
