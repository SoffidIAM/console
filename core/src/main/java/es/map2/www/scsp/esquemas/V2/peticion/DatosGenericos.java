/**
 * DatosGenericos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map2.www.scsp.esquemas.V2.peticion;


public class DatosGenericos  implements java.io.Serializable {
    private es.map2.www.scsp.esquemas.V2.peticion.Emisor emisor;

    private es.map2.www.scsp.esquemas.V2.peticion.Solicitante solicitante;

    private es.map2.www.scsp.esquemas.V2.peticion.Titular titular;

    private es.map2.www.scsp.esquemas.V2.peticion.Transmision transmision;

    public DatosGenericos() {
    }

    public DatosGenericos(
           es.map2.www.scsp.esquemas.V2.peticion.Emisor emisor,
           es.map2.www.scsp.esquemas.V2.peticion.Solicitante solicitante,
           es.map2.www.scsp.esquemas.V2.peticion.Titular titular,
           es.map2.www.scsp.esquemas.V2.peticion.Transmision transmision) {
           this.emisor = emisor;
           this.solicitante = solicitante;
           this.titular = titular;
           this.transmision = transmision;
    }


    /**
     * Gets the emisor value for this DatosGenericos.
     * 
     * @return emisor
     */
    public es.map2.www.scsp.esquemas.V2.peticion.Emisor getEmisor() {
        return emisor;
    }


    /**
     * Sets the emisor value for this DatosGenericos.
     * 
     * @param emisor
     */
    public void setEmisor(es.map2.www.scsp.esquemas.V2.peticion.Emisor emisor) {
        this.emisor = emisor;
    }


    /**
     * Gets the solicitante value for this DatosGenericos.
     * 
     * @return solicitante
     */
    public es.map2.www.scsp.esquemas.V2.peticion.Solicitante getSolicitante() {
        return solicitante;
    }


    /**
     * Sets the solicitante value for this DatosGenericos.
     * 
     * @param solicitante
     */
    public void setSolicitante(es.map2.www.scsp.esquemas.V2.peticion.Solicitante solicitante) {
        this.solicitante = solicitante;
    }


    /**
     * Gets the titular value for this DatosGenericos.
     * 
     * @return titular
     */
    public es.map2.www.scsp.esquemas.V2.peticion.Titular getTitular() {
        return titular;
    }


    /**
     * Sets the titular value for this DatosGenericos.
     * 
     * @param titular
     */
    public void setTitular(es.map2.www.scsp.esquemas.V2.peticion.Titular titular) {
        this.titular = titular;
    }


    /**
     * Gets the transmision value for this DatosGenericos.
     * 
     * @return transmision
     */
    public es.map2.www.scsp.esquemas.V2.peticion.Transmision getTransmision() {
        return transmision;
    }


    /**
     * Sets the transmision value for this DatosGenericos.
     * 
     * @param transmision
     */
    public void setTransmision(es.map2.www.scsp.esquemas.V2.peticion.Transmision transmision) {
        this.transmision = transmision;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosGenericos)) return false;
        DatosGenericos other = (DatosGenericos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.emisor==null && other.getEmisor()==null) || 
             (this.emisor!=null &&
              this.emisor.equals(other.getEmisor()))) &&
            ((this.solicitante==null && other.getSolicitante()==null) || 
             (this.solicitante!=null &&
              this.solicitante.equals(other.getSolicitante()))) &&
            ((this.titular==null && other.getTitular()==null) || 
             (this.titular!=null &&
              this.titular.equals(other.getTitular()))) &&
            ((this.transmision==null && other.getTransmision()==null) || 
             (this.transmision!=null &&
              this.transmision.equals(other.getTransmision())));
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
        if (getEmisor() != null) {
            _hashCode += getEmisor().hashCode();
        }
        if (getSolicitante() != null) {
            _hashCode += getSolicitante().hashCode();
        }
        if (getTitular() != null) {
            _hashCode += getTitular().hashCode();
        }
        if (getTransmision() != null) {
            _hashCode += getTransmision().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosGenericos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", ">DatosGenericos")); //$NON-NLS-1$ //$NON-NLS-2$
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emisor"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", "Emisor")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", ">Emisor")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("solicitante"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", "Solicitante")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", ">Solicitante")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titular"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", "Titular")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", ">Titular")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transmision"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", "Transmision")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", ">Transmision")); //$NON-NLS-1$ //$NON-NLS-2$
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
