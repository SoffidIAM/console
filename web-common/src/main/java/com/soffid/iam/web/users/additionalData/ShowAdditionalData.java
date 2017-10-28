package com.soffid.iam.web.users.additionalData;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;

import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.api.MetadataScope;

import es.caib.seycon.ng.EJBLocator;
import es.caib.seycon.ng.comu.DadaUsuari;
import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.ejb.DadesAddicionalsService;
import es.caib.seycon.ng.servei.ejb.UsuariService;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.datamodel.DataNode;

public class ShowAdditionalData {
	
	private UsuariService usuariServ;
	private DadesAddicionalsService dadesAddicionalsService;
	
	private DadesAddicionalsService getDadesAddicionalsService () throws CreateException, NamingException {
		if (dadesAddicionalsService == null) dadesAddicionalsService = EJBLocator.getDadesAddicionalsService();
		return dadesAddicionalsService;
	}
	
	public ShowAdditionalData() throws NamingException, CreateException{
		usuariServ = EJBLocator.getUsuariService();
	}
	
	private long getOrder (Collection<TipusDada> tdaList, String attName)
	{
		for (TipusDada tda: tdaList)
		{
			if (tda.getCodi().equals(attName))
				return tda.getOrdre().longValue();
		}
		return -1;
	}
	
	public List<DadaUsuari> getDadaUsuari(String codiUsuari) throws InternalErrorException, CreateException, NamingException{
			final Collection<TipusDada> tipusDadaList =  getDadesAddicionalsService().findDataTypes(MetadataScope.USER);
			List<DadaUsuari> dadaUsuariCollection;
			if (codiUsuari != null) 
				dadaUsuariCollection = new LinkedList<DadaUsuari> (usuariServ.findDadesUsuariByCodiUsuari(codiUsuari));
			else
			{
				dadaUsuariCollection = new LinkedList<DadaUsuari>();
				for (TipusDada tda: tipusDadaList)
				{
					if (! "PHONE". equals (tda.getCodi()) && ! "NIF".equals(tda.getCodi()))
					{
						if (tda.getOperatorVisibility() != AttributeVisibilityEnum.HIDDEN)
						{
							DadaUsuari du = new DadaUsuari ();
							du.setCodiDada(tda.getCodi());
							du.setDataLabel(tda.getLabel());
							du.setVisibility(tda.getOperatorVisibility());
							dadaUsuariCollection.add(du);
						}
					} 
				}
			}
			Collections.sort(dadaUsuariCollection, new Comparator<DadaUsuari>() {

				public int compare(DadaUsuari o1, DadaUsuari o2) {
					long l1 = getOrder (tipusDadaList, o1.getCodiDada());
					long l2 = getOrder (tipusDadaList, o2.getCodiDada());
					if (l1 == l2) return 0;
					else if (l1 > l2) return +1;
					else return -1;
				}
				
			});
			return dadaUsuariCollection;
	}
	
	
	public void checkRequiredAttributes (DataNode userNode) throws InternalErrorException, CreateException, NamingException
	{
		DataModelCollection lm = userNode.getListModel("dada");
		for (int i = 0; i < lm.getSize(); i++)
		{
			DataNode dadaNode = (DataNode) lm.getDataModel(i);
			checkRequiredAttribute(dadaNode);
		}
	}

	public void checkRequiredAttribute (DataNode dadaNode) throws InternalErrorException, CreateException, NamingException
	{
		if (dadaNode != null)
		{
			DadaUsuari du = (DadaUsuari) dadaNode.getInstance();
			if (du != null)
			{
				if ((du.getBlobDataValue() == null || du.getBlobDataValue().length == 0) &&
						(du.getValorDada() == null || du.getValorDada().length() == 0) && 
						du.getValorDadaDate() == null)
				{
					TipusDada tda = getDadesAddicionalsService().findTipusDadaByCodi(du.getCodiDada());
					if (tda != null && tda.isRequired())
						throw new InternalErrorException (String.format (Labels.getLabel("usuaris.zul.RequiredAttribute"), tda.getLabel()));
				}
			}
		}
	}
}
