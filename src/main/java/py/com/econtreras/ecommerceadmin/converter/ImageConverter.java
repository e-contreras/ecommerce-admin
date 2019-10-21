package py.com.econtreras.ecommerceadmin.converter;

import org.springframework.stereotype.Component;
import py.com.econtreras.api.beans.ImageBean;
import py.com.econtreras.ecommerceadmin.entity.Image;

@Component
public class ImageConverter {

    public Image buildEntity(ImageBean bean){
        Image entity = new Image();
        entity.setId(bean.getId());
        entity.setSrc(bean.getSrc());
        return  entity;
    }
}
