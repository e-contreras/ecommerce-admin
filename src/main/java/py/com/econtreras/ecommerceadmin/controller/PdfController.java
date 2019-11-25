//package py.com.econtreras.ecommerceadmin.controller;
//
//import com.lowagie.text.DocumentException;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.ByteArrayOutputStream;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/pdf")
//public class PdfController {
//    @GetMapping("/raffle/pdf")
//    public ResponseEntity rafflePDF(@ModelAttribute final Raffle raffle, final HttpServletRequest request,
//                                    final HttpServletResponse response) throws DocumentException {
//
//        List winners = raffle.getWinners();
//
//        Map<String, Object> mapParameter = new HashMap<String, Object>();
//        mapParameter.put("name", "Softtekiano");
//        mapParameter.put("winners", winners);
//
//        ByteArrayOutputStream byteArrayOutputStreamPDF = pdfGenarator.createPdf(templateName, mapParameter, request, response);
//        ByteArrayResource inputStreamResourcePDF = new ByteArrayResource(byteArrayOutputStreamPDF.toByteArray());
//
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName).contentType(MediaType.APPLICATION_PDF)
//                .contentLength(inputStreamResourcePDF.contentLength()).body(inputStreamResourcePDF);
//
//    }
//
//}
