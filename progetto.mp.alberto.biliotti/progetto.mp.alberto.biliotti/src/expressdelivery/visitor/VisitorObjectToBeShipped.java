package expressdelivery.visitor;

import expressdelivery.concreteentities.Box;
import expressdelivery.concreteentities.ElectronicDevice;
import expressdelivery.concreteentities.ShockProofPackagingFoam;

public interface VisitorObjectToBeShipped<T> {

	public T visitBox(Box box);

	public T visitElectronicDevice(ElectronicDevice electronicDevice);

	public T visitShockProofPackagingFoam(ShockProofPackagingFoam shockProofPackagingFoam);

}
