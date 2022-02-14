import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.input.concurrent.ConcurrentCharInputReader;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;




import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class GeoServiceImplTests {

    GeoServiceImpl sut;

    @BeforeEach
    public void init() {
        System.out.println("Test started");
        sut = new GeoServiceImpl();
    }

    @BeforeAll
    public static void started() {
        System.out.println("Tests started");
    }

    @AfterEach
    public void finished() {
        System.out.println("Test completed");
    }

    @AfterAll
    public static void finishedAll() {
        System.out.println("Tests completed");
    }


    @ParameterizedTest
    @MethodSource("source")
    public void testByIp(String ip, Location expected) {
        Location result = sut.byIp(ip);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> source() {
        return Stream.of(Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)));
    }


    @ParameterizedTest
    @MethodSource("source3")
    public void testByCoordinates(double latitude, double longitude, Location location) {
        var expected = RuntimeException.class;
        assertThrows(expected,
                () -> sut.byCoordinates(latitude, longitude));
    }
    private static Stream<Arguments> source3() {
        return Stream.of(Arguments.of(55.75, 37.61, new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of(40.71, -74.00, new Location("New York", Country.USA, " 10th Avenue", 32)));
    }

}
