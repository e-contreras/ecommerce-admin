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
import py.com.econtreras.api.beans.DebitNote;
import py.com.econtreras.api.beans.DebitNoteDetail;
import py.com.econtreras.ecommerceadmin.service.PdfGenerator;
import py.com.econtreras.ecommerceadmin.util.RequestConstant;

/**
 * @author
 */
@Controller
@RequestMapping(RequestConstant.SALES_DEBIT_NOTE)
@PreAuthorize("hasAuthority('compras')")
public class DebitSalesController {
    
    @Autowired
    private PdfGenerator pdfGenerator;
    
    @GetMapping
    public ModelAndView getDebitSalesView(){
        ModelAndView mav = new ModelAndView("general");
        mav.addObject("piece", RequestConstant.SALES_DEBIT_NOTE_MAIN_PATH_RESOURCE);
        return mav;
    }

    @GetMapping("/pdf/{id}")
    public ResponseEntity<?> generatePDF(@PathVariable("id") Integer id, final HttpServletRequest request, final HttpServletResponse response) throws DocumentException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/debit_note_detail/debit/" + id;
        ResponseEntity<DebitNoteDetail[]> res = restTemplate.getForEntity(url, DebitNoteDetail[].class);
        if (res.getStatusCode().value() == 200) {
            url = "http://localhost:8080/debit_note/" + id;
            ResponseEntity<DebitNote> creditNote = restTemplate.getForEntity(url, DebitNote.class);
            List<DebitNoteDetail> details = Arrays.asList(res.getBody());
            Map<String, Object> params = new HashMap();
            params.put("details", details);
            params.put("debit_note", creditNote.getBody());
            ByteArrayOutputStream byteArrayOutputStreamPDF = pdfGenerator.createPdf("sales/debit_sales/debit-note-pdf", params, request, response);
            ByteArrayResource inputStreamResourcePDF = new ByteArrayResource(byteArrayOutputStreamPDF.toByteArray());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + System.currentTimeMillis()).contentType(MediaType.APPLICATION_PDF)
                    .contentLength(inputStreamResourcePDF.contentLength()).body(inputStreamResourcePDF);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }    
    
}
