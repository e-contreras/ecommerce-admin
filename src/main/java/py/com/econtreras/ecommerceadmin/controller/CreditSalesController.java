package py.com.econtreras.ecommerceadmin.controller;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import py.com.econtreras.api.beans.CreditNote;
import py.com.econtreras.api.beans.CreditNoteDetail;
import py.com.econtreras.ecommerceadmin.service.PdfGenerator;
import py.com.econtreras.ecommerceadmin.util.RequestConstant;

/**
 * @author
 */
@Controller
@RequestMapping(RequestConstant.SALES_CREDIT_NOTE)
@PreAuthorize("hasAuthority('compras')")
public class CreditSalesController {

    @Autowired
    private PdfGenerator pdfGenerator;

    @GetMapping
    public ModelAndView getCreditSalesView() {
        ModelAndView mav = new ModelAndView("general");
        mav.addObject("piece", RequestConstant.SALES_CREDIT_NOTE_MAIN_PATH_RESOURCE);
        return mav;
    }

    @GetMapping("/pdf/{id}")
    public ResponseEntity<?> generatePDF(@PathVariable("id") Integer id, final HttpServletRequest request, final HttpServletResponse response) throws DocumentException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/credit_note_detail/credit/" + id;
        ResponseEntity<CreditNoteDetail[]> res = restTemplate.getForEntity(url, CreditNoteDetail[].class);
        if (res.getStatusCode().value() == 200) {
            url = "http://localhost:8080/credit_note/" + id;
            ResponseEntity<CreditNote> creditNote = restTemplate.getForEntity(url, CreditNote.class);
            List<CreditNoteDetail> details = Arrays.asList(res.getBody());
            double total = 0F;
            for(CreditNoteDetail detail : details){
                total += detail.getAmount() * detail.getQuantity();
            }
            Map<String, Object> params = new HashMap();
            params.put("details", details);
            params.put("credit_note", creditNote.getBody());
            params.put("total", total);
            ByteArrayOutputStream byteArrayOutputStreamPDF = pdfGenerator.createPdf("sales/credit_sales/credit-note-pdf", params, request, response);
            ByteArrayResource inputStreamResourcePDF = new ByteArrayResource(byteArrayOutputStreamPDF.toByteArray());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + System.currentTimeMillis()).contentType(MediaType.APPLICATION_PDF)
                    .contentLength(inputStreamResourcePDF.contentLength()).body(inputStreamResourcePDF);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
