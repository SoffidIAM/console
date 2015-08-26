package es.caib.apps.xestib.wsdl.DestinacioProfessorFacade_wsdl;

public class DestinacioProfessorFacadeProxy implements es.caib.apps.xestib.wsdl.DestinacioProfessorFacade_wsdl.DestinacioProfessorFacade {
  private String _endpoint = null;
  private es.caib.apps.xestib.wsdl.DestinacioProfessorFacade_wsdl.DestinacioProfessorFacade destinacioProfessorFacade = null;
  
  public DestinacioProfessorFacadeProxy() {
    _initDestinacioProfessorFacadeProxy();
  }
  
  public DestinacioProfessorFacadeProxy(String endpoint) {
    _endpoint = endpoint;
    _initDestinacioProfessorFacadeProxy();
  }
  
  private void _initDestinacioProfessorFacadeProxy() {
    try {
      destinacioProfessorFacade = (new es.caib.apps.xestib.wsdl.DestinacioProfessorFacade_wsdl.DestinacioProfessorFacadeServiceLocator()).getDestinacioProfessorFacade();
      if (destinacioProfessorFacade != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)destinacioProfessorFacade)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint); //$NON-NLS-1$
        else
          _endpoint = (String)((javax.xml.rpc.Stub)destinacioProfessorFacade)._getProperty("javax.xml.rpc.service.endpoint.address"); //$NON-NLS-1$
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (destinacioProfessorFacade != null)
      ((javax.xml.rpc.Stub)destinacioProfessorFacade)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint); //$NON-NLS-1$
    
  }
  
  public es.caib.apps.xestib.wsdl.DestinacioProfessorFacade_wsdl.DestinacioProfessorFacade getDestinacioProfessorFacade() {
    if (destinacioProfessorFacade == null)
      _initDestinacioProfessorFacadeProxy();
    return destinacioProfessorFacade;
  }
  
  public DadesDocent consultaDestinacions(java.lang.String nifProfessor) throws java.rmi.RemoteException{
    if (destinacioProfessorFacade == null)
      _initDestinacioProfessorFacadeProxy();
    return destinacioProfessorFacade.consultaDestinacions(nifProfessor);
  }
  
  
}