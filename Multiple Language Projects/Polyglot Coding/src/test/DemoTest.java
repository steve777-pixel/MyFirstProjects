package test;

import models.*;
import org.junit.Test;
import org.junit.Before;        // Runs before every individual @Test method
import org.junit.After;         // Runs after every individual @Test method
import org.junit.BeforeClass;   // Runs once before all tests in the class (must be static)
import org.junit.AfterClass;    // Runs once after all tests in the class (must be static)
import org.junit.Ignore;        // To temporarily disable/skip a test case
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;;

public class DemoTest{

    @Test
    public void testValidContractor(){
        Contractor contractor = new Contractor(9908, "Steve", 10_000, 0.2, 25);
        assertEquals(9908, contractor.getId(), 0.001);
        assertEquals("Steve", contractor.getName());
        assertEquals(10_000, contractor.getBaseSalary(), 0.001);
        assertEquals(0.2, contractor.getTaxRate(), 0.001);
        assertEquals(25, contractor.getHourlyRate(), 0.001);
        assertEquals(0, contractor.getHoursWorked(), 0.001);
        assertEquals(0, contractor.calculateGrossPay(), 0.001);
        contractor.addWorkHours(65);
        assertEquals(1_625, contractor.calculateGrossPay(), 0.001);
    }

}