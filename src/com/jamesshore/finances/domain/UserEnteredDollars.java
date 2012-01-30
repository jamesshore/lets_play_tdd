package com.jamesshore.finances.domain;

import com.jamesshore.finances.ui.*;

public class UserEnteredDollars extends Dollars {

	public UserEnteredDollars(String userText) {
		// TODO Auto-generated constructor stub
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

}
