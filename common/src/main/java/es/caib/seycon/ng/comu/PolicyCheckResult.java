package es.caib.seycon.ng.comu;

public class PolicyCheckResult extends com.soffid.iam.api.PolicyCheckResult {
    public PolicyCheckResult(int value) {
    	super (value);
    }

    public PolicyCheckResult(int value, String extraInfo) {
    	super (value, extraInfo);
    }

	public static PolicyCheckResult toPolicyCheckResult(
			com.soffid.iam.api.PolicyCheckResult pcr)
	{
		return new PolicyCheckResult (pcr.getReasonCode(), pcr.getReason());
	}
}
