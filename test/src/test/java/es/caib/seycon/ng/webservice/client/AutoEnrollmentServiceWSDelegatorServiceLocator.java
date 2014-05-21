/**
 * AutoEnrollmentServiceWSDelegatorServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.seycon.ng.webservice.client;

public class AutoEnrollmentServiceWSDelegatorServiceLocator extends org.apache.axis.client.Service implements es.caib.seycon.ng.webservice.client.AutoEnrollmentServiceWSDelegatorService {

/**

 */

    public AutoEnrollmentServiceWSDelegatorServiceLocator() {
    }


    public AutoEnrollmentServiceWSDelegatorServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AutoEnrollmentServiceWSDelegatorServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AutoEnrollmentService
    private java.lang.String AutoEnrollmentService_address = "http://tticlin2.test.lab:18080/seycon-webservice/services/AutoEnrollmentService"; //$NON-NLS-1$

    public java.lang.String getAutoEnrollmentServiceAddress() {
        return AutoEnrollmentService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AutoEnrollmentServiceWSDDServiceName = "AutoEnrollmentService"; //$NON-NLS-1$

    public java.lang.String getAutoEnrollmentServiceWSDDServiceName() {
        return AutoEnrollmentServiceWSDDServiceName;
    }

    public void setAutoEnrollmentServiceWSDDServiceName(java.lang.String name) {
        AutoEnrollmentServiceWSDDServiceName = name;
    }

    public es.caib.seycon.ng.webservice.client.AutoEnrollmentServiceWSDelegator getAutoEnrollmentService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AutoEnrollmentService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAutoEnrollmentService(endpoint);
    }

    public es.caib.seycon.ng.webservice.client.AutoEnrollmentServiceWSDelegator getAutoEnrollmentService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.caib.seycon.ng.webservice.client.AutoEnrollmentServiceSoapBindingStub _stub = new es.caib.seycon.ng.webservice.client.AutoEnrollmentServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getAutoEnrollmentServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAutoEnrollmentServiceEndpointAddress(java.lang.String address) {
        AutoEnrollmentService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.caib.seycon.ng.webservice.client.AutoEnrollmentServiceWSDelegator.class.isAssignableFrom(serviceEndpointInterface)) {
                es.caib.seycon.ng.webservice.client.AutoEnrollmentServiceSoapBindingStub _stub = new es.caib.seycon.ng.webservice.client.AutoEnrollmentServiceSoapBindingStub(new java.net.URL(AutoEnrollmentService_address), this);
                _stub.setPortName(getAutoEnrollmentServiceWSDDServiceName());
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
        if ("AutoEnrollmentService".equals(inputPortName)) { //$NON-NLS-1$
            return getAutoEnrollmentService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://es.caib.seycon.ng.webservice", "AutoEnrollmentServiceWSDelegatorService"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://es.caib.seycon.ng.webservice", "AutoEnrollmentService")); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AutoEnrollmentService".equals(portName)) { //$NON-NLS-1$
            setAutoEnrollmentServiceEndpointAddress(address);
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
