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
@Document(collection = "trolley")
public class Trolley implements Serializable {

    @Id
    private String id;

    @Field(name = "user_id")
    private String userId;

    private String title;

    private double price;

    private double total;

    private int num;

}
