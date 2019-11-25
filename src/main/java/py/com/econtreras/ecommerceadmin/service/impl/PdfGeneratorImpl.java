package py.com.econtreras.ecommerceadmin.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;
import py.com.econtreras.ecommerceadmin.service.PdfGenerator;

@Service
public class PdfGeneratorImpl implements PdfGenerator {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private ApplicationContext context;

    @Autowired
    ServletContext servletContext;

    String urlBase = "http://localhost:8081";

    @Override
    public ByteArrayOutputStream createPdf(String templateName, Map map, HttpServletRequest request, HttpServletResponse response) throws DocumentException {
        Context ctx = new Context();
        ctx.setVariables(map);
        String processedHtml = templateEngine.process(templateName, ctx);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(processedHtml, urlBase);
            renderer.layout();
            renderer.createPDF(bos, false);
            renderer.finishPDF();
        } catch (com.lowagie.text.DocumentException ex) {
            Logger.getLogger(PdfGeneratorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bos;
    }

}
