package order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;
import java.text.SimpleDateFormat;
import java.util.*;

@AllArgsConstructor
@Getter
public class Order {

    private final String firstName;
    private final String lastName;
    private final String address;
    private final int metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final List<String> color;

    public static Order randomOrder(List<String> color) {
        String firstName = RandomStringUtils.randomAlphabetic(5);
        String lastName = RandomStringUtils.randomAlphabetic(5);
        String address = RandomStringUtils.randomAlphanumeric(5);
        int metroStation = new Random().nextInt(208);
        String phone = "+7" + RandomStringUtils.randomNumeric(10);
        int rentTime = new Random().nextInt(14);
        String deliveryDate = Order.getTomorrowDate();
        ;
        String comment = RandomStringUtils.randomAlphanumeric(5);

        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }

    public static String getTomorrowDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1);

        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }
}
