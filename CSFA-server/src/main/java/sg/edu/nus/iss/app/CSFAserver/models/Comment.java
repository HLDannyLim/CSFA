package sg.edu.nus.iss.app.CSFAserver.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private String name;
    private String posterName;
    private Integer rating;
    private String commentText;


    public JsonObject toJSON() {

        return Json.createObjectBuilder()
                .add("name", getName())
                .add("posterName", getPosterName())
                .add("rating", getRating())
                .add("commentText", getCommentText())
                .build();
    }
}
