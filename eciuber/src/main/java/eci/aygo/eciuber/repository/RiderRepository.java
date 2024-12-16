package eci.aygo.eciuber.repository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import eci.aygo.eciuber.model.Rider;
import eci.aygo.eciuber.model.User;
import eci.aygo.eciuber.model.db.DynamoDBUserRepository;
import eci.aygo.eciuber.model.enums.PaymentMethod;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

@Repository
public class RiderRepository extends AbstractDynamoDBUserRepository<Rider> {

	private final DynamoDbEnhancedClient dynamoDbClient;
	private final DynamoDbTable<Rider> riderTable;
    private static final String TABLE_NAME = "Riders";


	public RiderRepository(DynamoDbEnhancedClient enhancedClient) {
        super(enhancedClient, Rider.class, TABLE_NAME);
		this.dynamoDbClient = enhancedClient;
		this.riderTable = dynamoDbClient.table("Riders", TableSchema.fromBean(Rider.class));
	}

	public Rider save(Rider user) {
		riderTable.putItem(user);
		return user;
	}

	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	public List<Rider> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<Rider> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

}