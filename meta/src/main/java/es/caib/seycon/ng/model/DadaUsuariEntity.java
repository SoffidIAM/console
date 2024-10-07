//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.soffid.iam.api.AttributeVisibilityEnum;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.servei.AutoritzacioService;
import com.soffid.mda.annotation.*;

@Entity (table="SC_DADUSU", translatedName="UserDataEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.DadaUsuari.class,
	es.caib.seycon.ng.model.TipusDadaEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	AutoritzacioService.class})
public abstract class DadaUsuariEntity {

	@Column (name="DUS_VALOR", length=1024, translated="value")
	@Nullable
	public java.lang.String valorDada;

	@Column (name="DUS_IDUSU", translated="user")
	public es.caib.seycon.ng.model.UsuariEntity usuari;

	@Column (name="DUS_TDAID", translated="dataType")
	public es.caib.seycon.ng.model.TipusDadaEntity tipusDada;

	@Column (name="DUS_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="DUS_BLOB", length=400000)
	@Nullable
	public byte[] blobDataValue;

	@Operation(translated="findByDataType")
	@DaoFinder("select dadaUsuari from com.soffid.iam.model.UserDataEntity as dadaUsuari "
			+ "left join dadaUsuari.user as user "
			+ "left join dadaUsuari.dataType as type "
			+ "where user.userName = :userName and type.name=:dataType and user.tenant.id = :tenantId")
	public Collection<es.caib.seycon.ng.model.DadaUsuariEntity> findDadaByCodiTipusDada(
		java.lang.String userName, 
		java.lang.String dataType) {
	 return null;
	}
	
	@DaoFinder("select dadaUsuari from com.soffid.iam.model.UserDataEntity as dadaUsuari "
			+ "left join dadaUsuari.dataType as type "
			+ "where dadaUsuari.value = :value and type.name=:dataType and "
			+ "dadaUsuari.user.tenant.id = :tenantId")
	public java.util.List<es.caib.seycon.ng.model.DadaUsuariEntity> findByTypeAndValue(
		java.lang.String dataType, 
		java.lang.String value) {
	 return null;
	}

	@DaoFinder("select d from com.soffid.iam.model.UserDataEntity as d "
			+ "where d.user.id = :userId and d.dataType.name in (:attributes)")
	List<DadaUsuariEntity>findByUserAndAttribute(Long userId, @Nullable String[] attributes) {return null;}
	
	@Description ("Gets the visibility level for an attribue")
	@Operation
	public AttributeVisibilityEnum getAttributeVisibility() {
		return null;
	}

	public boolean isAllowed(String permission) {
		return false;
	}

	public void setObjectValue(Object value) {}
	public Object getObjectValue() {return null;}

}

@Index(columns={"DUS_IDUSU","DUS_TDAID"}, name="SC_DADUSU_USU_UK", entity=DadaUsuariEntity.class)
class DadaUsuariIndex {
	
}
