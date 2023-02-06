package order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Orders {

    private int id;
    private int courierId;
    private String firstName;
    private String lastName;
    private String address;
    private int metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private int track;
    private List<String> color;
    private String comment;
    private String createdAt;
    private String updatedAt;
    private int status;
}
