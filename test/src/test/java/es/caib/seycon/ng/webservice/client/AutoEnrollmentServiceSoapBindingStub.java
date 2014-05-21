/**
 * AutoEnrollmentServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.seycon.ng.webservice.client;

public class AutoEnrollmentServiceSoapBindingStub extends org.apache.axis.client.Stub implements es.caib.seycon.ng.webservice.client.AutoEnrollmentServiceWSDelegator {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[5];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("alta"); //$NON-NLS-1$
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "nom"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "llinatge1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "llinatge2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "correuElectronic"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "urlServidor"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("", "fault"), //$NON-NLS-1$ //$NON-NLS-2$
                      "es.caib.seycon.ng.exception.SeyconException", //$NON-NLS-1$
                      new javax.xml.namespace.QName("http://es.caib.seycon.ng.exception", "SeyconException"),  //$NON-NLS-1$ //$NON-NLS-2$
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("assignarPassword"); //$NON-NLS-1$
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "correuElectronic"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "PIN"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "newPassword"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("", "fault"), //$NON-NLS-1$ //$NON-NLS-2$
                      "es.caib.seycon.ng.webservice.exception.UnknownUserException", //$NON-NLS-1$
                      new javax.xml.namespace.QName("http://es.caib.seycon", "UnknownUserException"),  //$NON-NLS-1$ //$NON-NLS-2$
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("", "fault"), //$NON-NLS-1$ //$NON-NLS-2$
                      "es.caib.seycon.ng.exception.SeyconException", //$NON-NLS-1$
                      new javax.xml.namespace.QName("http://es.caib.seycon.ng.exception", "SeyconException"),  //$NON-NLS-1$ //$NON-NLS-2$
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("", "fault"), //$NON-NLS-1$ //$NON-NLS-2$
                      "es.caib.seycon.ng.webservice.exception.InvalidPasswordException", //$NON-NLS-1$
                      new javax.xml.namespace.QName("http://es.caib.seycon", "InvalidPasswordException"),  //$NON-NLS-1$ //$NON-NLS-2$
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("", "fault"), //$NON-NLS-1$ //$NON-NLS-2$
                      "es.caib.seycon.ng.webservice.exception.BadPasswordException", //$NON-NLS-1$
                      new javax.xml.namespace.QName("http://es.caib.seycon", "BadPasswordException"),  //$NON-NLS-1$ //$NON-NLS-2$
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("enviarNouPIN"); //$NON-NLS-1$
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "correuElectronic"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "urlServidor"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("", "fault"), //$NON-NLS-1$ //$NON-NLS-2$
                      "es.caib.seycon.ng.webservice.exception.UnknownUserException", //$NON-NLS-1$
                      new javax.xml.namespace.QName("http://es.caib.seycon", "UnknownUserException"),  //$NON-NLS-1$ //$NON-NLS-2$
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("", "fault"), //$NON-NLS-1$ //$NON-NLS-2$
                      "es.caib.seycon.ng.exception.SeyconException", //$NON-NLS-1$
                      new javax.xml.namespace.QName("http://es.caib.seycon.ng.exception", "SeyconException"),  //$NON-NLS-1$ //$NON-NLS-2$
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("modificarDades"); //$NON-NLS-1$
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "nom"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "llinatge1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "llinatge2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "correuElectronic"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("", "fault"), //$NON-NLS-1$ //$NON-NLS-2$
                      "es.caib.seycon.ng.webservice.exception.UnknownUserException", //$NON-NLS-1$
                      new javax.xml.namespace.QName("http://es.caib.seycon", "UnknownUserException"),  //$NON-NLS-1$ //$NON-NLS-2$
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("", "fault"), //$NON-NLS-1$ //$NON-NLS-2$
                      "es.caib.seycon.ng.exception.SeyconException", //$NON-NLS-1$
                      new javax.xml.namespace.QName("http://es.caib.seycon.ng.exception", "SeyconException"),  //$NON-NLS-1$ //$NON-NLS-2$
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("resetejarPassword"); //$NON-NLS-1$
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "correuElectronic"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "oldPassword"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "newPassword"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("", "fault"), //$NON-NLS-1$ //$NON-NLS-2$
                      "es.caib.seycon.ng.webservice.exception.UnknownUserException", //$NON-NLS-1$
                      new javax.xml.namespace.QName("http://es.caib.seycon", "UnknownUserException"),  //$NON-NLS-1$ //$NON-NLS-2$
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("", "fault"), //$NON-NLS-1$ //$NON-NLS-2$
                      "es.caib.seycon.ng.exception.SeyconException", //$NON-NLS-1$
                      new javax.xml.namespace.QName("http://es.caib.seycon.ng.exception", "SeyconException"),  //$NON-NLS-1$ //$NON-NLS-2$
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("", "fault"), //$NON-NLS-1$ //$NON-NLS-2$
                      "es.caib.seycon.ng.webservice.exception.InvalidPasswordException", //$NON-NLS-1$
                      new javax.xml.namespace.QName("http://es.caib.seycon", "InvalidPasswordException"),  //$NON-NLS-1$ //$NON-NLS-2$
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("", "fault"), //$NON-NLS-1$ //$NON-NLS-2$
                      "es.caib.seycon.ng.webservice.exception.BadPasswordException", //$NON-NLS-1$
                      new javax.xml.namespace.QName("http://es.caib.seycon", "BadPasswordException"),  //$NON-NLS-1$ //$NON-NLS-2$
                      true
                     ));
        _operations[4] = oper;

    }

    public AutoEnrollmentServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public AutoEnrollmentServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public AutoEnrollmentServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2"); //$NON-NLS-1$
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://es.caib.seycon.ng.exception", "SeyconException"); //$NON-NLS-1$ //$NON-NLS-2$
            cachedSerQNames.add(qName);
            cls = es.caib.seycon.ng.exception.SeyconException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.caib.seycon", "BadPasswordException"); //$NON-NLS-1$ //$NON-NLS-2$
            cachedSerQNames.add(qName);
            cls = es.caib.seycon.ng.webservice.exception.BadPasswordException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.caib.seycon", "InvalidPasswordException"); //$NON-NLS-1$ //$NON-NLS-2$
            cachedSerQNames.add(qName);
            cls = es.caib.seycon.ng.webservice.exception.InvalidPasswordException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.caib.seycon", "UnknownUserException"); //$NON-NLS-1$ //$NON-NLS-2$
            cachedSerQNames.add(qName);
            cls = es.caib.seycon.ng.webservice.exception.UnknownUserException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t); //$NON-NLS-1$
        }
    }

    public void alta(java.lang.String nom, java.lang.String llinatge1, java.lang.String llinatge2, java.lang.String correuElectronic, java.lang.String urlServidor) throws java.rmi.RemoteException, es.caib.seycon.ng.exception.SeyconException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI(""); //$NON-NLS-1$
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://es.caib.seycon.ng.webservice", "alta")); //$NON-NLS-1$ //$NON-NLS-2$

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {nom, llinatge1, llinatge2, correuElectronic, urlServidor});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof es.caib.seycon.ng.exception.SeyconException) {
              throw (es.caib.seycon.ng.exception.SeyconException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void assignarPassword(java.lang.String correuElectronic, java.lang.String PIN, java.lang.String newPassword) throws java.rmi.RemoteException, es.caib.seycon.ng.webservice.exception.UnknownUserException, es.caib.seycon.ng.exception.SeyconException, es.caib.seycon.ng.webservice.exception.InvalidPasswordException, es.caib.seycon.ng.webservice.exception.BadPasswordException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI(""); //$NON-NLS-1$
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://es.caib.seycon.ng.webservice", "assignarPassword")); //$NON-NLS-1$ //$NON-NLS-2$

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {correuElectronic, PIN, newPassword});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof es.caib.seycon.ng.webservice.exception.UnknownUserException) {
              throw (es.caib.seycon.ng.webservice.exception.UnknownUserException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof es.caib.seycon.ng.exception.SeyconException) {
              throw (es.caib.seycon.ng.exception.SeyconException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof es.caib.seycon.ng.webservice.exception.InvalidPasswordException) {
              throw (es.caib.seycon.ng.webservice.exception.InvalidPasswordException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof es.caib.seycon.ng.webservice.exception.BadPasswordException) {
              throw (es.caib.seycon.ng.webservice.exception.BadPasswordException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void enviarNouPIN(java.lang.String correuElectronic, java.lang.String urlServidor) throws java.rmi.RemoteException, es.caib.seycon.ng.webservice.exception.UnknownUserException, es.caib.seycon.ng.exception.SeyconException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI(""); //$NON-NLS-1$
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://es.caib.seycon.ng.webservice", "enviarNouPIN")); //$NON-NLS-1$ //$NON-NLS-2$

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {correuElectronic, urlServidor});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof es.caib.seycon.ng.webservice.exception.UnknownUserException) {
              throw (es.caib.seycon.ng.webservice.exception.UnknownUserException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof es.caib.seycon.ng.exception.SeyconException) {
              throw (es.caib.seycon.ng.exception.SeyconException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void modificarDades(java.lang.String nom, java.lang.String llinatge1, java.lang.String llinatge2, java.lang.String correuElectronic) throws java.rmi.RemoteException, es.caib.seycon.ng.webservice.exception.UnknownUserException, es.caib.seycon.ng.exception.SeyconException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI(""); //$NON-NLS-1$
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://es.caib.seycon.ng.webservice", "modificarDades")); //$NON-NLS-1$ //$NON-NLS-2$

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {nom, llinatge1, llinatge2, correuElectronic});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof es.caib.seycon.ng.webservice.exception.UnknownUserException) {
              throw (es.caib.seycon.ng.webservice.exception.UnknownUserException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof es.caib.seycon.ng.exception.SeyconException) {
              throw (es.caib.seycon.ng.exception.SeyconException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void resetejarPassword(java.lang.String correuElectronic, java.lang.String oldPassword, java.lang.String newPassword) throws java.rmi.RemoteException, es.caib.seycon.ng.webservice.exception.UnknownUserException, es.caib.seycon.ng.exception.SeyconException, es.caib.seycon.ng.webservice.exception.InvalidPasswordException, es.caib.seycon.ng.webservice.exception.BadPasswordException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI(""); //$NON-NLS-1$
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://es.caib.seycon.ng.webservice", "resetejarPassword")); //$NON-NLS-1$ //$NON-NLS-2$

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {correuElectronic, oldPassword, newPassword});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof es.caib.seycon.ng.webservice.exception.UnknownUserException) {
              throw (es.caib.seycon.ng.webservice.exception.UnknownUserException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof es.caib.seycon.ng.exception.SeyconException) {
              throw (es.caib.seycon.ng.exception.SeyconException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof es.caib.seycon.ng.webservice.exception.InvalidPasswordException) {
              throw (es.caib.seycon.ng.webservice.exception.InvalidPasswordException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof es.caib.seycon.ng.webservice.exception.BadPasswordException) {
              throw (es.caib.seycon.ng.webservice.exception.BadPasswordException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
