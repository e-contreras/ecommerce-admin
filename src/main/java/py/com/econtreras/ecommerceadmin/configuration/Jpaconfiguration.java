package py.com.econtreras.ecommerceadmin.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = {"py.com.econtreras.entity"})
public class Jpaconfiguration {
}