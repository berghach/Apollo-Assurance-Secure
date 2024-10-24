package enums;

import org.example.enums.Insurance;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class InsuranceTest {

    @Test
    public void toStringTest(){
        System.out.println(Insurance.HABITATION);
        assertEquals("automobile", Insurance.AUTO.toString());
    }
  
}