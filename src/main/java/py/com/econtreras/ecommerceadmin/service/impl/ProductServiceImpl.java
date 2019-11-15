package py.com.econtreras.ecommerceadmin.service.impl;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.com.econtreras.api.beans.ImageBean;
import py.com.econtreras.api.beans.ProductBean;
import py.com.econtreras.ecommerceadmin.converter.ImageConverter;
import py.com.econtreras.ecommerceadmin.converter.ProductConverter;
import py.com.econtreras.entity.*;
import py.com.econtreras.ecommerceadmin.exception.EcontrerasException;
import py.com.econtreras.ecommerceadmin.repository.*;
import py.com.econtreras.ecommerceadmin.service.ProductService;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
    public ProductBean save(ProductBean productRequest) {

        try {
            Image image = null;
            if (productRequest.getFile() != null) {
                ImageBean imageBean = new ImageBean();
                imageBean.setSrc(productRequest.getFile().getBytes());
                String imgExtension = FilenameUtils.getExtension(productRequest.getFile().getOriginalFilename());
                String imgName = FilenameUtils.getBaseName(productRequest.getFile().getOriginalFilename());
                image = imageConverter.buildEntity(imageBean);
                image.setName(imgName);
                image.setFormat(imgExtension);
                image.setOrder(1);
                image = imageRepository.save(image);
            }
            Product product = productConverter.buildEntity(productRequest);
            Optional<Category> categoryOptional = categoryRepository.findById(productRequest.getCategoryId());
            if (categoryOptional.isPresent())
                product.setCategory(categoryOptional.get());
            Optional<Brand> optionalBrand = brandRepository.findById(productRequest.getBrandId());
            if (optionalBrand.isPresent())
                product.setBrand(optionalBrand.get());
            productRepository.save(product);
            if (image != null) {
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
            }
            return productConverter.buildBean(product);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new EcontrerasException(e.getMessage(), e);
        }

    }

    @Override
    public List<ProductBean> findAll() {

        try {
            Iterable<py.com.econtreras.entity.Product> entityList = productRepository.findAll();
            if (IterableUtils.isEmpty(entityList)) {
                return null;
            }
            List<ProductBean> beans = new ArrayList<>();
            for (py.com.econtreras.entity.Product entity : entityList) {
                beans.add(productConverter.buildBean(entity));
            }
            return beans;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

    }


    public ProductBean findById(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            ProductBean productBean = productConverter.buildBean(optionalProduct.get());
            List<Image> images = imageRepository.findByProductId(productBean.getId());
            images = images.stream().sorted(Comparator.comparingInt(Image::getId).reversed()).collect(Collectors.toList());
            if (images != null && !images.isEmpty()) {
                byte[] imageByte = images.get(0).getSrc();
                String imageExtension = images.get(0).getFormat();
                productBean.setImageDB("data:image/" + imageExtension + ";base64, " + Base64.getEncoder().encodeToString(imageByte));
            }
            return productBean;
        }
        return null;
    }

    @Override
    public List<ProductBean> findByProductName(String name) {
        try {
            List<ProductBean> beans = new ArrayList<>();
            Iterable<py.com.econtreras.entity.Product> entityList = productRepository.findByProductName(name);
            if (!IterableUtils.isEmpty(entityList)) {
                for (py.com.econtreras.entity.Product entity : entityList) {
                    beans.add(productConverter.buildBean(entity));
                }
            }
            return beans;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
