//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="AgentDescriptor",
	 translatedPackage="com.soffid.iam.api")
public abstract class AgentDescriptor {

	public java.lang.Long id;

	public java.lang.String description;

	public java.lang.String className;

	public byte[] userInterface;

	public boolean enableAccessControl;

	public boolean authoritativeSource;

	public boolean enableAttributeMapping;

	public boolean enableObjectTriggers;
}
