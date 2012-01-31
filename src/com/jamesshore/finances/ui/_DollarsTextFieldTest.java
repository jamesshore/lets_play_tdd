package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import java.awt.*;
import javax.swing.*;
import org.junit.*;
import com.jamesshore.finances.domain.*;

public class _DollarsTextFieldTest {

	private DollarsTextField field;
	private JPanel iconPanel;
	private JTextField textComponent;
	private JLabel iconComponent;

	@Before
	public void setup() {
		field = new DollarsTextField(new ValidDollars(42));

		// Note: for overlay layout to work properly, icon must be first. If you change
		// the way components are added to the container, be sure to do a visual check.
		Component[] components = field.getComponents();
		iconPanel = (JPanel)components[0];
		iconComponent = (JLabel)iconPanel.getComponents()[0];
		textComponent = (JTextField)components[1];
	}

	@Test
	public void layout() {
		Component[] components = field.getComponents();

		assertEquals("layout", OverlayLayout.class, field.getLayout().getClass());
		assertEquals("# of components", 2, components.length);

		FlowLayout iconLayout = (FlowLayout)iconPanel.getLayout();
		assertEquals("icon should be contained within a panel", JPanel.class, iconPanel.getClass());
		assertFalse("icon panel should be transparent", iconPanel.isOpaque());
		assertEquals("icon panel layout", FlowLayout.class, iconLayout.getClass());
		assertEquals("icon panel alignment", FlowLayout.RIGHT, iconLayout.getAlignment());

		assertEquals("layout should include icon", JLabel.class, iconComponent.getClass());
		assertEquals("layout should include text field", JTextField.class, textComponent.getClass());

		assertFalse("icon should be invisible by default", iconComponent.isVisible());
	}

	@Test
	public void canSetAndClearIcon() {
		ImageIcon icon = new ImageIcon();
		field.setIcon(icon);

		assertEquals("icon image", icon, iconComponent.getIcon());
		assertTrue("icon label should be visible", iconComponent.isVisible());

		field.setIcon(null);
		assertFalse("icon label should not be visible", iconComponent.isVisible());
	}

	@Test
	public void canGetAndSetArbitraryText() {
		field.setText("foo");
		assertEquals("foo", field.getText());
	}

	@Test
	public void settingForegroundColorChangesTextColor() {
		field.setForeground(Color.CYAN);
		assertEquals("can retrieve color", Color.CYAN, field.getForeground());
		assertEquals("actual text color changed", Color.CYAN, textComponent.getForeground());
	}

	@Test
	public void getForegroundColorIsBasedOnTextColorNotPanelColor() {
		textComponent.setForeground(Color.PINK);
		assertEquals("color is based on text color", Color.PINK, field.getForeground());
	}

	@Test
	public void textReflectsDollarAmountUponConstruction() {
		assertEquals("$42", field.getText());
	}

	@Test
	public void canRetrieveAmount() {
		assertEquals(new ValidDollars(42), field.getDollars());
	}

	@Test
	public void changingTextChangesDollarAmount() {
		field.setText("1024");
		assertEquals(new ValidDollars(1024), field.getDollars());
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
		field.setText("10");
		assertEquals("starts black", Color.BLACK, textComponent.getForeground());
		assertFalse("starts with no icon", iconComponent.isVisible());
		assertNull("starts with no tooltip", iconComponent.getToolTipText());

		field.setText("  -10 ");
		assertEquals("should not change text", "  -10 ", textComponent.getText());
		assertFalse("should change color", Color.BLACK.equals(textComponent.getForeground()));

		field.setText("xxx");
		assertTrue("should set icon", iconComponent.isVisible());
		assertNotNull("should set tooltip text", iconComponent.getToolTipText());
	}
}
