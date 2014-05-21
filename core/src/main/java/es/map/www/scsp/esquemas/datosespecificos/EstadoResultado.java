/**
 * EstadoResultado.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.www.scsp.esquemas.datosespecificos;

public class EstadoResultado  implements java.io.Serializable {
    private java.lang.String codigoEstado;

    private java.lang.String codigoEstadoSecundario;

    private java.lang.String literalError;

    public EstadoResultado() {
    }

    public EstadoResultado(
           java.lang.String codigoEstado,
           java.lang.String codigoEstadoSecundario,
           java.lang.String literalError) {
           this.codigoEstado = codigoEstado;
           this.codigoEstadoSecundario = codigoEstadoSecundario;
           this.literalError = literalError;
    }


    /**
     * Gets the codigoEstado value for this EstadoResultado.
     * 
     * @return codigoEstado
     */
    public java.lang.String getCodigoEstado() {
        return codigoEstado;
    }


    /**
     * Sets the codigoEstado value for this EstadoResultado.
     * 
     * @param codigoEstado
     */
    public void setCodigoEstado(java.lang.String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }


    /**
     * Gets the codigoEstadoSecundario value for this EstadoResultado.
     * 
     * @return codigoEstadoSecundario
     */
    public java.lang.String getCodigoEstadoSecundario() {
        return codigoEstadoSecundario;
    }


    /**
     * Sets the codigoEstadoSecundario value for this EstadoResultado.
     * 
     * @param codigoEstadoSecundario
     */
    public void setCodigoEstadoSecundario(java.lang.String codigoEstadoSecundario) {
        this.codigoEstadoSecundario = codigoEstadoSecundario;
    }


    /**
     * Gets the literalError value for this EstadoResultado.
     * 
     * @return literalError
     */
    public java.lang.String getLiteralError() {
        return literalError;
    }


    /**
     * Sets the literalError value for this EstadoResultado.
     * 
     * @param literalError
     */
    public void setLiteralError(java.lang.String literalError) {
        this.literalError = literalError;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EstadoResultado)) return false;
        EstadoResultado other = (EstadoResultado) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoEstado==null && other.getCodigoEstado()==null) || 
             (this.codigoEstado!=null &&
              this.codigoEstado.equals(other.getCodigoEstado()))) &&
            ((this.codigoEstadoSecundario==null && other.getCodigoEstadoSecundario()==null) || 
             (this.codigoEstadoSecundario!=null &&
              this.codigoEstadoSecundario.equals(other.getCodigoEstadoSecundario()))) &&
            ((this.literalError==null && other.getLiteralError()==null) || 
             (this.literalError!=null &&
              this.literalError.equals(other.getLiteralError())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCodigoEstado() != null) {
            _hashCode += getCodigoEstado().hashCode();
        }
        if (getCodigoEstadoSecundario() != null) {
            _hashCode += getCodigoEstadoSecundario().hashCode();
        }
        if (getLiteralError() != null) {
            _hashCode += getLiteralError().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EstadoResultado.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", ">EstadoResultado")); //$NON-NLS-1$ //$NON-NLS-2$
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEstado"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", "CodigoEstado")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", ">CodigoEstado")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEstadoSecundario"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", "CodigoEstadoSecundario")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", ">CodigoEstadoSecundario")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("literalError"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", "LiteralError")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", ">LiteralError")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
