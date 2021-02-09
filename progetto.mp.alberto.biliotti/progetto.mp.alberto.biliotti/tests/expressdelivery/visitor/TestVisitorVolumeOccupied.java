package expressdelivery.visitor;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;

import expressdelivery.concreteentities.Box;
import expressdelivery.concreteentities.ElectronicDevice;
import expressdelivery.concreteentities.ShockProofPackagingFoam;

public class TestVisitorVolumeOccupied {
	
	private VisitorVolumeOccupied visitor;

	@Before
	public void setUp() {
		visitor=new VisitorVolumeOccupied();
	}

	@Test
	public void testVisitElectronicDevice() {
		assertThat(new ElectronicDevice(21, 34, 54).accept(visitor)).isEqualTo(21);
	}
	@Test
	public void testVisitFoam() {
		assertThat(new ShockProofPackagingFoam(23, 2).accept(visitor)).isEqualTo(23);
	}
	@Test
	public void testVisitEmptyBox() {
		assertThat(new Box(23).accept(visitor)).isEqualTo(0);
	}
	@Test
	public void testVisitBoxWithElectronicDeviceAndFoam() {
		Box box=new Box(23);
		box.addObjectToBeShipped(new ElectronicDevice(34, 34, 42));
		box.addObjectToBeShipped(new ElectronicDevice(34, 34, 10));
		box.addObjectToBeShipped(new ShockProofPackagingFoam(2, 34));
		assertThat(box.accept(visitor)).isEqualTo(70);
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
		assertThat(box.accept(visitor)).isEqualTo(14);
	}

}
