//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="AccessTreeExecution",
	 translatedPackage="com.soffid.iam.api")
public class ExecucioPuntEntrada {

	@Nullable
	public java.lang.Long id;

	@Attribute(translated = "scope" )
	public java.lang.String ambit;

	@Nullable
	@Attribute(translated = "content" )
	public java.lang.String contingut;

	@Nullable
	@Attribute(translated = "executionTypeCode" )
	public java.lang.String codiTipusExecucio;

	@Nullable
	@Attribute(translated = "typeMimeExecution" )
	public java.lang.String tipusMimeExecucio;

	@Nullable
	@Attribute(translated = "AccessTreeId" )
	public java.lang.Long idPuntEntrada;

}
