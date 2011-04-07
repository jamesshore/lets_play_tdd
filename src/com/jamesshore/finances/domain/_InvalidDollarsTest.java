package com.jamesshore.finances.domain;

import static org.junit.Assert.*;
import javax.swing.*;
import org.junit.*;
import com.jamesshore.finances.ui.*;

public class _InvalidDollarsTest {

	private InvalidDollars invalidA;
	private InvalidDollars invalidB;
	private ValidDollars valid;

	@Before
	public void setup() {
		invalidA = new InvalidDollars();
		invalidB = new InvalidDollars();
		valid = (ValidDollars)ValidDollars.create(13);
	}

	@Test
	public void isValid() {
		assertFalse(invalidA.isValid());
	}

	@Test
	public void plus() {
		assertEquals(new InvalidDollars(), invalidA.plus(invalidB));
		assertEquals(new InvalidDollars(), invalidA.plus(valid));
		assertEquals(new InvalidDollars(), valid.plus(invalidA));
	}

	@Test
	public void minus() {
		assertEquals(new InvalidDollars(), invalidA.minus(invalidB));
		assertEquals(new InvalidDollars(), invalidA.minus(valid));
		assertEquals(new InvalidDollars(), valid.minus(invalidB));
	}

	@Test
	public void subtractToZero() {
		assertEquals(new InvalidDollars(), invalidA.subtractToZero(invalidB));
		assertEquals(new InvalidDollars(), invalidA.subtractToZero(valid));
		assertEquals(new InvalidDollars(), valid.subtractToZero(invalidB));
	}

	@Test
	public void percentage() {
		assertEquals(new InvalidDollars(), invalidA.percentage(10));
	}

	@Test
	public void min() {
		assertEquals(new InvalidDollars(), invalidA.min(invalidB));
		assertEquals(new InvalidDollars(), invalidA.min(valid));
		assertEquals(new InvalidDollars(), valid.min(invalidB));
	}

	@Test
	public void rendersItself() {
		__RenderTargetStub target = new __RenderTargetStub();
		invalidA.render(new Resources(), target);

		// TODO: finish him!!
		// ImageIcon expectedIcon = new Resources().invalidDollarIcon();
		// ImageIcon actualIcon = (ImageIcon)label.getIcon();
		//
		// assertEquals("icon image", expectedIcon.getImage(), actualIcon.getImage());
		// assertEquals("icon description", "Invalid dollar amount", actualIcon.getDescription());
		// assertEquals("tooltip message", "Invalid dollar amount", label.getToolTipText());
	}

	@Test
	public void renderingShouldResetLabelToDefaultState() {
		JLabel label = new JLabel();
		label.setText("foodle");
		label.setToolTipText("bogus tooltip");

		invalidA.render(new Resources(), label);
		assertNull("should have no text", label.getText());
	}

	@Test
	public void valueObject() {
		assertEquals("$???", invalidA.toString());
		assertTrue("invalid dollars are always equal", invalidA.equals(invalidB));
		assertFalse("invalid dollars don't equal anything else", invalidA.equals(valid));
		assertFalse("shouldn't blow up when comparing to null", invalidA.equals(null));
		assertTrue("equal dollars should have same hash code", invalidA.hashCode() == invalidB.hashCode());
	}

}
