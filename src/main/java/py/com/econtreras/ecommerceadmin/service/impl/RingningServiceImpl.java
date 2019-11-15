package py.com.econtreras.ecommerceadmin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.com.econtreras.ecommerceadmin.converter.RingingConverter;
import py.com.econtreras.entity.Ringing;
import py.com.econtreras.ecommerceadmin.model.RingingModel;
import py.com.econtreras.ecommerceadmin.repository.RinginRepository;
import py.com.econtreras.ecommerceadmin.service.RingningService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "RingningService")
public class RingningServiceImpl implements RingningService {

    @Autowired
    private RinginRepository ringinRepository;
    @Autowired
    private RingingConverter ringingConverter;

    @Override
    public List<RingingModel> getAll() {
        List<RingingModel> entityList = new ArrayList<>();
        ringinRepository.findAll().forEach(r ->{
            entityList.add(ringingConverter.build(r));
        });

        return entityList;
    }

    @Override
    public void save(RingingModel model) {
        ringinRepository.save(ringingConverter.build(model));
    }

    @Override
    public void delete(Integer id) {
        ringinRepository.deleteById(id);
    }

    @Override
    public RingingModel getById(Integer id) {
        Optional<Ringing> optionalRinging = ringinRepository.findById(id);
        if(optionalRinging.isPresent()){
            return ringingConverter.build(optionalRinging.get());
        }
        return null;
    }


    @Override
    public boolean checkExistByRingning(String ringing) {
        return ringinRepository.findByRinging(ringing).isPresent();
    }
}
