package expressdelivery.concreteentities;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import expressdelivery.abstractclassesforentities.Contained;

public class TestBox {
	
	private Box box;

	@Before
	public void setUp(){
		box=new Box(0.5);
	}

	@Test
	public void testAddNull() {
		try {
			box.addObjectToBeShipped(null);
			fail("The method addObjectToBeShipped should throw a NullPointerException when null is passed!");
		}catch(NullPointerException exception) {
			assertThat(exception).hasMessage("You can't put null element in a box!");
		}
	}
	@Test
	public void testAddPCAndFoam() {
		Contained pc= new ElectronicDevice(23, 250, 234);
		box.addObjectToBeShipped(pc);
		ShockProofPackagingFoam shockProofPackagingFoam= new ShockProofPackagingFoam(12, 34);
		assertThat(box.addObjectToBeShipped(shockProofPackagingFoam)).isTrue();
		assertThat(box.getBoxContent()).contains(pc, shockProofPackagingFoam);
	}
	@Test
	public void testAddBoxThatContainsPCAndFoam() {
		Box anotherBox = new Box(12);
		Contained pc= new ElectronicDevice(23, 250, 234);
		anotherBox.addObjectToBeShipped(pc);
		ShockProofPackagingFoam shockProofPackagingFoam= new ShockProofPackagingFoam(12, 34);
		anotherBox.addObjectToBeShipped(shockProofPackagingFoam);
		assertThat(box.addObjectToBeShipped(anotherBox)).isTrue();
		assertThat(box.getBoxContent()).containsExactly(anotherBox);
		assertThat(anotherBox.getBoxContent()).containsExactly(pc, shockProofPackagingFoam);
	}
	@Test
	public void testIterator() {
		ShockProofPackagingFoam shockProofPackagingFoam= new ShockProofPackagingFoam(12, 34);
		Contained pc= new ElectronicDevice(23, 250, 234);
		Box anotherBox = new Box(12);
		box.getBoxContent().add(pc);
		box.getBoxContent().add(anotherBox);
		box.getBoxContent().add(shockProofPackagingFoam);
		assertThat(box.iteatorForSons()).toIterable().containsExactly(pc,anotherBox,shockProofPackagingFoam);
	}
	@Test
	public void testRemoveFromBox() {
		Contained pc= new ElectronicDevice(23, 250, 234);
		box.getBoxContent().add(pc);
		ShockProofPackagingFoam shockProofPackagingFoam= new ShockProofPackagingFoam(12, 34);
		box.getBoxContent().add(shockProofPackagingFoam);
		box.removeObjectToBeShipped(pc);
		assertThat(box.getBoxContent()).contains(shockProofPackagingFoam);
	}
	@Test
	public void testCalculateTotalWeight() {

			ShockProofPackagingFoam shockProofPackagingFoam= new ShockProofPackagingFoam(12, 34);
			Contained pc= new ElectronicDevice(23, 250, 234);
			Box anotherBox = new Box(12);
			box.getBoxContent().add(pc);
			box.getBoxContent().add(anotherBox);
			box.getBoxContent().add(shockProofPackagingFoam);
			assertThat(box.calculateTotalWeight()).isEqualTo(296.5);
	}

}
