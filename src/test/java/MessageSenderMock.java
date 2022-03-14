import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;
import java.util.HashMap;
import java.util.Map;

public class MessageSenderMock {

    @Test
    public void testSend() {

        GeoService geoService = Mockito.mock(GeoServiceImpl.class);

        Mockito.when(geoService.byIp(Mockito.startsWith("172.")))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        Map <String, String> map = new HashMap<>();
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.44.183.149");

        String expected = "Добро пожаловать";
        String result = messageSender.send(map);

        Assertions.assertEquals(expected, result);
    }

}
