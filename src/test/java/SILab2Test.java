import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    private List<Time> createList(Time...times){
        return new ArrayList<>(Arrays.asList(times));
    }

    private final SILab2 lab = new SILab2();

    @Test
    void multipleCondition(){
        RuntimeException ex;

        //if (hr < 0 || hr > 24)
        Time time1 = new Time(-1, 25,23); //TX
        ex = assertThrows(RuntimeException.class, () -> lab.function(createList(time1)));
        assertTrue(ex.getMessage().contains("The hours are smaller than the minimum"));

        Time time2 = new Time(28, 25, 21); //FT
        ex = assertThrows(RuntimeException.class, () -> lab.function(createList(time2)));
        assertTrue(ex.getMessage().contains("The hours are grater than the maximum"));

        Time time3 = new Time(10,3,9); //FF
        List<Integer> list1 = Arrays.asList(36189);
        assertEquals(list1,lab.function(createList(time3)));


        //if (min < 0 || min > 59)
        Time time4 = new Time(15, 65,8); //TX
        ex = assertThrows(RuntimeException.class, () -> lab.function(createList(time4)));
        assertTrue(ex.getMessage().contains("The minutes are not valid!"));

        Time time5 = new Time(20, 60, 19); //FT
        ex = assertThrows(RuntimeException.class, () -> lab.function(createList(time5)));
        assertTrue(ex.getMessage().contains("The minutes are not valid!"));

        //time3 FF
        List<Integer> list2 = Arrays.asList(36189);
        assertEquals(list2,lab.function(createList(time3)));


        //if (sec >= 0 && sec <= 59)
        Time time6 = new Time(18, 14,-3); //FX
        ex = assertThrows(RuntimeException.class, () -> lab.function(createList(time6)));
        assertTrue(ex.getMessage().contains("The seconds are not valid"));

        Time time7 = new Time(17, 11, 68); //TF
        ex = assertThrows(RuntimeException.class, () -> lab.function(createList(time7)));
        assertTrue(ex.getMessage().contains("The seconds are not valid"));

        //time3 TT
        List<Integer> list3 = Arrays.asList(36189);
        assertEquals(list2,lab.function(createList(time3)));


        //if (hr == 24 && min == 0 && sec == 0)
        Time time8 = new Time(24, 0, 0);//TTT
        List<Integer> result3 = Arrays.asList(86400);
        assertEquals(result3,lab.function(createList(time8)));

        Time time9 = new Time(24, 0, 10); //TTF
        ex = assertThrows(RuntimeException.class, () -> lab.function(createList(time9)));
        assertTrue(ex.getMessage().contains("The time is greater than the maximum"));

        Time time10 = new Time(24, 6, 20); //TFX
        ex = assertThrows(RuntimeException.class, () -> lab.function(createList(time10)));
        assertTrue(ex.getMessage().contains("The time is greater than the maximum"));

    }

    @Test
    void everyBranch(){
        List<Integer> list = new ArrayList<>();
        RuntimeException ex;

        Time time1 = new Time(-1, 25, 23);
        ex = assertThrows(RuntimeException.class, () -> lab.function(createList(time1)));
        assertTrue(ex.getMessage().contains("The hours are smaller than the minimum"));

        Time time2 = new Time(28,25,21);
        ex = assertThrows(RuntimeException.class, () -> lab.function(createList(time2)));
        assertTrue(ex.getMessage().contains("The hours are grater than the maximum"));

        Time time3 = new Time(15, 65, 8);
        list= Arrays.asList(57908);
        assertEquals(list, lab.function(createList(time3)));

        Time time4 = new Time(10, 3,9);
        ex = assertThrows(RuntimeException.class, () -> lab.function(createList(time4)));
        assertTrue(ex.getMessage().contains("The minutes are not valid!"));

        Time time5 = new Time(18,14,-3);
        ex = assertThrows(RuntimeException.class, () -> lab.function(createList(time5)));
        assertTrue(ex.getMessage().contains("The seconds are not valid"));

        Time time6 = new Time(24,0,0);
        list= Arrays.asList(86400);
        assertEquals(list, lab.function(createList(time6)));

        Time time7 = new Time(24,6,20);
        ex = assertThrows(RuntimeException.class, () -> lab.function(createList(time7)));
        assertTrue(ex.getMessage().contains("The time is greater than the maximum"));

    }
}