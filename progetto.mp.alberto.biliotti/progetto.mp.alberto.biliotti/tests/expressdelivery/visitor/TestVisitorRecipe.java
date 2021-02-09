package expressdelivery.visitor;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import expressdelivery.concreteentities.Box;
import expressdelivery.concreteentities.ElectronicDevice;
import expressdelivery.concreteentities.ShockProofPackagingFoam;

public class TestVisitorRecipe {

	private VisitorRecipe visitor;

	@Before
	public void setUp() {
		visitor=new VisitorRecipe();
	}

	@Test
	public void testVisitElectronicDevice() {
		assertThat(new ElectronicDevice(21, 34, 54).accept(visitor)).isEqualTo("Electronic device that is worth: 54€ and occupies: 21.0 cube meters. ");
	}
	@Test
	public void testVisitFoam() {
		assertThat(new ShockProofPackagingFoam(23, 2).accept(visitor)).isEqualTo("Shockproof Packaging Foam which occupies: 23.0 cube meters. ");
	}
	@Test
	public void testVisitEmptyBox() {
		assertThat(new Box(23).accept(visitor)).isEqualTo("Box that wheigs: 23.0Kg and contains: ");
	}
	@Test
	public void testVisitBoxWithElectronicDeviceAndFoam() {
		Box box=new Box(23);
		box.addObjectToBeShipped(new ElectronicDevice(34, 34, 42));
		box.addObjectToBeShipped(new ElectronicDevice(34, 34, 10));
		box.addObjectToBeShipped(new ShockProofPackagingFoam(2, 34));
		assertThat(box.accept(visitor)).isEqualTo("Box that wheigs: 125.0Kg and contains: Electronic device that is worth: 42€ and occupies: 34.0 cube meters. Electronic device that is worth: 10€ and occupies: 34.0 cube meters. Shockproof Packaging Foam which occupies: 2.0 cube meters. ");
	}
	@Test
	public void testVisitBoxThatContainsAnotherBox() {
		Box box=new Box(1);
		Box anotherBox=new Box(0.5);
		box.addObjectToBeShipped(new ElectronicDevice(2, 34, 42));
		box.addObjectToBeShipped(anotherBox);
		anotherBox.addObjectToBeShipped(new ElectronicDevice(3, 34, 42));
		box.addObjectToBeShipped(new ElectronicDevice(4, 34, 10));
		box.addObjectToBeShipped(new ShockProofPackagingFoam(5, 34));
		box.accept(visitor);
		assertThat(box.accept(visitor)).isEqualTo("Box that wheigs: 137.5Kg and contains: Electronic device that is worth: 42€ and occupies: 2.0 cube meters. Box that wheigs: 34.5Kg and contains: Electronic device that is worth: 42€ and occupies: 3.0 cube meters. Electronic device that is worth: 10€ and occupies: 4.0 cube meters. Shockproof Packaging Foam which occupies: 5.0 cube meters. ");
	}
}
