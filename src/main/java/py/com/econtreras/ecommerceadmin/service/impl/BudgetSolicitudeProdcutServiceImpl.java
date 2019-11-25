package py.com.econtreras.ecommerceadmin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.com.econtreras.api.beans.BudgetSolicitudeProdcutBean;
import py.com.econtreras.ecommerceadmin.repository.BudgetSolicitudeProdcutRepository;
import py.com.econtreras.ecommerceadmin.repository.BudgetSolicitudeRepository;
import py.com.econtreras.ecommerceadmin.repository.ProductRepository;
import py.com.econtreras.ecommerceadmin.service.BudgetSolicitudeProdcutService;
import py.com.econtreras.entity.BudgetSolicitudePK;
import py.com.econtreras.entity.BudgetSolicitudeProdcut;
import py.com.econtreras.entity.BugetSolicitude;
import py.com.econtreras.entity.Product;

import java.util.Optional;

@Service
public class BudgetSolicitudeProdcutServiceImpl implements BudgetSolicitudeProdcutService {


    @Autowired
    private BudgetSolicitudeProdcutRepository budgetSolicitudeProdcutRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BudgetSolicitudeRepository budgetSolicitudeRepository;

    @Override
    public BudgetSolicitudeProdcutBean save(BudgetSolicitudeProdcutBean bean) {

        Optional<Product> optionalProduct = productRepository.findById(bean.getProductId());
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            BugetSolicitude bugetSolicitude = null;
            Optional<BugetSolicitude> optionalBugetSolicitude = budgetSolicitudeRepository.findById(bean.getBugetSolicitudeProdcutId());
            if(optionalBugetSolicitude.isPresent()){
                bugetSolicitude = optionalBugetSolicitude.get();
            }
            BudgetSolicitudePK pk = new BudgetSolicitudePK(bean.getProductId(), bean.getBugetSolicitudeProdcutId());
            BudgetSolicitudeProdcut budgetSolicitudeProdcut = new BudgetSolicitudeProdcut(pk.getProductId(),pk.getBudgetSolicitudeId());
            budgetSolicitudeProdcut.setQuantity(bean.getQuantity());
            budgetSolicitudeProdcutRepository.save(budgetSolicitudeProdcut);
            bean.setProductId(bean.getProductId());
            bean.setProductName(product.getProductName());
            bean.setQuantity(bean.getQuantity());
            bean.setBugetSolicitudeProdcutId(bean.getBugetSolicitudeProdcutId());
            return bean;
        }
        return null;
    }
}
