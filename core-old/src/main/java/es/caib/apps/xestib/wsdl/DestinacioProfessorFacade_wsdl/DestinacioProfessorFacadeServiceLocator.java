/**
 * DestinacioProfessorFacadeServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.apps.xestib.wsdl.DestinacioProfessorFacade_wsdl;

public class DestinacioProfessorFacadeServiceLocator extends org.apache.axis.client.Service implements es.caib.apps.xestib.wsdl.DestinacioProfessorFacade_wsdl.DestinacioProfessorFacadeService {

    public DestinacioProfessorFacadeServiceLocator() {
    }


    public DestinacioProfessorFacadeServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DestinacioProfessorFacadeServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for DestinacioProfessorFacade
    private java.lang.String DestinacioProfessorFacade_address = "https://apps.caib.es/xestib/ws"; //$NON-NLS-1$

    public java.lang.String getDestinacioProfessorFacadeAddress() {
        return DestinacioProfessorFacade_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String DestinacioProfessorFacadeWSDDServiceName = "DestinacioProfessorFacade"; //$NON-NLS-1$

    public java.lang.String getDestinacioProfessorFacadeWSDDServiceName() {
        return DestinacioProfessorFacadeWSDDServiceName;
    }

    public void setDestinacioProfessorFacadeWSDDServiceName(java.lang.String name) {
        DestinacioProfessorFacadeWSDDServiceName = name;
    }

    public es.caib.apps.xestib.wsdl.DestinacioProfessorFacade_wsdl.DestinacioProfessorFacade getDestinacioProfessorFacade() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(DestinacioProfessorFacade_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getDestinacioProfessorFacade(endpoint);
    }

    public es.caib.apps.xestib.wsdl.DestinacioProfessorFacade_wsdl.DestinacioProfessorFacade getDestinacioProfessorFacade(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.caib.apps.xestib.wsdl.DestinacioProfessorFacade_wsdl.DestinacioProfessorFacadeSoapBindingStub _stub = new es.caib.apps.xestib.wsdl.DestinacioProfessorFacade_wsdl.DestinacioProfessorFacadeSoapBindingStub(portAddress, this);
            _stub.setPortName(getDestinacioProfessorFacadeWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setDestinacioProfessorFacadeEndpointAddress(java.lang.String address) {
        DestinacioProfessorFacade_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.caib.apps.xestib.wsdl.DestinacioProfessorFacade_wsdl.DestinacioProfessorFacade.class.isAssignableFrom(serviceEndpointInterface)) {
                es.caib.apps.xestib.wsdl.DestinacioProfessorFacade_wsdl.DestinacioProfessorFacadeSoapBindingStub _stub = new es.caib.apps.xestib.wsdl.DestinacioProfessorFacade_wsdl.DestinacioProfessorFacadeSoapBindingStub(new java.net.URL(DestinacioProfessorFacade_address), this);
                _stub.setPortName(getDestinacioProfessorFacadeWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName())); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("DestinacioProfessorFacade".equals(inputPortName)) { //$NON-NLS-1$
            return getDestinacioProfessorFacade();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://apps.caib.es/xestib/wsdl/DestinacioProfessorFacade.wsdl", "DestinacioProfessorFacadeService"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://apps.caib.es/xestib/wsdl/DestinacioProfessorFacade.wsdl", "DestinacioProfessorFacade")); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("DestinacioProfessorFacade".equals(portName)) { //$NON-NLS-1$
            setDestinacioProfessorFacadeEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName); //$NON-NLS-1$
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
