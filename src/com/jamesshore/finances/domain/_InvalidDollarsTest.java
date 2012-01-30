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
	public void flipSign() {
		assertEquals(new InvalidDollars(), invalidA.flipSign());
	}

	@Test
	public void rendersItself() {
		__RenderTargetStub target = new __RenderTargetStub();
		invalidA.render(new Resources(), target);

		ImageIcon expectedIcon = new Resources().invalidDollarIcon();
		ImageIcon actualIcon = (ImageIcon)target.icon;

		assertEquals("icon image", expectedIcon.getImage(), actualIcon.getImage());
		assertEquals("icon description", "Invalid dollar amount", actualIcon.getDescription());
		assertEquals("tooltip message", "Invalid dollar amount", target.toolTipText);
	}

	@Test
	public void renderingShouldResetLabelToDefaultState() {
		__RenderTargetStub target = new __RenderTargetStub();
		target.text = "foodle";

		invalidA.render(new Resources(), target);
		assertNull("should have no text", target.text);
	}

	@Test
	public void valueObject() {
		assertEquals("$???", invalidA.toString());
		assertTrue("invalid dollars should always be equal", invalidA.equals(invalidB));
		assertFalse("invalid dollars shouldn't equal valid dollars", invalidA.equals(valid));
		assertFalse("invalid dollars shouldn't equal valid user-entered dollars", invalidA.equals(new UserEnteredDollars("1")));
		assertTrue("invalid dollars should equal invalid user-entered dollars", invalidA.equals(new UserEnteredDollars("xxx")));

		assertFalse("shouldn't blow up when comparing to null", invalidA.equals(null));
		assertTrue("equal dollars should have same hash code", invalidA.hashCode() == invalidB.hashCode());
	}

}
