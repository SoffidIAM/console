package es.caib.bpm.exception;

public class InvalidConfigurationException extends BPMException 
{
	public InvalidConfigurationException(String message) 
	{
		super(message, BPMErrorCodes.CONFIGURACION_INVALIDA);
	}
}
