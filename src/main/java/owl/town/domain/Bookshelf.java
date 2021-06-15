package owl.town.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "bookshelfs")
public class Bookshelf {

    @Id
    private String id;

    private String[] previews;

    private String title;

    private String profile;

    private Author[] authors;

    private String press;

    @Field(name = "press_date")
    private String pressDate;

    private double price;

    private double discount;

    private String[] tags;

    private String cover;

    private String[] types;

    private Comment[] comments;

}