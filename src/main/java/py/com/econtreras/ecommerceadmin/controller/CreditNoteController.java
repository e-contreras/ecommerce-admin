package py.com.econtreras.ecommerceadmin.controller;

import java.io.ByteArrayOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import py.com.econtreras.ecommerceadmin.service.PdfGenerator;
import py.com.econtreras.ecommerceadmin.util.RequestConstant;

/**
 * @author
 */
@Controller
@RequestMapping(RequestConstant.PURCHASE_CREDIT_NOTE)
@PreAuthorize("hasAuthority('compras')")
public class CreditNoteController {
    
    @Autowired
    private PdfGenerator pdfGenerator;

    @GetMapping
    public ModelAndView getCreditNoteView() {
        ModelAndView mav = new ModelAndView("general");
        mav.addObject("piece", RequestConstant.CREDIT_NOTE_MAIN_PATH_RESOURCE);
        return mav;
    }

    @GetMapping("/pdf")
    public ResponseEntity generatePDF(final HttpServletRequest request, final HttpServletResponse response) throws DocumentException {
        ByteArrayOutputStream byteArrayOutputStreamPDF = pdfGenerator.createPdf("purchase/credit_note/credit-note-pdf", null, request, response);
        ByteArrayResource inputStreamResourcePDF = new ByteArrayResource(byteArrayOutputStreamPDF.toByteArray());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + System.currentTimeMillis()).contentType(MediaType.APPLICATION_PDF)
                .contentLength(inputStreamResourcePDF.contentLength()).body(inputStreamResourcePDF);
    }
}
