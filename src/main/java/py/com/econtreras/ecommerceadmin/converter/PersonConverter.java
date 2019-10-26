package py.com.econtreras.ecommerceadmin.converter;

import org.springframework.stereotype.Component;
import py.com.econtreras.api.beans.PersonResponse;
import py.com.econtreras.ecommerceadmin.entity.Person;

@Component
public class PersonConverter {

    public PersonResponse build(Person entity){
        PersonResponse bean = new PersonResponse();
        bean.setBirthday(entity.getBirthday());
        bean.setBusinessName(entity.getBusinessName());
        bean.setName(entity.getName());
        bean.setLastname(entity.getLastname());
        bean.setPersonId(entity.getId());
        return bean;
    }

}
