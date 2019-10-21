package py.com.econtreras.ecommerceadmin.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "menus", catalog = "econtreras")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 100)
    private String name;
    @Basic(optional = false)
    @Column(name = "url", nullable = false, length = 100)
    private String url;
    @Basic(optional = false)
    @Column(name = "icons", nullable = false, length = 45)
    private String icons;
    @JoinColumn(name = "mod_menu", referencedColumnName = "id")
    @ManyToOne
    private MenuModule menuModule;
}
