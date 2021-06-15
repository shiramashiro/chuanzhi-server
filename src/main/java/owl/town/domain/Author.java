package owl.town.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author implements Serializable {

    private String country;

    @Field("zh_name")
    private String zhName;

    @Field("en_name")
    private String enName;

}
