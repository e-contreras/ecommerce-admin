package py.com.econtreras.ecommerceadmin.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "mod_menu", catalog = "econtreras")
public class MenuModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 45)
    private String name;
    @Basic(optional = false)
    @Column(name = "icon", nullable = false, length = 45)
    private String icon;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menuModule", fetch = FetchType.EAGER)
    private List<Menu> menuList;


}
