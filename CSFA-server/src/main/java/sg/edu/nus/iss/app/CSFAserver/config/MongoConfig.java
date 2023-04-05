package sg.edu.nus.iss.app.CSFAserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;


@Configuration
public class MongoConfig {
    @Value("${mongo.url}")
	private String mongoUrl;
	@Value("${mongo.databasetable}")
	private String databasetable;

	// @Bean(name=DB_NAME_ORDERDB)
	// public MongoTemplate createOrder(){
	// 	//create a MongoClient with the mongo connection string
	// 	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+mongoUrl);
	// 	MongoClient client = MongoClients.create(mongoUrl);
	// 	return new MongoTemplate(client, DB_NAME_ORDERDB);
	// }

	// @Primary
	// @Bean(name=DB_NAME_SHOWS)
	// public MongoTemplate createSHOWS(){
	// 	//create a MongoClient with the mongo connection string
	// 	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+mongoUrl);
	// 	MongoClient client = MongoClients.create(mongoUrl);
	// 	return new MongoTemplate(client, DB_NAME_SHOWS);
	// }

	//For railway
	@Bean
	public MongoTemplate createTesting(){
		//create a MongoClient with the mongo connection string
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+mongoUrl);
		MongoClient client = MongoClients.create(mongoUrl);
		return new MongoTemplate(client, databasetable);
	}
    

}