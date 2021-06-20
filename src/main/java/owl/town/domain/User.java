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
@Document(collection = "users")
public class User implements Serializable {

    @Id
    private String id;

    private String username;

    private String password;

    @Field(name = "profile_photo")
    private String profilePhoto;

    private int level;

    private String email;

    private String sex;

    private String phone;

    private String profile;

    private String date;

    @Field(name = "is_admin")
    private boolean isAdmin;

}
