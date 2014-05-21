package es.caib.seycon.ng.webservice.client;

public class AutoEnrollmentServiceWSDelegatorProxy implements es.caib.seycon.ng.webservice.client.AutoEnrollmentServiceWSDelegator {
  private String _endpoint = null;
  private es.caib.seycon.ng.webservice.client.AutoEnrollmentServiceWSDelegator autoEnrollmentServiceWSDelegator = null;
  
  public AutoEnrollmentServiceWSDelegatorProxy() {
    _initAutoEnrollmentServiceWSDelegatorProxy();
  }
  
  public AutoEnrollmentServiceWSDelegatorProxy(String endpoint) {
    _endpoint = endpoint;
    _initAutoEnrollmentServiceWSDelegatorProxy();
  }
  
  private void _initAutoEnrollmentServiceWSDelegatorProxy() {
    try {
      autoEnrollmentServiceWSDelegator = (new es.caib.seycon.ng.webservice.client.AutoEnrollmentServiceWSDelegatorServiceLocator()).getAutoEnrollmentService();
      if (autoEnrollmentServiceWSDelegator != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)autoEnrollmentServiceWSDelegator)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint); //$NON-NLS-1$
        else
          _endpoint = (String)((javax.xml.rpc.Stub)autoEnrollmentServiceWSDelegator)._getProperty("javax.xml.rpc.service.endpoint.address"); //$NON-NLS-1$
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (autoEnrollmentServiceWSDelegator != null)
      ((javax.xml.rpc.Stub)autoEnrollmentServiceWSDelegator)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint); //$NON-NLS-1$
    
  }
  
  public es.caib.seycon.ng.webservice.client.AutoEnrollmentServiceWSDelegator getAutoEnrollmentServiceWSDelegator() {
    if (autoEnrollmentServiceWSDelegator == null)
      _initAutoEnrollmentServiceWSDelegatorProxy();
    return autoEnrollmentServiceWSDelegator;
  }
  
  public void alta(java.lang.String nom, java.lang.String llinatge1, java.lang.String llinatge2, java.lang.String correuElectronic, java.lang.String urlServidor) throws java.rmi.RemoteException, es.caib.seycon.ng.exception.SeyconException{
    if (autoEnrollmentServiceWSDelegator == null)
      _initAutoEnrollmentServiceWSDelegatorProxy();
    autoEnrollmentServiceWSDelegator.alta(nom, llinatge1, llinatge2, correuElectronic, urlServidor);
  }
  
  public void assignarPassword(java.lang.String correuElectronic, java.lang.String PIN, java.lang.String newPassword) throws java.rmi.RemoteException, es.caib.seycon.ng.webservice.exception.UnknownUserException, es.caib.seycon.ng.exception.SeyconException, es.caib.seycon.ng.webservice.exception.InvalidPasswordException, es.caib.seycon.ng.webservice.exception.BadPasswordException{
    if (autoEnrollmentServiceWSDelegator == null)
      _initAutoEnrollmentServiceWSDelegatorProxy();
    autoEnrollmentServiceWSDelegator.assignarPassword(correuElectronic, PIN, newPassword);
  }
  
  public void enviarNouPIN(java.lang.String correuElectronic, java.lang.String urlServidor) throws java.rmi.RemoteException, es.caib.seycon.ng.webservice.exception.UnknownUserException, es.caib.seycon.ng.exception.SeyconException{
    if (autoEnrollmentServiceWSDelegator == null)
      _initAutoEnrollmentServiceWSDelegatorProxy();
    autoEnrollmentServiceWSDelegator.enviarNouPIN(correuElectronic, urlServidor);
  }
  
  public void modificarDades(java.lang.String nom, java.lang.String llinatge1, java.lang.String llinatge2, java.lang.String correuElectronic) throws java.rmi.RemoteException, es.caib.seycon.ng.webservice.exception.UnknownUserException, es.caib.seycon.ng.exception.SeyconException{
    if (autoEnrollmentServiceWSDelegator == null)
      _initAutoEnrollmentServiceWSDelegatorProxy();
    autoEnrollmentServiceWSDelegator.modificarDades(nom, llinatge1, llinatge2, correuElectronic);
  }
  
  public void resetejarPassword(java.lang.String correuElectronic, java.lang.String oldPassword, java.lang.String newPassword) throws java.rmi.RemoteException, es.caib.seycon.ng.webservice.exception.UnknownUserException, es.caib.seycon.ng.exception.SeyconException, es.caib.seycon.ng.webservice.exception.InvalidPasswordException, es.caib.seycon.ng.webservice.exception.BadPasswordException{
    if (autoEnrollmentServiceWSDelegator == null)
      _initAutoEnrollmentServiceWSDelegatorProxy();
    autoEnrollmentServiceWSDelegator.resetejarPassword(correuElectronic, oldPassword, newPassword);
  }
  
  
}