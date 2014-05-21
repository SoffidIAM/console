//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_CONTRA" )
@Depends ({es.caib.seycon.ng.comu.EstatContrasenya.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.ConfiguracioEntity.class,
	es.caib.seycon.ng.model.DominiContrasenyaEntity.class})
public abstract class ContrasenyaEntity {

	@Column (name="CTR_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="CTR_CONTRA", length=50)
	@Nullable
	public java.lang.String contrasenya;

	@Column (name="CTR_ACTIU", length=1)
	@Nullable
	public java.lang.String actiu;

	@Column (name="CTR_ORDRE")
	public java.lang.Long ordre;

	@Column (name="CTR_DATA")
	@Nullable
	public java.util.Date data;

	@Column (name="CTR_DATCAD")
	@Nullable
	public java.util.Date dataCaducitat;

	@Column (name="CTR_DCN_ID")
	public es.caib.seycon.ng.model.DominiContrasenyaEntity domini;

	@Column (name="CTR_IDUSU")
	public es.caib.seycon.ng.model.UsuariEntity usuari;

	@DaoFinder("from es.caib.seycon.ng.model.ContrasenyaEntity as contrasenyaEntity \nwhere contrasenyaEntity.usuari = :usuari and contrasenyaEntity.domini = :domini\norder by contrasenyaEntity.ordre desc")
	public java.util.List<es.caib.seycon.ng.model.ContrasenyaEntity> findByUsuariDomini(
		es.caib.seycon.ng.model.UsuariEntity usuari, 
		es.caib.seycon.ng.model.DominiContrasenyaEntity domini) {
	 return null;
	}
	@DaoFinder("from es.caib.seycon.ng.model.ContrasenyaEntity as contrasenyaEntity \nwhere contrasenyaEntity.usuari = :usuari and contrasenyaEntity.domini = :domini\nand contrasenyaEntity.ordre = 0\n")
	public es.caib.seycon.ng.model.ContrasenyaEntity findLastByUsuariDomini(
		es.caib.seycon.ng.model.UsuariEntity usuari, 
		es.caib.seycon.ng.model.DominiContrasenyaEntity domini) {
	 return null;
	}
}
