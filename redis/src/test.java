import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Province;
import service.impl.ProvinceServiceImp;

import java.util.List;

public class test {
    public static void main(String[] args) {
        ProvinceServiceImp provinceServiceImp = new ProvinceServiceImp();
        List<Province> list = provinceServiceImp.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        String value = null;
        try {
            value = objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(value);
    }
}
