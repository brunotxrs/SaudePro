package br.com.senac.saudepro.util;

import java.awt.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CompactCalendarPanel extends JPanel {
    
    private LocalDate dataSelecionada;
    private JLabel dataLabelExterna;
    private JLabel mesLabel;
    private JPanel daysPanel;
    private YearMonth anoMesAtual;
    private Runnable onDataChangeListener;
    
    public CompactCalendarPanel() {
        dataSelecionada = LocalDate.now();
        anoMesAtual = YearMonth.now();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xE0E0E0), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        initComponents();
        atualizarCalendario();
    }
    
    public void setDataLabelExterna(JLabel label) {
        this.dataLabelExterna = label;
    }
    
    public void setOnDataChangeListener(Runnable listener) {
        this.onDataChangeListener = listener;
    }
    
    private void initComponents() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(new EmptyBorder(0, 0, 5, 0));
        
        JLabel btnAnterior = new JLabel("◀");
        btnAnterior.setFont(new Font("Arial", Font.BOLD, 10));
        btnAnterior.setForeground(new Color(0x7ED348));
        btnAnterior.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAnterior.setBorder(new EmptyBorder(0, 5, 0, 5));
        btnAnterior.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                anoMesAtual = anoMesAtual.minusMonths(1);
                atualizarCalendario();
            }
        });
        
        mesLabel = new JLabel();
        mesLabel.setFont(new Font("Arial", Font.BOLD, 11));
        mesLabel.setForeground(new Color(0x7ED348));
        mesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel btnProximo = new JLabel("▶");
        btnProximo.setFont(new Font("Arial", Font.BOLD, 10));
        btnProximo.setForeground(new Color(0x7ED348));
        btnProximo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnProximo.setBorder(new EmptyBorder(0, 5, 0, 5));
        btnProximo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                anoMesAtual = anoMesAtual.plusMonths(1);
                atualizarCalendario();
            }
        });
        
        headerPanel.add(btnAnterior, BorderLayout.WEST);
        headerPanel.add(mesLabel, BorderLayout.CENTER);
        headerPanel.add(btnProximo, BorderLayout.EAST);
        
        JPanel weekPanel = new JPanel(new GridLayout(1, 7, 1, 1));
        weekPanel.setBackground(Color.WHITE);
        String[] dias = {"D", "S", "T", "Q", "Q", "S", "S"};
        for (String dia : dias) {
            JLabel label = new JLabel(dia, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 9));
            label.setForeground(new Color(0x458C45));
            weekPanel.add(label);
        }
        
        daysPanel = new JPanel(new GridLayout(0, 7, 1, 1));
        daysPanel.setBackground(Color.WHITE);
        
        add(headerPanel, BorderLayout.NORTH);
        add(weekPanel, BorderLayout.CENTER);
        add(daysPanel, BorderLayout.SOUTH);
    }
    
    private void atualizarCalendario() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM/yyyy");
        mesLabel.setText(formatter.format(anoMesAtual).toUpperCase());
        
        daysPanel.removeAll();
        
        int primeiroDia = anoMesAtual.atDay(1).getDayOfWeek().getValue() % 7;
        int diasNoMes = anoMesAtual.lengthOfMonth();
        
        for (int i = 0; i < primeiroDia; i++) {
            JLabel empty = new JLabel("");
            empty.setPreferredSize(new Dimension(25, 22));
            daysPanel.add(empty);
        }
        
        LocalDate hoje = LocalDate.now();
        
        for (int dia = 1; dia <= diasNoMes; dia++) {
            LocalDate dataDia = LocalDate.of(anoMesAtual.getYear(), anoMesAtual.getMonth(), dia);
            JLabel diaLabel = new JLabel(String.valueOf(dia), SwingConstants.CENTER);
            diaLabel.setFont(new Font("Arial", Font.PLAIN, 9));
            diaLabel.setPreferredSize(new Dimension(25, 22));
            diaLabel.setOpaque(true);
            diaLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            if (dataDia.equals(hoje)) {
                diaLabel.setBackground(new Color(0x7ED348));
                diaLabel.setForeground(Color.WHITE);
                diaLabel.setFont(new Font("Arial", Font.BOLD, 9));
            } else {
                diaLabel.setBackground(Color.WHITE);
                diaLabel.setForeground(Color.BLACK);
            }
            
            final LocalDate dataClicada = dataDia;
            diaLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    dataSelecionada = dataClicada;
                    
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String dataFormatada = dataClicada.format(fmt);
                    
                    if (dataLabelExterna != null) {
                        dataLabelExterna.setText(dataFormatada);
                    }
                    
                    destacarDiaSelecionado(dataClicada);
                    
                    // 🔥 NOTIFICAR O LISTENER QUANDO A DATA MUDAR
                    if (onDataChangeListener != null) {
                        onDataChangeListener.run();
                    }
                    
                    System.out.println("Data selecionada: " + dataFormatada);
                }
                
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    if (!dataClicada.equals(dataSelecionada) && !dataClicada.equals(hoje)) {
                        diaLabel.setBackground(new Color(0xE8F5E9));
                    }
                }
                
                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    if (!dataClicada.equals(dataSelecionada) && !dataClicada.equals(hoje)) {
                        diaLabel.setBackground(Color.WHITE);
                    }
                }
            });
            
            daysPanel.add(diaLabel);
        }
        
        daysPanel.revalidate();
        daysPanel.repaint();
    }
    
    private void destacarDiaSelecionado(LocalDate data) {
        Component[] components = daysPanel.getComponents();
        
        for (int dia = 1; dia <= anoMesAtual.lengthOfMonth(); dia++) {
            int posicao = (anoMesAtual.atDay(1).getDayOfWeek().getValue() % 7) + dia - 1;
            if (posicao >= 0 && posicao < components.length) {
                JLabel label = (JLabel) components[posicao];
                LocalDate dataDia = LocalDate.of(anoMesAtual.getYear(), anoMesAtual.getMonth(), dia);
                
                if (dataDia.equals(data)) {
                    label.setBackground(Color.BLUE);
                    label.setForeground(Color.WHITE);
                    label.setFont(new Font("Arial", Font.BOLD, 9));
                } else if (dataDia.equals(LocalDate.now())) {
                    label.setBackground(new Color(0x7ED348));
                    label.setForeground(Color.WHITE);
                    label.setFont(new Font("Arial", Font.BOLD, 9));
                } else {
                    label.setBackground(Color.WHITE);
                    label.setForeground(Color.BLACK);
                    label.setFont(new Font("Arial", Font.PLAIN, 9));
                }
            }
        }
    }
    
    public LocalDate getDataSelecionada() {
        return dataSelecionada;
    }
}