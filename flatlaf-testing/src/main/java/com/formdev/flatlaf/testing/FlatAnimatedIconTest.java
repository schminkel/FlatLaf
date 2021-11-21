/*
 * Copyright 2020 FormDev Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.formdev.flatlaf.testing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.*;
import com.formdev.flatlaf.icons.FlatAnimatedIcon;
import com.formdev.flatlaf.util.AnimatedIcon;
import com.formdev.flatlaf.util.ColorFunctions;
import net.miginfocom.swing.*;

/**
 * @author Karl Tauber
 */
public class FlatAnimatedIconTest
	extends FlatTestPanel
{
	private static final Color CHART_RADIO_BUTTON_1 = Color.blue;
	private static final Color CHART_RADIO_BUTTON_2 = Color.red;
	private static final Color CHART_RADIO_BUTTON_3 = Color.green;
	private static final Color CHART_CHECK_BOX_1 = Color.magenta;
	private static final Color CHART_CHECK_BOX_2 = Color.orange;

	private static final String CHART_COLOR_KEY = "chartColor";

	public static void main( String[] args ) {
		SwingUtilities.invokeLater( () -> {
			FlatTestFrame frame = FlatTestFrame.create( args, "FlatAnimatedIconTest" );
			frame.showFrame( FlatAnimatedIconTest::new );
		} );
	}

	FlatAnimatedIconTest() {
		initComponents();

		AnimatedRadioButtonIcon radioIcon = new AnimatedRadioButtonIcon();
		radioButton1.setIcon( radioIcon );
		radioButton2.setIcon( radioIcon );
		radioButton3.setIcon( radioIcon );

		checkBox1.setIcon( new AnimatedSwitchIcon() );
		checkBox2.setIcon( new AnimatedMinimalTestIcon() );

		radioButton1.putClientProperty( CHART_COLOR_KEY, CHART_RADIO_BUTTON_1 );
		radioButton2.putClientProperty( CHART_COLOR_KEY, CHART_RADIO_BUTTON_2 );
		radioButton3.putClientProperty( CHART_COLOR_KEY, CHART_RADIO_BUTTON_3 );
		checkBox1.putClientProperty( CHART_COLOR_KEY, CHART_CHECK_BOX_1 );
		checkBox2.putClientProperty( CHART_COLOR_KEY, CHART_CHECK_BOX_2 );

		radioButton1ChartColor.setForeground( CHART_RADIO_BUTTON_1 );
		radioButton2ChartColor.setForeground( CHART_RADIO_BUTTON_2 );
		radioButton3ChartColor.setForeground( CHART_RADIO_BUTTON_3 );
		checkBox1ChartColor.setForeground( CHART_CHECK_BOX_1 );
		checkBox2ChartColor.setForeground( CHART_CHECK_BOX_2 );
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		radioButton1 = new JRadioButton();
		radioButton1ChartColor = new FlatAnimatorTest.JChartColor();
		lineChartPanel = new FlatAnimatorTest.LineChartPanel();
		radioButton2 = new JRadioButton();
		radioButton2ChartColor = new FlatAnimatorTest.JChartColor();
		radioButton3 = new JRadioButton();
		radioButton3ChartColor = new FlatAnimatorTest.JChartColor();
		checkBox1 = new JCheckBox();
		checkBox1ChartColor = new FlatAnimatorTest.JChartColor();
		checkBox2 = new JCheckBox();
		checkBox2ChartColor = new FlatAnimatorTest.JChartColor();
		durationLabel = new JLabel();
		durationField = new JSpinner();

		//======== this ========
		setLayout(new MigLayout(
			"insets dialog,hidemode 3",
			// columns
			"[]" +
			"[fill]para" +
			"[grow,fill]",
			// rows
			"[]" +
			"[]" +
			"[]para" +
			"[]" +
			"[]" +
			"[grow]" +
			"[]"));

		//---- radioButton1 ----
		radioButton1.setText("radio 1");
		radioButton1.setSelected(true);
		add(radioButton1, "cell 0 0");
		add(radioButton1ChartColor, "cell 1 0");
		add(lineChartPanel, "cell 2 0 1 6");

		//---- radioButton2 ----
		radioButton2.setText("radio 2");
		add(radioButton2, "cell 0 1");
		add(radioButton2ChartColor, "cell 1 1");

		//---- radioButton3 ----
		radioButton3.setText("radio 3");
		add(radioButton3, "cell 0 2");
		add(radioButton3ChartColor, "cell 1 2");

		//---- checkBox1 ----
		checkBox1.setText("switch");
		add(checkBox1, "cell 0 3");
		add(checkBox1ChartColor, "cell 1 3");

		//---- checkBox2 ----
		checkBox2.setText("minimal");
		add(checkBox2, "cell 0 4");
		add(checkBox2ChartColor, "cell 1 4");

		//---- durationLabel ----
		durationLabel.setText("Duration:");
		add(durationLabel, "cell 0 6 3 1");

		//---- durationField ----
		durationField.setModel(new SpinnerNumberModel(200, 100, null, 50));
		add(durationField, "cell 0 6 3 1");

		//---- buttonGroup1 ----
		ButtonGroup buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(radioButton1);
		buttonGroup1.add(radioButton2);
		buttonGroup1.add(radioButton3);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JRadioButton radioButton1;
	private FlatAnimatorTest.JChartColor radioButton1ChartColor;
	private FlatAnimatorTest.LineChartPanel lineChartPanel;
	private JRadioButton radioButton2;
	private FlatAnimatorTest.JChartColor radioButton2ChartColor;
	private JRadioButton radioButton3;
	private FlatAnimatorTest.JChartColor radioButton3ChartColor;
	private JCheckBox checkBox1;
	private FlatAnimatorTest.JChartColor checkBox1ChartColor;
	private JCheckBox checkBox2;
	private FlatAnimatorTest.JChartColor checkBox2ChartColor;
	private JLabel durationLabel;
	private JSpinner durationField;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	//---- class AnimatedRadioButtonIcon --------------------------------------

	/**
	 * Experimental radio button icon that:
	 * - fades icon color from off to on color
	 * - animates size of center dot from zero to full size
	 */
	private class AnimatedRadioButtonIcon
		extends FlatAnimatedIcon
	{
		private static final int SIZE = 16;
		private static final int BORDER_SIZE = 2;
		private static final int DOT_SIZE = 8;

		private final Color offColor = Color.lightGray;
		private final Color onColor = Color.red;

		public AnimatedRadioButtonIcon() {
			super( SIZE, SIZE, null );
		}

		@Override
		public void paintIconAnimated( Component c, Graphics g, int x, int y, float animatedValue ) {
			Color color = ColorFunctions.mix( onColor, offColor, animatedValue );

			// border
			g.setColor( color );
			g.fillOval( 0, 0, SIZE, SIZE );

			// background
			g.setColor( c.getBackground() );
			int bwh = SIZE - (BORDER_SIZE * 2);
			g.fillOval( BORDER_SIZE, BORDER_SIZE, bwh, bwh );

			// dot
			float dotDiameter = DOT_SIZE * animatedValue;
			float xy = (SIZE - dotDiameter) / 2f;
			g.setColor( color );
			((Graphics2D)g).fill( new Ellipse2D.Float( xy, xy, dotDiameter, dotDiameter ) );

			if( animatedValue != 0 && animatedValue != 1 ) {
				Color chartColor = (Color) ((JComponent)c).getClientProperty( CHART_COLOR_KEY );
				lineChartPanel.lineChart.addValue( animatedValue, chartColor );
			}
		}

		@Override
		public float getValue( Component c ) {
			return ((JRadioButton)c).isSelected() ? 1 : 0;
		}

		@Override
		public int getAnimationDuration() {
			return (Integer) durationField.getValue();
		}
	}

	//---- class AnimatedSwitchIcon -------------------------------------------

	public class AnimatedSwitchIcon
		extends FlatAnimatedIcon
	{
		private final Color offColor = Color.lightGray;
		private final Color onColor = Color.red;

		public AnimatedSwitchIcon() {
			super( 28, 16, null );
		}

		@Override
		public void paintIconAnimated( Component c, Graphics g, int x, int y, float animatedValue ) {
			Color color = ColorFunctions.mix( onColor, offColor, animatedValue );

			g.setColor( color );
			g.fillRoundRect( x, y, width, height, height, height );

			int thumbSize = height - 4;
			float thumbX = x + 2 + ((width - 4 - thumbSize) * animatedValue);
			int thumbY = y + 2;
			g.setColor( Color.white );
			((Graphics2D)g).fill( new Ellipse2D.Float( thumbX, thumbY, thumbSize, thumbSize ) );

			if( animatedValue != 0 && animatedValue != 1 ) {
				Color chartColor = (Color) ((JComponent)c).getClientProperty( CHART_COLOR_KEY );
				lineChartPanel.lineChart.addValue( animatedValue, chartColor );
			}
		}

		@Override
		public float getValue( Component c ) {
			return ((AbstractButton)c).isSelected() ? 1 : 0;
		}

		@Override
		public int getAnimationDuration() {
			return (Integer) durationField.getValue();
		}
	}

	//---- class AnimatedMinimalTestIcon --------------------------------------

	/**
	 * Minimal example for an animated icon.
	 */
	private class AnimatedMinimalTestIcon
		implements AnimatedIcon
	{
		@Override
		public int getIconWidth() {
			return 100;
		}

		@Override
		public int getIconHeight() {
			return 20;
		}

		@Override
		public void paintIconAnimated( Component c, Graphics g, int x, int y, float animatedValue ) {
			int w = getIconWidth();
			int h = getIconHeight();

			g.setColor( Color.red );
			g.drawRect( x, y, w - 1, h - 1 );
			g.fillRect( x, y, Math.round( w * animatedValue ), h );

			if( animatedValue != 0 && animatedValue != 1 ) {
				Color chartColor = (Color) ((JComponent)c).getClientProperty( CHART_COLOR_KEY );
				lineChartPanel.lineChart.addValue( animatedValue, chartColor );
			}
		}

		@Override
		public float getValue( Component c ) {
			return ((AbstractButton)c).isSelected() ? 1 : 0;
		}

		@Override
		public int getAnimationDuration() {
			return (Integer) durationField.getValue();
		}
	}
}
