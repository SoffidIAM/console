package es.caib.seycon.ng.web.component;

import java.io.Serializable;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

import es.caib.zkib.zkiblaf.ImageClic;

/**
 * Classe gràfica per implementar arbres amb nodes interns by Alejandro Usero
 * Ruiz - 09/05/2012
 * 
 * @author u88683
 * 
 */
public class TreeElementFill implements Serializable {

	private static final long serialVersionUID = 1L;

	private Component contenidor; // Contenedor gráfico de filas
	private String[] elementsFilera;
	private Treecell[] tb;
	private Treeitem titem;
	private ImageClic botoEsborrar;

	public TreeElementFill(Component contenidor, String[] elementsFilera) {
		this.contenidor = contenidor;
		this.elementsFilera = elementsFilera;
		tb = new Treecell[elementsFilera.length];
		for (int i = 0; i < elementsFilera.length; i++) {
			tb[i] = new Treecell(elementsFilera[i]);
		}
		Treecell tim = new Treecell();
		botoEsborrar = new es.caib.zkib.zkiblaf.ImageClic();
		botoEsborrar.setSrc("~./img/list-remove.gif"); //$NON-NLS-1$
		botoEsborrar.setAlign("right"); //$NON-NLS-1$
		// ElementFill yomismo = this;
		// im.addEventListener("onClick", new
		// org.zkoss.zk.ui.event.EventListener() {
		// public void onEvent(Event event) throws Exception {
		// eliminarElement(yomismo);
		// }
		// });
		titem = new Treeitem();
		Treerow fila = new Treerow();

		for (int i = 0; i < tb.length; i++) {
			fila.appendChild(tb[i]);
		}
		tim.appendChild(botoEsborrar);
		fila.appendChild(tim);
		titem.appendChild(fila);

		// Lo insertamos gráficamente
		contenidor.insertBefore(titem, null);
	}

	public Treeitem getFila() {
		return titem;
	}

	public void setTextoCampo(String texto, int campo) {
		tb[campo].setLabel(texto);
	}

	public Component getContenidor() {
		return contenidor;
	}

	public String getElement(int posicio) {
		return elementsFilera[posicio];
	}

	public ImageClic getBotoEsborrar() {
		return botoEsborrar;
	}

}
