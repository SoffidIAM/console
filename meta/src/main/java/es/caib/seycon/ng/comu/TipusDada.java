//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import java.util.List;

import com.soffid.iam.api.LetterCaseEnum;
import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.api.MetadataScope;
import com.soffid.mda.annotation.Attribute;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject ( translatedName="DataType",
	 translatedPackage="com.soffid.iam.api")
public class TipusDada {

	@Attribute(translated = "code" )
	public java.lang.String codi;

	@Attribute(translated = "order" )
	@Nullable
	public java.lang.Long ordre;

	@Nullable
	public java.lang.Long id;

	@Column (name="TDA_SCOPE", length=50)
	@Nullable
	public MetadataScope scope;

	@Nullable
	@Description("Object type acting as owner of the attribute")
	public String customObjectType;

	@Description("Object type instances acting as value of the attribute, for custom object type attributes")
	@Nullable
	public String dataObjectType;

	public es.caib.seycon.ng.comu.TypeEnumeration type;

	@Nullable
	public java.lang.Integer size;
	
	public boolean required;
	
	public boolean multiValued;
	
	@Description("Include field in quick searches")
	@Nullable
	public Boolean searchCriteria;

	@Description ("Display a maxim of rows values. For more values, a scroll bar will appear")
	@Nullable
	public Integer multiValuedRows;

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

	@Description("Expression to test if attribute should be displayed or not")
	@Nullable
	String visibilityExpression;
	
	@Description("Expression to test if attribute value is valid or not")
	@Nullable
	String validationExpression;
		
	@Description("Expression to test if the reference object can be selected ")
	@Nullable
	String filterExpression;

	
	@Description("Trigger to run when the data is displayed")
	@Nullable
	String onLoadTrigger;
	
	@Description("Trigger to run when the data is changed")
	@Nullable
	String onChangeTrigger;
	
	@Description("Trigger to run when the field is focused")
	@Nullable
	String onFocusTrigger;
	
	@Description("Built-in attribute")
	@Nullable
	@Attribute (defaultValue="false")
	Boolean builtin;
	
	@Description ("Uppercase / lowercase usage")
	@Nullable
	@Attribute (defaultValue="com.soffid.iam.api.LetterCaseEnum.MIXEDCASE")
	LetterCaseEnum letterCase;
}
