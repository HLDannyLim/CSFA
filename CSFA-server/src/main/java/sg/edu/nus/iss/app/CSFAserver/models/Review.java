package sg.edu.nus.iss.app.CSFAserver.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;


import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    
    private String displayTitle;
    private String mpaaRating;
    private String byline;
    private String headline;
    private String summaryShort;
    private String url;
    private String src;
    private Long commentCount;




    public static List<Review> create(String json) throws IOException{
        List<Review> movie = new LinkedList<>();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())){
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            if(o.getJsonArray("results") != null)
                movie = o.getJsonArray("results").stream()
                    .map(v-> (JsonObject)v)
                    .map(v-> Review.createJson(v))
                    .toList(); 
        }
        return movie;
    }

    public static Review createJson(JsonObject o){
        Review m = new Review();
        m.displayTitle = o.getString("display_title");
        m.mpaaRating = o.getString("mpaa_rating");
        m.byline = o.getString("byline");
        m.headline = o.getString("headline");
        m.summaryShort = o.getString("summary_short");

        
        JsonObject oo = o.getJsonObject("link");
        m.url = oo.getString("url");
        try{
                JsonObject ooo = o.getJsonObject("multimedia");
                m.src = ooo.getString("src");
        }catch( Exception e){
            m.src = "null";
        }


    

        return m;
    }

    public JsonValue toJSON() {
        return Json.createObjectBuilder()
        .add("displayTitle", getDisplayTitle())
        .add("mpaaRating", getMpaaRating())
        .add("byline", getByline())
        .add("headline", getHeadline())
        .add("summaryShort", getSummaryShort())
        .add("url", getUrl())
        .add("src", getSrc())
        .add("commentCount", getCommentCount())
        .build();
    }
}


