//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import java.util.Collection;

import com.soffid.mda.annotation.*;

@Entity (table="SC_CONTRA", translatedName="PasswordEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.EstatContrasenya.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.ConfiguracioEntity.class,
	es.caib.seycon.ng.model.DominiContrasenyaEntity.class})
public abstract class ContrasenyaEntity {

	@Column (name="CTR_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="CTR_CONTRA", length=50, translated="password")
	@Nullable
	public java.lang.String contrasenya;

	@Column (name="CTR_PASSWD", length=150, translated="password2")
	@Nullable
	public java.lang.String contrasenya2;

	@Column (name="CTR_ACTIU", length=1, translated="active")
	@Nullable
	public java.lang.String actiu;

	@Column (name="CTR_ORDRE", translated="order")
	public java.lang.Long ordre;

	@Column (name="CTR_DATA", translated="date")
	@Nullable
	public java.util.Date data;

	@Column (name="CTR_DATCAD", translated="expirationDate")
	@Nullable
	public java.util.Date dataCaducitat;

	@Column (name="CTR_DCN_ID", translated="domain")
	public es.caib.seycon.ng.model.DominiContrasenyaEntity domini;

	@Column (name="CTR_IDUSU", translated="user")
	public es.caib.seycon.ng.model.UsuariEntity usuari;
	
	@Column (name="CTR_FAILS")
	@Nullable
	public Integer fails;

	@Column (name="CTR_UNLOCK")
	@Nullable
	public java.util.Date unlockDate;



	@Operation(translated="findByUserDomain")
	@DaoFinder("from com.soffid.iam.model.PasswordEntity as contrasenyaEntity \n"
			+ "where contrasenyaEntity.user = :user and contrasenyaEntity.domain = :domain\n"
			+ "order by contrasenyaEntity.order desc")
	public java.util.List<es.caib.seycon.ng.model.ContrasenyaEntity> findByUsuariDomini(
		es.caib.seycon.ng.model.UsuariEntity user, 
		es.caib.seycon.ng.model.DominiContrasenyaEntity domain) {
	 return null;
	}
	@Operation(translated="findLastByUserDomain")
	@DaoFinder("from  com.soffid.iam.model.PasswordEntity as contrasenyaEntity \n"
			+ "where contrasenyaEntity.user = :user and contrasenyaEntity.domain = :domain\n"
			+ "and contrasenyaEntity.order = 0\n")
	public Collection<es.caib.seycon.ng.model.ContrasenyaEntity> findLastByUsuariDomini(
		es.caib.seycon.ng.model.UsuariEntity user, 
		es.caib.seycon.ng.model.DominiContrasenyaEntity domain) {
	 return null;
	}
}

@Index(name="SC_CONTRA_ORDRE_UK", unique=true, columns= {"CTR_IDUSU", "CTR_DCN_ID", "CTR_ORDRE"}, entity=ContrasenyaEntity.class)
class ContrasenyaEntityOrderUK
{
	
}
