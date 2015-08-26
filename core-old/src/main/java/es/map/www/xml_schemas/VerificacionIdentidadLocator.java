/**
 * VerificacionIdentidadLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.www.xml_schemas;

public class VerificacionIdentidadLocator extends org.apache.axis.client.Service implements es.map.www.xml_schemas.VerificacionIdentidad {

    public VerificacionIdentidadLocator() {
    }


    public VerificacionIdentidadLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public VerificacionIdentidadLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for VerificacionIdentidad
    private java.lang.String VerificacionIdentidad_address = "https://intermediacionpp.redsara.es/peticionSVDI/services/VerificacionIdentidad"; //$NON-NLS-1$

    public java.lang.String getVerificacionIdentidadAddress() {
        return VerificacionIdentidad_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String VerificacionIdentidadWSDDServiceName = "VerificacionIdentidad"; //$NON-NLS-1$

    public java.lang.String getVerificacionIdentidadWSDDServiceName() {
        return VerificacionIdentidadWSDDServiceName;
    }

    public void setVerificacionIdentidadWSDDServiceName(java.lang.String name) {
        VerificacionIdentidadWSDDServiceName = name;
    }

    public es.map.www.xml_schemas.PeticionPortType getVerificacionIdentidad() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(VerificacionIdentidad_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getVerificacionIdentidad(endpoint);
    }

    public es.map.www.xml_schemas.PeticionPortType getVerificacionIdentidad(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.map.www.xml_schemas.VerificacionIdentidadSoapBindingStub _stub = new es.map.www.xml_schemas.VerificacionIdentidadSoapBindingStub(portAddress, this);
            _stub.setPortName(getVerificacionIdentidadWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setVerificacionIdentidadEndpointAddress(java.lang.String address) {
        VerificacionIdentidad_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.map.www.xml_schemas.PeticionPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                es.map.www.xml_schemas.VerificacionIdentidadSoapBindingStub _stub = new es.map.www.xml_schemas.VerificacionIdentidadSoapBindingStub(new java.net.URL(VerificacionIdentidad_address), this);
                _stub.setPortName(getVerificacionIdentidadWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException(Messages.getString("VerificacionIdentidadLocator.2") + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName())); //$NON-NLS-1$ //$NON-NLS-2$
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
        if ("VerificacionIdentidad".equals(inputPortName)) { //$NON-NLS-1$
            return getVerificacionIdentidad();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.map.es/xml-schemas", "VerificacionIdentidad"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.map.es/xml-schemas", "VerificacionIdentidad")); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("VerificacionIdentidad".equals(portName)) { //$NON-NLS-1$
            setVerificacionIdentidadEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(String.format(Messages.getString("VerificacionIdentidadLocator.NoSetEndpointUnknownPort"), portName));  //$NON-NLS-1$
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
