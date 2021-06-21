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
@Document(collection = "bookshelfs")
public class Bookshelf implements Serializable {

    @Id
    private String id;

    private String[] previews;

    private String title;

    private String profile;

    private String[] authors;

    private String press;

    @Field(name = "press_date")
    private String pressDate;

    private double price;

    private double discount;

    private String[] tags;

    private String cover;

    private String[] types;

    private Comment[] comments;

    @Field(name = "press_id")
    private String pressId;

    private String symbol;

}
