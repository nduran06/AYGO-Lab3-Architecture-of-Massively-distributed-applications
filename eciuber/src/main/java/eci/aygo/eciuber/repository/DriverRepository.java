package eci.aygo.eciuber.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;

import eci.aygo.eciuber.model.Driver;
import eci.aygo.eciuber.model.Rider;
import eci.aygo.eciuber.model.User;
import eci.aygo.eciuber.model.db.DynamoDBUserRepository;
import eci.aygo.eciuber.model.enums.DriverStatus;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class DriverRepository extends AbstractDynamoDBUserRepository<Driver> {

	private final DynamoDbEnhancedClient dynamoDbClient;
	private final DynamoDbTable<Driver> driverTable;
    private static final String TABLE_NAME = "Drivers";
    
    // Constructor that passes required parameters to the abstract parent class
    public DriverRepository(DynamoDbEnhancedClient enhancedClient) {
       
        super(enhancedClient, Driver.class, TABLE_NAME);
        this.dynamoDbClient = enhancedClient;
		this.driverTable = dynamoDbClient.table("Drivers", TableSchema.fromBean(Driver.class));
    }

    // Implementation of the abstract method that specifies the entity class
    @Override
    protected Class<Driver> getEntityClass() {
        return Driver.class;
    }
    
    // You can add driver-specific query methods here if needed
    // For example:
    public List<Driver> findAvailableDrivers() {
        return findByType("AVAILABLE");
    }

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}
}

/*public interface DriverRepository extends CrudRepository<Driver, String> {

/*public class RiderRepository {

	private final DynamoDbEnhancedClient dynamoDbClient;
	private final DynamoDbTable<User> userTable;

	public RiderRepository(DynamoDbEnhancedClient dynamoDbClient) {
		this.dynamoDbClient = dynamoDbClient;
		this.userTable = dynamoDbClient.table("Users", TableSchema.fromBean(User.class));
	}

	public User save(User user) {
		userTable.putItem(user);
		return user;
	}

	public Optional<User> findById(String id) {
		Key key = Key.builder().partitionValue(id).build();
		return Optional.ofNullable(userTable.getItem(key));
	}

	public boolean existsByEmail(String email) {
		QueryConditional queryConditional = QueryConditional.keyEqualTo(Key.builder().partitionValue(email).build());

		// Corrected way to handle query results
		Iterator<Page<User>> results = userTable.index("email-index").query(queryConditional).iterator();

		return results.hasNext() && results.next().items().iterator().hasNext();
	}

	public void delete(User user) {
		userTable.deleteItem(Key.builder().partitionValue(user.getId()).build());
	}

}*/