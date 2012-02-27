package com.jamesshore.finances.values;

import com.jamesshore.finances.ui.*;
import com.jamesshore.finances.util.*;

public class InvalidDollars extends Dollars {

	@Override
	public boolean isValid() {
		return false;
	}

	@Override
	protected double toCoreDataType() {
		throw new UnreachableCodeException();
	}

	@Override
	public Dollars plus(Dollars dollars) {
		return new InvalidDollars();
	}

	@Override
	public Dollars minus(Dollars dollars) {
		return new InvalidDollars();
	}

	@Override
	public Dollars subtractToZero(Dollars dollars) {
		return new InvalidDollars();
	}

	@Override
	public Dollars percentage(double percent) {
		return new InvalidDollars();
	}

	@Override
	public Dollars min(Dollars value2) {
		return new InvalidDollars();
	}

	public void render(Resources resources, RenderTarget target) {
		target.setIcon(resources.invalidDollarIcon(), "Invalid dollar amount");
		target.setText(null);
	}

	@Override
	public String toString() {
		return "$???";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		Dollars that = (Dollars)obj;

		return !that.isValid();
	}

	@Override
	public int hashCode() {
		return 13;
	}

}
