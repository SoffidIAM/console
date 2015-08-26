/**
 * Solicitud.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map2.www.scsp.esquemas.V2.peticion;

import es.map2.www.scsp.esquemas.V2.peticion.SolicitudTransmision;
import es.map2.www.scsp.esquemas.V2.peticion.Solicitudes;

public class Solicitudes  implements java.io.Serializable {
    private es.map2.www.scsp.esquemas.V2.peticion.SolicitudTransmision[] solicitudTransmision;

    public Solicitudes() {
    }

    public Solicitudes(SolicitudTransmision[] solicitudes) {
           this.solicitudTransmision = solicitudes;
    }


    /**
     * Gets the solicitudes value for this Solicitud.
     * 
     * @return solicitudes
     */
    public es.map2.www.scsp.esquemas.V2.peticion.SolicitudTransmision[] getSolicitudTransmision() {
        return solicitudTransmision;
    }


    /**
     * Sets the solicitudes value for this Solicitud.
     * 
     * @param solicitudes
     */
    public void setSolicitudTransmision(es.map2.www.scsp.esquemas.V2.peticion.SolicitudTransmision[] solicitudes) {
        this.solicitudTransmision = solicitudes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Solicitudes)) return false;
        Solicitudes other = (Solicitudes) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.solicitudTransmision==null && other.getSolicitudTransmision()==null) || 
             (this.solicitudTransmision!=null &&
              java.util.Arrays.equals(this.solicitudTransmision, other.getSolicitudTransmision())));
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
        if (getSolicitudTransmision() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSolicitudTransmision());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSolicitudTransmision(), i);
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
        new org.apache.axis.description.TypeDesc(Solicitudes.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", ">Solicitudes")); //$NON-NLS-1$ //$NON-NLS-2$
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("solicitudTransmision"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", "SolicitudTransmision")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", ">SolicitudTransmision")); //$NON-NLS-1$ //$NON-NLS-2$
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
