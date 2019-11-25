package py.com.econtreras.ecommerceadmin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.com.econtreras.api.beans.BugetSolicitudeModel;
import py.com.econtreras.ecommerceadmin.converter.BudgetSolicitudeStatusConverter;
import py.com.econtreras.ecommerceadmin.converter.BugetSolicitudeConverter;
import py.com.econtreras.ecommerceadmin.repository.BudgetSolicitudeRepository;
import py.com.econtreras.ecommerceadmin.repository.BudgetSolicitudeStatusRepository;
import py.com.econtreras.ecommerceadmin.service.BugetSolicitudeService;
import py.com.econtreras.entity.BudgetSolicitudeStatus;
import py.com.econtreras.entity.BugetSolicitude;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BugetSolicitudeServiceImpl implements BugetSolicitudeService {

    public static final String CREATE_STATUS = "creado";
    @Autowired
    private BugetSolicitudeConverter bugetSolicitudeConverter;
    @Autowired
    private BudgetSolicitudeRepository budgetSolicitudeRepository;
    @Autowired
    private BudgetSolicitudeStatusRepository bugBudgetSolicitudeStatusRepository;
    @Autowired
    private BudgetSolicitudeStatusConverter budgetSolicitudeStatusConverter;


    @Override
    public BugetSolicitudeModel save(BugetSolicitudeModel bugetSolicitude) {
        if(bugetSolicitude != null){
            BugetSolicitude solicitude = bugetSolicitudeConverter.build(bugetSolicitude);
            if(bugetSolicitude.getState() == null || bugetSolicitude.getState().equalsIgnoreCase(CREATE_STATUS)){
                BudgetSolicitudeStatus status = bugBudgetSolicitudeStatusRepository.findByStatus(CREATE_STATUS);
                solicitude.setBudgetSolicitudeStatus(status);
                solicitude.setSent('N');
            }
            solicitude = budgetSolicitudeRepository.save(solicitude);
            return bugetSolicitudeConverter.build(solicitude);
        }
        return null;
    }

    @Override
    public BugetSolicitudeModel findOne(Integer id) {
        Optional<BugetSolicitude> optionalBugetSolicitude = budgetSolicitudeRepository.findById(id);
        if(optionalBugetSolicitude.isPresent()){
            return  bugetSolicitudeConverter.build(optionalBugetSolicitude.get());
        }
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<BugetSolicitudeModel> findAll() {
        Iterable<BugetSolicitude> iterable = budgetSolicitudeRepository.findAll();
        List<BugetSolicitudeModel> bugetSolicitudeModels = new ArrayList<>();
        iterable.forEach(bugetSolicitude -> {
            bugetSolicitudeModels.add(bugetSolicitudeConverter.build(bugetSolicitude));
        });

        return bugetSolicitudeModels;
    }
}
