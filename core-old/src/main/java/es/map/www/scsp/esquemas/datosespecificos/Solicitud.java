/**
 * Solicitud.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.www.scsp.esquemas.datosespecificos;

public class Solicitud  implements java.io.Serializable {
    private es.map.www.scsp.esquemas.datosespecificos.Sexo sexo;

    private java.lang.String numSoporte;

    private es.map.www.scsp.esquemas.datosespecificos.DatosNacimiento datosNacimiento;

    private es.map.www.scsp.esquemas.datosespecificos.Direccion direccion;

    public Solicitud() {
    }

    public Solicitud(
           es.map.www.scsp.esquemas.datosespecificos.Sexo sexo,
           java.lang.String numSoporte,
           es.map.www.scsp.esquemas.datosespecificos.DatosNacimiento datosNacimiento,
           es.map.www.scsp.esquemas.datosespecificos.Direccion direccion) {
           this.sexo = sexo;
           this.numSoporte = numSoporte;
           this.datosNacimiento = datosNacimiento;
           this.direccion = direccion;
    }


    /**
     * Gets the sexo value for this Solicitud.
     * 
     * @return sexo
     */
    public es.map.www.scsp.esquemas.datosespecificos.Sexo getSexo() {
        return sexo;
    }


    /**
     * Sets the sexo value for this Solicitud.
     * 
     * @param sexo
     */
    public void setSexo(es.map.www.scsp.esquemas.datosespecificos.Sexo sexo) {
        this.sexo = sexo;
    }


    /**
     * Gets the numSoporte value for this Solicitud.
     * 
     * @return numSoporte
     */
    public java.lang.String getNumSoporte() {
        return numSoporte;
    }


    /**
     * Sets the numSoporte value for this Solicitud.
     * 
     * @param numSoporte
     */
    public void setNumSoporte(java.lang.String numSoporte) {
        this.numSoporte = numSoporte;
    }


    /**
     * Gets the datosNacimiento value for this Solicitud.
     * 
     * @return datosNacimiento
     */
    public es.map.www.scsp.esquemas.datosespecificos.DatosNacimiento getDatosNacimiento() {
        return datosNacimiento;
    }


    /**
     * Sets the datosNacimiento value for this Solicitud.
     * 
     * @param datosNacimiento
     */
    public void setDatosNacimiento(es.map.www.scsp.esquemas.datosespecificos.DatosNacimiento datosNacimiento) {
        this.datosNacimiento = datosNacimiento;
    }


    /**
     * Gets the direccion value for this Solicitud.
     * 
     * @return direccion
     */
    public es.map.www.scsp.esquemas.datosespecificos.Direccion getDireccion() {
        return direccion;
    }


    /**
     * Sets the direccion value for this Solicitud.
     * 
     * @param direccion
     */
    public void setDireccion(es.map.www.scsp.esquemas.datosespecificos.Direccion direccion) {
        this.direccion = direccion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Solicitud)) return false;
        Solicitud other = (Solicitud) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sexo==null && other.getSexo()==null) || 
             (this.sexo!=null &&
              this.sexo.equals(other.getSexo()))) &&
            ((this.numSoporte==null && other.getNumSoporte()==null) || 
             (this.numSoporte!=null &&
              this.numSoporte.equals(other.getNumSoporte()))) &&
            ((this.datosNacimiento==null && other.getDatosNacimiento()==null) || 
             (this.datosNacimiento!=null &&
              this.datosNacimiento.equals(other.getDatosNacimiento()))) &&
            ((this.direccion==null && other.getDireccion()==null) || 
             (this.direccion!=null &&
              this.direccion.equals(other.getDireccion())));
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
        if (getSexo() != null) {
            _hashCode += getSexo().hashCode();
        }
        if (getNumSoporte() != null) {
            _hashCode += getNumSoporte().hashCode();
        }
        if (getDatosNacimiento() != null) {
            _hashCode += getDatosNacimiento().hashCode();
        }
        if (getDireccion() != null) {
            _hashCode += getDireccion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Solicitud.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", ">Solicitud")); //$NON-NLS-1$ //$NON-NLS-2$
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sexo"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", "Sexo")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", ">Sexo")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numSoporte"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", "NumSoporte")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", ">NumSoporte")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosNacimiento"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", "DatosNacimiento")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", ">DatosNacimiento")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("direccion"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", "Direccion")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", ">Direccion")); //$NON-NLS-1$ //$NON-NLS-2$
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
