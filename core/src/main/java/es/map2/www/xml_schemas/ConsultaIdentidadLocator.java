/**
 * ConsultaIdentidadLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map2.www.xml_schemas;

public class ConsultaIdentidadLocator extends org.apache.axis.client.Service implements es.map2.www.xml_schemas.ConsultaIdentidad {

    public ConsultaIdentidadLocator() {
    }


    public ConsultaIdentidadLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ConsultaIdentidadLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ConsultaIdentidad
    private java.lang.String ConsultaIdentidad_address = "https://intermediacionpp.redsara.es/peticionSVDI/services/ConsultaIdentidad"; //$NON-NLS-1$

    public java.lang.String getConsultaIdentidadAddress() {
        return ConsultaIdentidad_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ConsultaIdentidadWSDDServiceName = "ConsultaIdentidad"; //$NON-NLS-1$

    public java.lang.String getConsultaIdentidadWSDDServiceName() {
        return ConsultaIdentidadWSDDServiceName;
    }

    public void setConsultaIdentidadWSDDServiceName(java.lang.String name) {
        ConsultaIdentidadWSDDServiceName = name;
    }

    public es.map2.www.xml_schemas.PeticionPortType getConsultaIdentidad() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ConsultaIdentidad_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getConsultaIdentidad(endpoint);
    }

    public es.map2.www.xml_schemas.PeticionPortType getConsultaIdentidad(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.map2.www.xml_schemas.ConsultaIdentidadSoapBindingStub _stub = new es.map2.www.xml_schemas.ConsultaIdentidadSoapBindingStub(portAddress, this);
            _stub.setPortName(getConsultaIdentidadWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setConsultaIdentidadEndpointAddress(java.lang.String address) {
        ConsultaIdentidad_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.map2.www.xml_schemas.PeticionPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                es.map2.www.xml_schemas.ConsultaIdentidadSoapBindingStub _stub = new es.map2.www.xml_schemas.ConsultaIdentidadSoapBindingStub(new java.net.URL(ConsultaIdentidad_address), this);
                _stub.setPortName(getConsultaIdentidadWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException(Messages.getString("ConsultaIdentidadLocator.NoStubImplementation") + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName())); //$NON-NLS-1$ //$NON-NLS-2$
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
        if ("ConsultaIdentidad".equals(inputPortName)) { //$NON-NLS-1$
            return getConsultaIdentidad();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.map.es/xml-schemas", "ConsultaIdentidad"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.map.es/xml-schemas", "ConsultaIdentidad")); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ConsultaIdentidad".equals(portName)) { //$NON-NLS-1$
            setConsultaIdentidadEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(
            		String.format(Messages.getString("ConsultaIdentidadLocator.NotEndPointUnknownPort"), portName));  //$NON-NLS-1$
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
