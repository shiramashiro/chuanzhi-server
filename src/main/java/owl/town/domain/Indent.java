package owl.town.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "indents")
public class Indent implements Serializable {

    @Id
    private String id;

    @Field(name = "user_id")
    private String userId;

    private String date;

    private double total;

    private Trolley[] trolley;

    @Field(name = "receive_location")
    private String receiveLocation;

    @Field(name = "receive_phone")
    private String receivePhone;

    @Field(name = "receive_name")
    private String receiveName;

    private String status;

    @Field(name = "pay_way")
    private String payWay;

    @Field(name = "status_type")
    private String statusType;

}
