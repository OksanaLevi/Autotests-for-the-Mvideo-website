package herokuapp.booker.models;

import lombok.Data;

@Data
public class BookingBodyModel {
    String firstname, lastname, additionalneeds;
    BookingDatesModel bookingdates;
    int totalprice;
    boolean depositpaid;

}


