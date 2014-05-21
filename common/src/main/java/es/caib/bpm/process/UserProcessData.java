package es.caib.bpm.process;

import org.jbpm.context.exe.VariableInstance;
import org.jbpm.graph.exe.ProcessInstance;

/**
 * Classe abstracta per poder guardar a base de dades les instàncies de procés
 * Alejandro Usero Ruiz - 10/10/2012
 * 
 * @author u88683
 * 
 */
public abstract class UserProcessData {

	public UserProcessData() {}

	public abstract void updateProcessInstance(ProcessInstance processInstance) throws Exception;
	public abstract void addVariableInstance(VariableInstance variableInstance) throws Exception;
	public abstract void deleteProcessInstance(ProcessInstance processInstance) throws Exception;

}
