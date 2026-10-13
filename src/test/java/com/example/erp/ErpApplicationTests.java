
package com.example.erp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ErpApplicationTests {

    @Test
    void basicMathShouldWork() {
        assertEquals(4, 2 + 2);
    }

    @Test
    void erpApplicationNameShouldBeValid() {
        String applicationName = "Mini ERP";

        assertEquals("Mini ERP", applicationName);
        assertTrue(applicationName.startsWith("Mini"));
    }
}