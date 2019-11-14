package py.com.econtreras.ecommerceadmin.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class RingingModel {

    private Integer id;
    @Min(value=8, message = "El timbrado deve contener exactamente 8 caracteres")
    private String ringing;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "debe especificar una fecha de validez")
    private LocalDate validityDate;
    private String documentType;
    private boolean deleted;

}
