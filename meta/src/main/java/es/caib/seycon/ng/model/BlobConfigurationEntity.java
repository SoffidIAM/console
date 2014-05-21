//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_BLOCON" )
public abstract class BlobConfigurationEntity {

	@Column (name="BCO_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="BCO_NAME", length=255)
	public java.lang.String name;

	@Column (name="BCO_VALUE", length=10240000)
	public byte[] value;

	@Column (name="BCO_VERSIO", length=50)
	@Nullable
	public java.lang.String versio;

	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.BlobConfigurationEntity> findByName(
		java.lang.String name) {
	 return null;
	}
}
