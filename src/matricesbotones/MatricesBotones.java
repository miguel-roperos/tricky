package matricesbotones;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class MatricesBotones extends JFrame {

    private JPanel panelBotones;
    private int x = 0;
    private int y = 0;
    private int jugada = 0;
    private static String[][] matrix;
    private String ganador = "";

    public MatricesBotones(int filas, int columnas) {
        iniciarComponents(filas, columnas);
    }

    private void iniciarComponents(int filas, int columnas) {
        panelBotones = new JPanel();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        panelBotones.setBackground(Color.LIGHT_GRAY);
        panelBotones.setLayout(new GridLayout(filas, columnas, 10, 10));
        setLocationRelativeTo(null);// se centra la pantalla
        JButton reiniciar = new JButton("Reiniciar");
        reiniciar.setFont(new Font("cooper black", 6, 35));
        getContentPane().add(reiniciar, BorderLayout.SOUTH);
        reiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                reiniciarJuego();
            }
        });
        for (int i = 0; i < filas; i++) {

            for (int j = 0; j < columnas; j++) {
                JButton boton = new JButton();
                boton.setFont(new Font("cooper black", 2, 80));
                boton.setText(" ");

                panelBotones.add(boton);
                final int x = i;
                final int y = j;
                boton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        String caracter = validar(x, y);
                        if (caracter.equalsIgnoreCase("x")) {
                            boton.setBackground(Color.green);
                        } else if (caracter.equalsIgnoreCase("o")) {
                            boton.setBackground(Color.orange);
                        }
                        boton.setText(caracter);

                        boton.setFont(new Font("cooper black", 3, 80));
                        boton.setEnabled(false);
                        Ganador();
                    }
                });
            }
        }
        getContentPane().add(panelBotones, BorderLayout.CENTER);
        setTitle("Tricky Traqui version GIT");
        pack();
        setVisible(true);
    }

    public String validar(int i, int j) {
        jugada++;
        if (jugada % 2 == 0) {
            matrix[i][j] = "X";
            return "X";
        } else {
            matrix[i][j] = "O";
        }
        return "O";
    }

    public void reiniciarJuego() {
        if (ganador == "x") {
            jugada = 1;
        } else if (ganador == "o") {
            jugada = 0;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = null;
                int indice = i * matrix[i].length + j;
                JButton boton = (JButton) panelBotones.getComponent(indice);
                boton.setText(" ");
                boton.setEnabled(true);
                boton.setBackground(Color.WHITE);
            }
        }

    }

    public void Ganador() {
        ganador = null;//la variable ganador se declara en vacio
        // victorias por horizontales 3 opciones
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] != null && matrix[i][0].equals(matrix[i][1]) && matrix[i][1].equals(matrix[i][2])) {
                ganador = matrix[i][0];
            }
        }
        // victorias por las verticales 3 opciones
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] != null && matrix[0][j].equals(matrix[1][j]) && matrix[1][j].equals(matrix[2][j])) {
                ganador = matrix[0][j];

            }
        }
        // victoria por diagonales 2 opciones
        if (matrix[0][0] != null && matrix[0][0].equals(matrix[1][1]) && matrix[1][1].equals(matrix[2][2])) {
            ganador = matrix[0][0];
        }
        if (matrix[0][2] != null && matrix[0][2].equals(matrix[1][1]) && matrix[1][1].equals(matrix[2][0])) {
            ganador = matrix[0][2];
        }
        if (ganador != null) {// aca se comprueba si hay un ganador y se imprime el mensaje de quien es el ganador
            JOptionPane.showMessageDialog(this, "El ganador es " + ganador);
            reiniciarJuego();
        }
    }

    public static void main(String args[]) {

        int filas = 3;
        int columnas = 3;
        MatricesBotones v = new MatricesBotones(filas, columnas);
        matrix = new String[filas][columnas];

    }
}
