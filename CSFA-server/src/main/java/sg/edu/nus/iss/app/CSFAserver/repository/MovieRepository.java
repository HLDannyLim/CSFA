package sg.edu.nus.iss.app.CSFAserver.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.app.CSFAserver.models.Comment;

@Repository
public class MovieRepository {
    @Autowired //@Qualifier(DB_NAME_SHOWS)
    MongoTemplate mongoTemplate;
    /*
    db.comment.find({ name: "danny" }).count()
     */
    public long countComments(String name){
        Query query = Query.query(
            Criteria.where("name").is(name)
            );
            return mongoTemplate.count(query, "comment");
    }


    /*
    db.comment.insert({
    "name" : "dannyyyy",
    "posterName" : "asdadasdasda",
    "rating" : 2,
    "commentTest" : "vjvjhvjvv"
    })
     */
    public Comment insertComment(Comment comment) {
        return mongoTemplate.insert(comment, "comment");
    }


}
