package es.caib.seycon.ng.comu;

import java.util.LinkedList;
import java.util.List;

import com.soffid.mda.annotation.Attribute;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.ValueObject;

@ValueObject(translatedName="SystemAccessControl", translatedPackage="com.soffid.iam.api")
public class DispatcherAccessControl {
	@Attribute(translated="system")
	private String codi;
	@Attribute(translated="enabled")
	private Boolean controlAccessActiu;
	
	@Description("Acces control rules")
	private List<ControlAcces> controlAcces;

}
