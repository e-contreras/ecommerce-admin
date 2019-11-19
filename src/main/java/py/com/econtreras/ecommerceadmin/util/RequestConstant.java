package py.com.econtreras.ecommerceadmin.util;

public class RequestConstant {

    private RequestConstant() {
    }

    public static final String PURCHASE_PRODUCT = "/purchase/products";
    public static final String PURCHASE_PRODUCT_VIEW = "/purchase/products/product-list";
    public static final String PURCHASE_PRODUCT_FORM_VIEW = "/purchase/products/form-product";
    public static final String PURCHASE_PRODUCT_FORM = "/form";
    public static final String PURCHASE_PRODUCT_FORM_EDIT = "/form-edit";
    
    /**
     *   Proveedores
     */
    public static final String PURCHASE_PROVAIDER           = "/purchase/provider";
    public static final String PROVAIDER_MAIN_PATH_RESOURCE = "purchase/provider/provider-index";
    
    /**
     * Cotizaciones
     */
    public static final String PURCHASE_EXCHANGE           = "/purchase/exchange";
    public static final String EXCHANGE_MAIN_PATH_RESOURCE = "purchase/exchange/exchange-index";
    
    /**
     * Nota de Remisión
     */
    public static final String PURCHASE_REMISSION_NOTE           = "/purchase/remission-note";
    public static final String REMISSION_NOTE_MAIN_PATH_RESOURCE = "purchase/remission_note/remission-note-index";
    
    /**
     * Nota de Crédito
     */
    public static final String PURCHASE_CREDIT_NOTE           = "/purchase/credit-note";
    public static final String CREDIT_NOTE_MAIN_PATH_RESOURCE = "purchase/credit_note/credit-note-index";
    
    /**
     * Nota de Débito
     */
    public static final String PURCHASE_DEBIT_NOTE            = "/purchase/debit-note";
    public static final String DEBIT_NOTE_MAIN_PATH_RESOURCE  = "purchase/debit_note/debit-note-index";
    
    /**
     * Libro de Compras
     */
    public static final String PURCHASE_BOOK            = "/purchase-book";
    public static final String BOOK_MAIN_PATH_RESOURCE  = "purchase/book/book-index";
    
    /**
     * Ventas - Pedidos
     */
    public static final String SALES_ORDERS                    = "/sales/orders";
    public static final String SALES_ORDERS_MAIN_PATH_RESOURCE = "sales/orders/order-index";
    
    /**
     * Ventas - Nota de credito
     */
    public static final String SALES_CREDIT_NOTE                    = "/sales/credit-note";
    public static final String SALES_CREDIT_NOTE_MAIN_PATH_RESOURCE = "sales/credit_sales/credit-sales-index";
    
    /**
     * Ventas - Nota de debito
     */
    public static final String SALES_DEBIT_NOTE                    = "/sales/debit-note";
    public static final String SALES_DEBIT_NOTE_MAIN_PATH_RESOURCE = "sales/debit_sales/debit-sales-index";
    
    /**
     * Ventas - Libro ventas
     */
    public static final String SALES_BOOK                    = "/sales-book";
    
    public static final String SALES_BOOK_MAIN_PATH_RESOURCE = "sales/book/book-index";
    
    /**
     * Lógistica - Asignar empaque
     */
    public static final String LOGISTICS_ASSIGN_PACKING                    = "/logistics/assign-packing";
    public static final String LOGISTICS_ASSIGN_PACKING_MAIN_PATH_RESOURCE = "logistics/assign_packing/assign-packing-index";
    
    /**
     * Lógistica - Asignar transporte
     */
    public static final String LOGISTICS_ASSIGN_TRANSPORTATION                    = "/logistics/assign-transportation";
    public static final String LOGISTICS_ASSIGN_TRANSPORTATION_MAIN_PATH_RESOURCE = "logistics/assign_transportation/assign-transportation-index";
    
    /**
     * Lógistica - generar Not. remisión
     */
    public static final String LOGISTICS_REFERRAL_NOTE                    = "/logistics/referral-note";
    public static final String LOGISTICS_REFERRAL_NOTE_MAIN_PATH_RESOURCE = "logistics/referral_note/referral-note-index";
}
