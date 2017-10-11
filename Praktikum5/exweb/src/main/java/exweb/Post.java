package exweb;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Post {

    private Long id;
    private String title;
    private String text;

}
