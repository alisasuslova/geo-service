import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.entity.Country.*;

public class LocalizationServiceImplTests {

    LocalizationServiceImpl sut;

    @BeforeEach
    public void init() {
        System.out.println("Test started");
        sut = new LocalizationServiceImpl();
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
    public void testLocate(Country country, String expected) {
        String result = sut.locale(country);
        assertEquals(expected, result);
    }
    private static Stream<Arguments> source() {
        return Stream.of(Arguments.of(RUSSIA, "Добро пожаловать"),
                Arguments.of(GERMANY, "Welcome"),
                Arguments.of(USA, "Welcome"),
                Arguments.of(BRAZIL, "Welcome"));
    }
}
