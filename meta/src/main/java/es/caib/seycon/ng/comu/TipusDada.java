//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import java.util.LinkedList;
import java.util.List;

import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="DataType",
	 translatedPackage="com.soffid.iam.api")
public class TipusDada {

	@Attribute(translated = "code" )
	public java.lang.String codi;

	@Attribute(translated = "order" )
	public java.lang.Long ordre;

	@Nullable
	public java.lang.Long id;

	@Nullable
	public es.caib.seycon.ng.comu.TypeEnumeration type;

	@Nullable
	public java.lang.Integer size;
	
	public boolean required;
	
	@Nullable
	public String label;
	
	@Nullable
	@Description("List of allowed values")
	@Attribute(defaultValue="new java.util.LinkedList<String>()")
	public List<String> values;

	@Description("Administrator visibility")
	@Nullable
	public AttributeVisibilityEnum adminVisibility;
	
	@Description("Operator visibility")
	@Nullable
	public AttributeVisibilityEnum operatorVisibility;
	

	@Description("User visibility")
	@Nullable
	public AttributeVisibilityEnum userVisibility;
	
	@Description ("System where this attribute applies to. Null applies to identity itself")
	@Nullable
	public String systemName;

	@Description("Unique value")
	@Nullable
	public Boolean unique;
}
