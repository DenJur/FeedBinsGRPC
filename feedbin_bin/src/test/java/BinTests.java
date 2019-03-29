import exceptions.BinOverflow;
import exceptions.BinUnderflow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BinTests {
    private Bin bin;

    @BeforeEach
    public void TestSetup() {
        bin = new Bin(40);
    }

    @Test
    public void TestBinAmountGetter() {
        int amount = bin.getStuffAmount();
        assertEquals(0, amount, "Amount not default after construction");
        try {
            Field field = Bin.class.getDeclaredField("stuffAmount");
            field.setAccessible(true);
            field.set(bin, 10);

            amount = bin.getStuffAmount();
            assertEquals(10, amount, "Getter does not return expected field value");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail(e);
        }
    }

    @Test
    public void TestBinNameGetter() {
        String name = bin.getStuffName();
        assertEquals("EMPTY", name, "Name not default after construction");
        try {
            Field field = Bin.class.getDeclaredField("stuffName");
            field.setAccessible(true);
            field.set(bin, "NewStuff");

            name = bin.getStuffName();
            assertEquals("NewStuff", name, "Getter does not return expected field value");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail(e);
        }
    }

    @Test
    public void TestBinFlush() {
        try {
            Field field = Bin.class.getDeclaredField("stuffAmount");
            field.setAccessible(true);
            field.set(bin, 10);

            int amount = bin.getStuffAmount();
            assertEquals(10, amount, "Stuff amount was not set");

            bin.flush();
            amount = bin.getStuffAmount();
            assertEquals(0, amount, "Bin was not empty after flushing");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail(e);
        }
    }

    @Test
    public void TestBinAddAmount() {
        try {
            bin.addAmount(10);
            int amount = bin.getStuffAmount();
            assertEquals(10, amount, "Stuff amount was not added");


            bin.addAmount(30);
            amount = bin.getStuffAmount();
            assertEquals(40, amount, "Bin was not filled up to the limit");

            try {
                bin.addAmount(1);
                fail("Expected BinOverflow exception");
            } catch (BinOverflow ignored) {
            }

        } catch (BinOverflow | BinUnderflow e) {
            fail(e);
        }
    }

    @Test
    public void TestBinAddAmountRemove() {
        try {
            Field field = Bin.class.getDeclaredField("stuffAmount");
            field.setAccessible(true);
            field.set(bin, 40);

            bin.addAmount(-10);
            int amount = bin.getStuffAmount();
            assertEquals(30, amount, "Stuff amount was not removed");

            bin.addAmount(-30);
            amount = bin.getStuffAmount();
            assertEquals(0, amount, "Bin was not emptied to the limit");

            try {
                bin.addAmount(-1);
                fail("Expected BinUnderflow exception");
            } catch (BinUnderflow ignored) {
            }

        } catch (BinOverflow | BinUnderflow | IllegalAccessException | NoSuchFieldException e) {
            fail(e);
        }
    }

    @Test
    public void TestBinChangeName() {
        bin.changeStuffName("NewName");
        String name = bin.getStuffName();
        assertEquals("NewName", name, "Stuff name was not changed");
    }
}
