
package py.com.econtreras.ecommerceadmin.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "usuarios", catalog = "econtreras", schema = "")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "username", nullable = false, length = 45)
    private String username;
    @Basic(optional = false)
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @Column(name = "activo")
    private Short active;
    @Column(name = "fec_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "fec_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;
    @OneToMany(mappedBy = "creationUser")
    private List<UndeliberedRemissionNote> UndeliberedRemissionNoteList;
    @OneToMany(mappedBy = "modificationUser")
    private List<UndeliberedRemissionNote> UndeliberedRemissionNoteList1;
    @OneToMany(mappedBy = "creationUser")
    private List<Event> eventList;
    @OneToMany(mappedBy = "modificationUser")
    private List<Event> eventosList1;
    @OneToMany(mappedBy = "creationUser")
    private List<Inventory> inventoryList;
    @OneToMany(mappedBy = "modificationUser")
    private List<Inventory> inventoryList1;
    @JoinColumn(name = "persona", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    private Person person;
    @JoinColumn(name = "role", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Role role;
    @OneToMany(mappedBy = "creationUser")
    private List<User> usuariosList;
    @JoinColumn(name = "usu_alta", referencedColumnName = "id")
    @ManyToOne
    private User creationUser;
    @OneToMany(mappedBy = "modificationUser")
    private List<User> usuariosList1;
    @JoinColumn(name = "usu_modificacion", referencedColumnName = "id")
    @ManyToOne
    private User modificationUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wareHousePersonal")
    private List<WorkOrder> ordTrabajosList;
    @Basic(optional = false)
    @Column(name = "int_fallidos", nullable = false, length = 45)
    private Integer loginFailed;

}
