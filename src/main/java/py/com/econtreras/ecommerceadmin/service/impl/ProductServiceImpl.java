package py.com.econtreras.ecommerceadmin.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.com.econtreras.api.beans.ImageBean;
import py.com.econtreras.api.beans.ProductRequest;
import py.com.econtreras.ecommerceadmin.converter.ImageConverter;
import py.com.econtreras.ecommerceadmin.converter.ProductConverter;
import py.com.econtreras.ecommerceadmin.entity.Image;
import py.com.econtreras.ecommerceadmin.entity.Product;
import py.com.econtreras.ecommerceadmin.entity.ProductImage;
import py.com.econtreras.ecommerceadmin.entity.ProductImagePK;
import py.com.econtreras.ecommerceadmin.exception.EcontrerasException;
import py.com.econtreras.ecommerceadmin.repository.*;
import py.com.econtreras.ecommerceadmin.service.ProductService;
import org.apache.commons.collections4.IterableUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductConverter productConverter;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageConverter imageConverter;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ProductImageRepository productImageRepository;
    private static final Logger LOGGER = LogManager.getLogger(ProductServiceImpl.class);


    @Override
    public ProductRequest save(ProductRequest productRequest) {

        try {
            ImageBean imageBean = new ImageBean();
            imageBean.setSrc(productRequest.getFile().getBytes());
            Image image = imageConverter.buildEntity(imageBean);
            image.setOrder(1);
            image = imageRepository.save(image);
            Product product = productConverter.build(productRequest);
            product.setCategory(categoryRepository.findById(productRequest.getCategoryId()).orElse(null));
            product.setBrand(brandRepository.findById(productRequest.getBrandId()).orElse(null));
            productRepository.save(product);
            List<ProductImage> productImages = new ArrayList<>();
            ProductImagePK imagePK = new ProductImagePK();
            imagePK.setImage(image.getId());
            imagePK.setProduct(product.getId());
            ProductImage productImage = new ProductImage();
            productImage.setImage(image);
            productImage.setProduct(product);
            productImages.add(productImage);
            productImage.setMerImagenesPK(imagePK);
            productImage.setPrincipal('S');
            productImageRepository.save(productImage);
            product.setProductImageList(productImages);
            return productConverter.build(product);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new EcontrerasException(e.getMessage(), e);
        }

    }

    @Override
    public List<ProductRequest> findAll() {

        try {
            Iterable<py.com.econtreras.ecommerceadmin.entity.Product> entityList = productRepository.findAll();
            if (IterableUtils.isEmpty(entityList)) {
                return null;
            }
            List<ProductRequest> beans = new ArrayList<>();
            for (py.com.econtreras.ecommerceadmin.entity.Product entity : entityList) {
                beans.add(productConverter.build(entity));
            }
            return beans;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

    }
}
