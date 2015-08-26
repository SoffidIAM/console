/**
 * DadesDocent.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.apps.xestib.wsdl.DestinacioProfessorFacade_wsdl;

public class DadesDocent  implements java.io.Serializable {
    private java.lang.String nom;

    private java.lang.String llinatge1;

    private java.lang.String llinatge2;

    private java.lang.String centrePrincipal;

    private java.lang.String[] centresSecundaris;

    public DadesDocent() {
    }

    public DadesDocent(
           java.lang.String nom,
           java.lang.String llinatge1,
           java.lang.String llinatge2,
           java.lang.String centrePrincipal,
           java.lang.String[] centresSecundaris) {
           this.nom = nom;
           this.llinatge1 = llinatge1;
           this.llinatge2 = llinatge2;
           this.centrePrincipal = centrePrincipal;
           this.centresSecundaris = centresSecundaris;
    }


    /**
     * Gets the nom value for this DadesDocent.
     * 
     * @return nom
     */
    public java.lang.String getNom() {
        return nom;
    }


    /**
     * Sets the nom value for this DadesDocent.
     * 
     * @param nom
     */
    public void setNom(java.lang.String nom) {
        this.nom = nom;
    }


    /**
     * Gets the llinatge1 value for this DadesDocent.
     * 
     * @return llinatge1
     */
    public java.lang.String getLlinatge1() {
        return llinatge1;
    }


    /**
     * Sets the llinatge1 value for this DadesDocent.
     * 
     * @param llinatge1
     */
    public void setLlinatge1(java.lang.String llinatge1) {
        this.llinatge1 = llinatge1;
    }


    /**
     * Gets the llinatge2 value for this DadesDocent.
     * 
     * @return llinatge2
     */
    public java.lang.String getLlinatge2() {
        return llinatge2;
    }


    /**
     * Sets the llinatge2 value for this DadesDocent.
     * 
     * @param llinatge2
     */
    public void setLlinatge2(java.lang.String llinatge2) {
        this.llinatge2 = llinatge2;
    }


    /**
     * Gets the centrePrincipal value for this DadesDocent.
     * 
     * @return centrePrincipal
     */
    public java.lang.String getCentrePrincipal() {
        return centrePrincipal;
    }


    /**
     * Sets the centrePrincipal value for this DadesDocent.
     * 
     * @param centrePrincipal
     */
    public void setCentrePrincipal(java.lang.String centrePrincipal) {
        this.centrePrincipal = centrePrincipal;
    }


    /**
     * Gets the centresSecundaris value for this DadesDocent.
     * 
     * @return centresSecundaris
     */
    public java.lang.String[] getCentresSecundaris() {
        return centresSecundaris;
    }


    /**
     * Sets the centresSecundaris value for this DadesDocent.
     * 
     * @param centresSecundaris
     */
    public void setCentresSecundaris(java.lang.String[] centresSecundaris) {
        this.centresSecundaris = centresSecundaris;
    }

    public java.lang.String getCentresSecundaris(int i) {
        return this.centresSecundaris[i];
    }

    public void setCentresSecundaris(int i, java.lang.String _value) {
        this.centresSecundaris[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DadesDocent)) return false;
        DadesDocent other = (DadesDocent) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nom==null && other.getNom()==null) || 
             (this.nom!=null &&
              this.nom.equals(other.getNom()))) &&
            ((this.llinatge1==null && other.getLlinatge1()==null) || 
             (this.llinatge1!=null &&
              this.llinatge1.equals(other.getLlinatge1()))) &&
            ((this.llinatge2==null && other.getLlinatge2()==null) || 
             (this.llinatge2!=null &&
              this.llinatge2.equals(other.getLlinatge2()))) &&
            ((this.centrePrincipal==null && other.getCentrePrincipal()==null) || 
             (this.centrePrincipal!=null &&
              this.centrePrincipal.equals(other.getCentrePrincipal()))) &&
            ((this.centresSecundaris==null && other.getCentresSecundaris()==null) || 
             (this.centresSecundaris!=null &&
              java.util.Arrays.equals(this.centresSecundaris, other.getCentresSecundaris())));
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
        if (getNom() != null) {
            _hashCode += getNom().hashCode();
        }
        if (getLlinatge1() != null) {
            _hashCode += getLlinatge1().hashCode();
        }
        if (getLlinatge2() != null) {
            _hashCode += getLlinatge2().hashCode();
        }
        if (getCentrePrincipal() != null) {
            _hashCode += getCentrePrincipal().hashCode();
        }
        if (getCentresSecundaris() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCentresSecundaris());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCentresSecundaris(), i);
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
        new org.apache.axis.description.TypeDesc(DadesDocent.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://apps.caib.es/xestib/wsdl/DestinacioProfessorFacade.wsdl", "DadesDocent")); //$NON-NLS-1$ //$NON-NLS-2$
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nom"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("https://apps.caib.es/xestib/wsdl/DestinacioProfessorFacade.wsdl", "nom")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("llinatge1"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("https://apps.caib.es/xestib/wsdl/DestinacioProfessorFacade.wsdl", "llinatge1")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("llinatge2"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("https://apps.caib.es/xestib/wsdl/DestinacioProfessorFacade.wsdl", "llinatge2")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("centrePrincipal"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("https://apps.caib.es/xestib/wsdl/DestinacioProfessorFacade.wsdl", "centrePrincipal")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("centresSecundaris"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("https://apps.caib.es/xestib/wsdl/DestinacioProfessorFacade.wsdl", "centresSecundaris")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
