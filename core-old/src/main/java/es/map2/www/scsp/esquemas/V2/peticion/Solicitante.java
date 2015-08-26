/**
 * Solicitante.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map2.www.scsp.esquemas.V2.peticion;


public class Solicitante  implements java.io.Serializable {
    private java.lang.String identificadorSolicitante;

    private java.lang.String nombreSolicitante;

    private java.lang.String finalidad;

    private es.map2.www.scsp.esquemas.V2.peticion.Consentimiento consentimiento;

    private es.map2.www.scsp.esquemas.V2.peticion.Funcionario funcionario;

    public Solicitante() {
    }

    public Solicitante(
           java.lang.String identificadorSolicitante,
           java.lang.String nombreSolicitante,
           java.lang.String finalidad,
           es.map2.www.scsp.esquemas.V2.peticion.Consentimiento consentimiento,
           es.map2.www.scsp.esquemas.V2.peticion.Funcionario funcionario) {
           this.identificadorSolicitante = identificadorSolicitante;
           this.nombreSolicitante = nombreSolicitante;
           this.finalidad = finalidad;
           this.consentimiento = consentimiento;
           this.funcionario = funcionario;
    }


    /**
     * Gets the identificadorSolicitante value for this Solicitante.
     * 
     * @return identificadorSolicitante
     */
    public java.lang.String getIdentificadorSolicitante() {
        return identificadorSolicitante;
    }


    /**
     * Sets the identificadorSolicitante value for this Solicitante.
     * 
     * @param identificadorSolicitante
     */
    public void setIdentificadorSolicitante(java.lang.String identificadorSolicitante) {
        this.identificadorSolicitante = identificadorSolicitante;
    }


    /**
     * Gets the nombreSolicitante value for this Solicitante.
     * 
     * @return nombreSolicitante
     */
    public java.lang.String getNombreSolicitante() {
        return nombreSolicitante;
    }


    /**
     * Sets the nombreSolicitante value for this Solicitante.
     * 
     * @param nombreSolicitante
     */
    public void setNombreSolicitante(java.lang.String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }


    /**
     * Gets the finalidad value for this Solicitante.
     * 
     * @return finalidad
     */
    public java.lang.String getFinalidad() {
        return finalidad;
    }


    /**
     * Sets the finalidad value for this Solicitante.
     * 
     * @param finalidad
     */
    public void setFinalidad(java.lang.String finalidad) {
        this.finalidad = finalidad;
    }


    /**
     * Gets the consentimiento value for this Solicitante.
     * 
     * @return consentimiento
     */
    public es.map2.www.scsp.esquemas.V2.peticion.Consentimiento getConsentimiento() {
        return consentimiento;
    }


    /**
     * Sets the consentimiento value for this Solicitante.
     * 
     * @param consentimiento
     */
    public void setConsentimiento(es.map2.www.scsp.esquemas.V2.peticion.Consentimiento consentimiento) {
        this.consentimiento = consentimiento;
    }


    /**
     * Gets the funcionario value for this Solicitante.
     * 
     * @return funcionario
     */
    public es.map2.www.scsp.esquemas.V2.peticion.Funcionario getFuncionario() {
        return funcionario;
    }


    /**
     * Sets the funcionario value for this Solicitante.
     * 
     * @param funcionario
     */
    public void setFuncionario(es.map2.www.scsp.esquemas.V2.peticion.Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Solicitante)) return false;
        Solicitante other = (Solicitante) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.identificadorSolicitante==null && other.getIdentificadorSolicitante()==null) || 
             (this.identificadorSolicitante!=null &&
              this.identificadorSolicitante.equals(other.getIdentificadorSolicitante()))) &&
            ((this.nombreSolicitante==null && other.getNombreSolicitante()==null) || 
             (this.nombreSolicitante!=null &&
              this.nombreSolicitante.equals(other.getNombreSolicitante()))) &&
            ((this.finalidad==null && other.getFinalidad()==null) || 
             (this.finalidad!=null &&
              this.finalidad.equals(other.getFinalidad()))) &&
            ((this.consentimiento==null && other.getConsentimiento()==null) || 
             (this.consentimiento!=null &&
              this.consentimiento.equals(other.getConsentimiento()))) &&
            ((this.funcionario==null && other.getFuncionario()==null) || 
             (this.funcionario!=null &&
              this.funcionario.equals(other.getFuncionario())));
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
        if (getIdentificadorSolicitante() != null) {
            _hashCode += getIdentificadorSolicitante().hashCode();
        }
        if (getNombreSolicitante() != null) {
            _hashCode += getNombreSolicitante().hashCode();
        }
        if (getFinalidad() != null) {
            _hashCode += getFinalidad().hashCode();
        }
        if (getConsentimiento() != null) {
            _hashCode += getConsentimiento().hashCode();
        }
        if (getFuncionario() != null) {
            _hashCode += getFuncionario().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Solicitante.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", ">Solicitante")); //$NON-NLS-1$ //$NON-NLS-2$
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificadorSolicitante"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", "IdentificadorSolicitante")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", ">IdentificadorSolicitante")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreSolicitante"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", "NombreSolicitante")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", ">NombreSolicitante")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("finalidad"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", "Finalidad")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", ">Finalidad")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consentimiento"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", "Consentimiento")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", ">Consentimiento")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("funcionario"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", "Funcionario")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/V2/peticion", ">Funcionario")); //$NON-NLS-1$ //$NON-NLS-2$
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
