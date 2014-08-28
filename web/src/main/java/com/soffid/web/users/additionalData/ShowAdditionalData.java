package com.soffid.web.users.additionalData;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;

import es.caib.seycon.ng.comu.DadaUsuari;
import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.DadesAddicionalsServiceImpl;
import es.caib.seycon.ng.servei.ejb.DadesAddicionalsService;
import es.caib.seycon.ng.servei.ejb.DadesAddicionalsServiceHome;
import es.caib.seycon.ng.servei.ejb.UsuariService;
import es.caib.seycon.ng.servei.ejb.UsuariServiceHome;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.datamodel.DataModelNode;
import es.caib.zkib.datamodel.DataNode;

public class ShowAdditionalData {
	
	private UsuariService usuariServ;
	private DadesAddicionalsService dadesAddicionalsServ;
	public ShowAdditionalData() throws NamingException, CreateException{
		UsuariServiceHome home = (UsuariServiceHome) new InitialContext().lookup (UsuariServiceHome.JNDI_NAME);
		usuariServ = home.create();
		
		DadesAddicionalsServiceHome homeDades = (DadesAddicionalsServiceHome) new InitialContext().lookup (DadesAddicionalsServiceHome.JNDI_NAME);
		dadesAddicionalsServ = homeDades.create();
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
	@SuppressWarnings("unchecked")
	public List<DadaUsuari> getDadaUsuari(String codiUsuari) throws InternalErrorException{
			final Collection<TipusDada> tipusDadaList =  dadesAddicionalsServ.findTipusDadesByCodi("%");
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
						DadaUsuari du = new DadaUsuari ();
						du.setCodiDada(tda.getCodi());
						du.setDataLabel(tda.getLabel());
						dadaUsuariCollection.add(du);
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
	
	
	void checkRequiredAttributes (DataNode userNode) throws InternalErrorException
	{
		DataModelCollection lm = userNode.getListModel("dada");
		for (int i = 0; i < lm.getSize(); i++)
		{
			DataNode dadaNode = (DataNode) lm.getDataModel(i);
			checkRequiredAttribute(dadaNode);
		}
	}

	void checkRequiredAttribute (DataNode dadaNode) throws InternalErrorException
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
					TipusDada tda = dadesAddicionalsServ.findTipusDadaByCodi(du.getCodiDada());
					if (tda.isRequired())
						throw new InternalErrorException (String.format (Labels.getLabel("usuaris.zul.RequiredAttribute"), tda.getLabel()));
				}
			}
		}
	}
}
