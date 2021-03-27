package models.output;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidInvoiceExceptionTest {

    static InvalidInvoiceException e;
    static InvalidInvoiceException i;

    @BeforeAll
    public static void setUp(){
        e = new InvalidInvoiceException(Message.MISSING_FILENUMBER);
        i = new InvalidInvoiceException(Message.MISSING_FILENUMBER);
    }

    @Test
    @DisplayName("Test Get ErrorOut")
    public void testGetErrorOut(){
        assertEquals(e.getErrorOut().getMessage(), i.getErrorOut().getMessage());
        assertEquals(e.getErrorOut().getCode(), i.getErrorOut().getCode());
    }
}
