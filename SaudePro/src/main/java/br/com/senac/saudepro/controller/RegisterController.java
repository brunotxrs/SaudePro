package br.com.senac.saudepro.controller;

import br.com.senac.saudepro.dao.PacienteDAO;
import br.com.senac.saudepro.gui.Register;
import br.com.senac.saudepro.model.Paciente;
import br.com.senac.saudepro.util.AuxiliaryMethod;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;

/**
 * RegisterControler controle de toda a view Register
 * @author bruno-teixeira
 */
public class RegisterController extends BaseViewController {
    
    
    private Register r;
    
    public RegisterController(Register register) {
        super(register);
        
        this.r = register;
        
        start();
    }

    @Override
    protected void loadIcones() {
        super.loadIcones();
        
        
    }

    @Override
    protected void initController() {
        super.initController();
        stateInitialize();
        
        // Selecionar Inicio por padrão
        selecionarBotao(baseView.getAllBtns(2), baseView.getAllIncons(2), icoRegisN, icoRegisH, baseView.getLabelsBtns(2));
        
        
        AuxiliaryMethod.aplicateHover(r.getAllInputs(1), r.getAllPanels(1));
        AuxiliaryMethod.aplicateHover(r.getAllInputs(2), r.getAllPanels(2));
        AuxiliaryMethod.aplicateHover(r.getAllInputs(3), r.getAllPanels(3));
        AuxiliaryMethod.aplicateHover(r.getAllInputs(4), r.getAllPanels(4));
        AuxiliaryMethod.aplicateHover(r.getAllInputs(5), r.getAllPanels(5));
    
        AuxiliaryMethod.aplicarHover(r.getInpuintDetails(), r.getAllPanels(6));
        
        AuxiliaryMethod.buttonsHover(r.getAllLabels(1), r.getAllPanels(7), new Color(0x458C45), new Color(0x7ED348));
        AuxiliaryMethod.buttonsHover(r.getAllLabels(2), r.getAllPanels(8), new Color(0x3182CE), new Color(0x4299E1));
        AuxiliaryMethod.buttonsHover(r.getAllLabels(3), r.getAllPanels(9), new Color(0xED8936), new Color(0xF6AD55));
    
        // CURSOR DA MAOZINHA
        r.getAllPanels(7).setCursor(new Cursor(Cursor.HAND_CURSOR));
        r.getAllPanels(8).setCursor(new Cursor(Cursor.HAND_CURSOR));
        r.getAllPanels(9).setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        actionsButtons(r.getAllLabels(1), "Cadastrar");
        
        
    }
    
    // metodo das acoes dos btns
    private void actionsButtons(JLabel label, String nomeBotao){
        // Botão "Novo Agendamento"
        if("Cadastrar".equals(nomeBotao)){
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cadastraNewPaciente();
                }
            });
        } else if("Atualizar".equals(nomeBotao)){
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //add here
                    System.out.println("EM DESENVOLVIMENTO");
                }
            });
        }else if("Deletar".equals(nomeBotao)){
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //add here
                    System.out.println("EM DESENVOLVIMENTO");
                }
            });
        }
        
    }
    
    
    //Cadastrar
    private void cadastraNewPaciente(){
        
        try {
            
            String nome = r.getAllInputs(1).getText().trim();
            
            // Condiçoes pra nao esta vazia e a segunda condiçao mas dura de nao ter caracteres ao nome como numero etc.
            if(nome.contains("Nome") || nome.isEmpty()){
                String mgs = "O Campo NOME* nao deve esta vazio!";
                AuxiliaryMethod.mostrarMensagemFlutuante(r, mgs, 300, 80);
                return;
            } else if(AuxiliaryMethod.isValidString(nome) != true){
                
                String mgs = mensage("nome", "[ -, /. + , 012... ]");
                AuxiliaryMethod.mostrarMensagemFlutuante(r, mgs, 350, 80);
                return;
            }

            String cpf = returnStringClear(r.getAllInputs(2).getText().trim());
            //condiçoes checar se nao esta vazio| ou conter Letras | ou ser maior ou menor que 11    
            if(cpf.contains("00000000000") || cpf.isEmpty()){
                String mgs = "O Campo CPF* nao deve esta vazio!";
                AuxiliaryMethod.mostrarMensagemFlutuante(r, mgs, 300, 80);
                return;
                
            } else if(AuxiliaryMethod.isValidNumber(cpf) != true){
                String mgs = mensage("CPF", "[ Aa, Bb, Cc... ]");
                AuxiliaryMethod.mostrarMensagemFlutuante(r, mgs, 350, 80);
                return;
            } else if(cpf.length() != 11){
                String mgs = "O CPF deve ter exatamente 11 dígitos.";
                AuxiliaryMethod.mostrarMensagemFlutuante(r, mgs, 350, 80);
                return;
            }

            String data = returnStringClear(r.getAllInputs(3).getText().trim());
            // Variaveis para validação lógica referente a dia e mes
            int dia = Integer.parseInt(data.substring(0, 2));
            int mes = Integer.parseInt(data.substring(2, 4));
            //condiçoes checar se nao esta vazio| ou conter Letras | ou ser maior ou menor que 8 | e checagem sobre dias e mes  
            if(data.contains("00000000") || data.isEmpty()){
                String mgs = "O Campo DATA* nao deve esta vazio!";
                AuxiliaryMethod.mostrarMensagemFlutuante(r, mgs, 300, 80);
                return;
            } else if((AuxiliaryMethod.isValidNumber(data) != true)){
                String mgs = mensage("DATA", "[ Aa, Bb, Cc... ]");
                AuxiliaryMethod.mostrarMensagemFlutuante(r, mgs, 350, 80);
                return;
            } else if(data.length() != 8){
                String mgs = "O DATA deve ter exatamente 8 dígitos.";
                AuxiliaryMethod.mostrarMensagemFlutuante(r, mgs, 350, 80);
                return;
            } else if(dia < 1 || dia > 31 || mes < 1 || mes > 12){
                String mgs = "Data inválida! Verifique o dia e o mês.";
                AuxiliaryMethod.mostrarMensagemFlutuante(r, mgs, 350, 80);
                return;
            }
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
            LocalDate dbData = LocalDate.parse(data, formatter);
            
            String telefone = returnStringClear(r.getAllInputs(4).getText().trim());
            //condiçoes checar se nao esta vazio| ou conter Letras | telefomne esta entre 10 a  11  
            if(telefone.contains("ex:") || telefone.isEmpty()){
                String mgs = "O Campo TELEFONE* nao deve esta vazio!";
                AuxiliaryMethod.mostrarMensagemFlutuante(r, mgs, 300, 80);
                return;
            } else if((AuxiliaryMethod.isValidNumber(telefone) != true)){
                String mgs = mensage("TELEFONE", "[ Aa, Bb, Cc... ]");
                AuxiliaryMethod.mostrarMensagemFlutuante(r, mgs, 350, 80);
                return;
            } else if(telefone.length() < 10 || telefone.length() > 11){
                String mgs = """
                            O Telefone deve ter 10 dígitos (Fixo) 
                            ou 11 dígitos (Celular), incluindo o DDD.
                             """;
                AuxiliaryMethod.mostrarMensagemFlutuante(r, mgs, 350, 80);
                return;
            }        

            String email = r.getAllInputs(5).getText().trim();
            if(!email.contains("@") || !email.contains(".")){
                String mgs = "Insira um E-mail Valido";
                AuxiliaryMethod.mostrarMensagemFlutuante(r, mgs, 350, 80);
                return;
            }
            
            
            String observation = r.getInpuintDetails().getText().trim();
            
            
            // instaciando novo paciente
            Paciente newPaciente = new Paciente();
            newPaciente.setNome(nome);
            newPaciente.setCpf(cpf);
            newPaciente.setDataNascimento(dbData);
            newPaciente.setTelefone(telefone);
            newPaciente.setEmail(email);
            newPaciente.setObservacoes(observation);
            
            // salvando ao db
            PacienteDAO pdao = new PacienteDAO();
            
            boolean pac = pdao.isExist(newPaciente.getCpf()); 
            
            if(pdao.isExist(cpf)){
                
                AuxiliaryMethod.mostrarMensagemFlutuante(r, "Esse Paciente: Já se encontra cadastrado", 300, 80);
                return;
            }
                
            pdao.cadastrarPaciente(newPaciente);
            
            String mm = "<html>"
                        +"<div style='text-align:center; 'width=300px'; height='45px'>" +
                            "O Paciente:" + newPaciente.getNome() + "<br>" +
                            "Cadastrado com susseço" 
                        +"</div>" +
                        "</html>";

            AuxiliaryMethod.mostrarMensagemFlutuante(r, " " + newPaciente.getNome() + " ", 350, 80);

            stateInitialize();                

            
            
        } catch (HeadlessException e) {
            AuxiliaryMethod.mostrarMensagemFlutuante(r, "Ocorreu uma falha ao salvar: " + e.getMessage(), 300, 80);
        }
        
    }
    
    
    private void stateInitialize(){
        AuxiliaryMethod.setPlaceholder(r.getAllInputs(1), "Nome do Paciente");
        AuxiliaryMethod.setPlaceholder(r.getAllInputs(2), "ex: 000.000.000-00");
        AuxiliaryMethod.addMascaraDinamica(r.getAllInputs(2), "CPF");
        AuxiliaryMethod.addMascaraDinamica(r.getAllInputs(3), "DATA");
        AuxiliaryMethod.setPlaceholder(r.getAllInputs(3), "00/00/0000");
        AuxiliaryMethod.setPlaceholder(r.getAllInputs(4), "ex: (00) 0 0000-0000");
        AuxiliaryMethod.addMascaraDinamica(r.getAllInputs(4), "TEL");
        AuxiliaryMethod.setPlaceholder(r.getAllInputs(5), "ex: paciente@paciente.com");
        r.getInpuintDetails().setText("");
    }
    
    
    private static String mensage(String campo, String tipo){
        String mgs ="<html>"
                    +"<div style='text-align:center; 'width=300px'; height='45px'>" +
                        "O " + campo + " do paciente nao deve conter tipos; <br>" +
                        "cacteres ex: " + tipo 
                    +"</div>" +
                    "</html>";
        
        return mgs;
    }
    
    private static String returnStringClear(String s){
        String input = s.trim();
        String cleanedInput = input.replaceAll("[^0-9]", "");
        return cleanedInput;
    }
    
        
}
