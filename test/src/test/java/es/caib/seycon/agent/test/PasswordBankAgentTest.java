package es.caib.seycon.agent.test;

import junit.framework.TestCase;
import es.caib.seycon.UserInfo;
import es.caib.seycon.impl.agent.PasswordBankAgent;

public class PasswordBankAgentTest extends TestCase {

	/**
	 * @param args
	 */
	protected void setUp() throws Exception {
		
/*		try {	
			String urlString = "http://10.215.3.98/webservice/administration.ws.php";
			String username = "demo";
			String password = "demo";
			String network = "PLAIN";
			String repositoryId = "REPOSITORY_1";
			PasswordBankAgent passwordBankAgent = new PasswordBankAgent(
					new String[] { urlString, username, password, network,
							repositoryId });
			passwordBankAgent.updateSessionId();
			System.out.println("Session id: " + passwordBankAgent.sessionId);
			passwordBankAgent.updateTicket();
			System.out.println("Ticket: " + passwordBankAgent.ticketId);
			
			String user = null;
			String group = null;
			RoleInfo roleInfo = null;
			UserInfo userInfo = null;
			String[] grups = null;
						
		
			user  = "test";
			boolean existeixUsuari = passwordBankAgent.userExists(user);
			System.out
					.println("Esisteix usuari '" + user + "': " + existeixUsuari);
			user  = "usuariNoExistent";
			existeixUsuari = passwordBankAgent.userExists(user);
			System.out
					.println("Esisteix usuari '" + user + "': " + existeixUsuari);
			
			
			group = "grupo1";
			boolean existeixGrup = passwordBankAgent.groupExists(group);
			System.out
					.println("Esisteix grup '" + group + "': " + existeixGrup);
			group = "grupoQueNoExiste";
			existeixGrup = passwordBankAgent.groupExists(group);
			System.out
					.println("Esisteix grup '" + group + "': " + existeixGrup);
			
			
			user = "demo";
			grups = passwordBankAgent.getGroupIDs(user);
			for(int i = 0; i < grups.length;i++){
				System.out.println("Usuari '" + user + "' te grup '" + grups[i] + "'");				
			}
			
			
			userInfo = new UserInfo();
			userInfo.FirstFamilyName = "carre";
			userInfo.SecondFamilyName = "cardona";
			userInfo.Name = "pau";
			userInfo.User = "pau.carre";
			passwordBankAgent.createBaicDataUser(userInfo);
			*/
				/*
			roleInfo = new RoleInfo();
			roleInfo.applicationName = "SEYCON";
			roleInfo.name = "SC_ADMINISTRADOR";
			roleInfo.db = "passwordbank";
			passwordBankAgent.createGroupPasswordBank(roleInfo);

			user = "pau.carre";
			group = "SC_ADMINISTRADOR";
			passwordBankAgent.addUserToGroup(user, group);
			grups = passwordBankAgent.getGroupIDs(user);
			for(int i = 0; i < grups.length;i++){
				System.out.println("Usuari '" + user + "' te grup '" + grups[i] + "'");				
			}
						
						
			user = "pau.carre";
			group = "SC_ADMINISTRADOR";
			passwordBankAgent.removeUserFromGroup(user, group);
			grups = passwordBankAgent.getGroupIDs(user);
			for(int i = 0; i < grups.length;i++){
				System.out.println("Usuari '" + user + "' te grup '" + grups[i] + "'");				
			}
			
			
			userInfo = new UserInfo();
			userInfo.FirstFamilyName = "FirstFamilyName";
			userInfo.SecondFamilyName = "SecondFamilyName";
			userInfo.Name = "Name";
			userInfo.User = "pau.carre";
			passwordBankAgent.updateBasicUserData(userInfo);			
			
			roleInfo = new RoleInfo();
			roleInfo.applicationName = "SEYCON_III";
			roleInfo.name = "SC_ADMINISTRADOR";
			roleInfo.db = "passwordbank_III";
			passwordBankAgent.updateGroupPasswordBank(roleInfo);
			
		} catch (Exception e) {
			System.err.println("Error: ");
			e.printStackTrace();
		}
		System.out
				.println("Finalizacion de la ejecucion del agente PasswordBank");
				*/
	}

	public void testDoSomething() {

	}

}
