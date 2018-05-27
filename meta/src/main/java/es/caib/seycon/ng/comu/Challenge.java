package es.caib.seycon.ng.comu;

import org.ietf.jgss.GSSContext;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject(translatedName="Challenge", translatedPackage="com.soffid.iam.api")
public class Challenge {

    /** No puede iniciar si no dispone de tarjeta */
    public static final int CARD_REQUIRED = 1;
    /** Se debe solicitar la tarjeta si esta disponible */
    public static final int CARD_IFABLE = 2;
    /** Sólo se pedira la tarjeta si el cliente (IP origen) es desconocido */
    public static final int CARD_IFNEEDED = 3;
    /** Nunca se debe pedir la tarjeta */
    public static final int CARD_DISABLED = 4;

    public static final int TYPE_RMI = 0;
    public static final int TYPE_KERBEROS = 2;
    public static final int TYPE_CERT = 3;
    public static final int TYPE_PASSWORD = 3;

	@Nullable
    Password password;
    /** Tipo de identifcación **/
    private int type;
	@Nullable
    Usuari user;
	@Nullable
    String userKey;
	@Nullable
    Maquina host;
	@Nullable
    Maquina clientHost;
	@Nullable
    int centinelPort;
	@Nullable
    String otpHandler;
	@Nullable
    String cardNumber;
	@Nullable
    String cell;
	@Nullable
    String value;
    /** Timestamp del desafio */
	@Nullable
    java.sql.Timestamp timeStamp;
    /** Versión del cliente */
	@Nullable
    int clientVersion;
    /** Domini kerberos */
	@Nullable
    String kerberosDomain;
    /** Identificador llarg */
	@Nullable
    String challengeId;
    /** Contexte kerberos */
	@Nullable
    GSSContext kerberosContext;
	@Nullable
    private String domain;
    boolean closeOldSessions;
    boolean silent;
}
