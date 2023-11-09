package AndrewWebServices;

import java.util.HashMap;
import java.util.Map;

/*
 * InMemoryDatabase is a fake for the AndrewWS database which is used to improve test efficiency.
 * Remember, fakes are fully functional classes with simplified implementation.
 * What is the simplest core functionality that we need for a functional database?
 * 
 * Hint: there are two methods you need to implement
 */
public class InMemoryDatabase extends Database {
    // Implement your fake database here
    private final Map<String, Integer> data = new HashMap<>();

    public InMemoryDatabase() {
        // Pre-populate the fake database with known account names and passwords
        data.put("user1", 1234);
        data.put("user2", 2345);
    }

    @Override
    public int getPassword(String accountName) {
        return data.getOrDefault(accountName, -1); // Return -1 or some "not found" value if the account does not exist
    }
}