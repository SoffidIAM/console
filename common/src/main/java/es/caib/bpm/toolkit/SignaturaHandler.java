package es.caib.bpm.toolkit;

import es.caib.bpm.toolkit.exception.WorkflowException;

public interface SignaturaHandler {
	public static final String CERTIFY_LOCATION_COOKIEID = "location1638678412"; //$NON-NLS-1$
	
	public void sign (String tag) throws WorkflowException;
	public void signPDF (String inputTag,String outputTag,String enabledStampType, String[] enabledPositions,String forcedAdditionalText) throws WorkflowException;
	public void signPDF (String inputTag,String outputTag,String enabledStampType,String [] stampPositions, float top, float left, float height, float width, float rotation,String forcedAdditionalText) throws WorkflowException;
	public void compulsaPDF(String inputTag ,String outputTag ,String url,String location,float x,float y,float rotation) throws WorkflowException;
}
