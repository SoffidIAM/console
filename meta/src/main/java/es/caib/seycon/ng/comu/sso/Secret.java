package es.caib.seycon.ng.comu.sso;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

import es.caib.seycon.ng.comu.Password;

@ValueObject(translatedName="Secret",translatedPackage="com.soffid.iam.api.sso")
public class Secret {
	@Nullable
	private String name;
	@Nullable
    private Password value;
}
