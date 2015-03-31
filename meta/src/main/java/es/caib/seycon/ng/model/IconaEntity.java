//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_ICONES" , translatedName="EntryPointIconEntity", translatedPackage="com.soffid.iam.model")
public abstract class IconaEntity {

	@Column (name="ICO_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="ICO_ICONA", length=20000, translated="icon")
	public byte[] icona;

	@DaoFinder
	public es.caib.seycon.ng.model.IconaEntity findById(
		java.lang.Long id) {
	 return null;
	}
}
