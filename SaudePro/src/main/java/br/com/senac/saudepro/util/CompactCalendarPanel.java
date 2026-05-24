package br.com.senac.saudepro.util;

import java.awt.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CompactCalendarPanel extends JPanel {
    
    public CompactCalendarPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xE0E0E0), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        initComponents();
    }
    
    private void initComponents() {
        LocalDate hoje = LocalDate.now();
        
        // Mês/Ano
        JLabel mesLabel = new JLabel();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM/yyyy");
        mesLabel.setText(formatter.format(hoje).toUpperCase());
        mesLabel.setFont(new Font("Arial", Font.BOLD, 11));
        mesLabel.setForeground(new Color(0x7ED348));
        mesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mesLabel.setBorder(new EmptyBorder(0, 0, 5, 0));
        
        // Dias da semana
        JPanel weekPanel = new JPanel(new GridLayout(1, 7, 1, 1));
        weekPanel.setBackground(Color.WHITE);
        String[] dias = {"D", "S", "T", "Q", "Q", "S", "S"};
        for (String dia : dias) {
            JLabel label = new JLabel(dia, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 9));
            label.setForeground(new Color(0x458C45));
            weekPanel.add(label);
        }
        
        // Dias do mês
        JPanel daysPanel = new JPanel(new GridLayout(0, 7, 1, 1));
        daysPanel.setBackground(Color.WHITE);
        
        YearMonth yearMonth = YearMonth.of(hoje.getYear(), hoje.getMonth());
        int primeiroDia = yearMonth.atDay(1).getDayOfWeek().getValue() % 7;
        int diasNoMes = yearMonth.lengthOfMonth();
        
        // Dias vazios
        for (int i = 0; i < primeiroDia; i++) {
            JLabel empty = new JLabel("");
            empty.setPreferredSize(new Dimension(25, 22));
            daysPanel.add(empty);
        }
        
        // Dias do mês
        for (int dia = 1; dia <= diasNoMes; dia++) {
            LocalDate dataDia = LocalDate.of(hoje.getYear(), hoje.getMonth(), dia);
            JLabel diaLabel = new JLabel(String.valueOf(dia), SwingConstants.CENTER);
            diaLabel.setFont(new Font("Arial", Font.PLAIN, 9));
            diaLabel.setPreferredSize(new Dimension(25, 22));
            diaLabel.setOpaque(true);
            
            if (dataDia.equals(hoje)) {
                diaLabel.setBackground(new Color(0x7ED348));
                diaLabel.setForeground(Color.WHITE);
                diaLabel.setFont(new Font("Arial", Font.BOLD, 9));
            } else {
                diaLabel.setBackground(Color.WHITE);
                diaLabel.setForeground(Color.BLACK);
            }
            
            daysPanel.add(diaLabel);
        }
        
        add(mesLabel, BorderLayout.NORTH);
        add(weekPanel, BorderLayout.CENTER);
        add(daysPanel, BorderLayout.SOUTH);
    }
}