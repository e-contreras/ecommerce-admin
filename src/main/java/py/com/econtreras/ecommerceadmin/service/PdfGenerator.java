package py.com.econtreras.ecommerceadmin.service;

import java.io.ByteArrayOutputStream;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dom4j.DocumentException;

public interface PdfGenerator {

    public ByteArrayOutputStream createPdf(final String templateName,
        final Map map, final HttpServletRequest request,
        final HttpServletResponse response) throws DocumentException;

}
