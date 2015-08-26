/**
 * Organizacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.www.scsp.esquemas.datosespecificos;

public class Organizacion  implements java.io.Serializable {
    private java.lang.String codOrganizacion;

    private java.lang.String nombreOrganizacion;

    public Organizacion() {
    }

    public Organizacion(
           java.lang.String codOrganizacion,
           java.lang.String nombreOrganizacion) {
           this.codOrganizacion = codOrganizacion;
           this.nombreOrganizacion = nombreOrganizacion;
    }


    /**
     * Gets the codOrganizacion value for this Organizacion.
     * 
     * @return codOrganizacion
     */
    public java.lang.String getCodOrganizacion() {
        return codOrganizacion;
    }


    /**
     * Sets the codOrganizacion value for this Organizacion.
     * 
     * @param codOrganizacion
     */
    public void setCodOrganizacion(java.lang.String codOrganizacion) {
        this.codOrganizacion = codOrganizacion;
    }


    /**
     * Gets the nombreOrganizacion value for this Organizacion.
     * 
     * @return nombreOrganizacion
     */
    public java.lang.String getNombreOrganizacion() {
        return nombreOrganizacion;
    }


    /**
     * Sets the nombreOrganizacion value for this Organizacion.
     * 
     * @param nombreOrganizacion
     */
    public void setNombreOrganizacion(java.lang.String nombreOrganizacion) {
        this.nombreOrganizacion = nombreOrganizacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Organizacion)) return false;
        Organizacion other = (Organizacion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codOrganizacion==null && other.getCodOrganizacion()==null) || 
             (this.codOrganizacion!=null &&
              this.codOrganizacion.equals(other.getCodOrganizacion()))) &&
            ((this.nombreOrganizacion==null && other.getNombreOrganizacion()==null) || 
             (this.nombreOrganizacion!=null &&
              this.nombreOrganizacion.equals(other.getNombreOrganizacion())));
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
        if (getCodOrganizacion() != null) {
            _hashCode += getCodOrganizacion().hashCode();
        }
        if (getNombreOrganizacion() != null) {
            _hashCode += getNombreOrganizacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Organizacion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", ">Organizacion")); //$NON-NLS-1$ //$NON-NLS-2$
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codOrganizacion"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", "CodOrganizacion")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", ">CodOrganizacion")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreOrganizacion"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", "NombreOrganizacion")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", ">NombreOrganizacion")); //$NON-NLS-1$ //$NON-NLS-2$
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
