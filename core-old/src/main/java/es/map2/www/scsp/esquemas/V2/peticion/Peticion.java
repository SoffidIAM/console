/**
 * Peticion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map2.www.scsp.esquemas.V2.peticion;


public class Peticion  implements java.io.Serializable {
    private es.map2.www.scsp.esquemas.V2.peticion.Atributos atributos;

    private es.map2.www.scsp.esquemas.V2.peticion.Solicitudes solicitudes;

    public Peticion() {
    }

    public Peticion(
           es.map2.www.scsp.esquemas.V2.peticion.Atributos atributos,
           es.map2.www.scsp.esquemas.V2.peticion.Solicitudes solicitudes) {
           this.atributos = atributos;
           this.solicitudes = solicitudes;
    }


    /**
     * Gets the atributos value for this Peticion.
     * 
     * @return atributos
     */
    public es.map2.www.scsp.esquemas.V2.peticion.Atributos getAtributos() {
        return atributos;
    }


    /**
     * Sets the atributos value for this Peticion.
     * 
     * @param atributos
     */
    public void setAtributos(es.map2.www.scsp.esquemas.V2.peticion.Atributos atributos) {
        this.atributos = atributos;
    }


    /**
     * Gets the solicitudes value for this Peticion.
     * 
     * @return solicitudes
     */
    public es.map2.www.scsp.esquemas.V2.peticion.Solicitudes getSolicitudes() {
        return solicitudes;
    }


    /**
     * Sets the solicitudes value for this Peticion.
     * 
     * @param solicitudes
     */
    public void setSolicitudes(es.map2.www.scsp.esquemas.V2.peticion.Solicitudes solicitudes) {
        this.solicitudes = solicitudes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Peticion)) return false;
        Peticion other = (Peticion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.atributos==null && other.getAtributos()==null) || 
             (this.atributos!=null &&
              this.atributos.equals(other.getAtributos()))) &&
            ((this.solicitudes==null && other.getSolicitudes()==null) || 
             (this.solicitudes!=null &&
              this.solicitudes.equals(other.getSolicitudes())));
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
        if (getAtributos() != null) {
            _hashCode += getAtributos().hashCode();
        }
        if (getSolicitudes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSolicitudes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSolicitudes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Peticion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", ">Peticion")); //$NON-NLS-1$ //$NON-NLS-2$
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("atributos"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", "Atributos")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", ">Atributos")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("solicitudes"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", "Solicitudes")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", ">Solicitudes")); //$NON-NLS-1$ //$NON-NLS-2$
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
