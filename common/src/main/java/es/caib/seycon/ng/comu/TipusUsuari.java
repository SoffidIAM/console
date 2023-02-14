package es.caib.seycon.ng.comu;

public class TipusUsuari extends AbstractTipusUsuari {
	@Override
	public void setUnmanaged(boolean unmanaged) {
		super.setUnmanaged(unmanaged);
		super.setManaged(!unmanaged);
	}

	@Override
	public void setManaged(boolean managed) {
		super.setManaged(managed);
		super.setUnmanaged(!managed);
	}
}
