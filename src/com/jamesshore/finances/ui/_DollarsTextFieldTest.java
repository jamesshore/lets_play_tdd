package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import java.awt.*;
import javax.swing.*;
import org.junit.*;
import com.jamesshore.finances.domain.*;

public class _DollarsTextFieldTest {

	private DollarsTextField field;

	@Before
	public void setup() {
		field = new DollarsTextField(ValidDollars.create(42));
	}

	@Test
	public void layout() {
		Component[] components = field.getComponents();

		assertEquals("layout", OverlayLayout.class, field.getLayout().getClass());
		assertEquals("# of components", 2, components.length);
		assertEquals("layout should include text field", JTextField.class, components[0].getClass());
		assertEquals("layout should include icon", JLabel.class, components[1].getClass());
		assertFalse("icon should be invisible by default", components[1].isVisible());
	}

	@Test
	public void canSetAndClearIcon() {
		ImageIcon icon = new ImageIcon();
		field.setIcon(icon);

		JLabel iconLabel = (JLabel)field.getComponents()[1];
		assertEquals("icon image", icon, iconLabel.getIcon());
		assertTrue("icon label should be visible", iconLabel.isVisible());

		field.setIcon(null);
		assertFalse("icon label should not be visible", iconLabel.isVisible());
	}

	@Test
	public void canGetAndSetArbitraryText() {
		field.setText("foo");
		assertEquals("foo", field.getText());
	}

	@Test
	public void textReflectsDollarAmountUponConstruction() {
		assertEquals("$42", field.getText());
	}

	@Test
	public void canRetrieveAmount() {
		assertEquals(ValidDollars.create(42), field.getDollars());
	}

	@Test
	public void changingTextChangesDollarAmount() {
		field.setText("1024");
		assertEquals(ValidDollars.create(1024), field.getDollars());
	}

	@Test
	public void canCallFunctionWhenTextChanges() {
		final boolean[] changed = { false };
		DollarsTextField.ChangeListener listener = new DollarsTextField.ChangeListener() {
			public void textChanged() {
				changed[0] = true;
			}
		};

		field.addTextChangeListener(listener);
		assertFalse("textChanged() should not have been called yet", changed[0]);
		field.setText("1000");
		assertTrue("textChanged() should have been called", changed[0]);
	}

	@Test
	public void fieldIsRenderedByDomainClassWhenTextChanges() throws Exception {
		JLabel iconLabel = (JLabel)field.getComponents()[1];

		field.setText("10");
		assertEquals("starts black", Color.BLACK, field.getForeground());
		assertFalse("starts with no icon", iconLabel.isVisible());

		field.setText("  -10 ");
		assertEquals("should not change text", "  -10 ", field.getText());
		assertFalse("should change color", Color.BLACK.equals(field.getForeground()));

		field.setText("xxx");
		assertTrue("should set icon", iconLabel.isVisible());
	}
}
