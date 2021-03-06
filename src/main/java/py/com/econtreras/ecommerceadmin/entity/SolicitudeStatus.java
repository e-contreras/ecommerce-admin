
package py.com.econtreras.ecommerceadmin.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "estados", catalog = "econtreras", schema = "")
public class SolicitudeStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nom_estado", nullable = false, length = 45)
    private String statusName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
    private List<Solicitude> solicitudeList;
}
