package expressdelivery.visitor;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import expressdelivery.concreteentities.Box;
import expressdelivery.concreteentities.ElectronicDevice;
import expressdelivery.concreteentities.ShockProofPackagingFoam;

public class TestVisitorTotalPrice {
	
	private VisitorTotalPrice visitorTotalPrice;

	@Before
	public void setUp() {
		visitorTotalPrice=new VisitorTotalPrice();
	}
	
	@Test
	public void testVisitElectronicDevice() {
		assertThat(new ElectronicDevice(21, 34, 54).accept(visitorTotalPrice)).isEqualTo(54);
	}
	@Test
	public void testVisitFoam() {
		assertThat(new ShockProofPackagingFoam(23, 2).accept(visitorTotalPrice)).isEqualTo(0);
	}
	@Test
	public void testVisitEmptyBox() {
		assertThat(new Box(23).accept(visitorTotalPrice)).isEqualTo(0);
	}
	@Test
	public void testVisitBoxWithElectronicDeviceAndFoam() {
		Box box=new Box(23);
		box.addObjectToBeShipped(new ElectronicDevice(34, 34, 42));
		box.addObjectToBeShipped(new ElectronicDevice(34, 34, 10));
		box.addObjectToBeShipped(new ShockProofPackagingFoam(23, 34));
		assertThat(box.accept(visitorTotalPrice)).isEqualTo(52);
	}
	@Test
	public void testVisitBoxThatContainsAnotherBox() {
		Box box=new Box(23);
		Box anotherBox=new Box(21);
		box.addObjectToBeShipped(new ElectronicDevice(34, 34, 42));
		box.addObjectToBeShipped(anotherBox);
		anotherBox.addObjectToBeShipped(new ElectronicDevice(34, 34, 42));
		box.addObjectToBeShipped(new ElectronicDevice(34, 34, 10));
		box.addObjectToBeShipped(new ShockProofPackagingFoam(23, 34));
		box.accept(visitorTotalPrice);
		assertThat(box.accept(visitorTotalPrice)).isEqualTo(94);
	}
}
