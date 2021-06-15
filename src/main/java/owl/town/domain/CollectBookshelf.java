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
@Document(collection = "collect_bookshelfs")
public class CollectBookshelf implements Serializable {

    @Id
    private String id;

    private Bookshelf bookshelf;

    @Field(name = "collected_date")
    private String collectedDate;

    @Field(name = "user_id")
    private String userId;

}
