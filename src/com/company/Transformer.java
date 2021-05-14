package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Transformer extends JFrame {

    JButton btn_trasladar, btn_rectangulo, btn_triangulo, btn_estrella, btn_escalar, btn_rotar, btn_borrar, btn_poligono;
    JLabel lb_tx, lb_ty, lb_PFx, lb_PFy, lb_PF, lb_sx, lb_sy, lb_grados, lb_sxsy;
    JTextField txt_tx, txt_ty, txt_PFx, txt_PFy, txt_grados, txt_sx, txt_sy;
    JPanel pnl_gnrl, pnl_figuras, pnl_t, pnl_e, pnl_sxy, pnl_acciones, pnl_grados, pnl_derecho;
    int Tx, Ty;
    PlanoCartesiano p;
    Border b;
    int x = this.getWidth(), y = this.getHeight();

    public Transformer() {
        super("Trasladar, Escalar y Rotar");
        setSize(800, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        p = new PlanoCartesiano();
        b = BorderFactory.createRaisedBevelBorder();
        pnl_t = new JPanel();
        pnl_t.setLayout(new GridLayout(2, 2, 0, 0));
        pnl_sxy = new JPanel();
        pnl_sxy.setLayout(new GridLayout(2, 2, 0, 0));
        pnl_grados = new JPanel();
        pnl_grados.setLayout(new GridLayout(1, 2, 0, 0));

        pnl_figuras = new JPanel();
        pnl_acciones = new JPanel();
        pnl_derecho = new JPanel();
        pnl_derecho.setLayout(new GridLayout(2, 1, 0, 0));

        //panel figuras
        pnl_figuras.setBorder(new TitledBorder(new LineBorder(Color.WHITE), "Figuras"));
        pnl_figuras.setLayout((new GridLayout(4, 1, 10, 10)));
        btn_poligono = new JButton("Polígono");
        btn_poligono.setBorder(b);
        btn_rectangulo = new JButton("Rectángulo");
        btn_rectangulo.setBorder(b);
        btn_triangulo = new JButton("Triángulo");
        btn_triangulo.setBorder(b);
        btn_estrella = new JButton("Estrella");
        btn_estrella.setBorder(b);

        //panel acciones
        pnl_acciones.setBorder(new TitledBorder(new LineBorder(Color.WHITE), "Acciones"));
        pnl_acciones.setLayout(new GridLayout(8, 1, 10, 10));

        lb_tx = new JLabel("Tx: ");
        lb_ty = new JLabel("Ty: ");
        txt_tx = new JTextField("0", 4);
        txt_ty = new JTextField("0", 4);

        pnl_t.add(lb_tx);
        pnl_t.add(txt_tx);
        pnl_t.add(lb_ty);
        pnl_t.add(txt_ty);

        btn_trasladar = new JButton("Trasladar");
        btn_trasladar.setBorder(b);

        lb_sxsy = new JLabel("Escalar a:");
        lb_sx = new JLabel("sx : ");
        lb_sy = new JLabel("sy : ");
        txt_sx = new JTextField("0", 4);
        txt_sy = new JTextField("0", 4);

        pnl_sxy.add(lb_sx);
        pnl_sxy.add(txt_sx);
        pnl_sxy.add(lb_sy);
        pnl_sxy.add(txt_sy);

        btn_escalar = new JButton("Escalar");
        btn_escalar.setBorder(b);

        btn_rotar = new JButton("Rotar");
        btn_rotar.setBorder(b);

        lb_grados = new JLabel("Rotar a: ");
        txt_grados = new JTextField("0", 4);
        pnl_grados.add(lb_grados);
        pnl_grados.add(txt_grados);

        btn_borrar = new JButton("Borrar");
        btn_borrar.setBorder(b);

        pnl_figuras.add(btn_rectangulo);
    
        pnl_acciones.add(pnl_t);
        pnl_acciones.add(btn_trasladar);
        pnl_acciones.add(lb_sxsy);
        pnl_acciones.add(pnl_sxy);

        pnl_acciones.add(btn_escalar);
        pnl_acciones.add(pnl_grados);
        pnl_acciones.add(btn_rotar);
        pnl_acciones.add(btn_borrar);

        //panel derecho
        pnl_derecho.add(pnl_figuras);
        pnl_derecho.add(pnl_acciones);

        //panel general
        pnl_gnrl = new JPanel();
        pnl_gnrl.setLayout(new BorderLayout());
        pnl_gnrl.add(p, BorderLayout.CENTER);
        pnl_gnrl.add(pnl_derecho, BorderLayout.EAST);

        setContentPane(pnl_gnrl);
        setVisible(true);
    }

    public class PlanoCartesiano extends JPanel {

        public PlanoCartesiano() {
            setBackground(Color.WHITE);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            dibujarplano(g);
            btn_rectangulo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {

                    Graphics g = p.getGraphics();
                    Polygon cuadrado = new Polygon();
                    int ptn[][] = new int[4][2];
                    ptn[0][0] = 430;
                    ptn[0][1] = 210;
                    ptn[1][0] = 370;
                    ptn[1][1] = 210;
                    ptn[2][0] = 370;
                    ptn[2][1] = 240;
                    ptn[3][0] = 430;
                    ptn[3][1] = 240;

                    int pF[][] = new int[1][2];
                    pF[0][0] = ptn[0][0];
                    pF[0][1] = ptn[0][1];

                    g.setColor(Color.RED);
                    for (int i = 0; i < ptn.length; i++) {
                        cuadrado.addPoint(ptn[i][0], ptn[i][1]);
                    }

                    g.drawPolygon(cuadrado);
                    g.setColor(Color.MAGENTA);
                    g.drawString("Fig. Original", ptn[ptn.length - 1][0] + 5, ptn[ptn.length - 1][1]);
                    puntos(g, ptn);
                }
            });
           
        }

    }

    public void dibujarplano(Graphics g) {
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

    public void puntos(Graphics g, int puntos[][]) {

        btn_borrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                p.repaint();
                for (int i = 0; i < puntos.length; i++) {
                    for (int j = 0; j < puntos[0].length; j++) {
                        puntos[i][j] = 0;
                    }
                }
            }
        });

        btn_trasladar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                int pF[][] = new int[1][2];

                Polygon trasladada = new Polygon();

                Tx = Integer.parseInt(txt_tx.getText()) * 20;
                Ty = Integer.parseInt(txt_ty.getText()) * 20;

                g.setColor(Color.ORANGE);
                for (int i = 0; i < puntos.length; i++) {
                    trasladada.addPoint(puntos[i][0] + Tx, puntos[i][1] - Ty);
                }
                g.drawPolygon(trasladada);
            }
        });
        btn_escalar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                int pF[][] = new int[1][2];
                int sx, sy, pfx, pfy;

                sx = Integer.parseInt(txt_sx.getText());
                sy = Integer.parseInt(txt_sy.getText());

                Polygon escalar = new Polygon();

                pF[0][0] = puntos[0][0];
                pF[0][1] = puntos[0][1];

                for (int m = 0; m < puntos[0].length; m++) {
                    for (int i = 0; i < puntos.length; i++) {
                        puntos[i][m] = puntos[i][m] - pF[0][m];
                    }

                    for (int i = 0; i < puntos.length; i++) {

                        if (m == 0) {
                            puntos[i][m] = puntos[i][m] * sx;
                        } else {
                            puntos[i][m] = puntos[i][m] * sy;
                        }
                    }

                    for (int i = 0; i < puntos.length; i++) {
                        puntos[i][m] = puntos[i][m] + pF[0][m];
                    }
                }
                for (int i = 0; i < puntos[0].length; i++) {
                    for (int j = 0; j < puntos.length; j++) {
                        if (i == 0) {
                            puntos[j][i] += Tx;
                        } else {
                            puntos[j][i] -= Ty;
                        }
                    }
                }
                for (int i = 0; i < puntos.length; i++) {
                    escalar.addPoint(puntos[i][0], puntos[i][1]);
                }
                g.setColor(Color.BLUE);
                g.drawPolygon(escalar);
//                g.setColor(Color.PINK);
//                g.drawString("Fig. Escalar", puntos[puntos.length - 1][0] + Tx, puntos[puntos.length - 1][1] - Ty);
            }
        });
        btn_rotar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                int gr;
                int pF[][] = new int[1][2];

                pF[0][0] = puntos[0][0];
                pF[0][1] = puntos[0][1];

                Graphics2D g2 = (Graphics2D) g;
                g.drawString("PF", pF[0][0], pF[0][1]);
                gr = Integer.parseInt(txt_grados.getText());

                Polygon rotar = new Polygon();

                g2.setColor(Color.green);

                for (int i = 0; i < puntos.length; i++) {
                    rotar.addPoint(puntos[i][0], puntos[i][1]);
                }

                for (int m = 2; m <= gr; m+=2) {
                    g2.rotate(Math.toRadians(m), pF[0][0], pF[0][1]);
                    g2.drawPolygon(rotar);
                    gr -= m;
                    
                    if (gr == m) {
                        g2.rotate(Math.toRadians(gr), pF[0][0], pF[0][1]);
                        g2.drawPolygon(rotar);
                    }
                }

            }
        });
    }

    public static void main(String[] args) {
        Transformer ventana = new Transformer();
    }
}
