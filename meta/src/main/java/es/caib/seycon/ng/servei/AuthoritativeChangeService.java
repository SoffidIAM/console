package es.caib.seycon.ng.servei;

import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.sync.intf.AuthoritativeChange;

@Service(internal=true) 
public class AuthoritativeChangeService {
	@Operation
	@Description ("Performs authoritative change")
	public void finishAuthoritativeChange (AuthoritativeChange change) {
		
	}
	
	@Operation
	@Description ("Cancels an authoritative change")
	public void cancelAuthoritativeChange (AuthoritativeChange change) {
		
	}
	@Operation
	@Description ("Notifies a new authoritative change has just arrived.\nResturns true if the authoritative change has been performed")
	public boolean startAuthoritativeChange (AuthoritativeChange change)
	{
		return true;
	}

}
