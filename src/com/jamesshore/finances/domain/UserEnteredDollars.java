package com.jamesshore.finances.domain;

import com.jamesshore.finances.ui.*;

public class UserEnteredDollars extends Dollars {

	private String userText;
	private Dollars backingDollars;

	public UserEnteredDollars(String userText) {
		this.userText = userText;
		this.backingDollars = Dollars.parse(userText); // TODO: move parse method into this class
	}

	@Override
	public void render(Resources resources, RenderTarget target) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Dollars plus(Dollars dollars) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dollars minus(Dollars dollars) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dollars subtractToZero(Dollars dollars) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dollars percentage(double percent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dollars min(Dollars value2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return backingDollars.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;

		if (obj instanceof UserEnteredDollars) {
			UserEnteredDollars that = (UserEnteredDollars)obj;
			return this.backingDollars.equals(that.backingDollars);
		}
		else {
			Dollars that = (Dollars)obj;
			return this.backingDollars.equals(that);
		}
	}
}
