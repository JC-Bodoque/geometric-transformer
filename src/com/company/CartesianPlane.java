package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CartesianPlane extends JPanel {
    public CartesianPlane() {
        setBackground(Color.WHITE);
    }

    public void drawPlane(){
        int x = this.getWidth(), y = this.getHeight();

        //Cuadrícula
        for (int i = 10; i <= x || i <= y; i += 10) {
            g.setColor(new Color(233, 233, 233));
            g.drawLine(0, i, x, i);
            g.drawLine(i, 0, i, y);
        }
        //Plano
        g.setColor(Color.BLACK);
        g.drawLine((x / 2) - 60, 0, (x / 2) - 60, y);
        g.drawLine(0, (y / 2) - 10, x, (y / 2) - 10);
        g.drawString("(0,0)", (x / 2) - 72, (y / 2) + 5);

        //Separación
        String num;
        //x
        for (int i = 20, contx = -16; i < x; i += 20, contx++) {

            g.setColor(Color.DARK_GRAY);
            g.drawLine(i, (y / 2) - 14, i, (y / 2) - 6);
            num = Integer.toString(contx);
            if (contx != 0) {
                g.drawString(num, i - 8, (y / 2) + 5);
            }
        }
        //y
        for (int k = 20, conty = 16; k < y; k += 20, conty--) {
            g.setColor(Color.DARK_GRAY);
            g.drawLine((x / 2) - 65, k, (x / 2) - 55, k);
            num = Integer.toString(conty);
            if (conty != 0) {
                if (conty >= 10 || conty < -9) {
                    g.drawString(num, (x / 2) - 83, k + 5);
                } else {
                    g.drawString(num, (x / 2) - 75, k + 5);
                }
            }

        }
    }
}