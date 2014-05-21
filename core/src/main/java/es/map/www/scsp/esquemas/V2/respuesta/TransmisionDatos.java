/**
 * TransmisionDatos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.www.scsp.esquemas.V2.respuesta;

public class TransmisionDatos  implements java.io.Serializable {
    private es.map.www.scsp.esquemas.V2.respuesta.DatosGenericos datosGenericos;

    private es.map.www.scsp.esquemas.datosespecificos.DatosEspecificos datosEspecificos;

    public TransmisionDatos() {
    }

    public TransmisionDatos(
           es.map.www.scsp.esquemas.V2.respuesta.DatosGenericos datosGenericos,
           es.map.www.scsp.esquemas.datosespecificos.DatosEspecificos datosEspecificos) {
           this.datosGenericos = datosGenericos;
           this.datosEspecificos = datosEspecificos;
    }


    /**
     * Gets the datosGenericos value for this TransmisionDatos.
     * 
     * @return datosGenericos
     */
    public es.map.www.scsp.esquemas.V2.respuesta.DatosGenericos getDatosGenericos() {
        return datosGenericos;
    }


    /**
     * Sets the datosGenericos value for this TransmisionDatos.
     * 
     * @param datosGenericos
     */
    public void setDatosGenericos(es.map.www.scsp.esquemas.V2.respuesta.DatosGenericos datosGenericos) {
        this.datosGenericos = datosGenericos;
    }


    /**
     * Gets the datosEspecificos value for this TransmisionDatos.
     * 
     * @return datosEspecificos
     */
    public es.map.www.scsp.esquemas.datosespecificos.DatosEspecificos getDatosEspecificos() {
        return datosEspecificos;
    }


    /**
     * Sets the datosEspecificos value for this TransmisionDatos.
     * 
     * @param datosEspecificos
     */
    public void setDatosEspecificos(es.map.www.scsp.esquemas.datosespecificos.DatosEspecificos datosEspecificos) {
        this.datosEspecificos = datosEspecificos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TransmisionDatos)) return false;
        TransmisionDatos other = (TransmisionDatos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.datosGenericos==null && other.getDatosGenericos()==null) || 
             (this.datosGenericos!=null &&
              this.datosGenericos.equals(other.getDatosGenericos()))) &&
            ((this.datosEspecificos==null && other.getDatosEspecificos()==null) || 
             (this.datosEspecificos!=null &&
              this.datosEspecificos.equals(other.getDatosEspecificos())));
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
        if (getDatosGenericos() != null) {
            _hashCode += getDatosGenericos().hashCode();
        }
        if (getDatosEspecificos() != null) {
            _hashCode += getDatosEspecificos().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TransmisionDatos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/respuesta", ">TransmisionDatos")); //$NON-NLS-1$ //$NON-NLS-2$
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosGenericos"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/respuesta", "DatosGenericos")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/respuesta", ">DatosGenericos")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosEspecificos"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", "DatosEspecificos")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", "DatosEspecificos")); //$NON-NLS-1$ //$NON-NLS-2$
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
