package es.caib.bpm.entity;

import java.lang.reflect.Constructor;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;
import org.hibernate.event.PreUpdateEvent;
import org.jbpm.taskmgmt.exe.PooledActor;
import org.jbpm.taskmgmt.exe.TaskInstance;

import es.caib.bpm.mail.EmailConfig;
import es.caib.bpm.mail.Mail;
import es.caib.bpm.toolkit.EJBContainer;
import es.caib.bpm.vo.ConfigParameterVO;

public class TaskNotificationEventListener implements PreInsertEventListener{
	
	protected String notifyTaskAssignment=null;
	private static String emailConfigClassName=null;
	
	protected void initialize() {
		if(notifyTaskAssignment==null){
			ConfigParameterVO param = null;
	        notifyTaskAssignment="false";
		        try {
		        	
					param = EJBContainer.getBPMConfigBean().findFirstByAppKey("BPM","es.caib.bpm.notifyTaskAssignment");
				} catch (Exception e) {
				}
		        if(param!=null)
		        	notifyTaskAssignment=param.getValue();
		}
	}
	private void notifyAssignedUsers(PreInsertEvent event) {
		Object entity=event.getEntity();
		if (entity instanceof TaskInstance){
			try{
				event.getSource().getPersistenceContext().setFlushing(true);
				initialize();
			
				if( notifyTaskAssignment!=null && "true".equals(notifyTaskAssignment)){
					
					/* INICIO:
					Enviamos correo de asignación a tareas pendientes no iniciadas.
					Este código va aquí para que la asignación ya esté realizada.
					si queremos que no se notifique una tarea síncrona del inicio del proceso, hay que establecer un evento que inicie la tarea por parte del usuario que inicia el procedimiento:
						<task-node name="Seleccio revisor">
							<task name="Seleccio revisor">
								<assignment expression="variable(initiator)"></assignment>
							</task>
							<event type="task-create">
								<script name="Iniciar tasca auto">
									taskInstance.start(initiator);
								</script>
							</event>
							...
						</task-node>
					*/
					
					TaskInstance task = (TaskInstance) entity;
					
					if(task.getStart()==null) {
						
						if(task.getActorId()!=null){
							sendAssignmentNotification(task.getActorId(), task);
						
						}else if(task.getPooledActors()!=null && !(task.getPooledActors().isEmpty())){
							Iterator<PooledActor> it=task.getPooledActors().iterator();
							while(it.hasNext()){
								PooledActor actor=it.next();
								sendAssignmentNotification(actor.getActorId(), task);
							}
						
						}else if(task.getSwimlaneInstance()!=null && task.getSwimlaneInstance().getActorId()!=null){
							sendAssignmentNotification(task.getSwimlaneInstance().getActorId(), task);
						
						}else if(task.getSwimlaneInstance()!=null && task.getSwimlaneInstance().getPooledActors()!=null && !(task.getSwimlaneInstance().getPooledActors().isEmpty())){
							Iterator<PooledActor> it=task.getSwimlaneInstance().getPooledActors().iterator();
							while(it.hasNext()){
								PooledActor actor=it.next();
								sendAssignmentNotification(actor.getActorId(), task);
							}
						}
		
					}
				}
			}finally{
				event.getSource().getPersistenceContext().setFlushing(false);
			}
		}	
	}
	/**
	 * Envia un correo de notificación al email configurado por el usuario al que se le asigna la tarea.
	 * Este método no puede lanzar excepciones, así que lo indica por LOG
	 * Delegamos la personalización del correo a otra clase para que se pueda obtener más información del usuario de la que se puede obtener aquí,
	 * ya que jbpm sólo dispone del código de usuario y poca cosa más. 
	 * @param actorId
	 */
	private void sendAssignmentNotification(String actorId,TaskInstance task){
		
		try{

			if(emailConfigClassName==null){
				ConfigParameterVO param = null;
				emailConfigClassName="es.caib.bpm.mail.TaskAssignmentNotificationEmailConfig";
				try {
					param = EJBContainer.getBPMConfigBean().findFirstByAppKey("BPM","es.caib.bpm.taskAssignmentNotificationEmailConfigClass");
				} catch (Exception e) {}
		        if(param!=null) emailConfigClassName=param.getValue();
			}
			
	        //cojemos el classloader del thread para evitar las restricciones del classpath de el ejb en el que está empaquetada esta clase.
			Class emailConfigClass=Thread.currentThread().getContextClassLoader().loadClass(emailConfigClassName);
			Constructor constructor=emailConfigClass.getConstructor(
					new Class[] {
							TaskInstance.class,
							String[].class 
					}
				);
			EmailConfig emailConfig=(EmailConfig)constructor.newInstance(task,new String[]{actorId});

			sendMail(emailConfig.getEmails(),emailConfig.getSubject(),emailConfig.getText());
			
		}catch(Exception e){
			Logger.getLogger(this.getClass().getName()).error("Error grave al enviar correo de notificacion de asignacion de tarea de usuario: "+e.getMessage(),e);
		}
				
	}

	private void sendMail(String[] emails, String subject, String text) {
		String formattedEmails="";
		
		for(int i=0;i<emails.length;i++){
			if(i==emails.length-1){
				formattedEmails+=emails[i];
			}else{
				formattedEmails+=emails[i]+",";
			}
		}
			
		new Mail(null,"",formattedEmails,subject,text).send();
		
	}
	
	public boolean onPreInsert(PreInsertEvent event) {
		notifyAssignedUsers(event);
		return false;
	}

}
