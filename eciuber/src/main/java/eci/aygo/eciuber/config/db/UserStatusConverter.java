package eci.aygo.eciuber.config.db;

import java.util.HashMap;
import java.util.Map;

import eci.aygo.eciuber.model.enums.DriverStatus;
import eci.aygo.eciuber.model.enums.RiderStatus;
import eci.aygo.eciuber.model.intf.UserStatus;
import software.amazon.awssdk.enhanced.dynamodb.AttributeConverter;
import software.amazon.awssdk.enhanced.dynamodb.AttributeValueType;
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;


public class UserStatusConverter implements AttributeConverter<UserStatus> {
	
	private final Map<String, Class<? extends Enum<?>>> typeRegistry = new HashMap<>();
    private final Map<String, Map<String, ? extends UserStatus>> valueRegistry = new HashMap<>();

    public UserStatusConverter() {
        // Register all enum types that implement UserStatus
        registerEnumType(DriverStatus.class);
        // Add other enum types as needed
        registerEnumType(RiderStatus.class);

    }

    private <T extends Enum<T> & UserStatus> void registerEnumType(Class<T> enumClass) {
        String typeName = enumClass.getSimpleName();
        typeRegistry.put(typeName, enumClass);

        Map<String, T> enumMap = new HashMap<>();
        for (T constant : enumClass.getEnumConstants()) {
            enumMap.put(constant.name(), constant);
        }
        valueRegistry.put(typeName, enumMap);
    }

    public AttributeValue transformFrom(UserStatus value) {
        if (value == null) {
            return AttributeValue.builder().nul(true).build();
        }

        Map<String, AttributeValue> mapValue = new HashMap<>();
        mapValue.put("type", AttributeValue.builder().s(value.getClass().getSimpleName()).build());
        mapValue.put("name", AttributeValue.builder().s(value.getDisplayName()).build());

        return AttributeValue.builder().m(mapValue).build();
    }

    public UserStatus transformTo(AttributeValue attributeValue) {
        if (attributeValue.nul()) {
            return null;
        }

        Map<String, AttributeValue> mapValue = attributeValue.m();
        String type = mapValue.get("type").s();
        String name = mapValue.get("name").s();

        Map<String, ? extends UserStatus> enumMap = valueRegistry.get(type);
        if (enumMap == null) {
            throw new IllegalArgumentException("Unknown enum type: " + type);
        }

        UserStatus value = enumMap.get(name);
        if (value == null) {
            throw new IllegalArgumentException(
                String.format("Invalid name '%s' for enum type %s", name, type)
            );
        }

        return value;
    }

    @Override
    public EnhancedType<UserStatus> type() {
        return EnhancedType.of(UserStatus.class);
    }

    @Override
    public AttributeValueType attributeValueType() {
        return AttributeValueType.M;
    }

	
}
