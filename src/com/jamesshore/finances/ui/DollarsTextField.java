package com.jamesshore.finances.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import com.jamesshore.finances.domain.*;

// If you want to subclass this class, it's okay to remove the 'final' designator, but be careful of race 
// conditions with the event handler in the constructor. It could execute before the subclass constructor.
public final class DollarsTextField extends JTextField {

	private static final long serialVersionUID = 1L;

	public DollarsTextField(Dollars initialValue) {
		this.setText(initialValue.toString());
		addTextChangeListener(new ChangeListener() {
			public void textChanged() {
				getDollars().render(new Resources(), new DollarsTextFieldRenderTargetAdapter(DollarsTextField.this));
			}
		});
	}

	public Dollars getDollars() {
		return Dollars.parse(getText());
	}

	public void addTextChangeListener(final ChangeListener listener) {
		this.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				render();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				render();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				render();
			}

			private void render() {
				listener.textChanged();
			}
		});
	}

	public static interface ChangeListener {
		public void textChanged();
	}

	private static class DollarsTextFieldRenderTargetAdapter implements RenderTarget {
		private DollarsTextField field;

		public DollarsTextFieldRenderTargetAdapter(DollarsTextField field) {
			this.field = field;
		}

		@Override
		public void setText(String text) {
			// this space intentionally left blank. It's the "NO" reformat text adapter.
		}

		@Override
		public void setIcon(Icon icon) {
			// TODO: implement?
		}

		@Override
		public void setToolTipText(String text) {
			// TODO: implement?
		}

		@Override
		public void setForegroundColor(Color color) {
			field.setForeground(color);
		}
	}
}
