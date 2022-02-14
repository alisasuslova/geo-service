import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.geo.GeoServiceImpl.MOSCOW_IP;

public class MessageSenderMock {

    @Test
    public void testSend() {
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(MOSCOW_IP))
                .thenReturn(new Location("Moscow", RUSSIA, "Lenina", 15));


        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(geoService.byIp(MOSCOW_IP).getCountry())
                .thenReturn(localizationService.locale(geoService.byIp(MOSCOW_IP).getCountry()));

        MessageSenderImpl messageSenderImpl = new MessageSenderImpl(geoService, localizationService);

        Map <String, String> map =  new HashMap<>();
        map.put(MOSCOW_IP, "Добро пожаловать");

        String expected = "Добро пожаловать";
        String result = messageSenderImpl.send(map);

        Assertions.assertEquals(expected, result);

    }

}
