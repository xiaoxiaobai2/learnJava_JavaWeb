package test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestOrder {
    @Test
    public void testOrder(){
        List<Integer> list = new ArrayList();
        list.add(3);
        list.add(6);
        list.add(9);
        list.add(1);
        list.add(2);
        list.add(5);
        int end = list.size();
        while (end>0){
            int flag =0;
            for (int i=0; i<end;i++ ){
                if (list.get(i)<list.get(flag)){
                    flag = i;
                }
            }
            list.add(list.get(flag));
            list.remove(flag);
            end --;
        }
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
}
