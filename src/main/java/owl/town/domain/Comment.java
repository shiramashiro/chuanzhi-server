package owl.town.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comments")
public class Comment implements Serializable {

    private String id;

    @Field(name = "post_date")
    private String postDate;

    private String content;

    private User user;

    private int dianzan;

    private int cai;

    private String type;

    @Field(name = "bookshelf_id")
    private String bookshelfId;

}
