package enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class InsuranceTest {

    @Test
    public void toStringTest(){
        assertEquals("automobile", Insurance.AUTO.toString());
    }
  
}