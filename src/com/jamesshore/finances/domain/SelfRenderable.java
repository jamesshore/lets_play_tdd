package com.jamesshore.finances.domain;

import javax.swing.*;
import com.jamesshore.finances.ui.*;

public interface SelfRenderable {

	public void render(Resources resources, RenderTarget target);

	public void render(Resources resources, JLabel label); // TODO: delete me

}
