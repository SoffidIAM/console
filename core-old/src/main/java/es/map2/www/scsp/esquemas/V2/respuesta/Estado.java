/**
 * Estado.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map2.www.scsp.esquemas.V2.respuesta;


public class Estado  implements java.io.Serializable {
    private java.lang.String codigoEstado;

    private java.lang.String codigoEstadoSecundario;

    private java.lang.String literalError;

    private java.lang.Integer tiempoEstimadoRespuesta;

    public Estado() {
    }

    public Estado(
           java.lang.String codigoEstado,
           java.lang.String codigoEstadoSecundario,
           java.lang.String literalError,
           java.lang.Integer tiempoEstimadoRespuesta) {
           this.codigoEstado = codigoEstado;
           this.codigoEstadoSecundario = codigoEstadoSecundario;
           this.literalError = literalError;
           this.tiempoEstimadoRespuesta = tiempoEstimadoRespuesta;
    }


    /**
     * Gets the codigoEstado value for this Estado.
     * 
     * @return codigoEstado
     */
    public java.lang.String getCodigoEstado() {
        return codigoEstado;
    }


    /**
     * Sets the codigoEstado value for this Estado.
     * 
     * @param codigoEstado
     */
    public void setCodigoEstado(java.lang.String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }


    /**
     * Gets the codigoEstadoSecundario value for this Estado.
     * 
     * @return codigoEstadoSecundario
     */
    public java.lang.String getCodigoEstadoSecundario() {
        return codigoEstadoSecundario;
    }


    /**
     * Sets the codigoEstadoSecundario value for this Estado.
     * 
     * @param codigoEstadoSecundario
     */
    public void setCodigoEstadoSecundario(java.lang.String codigoEstadoSecundario) {
        this.codigoEstadoSecundario = codigoEstadoSecundario;
    }


    /**
     * Gets the literalError value for this Estado.
     * 
     * @return literalError
     */
    public java.lang.String getLiteralError() {
        return literalError;
    }


    /**
     * Sets the literalError value for this Estado.
     * 
     * @param literalError
     */
    public void setLiteralError(java.lang.String literalError) {
        this.literalError = literalError;
    }


    /**
     * Gets the tiempoEstimadoRespuesta value for this Estado.
     * 
     * @return tiempoEstimadoRespuesta
     */
    public java.lang.Integer getTiempoEstimadoRespuesta() {
        return tiempoEstimadoRespuesta;
    }


    /**
     * Sets the tiempoEstimadoRespuesta value for this Estado.
     * 
     * @param tiempoEstimadoRespuesta
     */
    public void setTiempoEstimadoRespuesta(java.lang.Integer tiempoEstimadoRespuesta) {
        this.tiempoEstimadoRespuesta = tiempoEstimadoRespuesta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Estado)) return false;
        Estado other = (Estado) obj;
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
              this.literalError.equals(other.getLiteralError()))) &&
            ((this.tiempoEstimadoRespuesta==null && other.getTiempoEstimadoRespuesta()==null) || 
             (this.tiempoEstimadoRespuesta!=null &&
              this.tiempoEstimadoRespuesta.equals(other.getTiempoEstimadoRespuesta())));
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
        if (getTiempoEstimadoRespuesta() != null) {
            _hashCode += getTiempoEstimadoRespuesta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Estado.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/respuesta", ">Estado")); //$NON-NLS-1$ //$NON-NLS-2$
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEstado"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/respuesta", "CodigoEstado")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/respuesta", ">CodigoEstado")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEstadoSecundario"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/respuesta", "CodigoEstadoSecundario")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/respuesta", ">CodigoEstadoSecundario")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("literalError"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/respuesta", "LiteralError")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/respuesta", ">LiteralError")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tiempoEstimadoRespuesta"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/respuesta", "TiempoEstimadoRespuesta")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/respuesta", ">TiempoEstimadoRespuesta")); //$NON-NLS-1$ //$NON-NLS-2$
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
