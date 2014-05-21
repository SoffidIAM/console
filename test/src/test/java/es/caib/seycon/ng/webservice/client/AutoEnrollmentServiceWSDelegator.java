/**
 * AutoEnrollmentServiceWSDelegator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.seycon.ng.webservice.client;

public interface AutoEnrollmentServiceWSDelegator extends java.rmi.Remote {
    public void alta(java.lang.String nom, java.lang.String llinatge1, java.lang.String llinatge2, java.lang.String correuElectronic, java.lang.String urlServidor) throws java.rmi.RemoteException, es.caib.seycon.ng.exception.SeyconException;
    public void assignarPassword(java.lang.String correuElectronic, java.lang.String PIN, java.lang.String newPassword) throws java.rmi.RemoteException, es.caib.seycon.ng.webservice.exception.UnknownUserException, es.caib.seycon.ng.exception.SeyconException, es.caib.seycon.ng.webservice.exception.InvalidPasswordException, es.caib.seycon.ng.webservice.exception.BadPasswordException;
    public void enviarNouPIN(java.lang.String correuElectronic, java.lang.String urlServidor) throws java.rmi.RemoteException, es.caib.seycon.ng.webservice.exception.UnknownUserException, es.caib.seycon.ng.exception.SeyconException;
    public void modificarDades(java.lang.String nom, java.lang.String llinatge1, java.lang.String llinatge2, java.lang.String correuElectronic) throws java.rmi.RemoteException, es.caib.seycon.ng.webservice.exception.UnknownUserException, es.caib.seycon.ng.exception.SeyconException;
    public void resetejarPassword(java.lang.String correuElectronic, java.lang.String oldPassword, java.lang.String newPassword) throws java.rmi.RemoteException, es.caib.seycon.ng.webservice.exception.UnknownUserException, es.caib.seycon.ng.exception.SeyconException, es.caib.seycon.ng.webservice.exception.InvalidPasswordException, es.caib.seycon.ng.webservice.exception.BadPasswordException;
}
