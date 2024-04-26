import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NumberStorageTest {
    private NumberStorage numberStorage;

    @BeforeEach
    public void setUp() {
        numberStorage = new NumberStorage();
    }

    @AfterEach
    public void tearDown() {
        numberStorage = null;
    }

    @Test
    public void testAddNumberPositive() {
        numberStorage.add(5);
        NumberStorage exceptedStorage = new NumberStorage(List.of(5));
        assertEquals(exceptedStorage.getNumbers(), numberStorage.getNumbers());
    }

    @Test
    public void testRemoveNumberPositive() {
        numberStorage.add(5);
        numberStorage.add(10);
        numberStorage.remove(5);
        NumberStorage exceptedStorage = new NumberStorage(List.of(10));
        assertEquals(exceptedStorage.getNumbers(), numberStorage.getNumbers());
    }

    @Test
    public void testFindClosestNumberPositive() {
        numberStorage.add(5);
        numberStorage.add(10);
        numberStorage.add(15);
        numberStorage.add(20);
        int closest = numberStorage.findClosest(16);
        assertEquals(15, closest);
    }

    @Test
    public void testAddDuplicateNumberNegative() {
        numberStorage.add(5);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> numberStorage.add(5));
        assertEquals(Constants.NUMBER_ALREADY_EXISTS, exception.getMessage());
    }

    @Test
    public void testRemoveNumberNotFoundNegative() {
        numberStorage.add(5);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> numberStorage.remove(10));
        assertEquals(Constants.NUMBER_NOT_FOUND, exception.getMessage());
    }

    @Test
    public void testFindClosestEmptyStorageNegative() {
        Exception exception = assertThrows(IllegalStateException.class, () -> numberStorage.findClosest(10));
        assertEquals(Constants.NUMBER_STORAGE_IS_EMPTY, exception.getMessage());
    }
}

