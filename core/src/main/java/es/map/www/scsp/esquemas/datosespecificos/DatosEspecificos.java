/**
 * DatosEspecificos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.www.scsp.esquemas.datosespecificos;

public class DatosEspecificos  implements java.io.Serializable {
    private es.map.www.scsp.esquemas.datosespecificos.SolicitanteDatos solicitanteDatos;

    private es.map.www.scsp.esquemas.datosespecificos.Solicitud solicitud;

    private es.map.www.scsp.esquemas.datosespecificos.EstadoResultado estadoResultado;

    public DatosEspecificos() {
    }

    public DatosEspecificos(
           es.map.www.scsp.esquemas.datosespecificos.SolicitanteDatos solicitanteDatos,
           es.map.www.scsp.esquemas.datosespecificos.Solicitud solicitud,
           es.map.www.scsp.esquemas.datosespecificos.EstadoResultado estadoResultado) {
           this.solicitanteDatos = solicitanteDatos;
           this.solicitud = solicitud;
           this.estadoResultado = estadoResultado;
    }


    /**
     * Gets the solicitanteDatos value for this DatosEspecificos.
     * 
     * @return solicitanteDatos
     */
    public es.map.www.scsp.esquemas.datosespecificos.SolicitanteDatos getSolicitanteDatos() {
        return solicitanteDatos;
    }


    /**
     * Sets the solicitanteDatos value for this DatosEspecificos.
     * 
     * @param solicitanteDatos
     */
    public void setSolicitanteDatos(es.map.www.scsp.esquemas.datosespecificos.SolicitanteDatos solicitanteDatos) {
        this.solicitanteDatos = solicitanteDatos;
    }


    /**
     * Gets the solicitud value for this DatosEspecificos.
     * 
     * @return solicitud
     */
    public es.map.www.scsp.esquemas.datosespecificos.Solicitud getSolicitud() {
        return solicitud;
    }


    /**
     * Sets the solicitud value for this DatosEspecificos.
     * 
     * @param solicitud
     */
    public void setSolicitud(es.map.www.scsp.esquemas.datosespecificos.Solicitud solicitud) {
        this.solicitud = solicitud;
    }


    /**
     * Gets the estadoResultado value for this DatosEspecificos.
     * 
     * @return estadoResultado
     */
    public es.map.www.scsp.esquemas.datosespecificos.EstadoResultado getEstadoResultado() {
        return estadoResultado;
    }


    /**
     * Sets the estadoResultado value for this DatosEspecificos.
     * 
     * @param estadoResultado
     */
    public void setEstadoResultado(es.map.www.scsp.esquemas.datosespecificos.EstadoResultado estadoResultado) {
        this.estadoResultado = estadoResultado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosEspecificos)) return false;
        DatosEspecificos other = (DatosEspecificos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.solicitanteDatos==null && other.getSolicitanteDatos()==null) || 
             (this.solicitanteDatos!=null &&
              this.solicitanteDatos.equals(other.getSolicitanteDatos()))) &&
            ((this.solicitud==null && other.getSolicitud()==null) || 
             (this.solicitud!=null &&
              this.solicitud.equals(other.getSolicitud()))) &&
            ((this.estadoResultado==null && other.getEstadoResultado()==null) || 
             (this.estadoResultado!=null &&
              this.estadoResultado.equals(other.getEstadoResultado())));
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
        if (getSolicitanteDatos() != null) {
            _hashCode += getSolicitanteDatos().hashCode();
        }
        if (getSolicitud() != null) {
            _hashCode += getSolicitud().hashCode();
        }
        if (getEstadoResultado() != null) {
            _hashCode += getEstadoResultado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosEspecificos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", "DatosEspecificos")); //$NON-NLS-1$ //$NON-NLS-2$
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("solicitanteDatos"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", "SolicitanteDatos")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", ">SolicitanteDatos")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("solicitud"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", "Solicitud")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", ">Solicitud")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estadoResultado"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", "EstadoResultado")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", ">EstadoResultado")); //$NON-NLS-1$ //$NON-NLS-2$
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
