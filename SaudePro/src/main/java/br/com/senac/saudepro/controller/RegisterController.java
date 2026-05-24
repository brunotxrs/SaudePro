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
import java.util.List;
import javax.swing.JLabel;

/**
 * RegisterControler controle de toda a view Register
 * @author bruno-teixeira
 */
public class RegisterController extends BaseViewController {
    
    
    private Register r;
    // instanciando paciente null para receber novos dados
    private Paciente pacienteOld = null;
    
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
      
        // CURSOR DA MAOZINHA
        r.getAllPanels(7).setCursor(new Cursor(Cursor.HAND_CURSOR));
        r.getAllPanels(8).setCursor(new Cursor(Cursor.HAND_CURSOR));
        r.getAllPanels(9).setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        baseView.getInputSearch().addActionListener(s -> searchPaciente());
        
        
        // Estado inicial: modo CADASTRO
        gerenciarEstadoBotoes(false);  // false = modo cadastro

        actionsButtons(r.getAllLabels(1), "Cadastrar");
        actionsButtons(r.getAllLabels(2), "Atualizar");
        actionsButtons(r.getAllLabels(3), "Deletar");
        
        
    }
    
    // metodo das acoes dos btns
    private void actionsButtons(JLabel label, String nomeBotao){
        // Botão "Novo Agendamento"
        if("Cadastrar".equals(nomeBotao)){
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    actionsForStatesPaciente("Cadastrar");
                }
            });
        } else if("Atualizar".equals(nomeBotao)){
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //add here
                    actionsForStatesPaciente("Atualizar");
                }
            });
        }else if("Deletar".equals(nomeBotao)){
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //add here
                    actionsForStatesPaciente("Deletar");
                }
            });
        }
        
    }
    
    // Buscar
    private void searchPaciente(){
        
        try {
            
            // instanciando paciente null para receber novos dados
            Paciente paciente  = null;
            
            String filter = baseView.getInputSearch().getText().trim();
            
            if(filter.contains("Buscar") || filter.isEmpty()){
                filter = "";
            }
            
            
            //PacienteDAO pdao = new PacienteDAO();
            
            String filterClean = AuxiliaryMethod.returnStringClear(filter);
            
            /* Paciente paciente = pdao.getPaciente(filterClean);*/

             // Limpar filtro
            String filtroLimpo = filter.replaceAll("[^a-zA-Z0-9]", "");

            PacienteDAO pdao = new PacienteDAO();
            

            // Verificar se é CPF (apenas números e 11 dígitos) ou Nome
            if (filtroLimpo.matches("\\d+") && filtroLimpo.length() == 11) {
                // Buscar por CPF
                paciente = pdao.getPacienteByCPF(filtroLimpo);
            } else {
                // Buscar por Nome
                paciente = pdao.getPacienteByNane(filter);
            }
            
            //  Verifica se a lista retornada é nula ou vazia
            if(paciente == null){
                String mgs = "Esse paciente nao esta cadastrado";
                AuxiliaryMethod.mostrarMensagemFlutuante(r, mgs, 300, 80);
                stateInitialize();
                return;
            }
            
            r.getAllInputs(1).setText(paciente.getNome());
            r.getAllInputs(2).setText(paciente.getCpf());           
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
            String data = paciente.getDataNascimento().format(formatter);
            r.getAllInputs(3).setText(data);
            r.getAllInputs(4).setText(paciente.getTelefone());
            r.getAllInputs(5).setText(paciente.getEmail());
            r.getInpuintDetails().setText(paciente.getObservacoes());
            
            r.getAllInputs(1).setForeground(Color.BLACK);
            r.getAllInputs(2).setForeground(Color.BLACK);;           
            r.getAllInputs(3).setForeground(Color.BLACK);
            r.getAllInputs(4).setForeground(Color.BLACK);
            r.getAllInputs(5).setForeground(Color.BLACK);
            r.getInpuintDetails().setForeground(Color.BLACK);
            
            // primeira instancia de paciente receber os dados apos pesquesidos;
            this.pacienteOld = paciente;
            
            gerenciarEstadoBotoes(true);
            
            
        } catch (HeadlessException e) {
            AuxiliaryMethod.mostrarMensagemFlutuante(r, "Ocorreu uma falha ao salvar: " + e.getMessage(), 300, 80);
        }
        
    }
    
    
    //Cadastrar
    private void actionsForStatesPaciente(String botao){
        
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

            String cpf = AuxiliaryMethod.returnStringClear(r.getAllInputs(2).getText().trim());
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

            String data = AuxiliaryMethod.returnStringClear(r.getAllInputs(3).getText().trim());
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
            
            String telefone = AuxiliaryMethod.returnStringClear(r.getAllInputs(4).getText().trim());
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
            
            // Instanciando novo paciente
            Paciente newPaciente = new Paciente();

            // Condiçao se novo paciente for nulo ele receber o antigo dados pesquisados
            if(pacienteOld != null){
                newPaciente = pacienteOld;
            }
            
            // recebem os dados
            newPaciente.setNome(nome);
            newPaciente.setCpf(cpf);
            newPaciente.setDataNascimento(dbData);
            newPaciente.setTelefone(telefone);
            newPaciente.setEmail(email);
            newPaciente.setObservacoes(observation);
            
            // instancia do DAO para o db
            PacienteDAO pdao = new PacienteDAO();
            
            // String de mensagem
            String mm = null;
            
            // condiçao se paciente da primeira instancia estiver nulo ele recebe novo paciente cadastrando novo
            if(pacienteOld == null && "Cadastrar".equals(botao)){
                // varialvel de checagem de duplicidade
                boolean pac = pdao.isExist(newPaciente.getCpf()); 
                
                // condiçao pra checagem de duplicidade
                if(pdao.isExist(cpf)){
                    AuxiliaryMethod.mostrarMensagemFlutuante(r, "Esse Paciente: Já se encontra cadastrado", 300, 80);
                    return;
                }
                // cadastra novo paciente
                pdao.cadastrarPaciente(newPaciente);
                // recebe a mengem
                mm = "<html>"
                        +"<div style='text-align:center; 'width=300px'; height='45px'>" +
                            "O Paciente: " + newPaciente.getNome() + "<br>" +
                            "Cadastrado com " + "<span style='font-weight: bold;'>sucesso</span>" 
                        +"</div>" +
                    "</html>";
                
            }else if("Atualizar".equals(botao)){ // condiçao para atualizar
                pdao.updatePaciente(newPaciente); // atualiza
                // recebe a mengem
                mm = "<html>"
                        +"<div style='text-align:center; 'width=300px'; height='45px'>" +
                            "O Paciente: " + newPaciente.getNome() + "<br>" +
                            "Atualizado com " + "<span style='font-weight: bold;'>sucesso</span>"
                        +"</div>" +
                    "</html>";
            }else if("Deletar".equals(botao)){
                String show ="<html>"
                                +"<div style='text-align:center; 'width=300px'; height='45px'>"+
                                    "Deseja realmente " + "<span style='font-weight: bold;'>Deletar</span>" +
                                    " este Paciente?"+"<br>"+
                                      "<span style='font-weight: bold;'>"+newPaciente.getNome()+"</span>"
                                +"</div>" +
                             "</html>";
                
                boolean confirm = AuxiliaryMethod.mostrarConfirmacaoFlutuante(r, show, 350, 150);
                if(confirm){
                    pdao.deletarPorId(newPaciente.getId());
                    // recebe a mengem
                    mm = "<html>"
                            +"<div style='text-align:center; 'width=300px'; height='45px'>" +
                                "O Paciente: " + newPaciente.getNome() + "<br>" +
                                "Deletado com " + "<span style='font-weight: bold;'>sucesso</span>" 
                            +"</div>" +
                        "</html>";
                    
                    // Lança a mensagem
                    AuxiliaryMethod.mostrarMensagemFlutuante(r, mm, 350, 80);

                    // estado inicial
                    stateInitialize(); 
                }
                
                return;
                
            }
            

            // Lança a mensagem
            AuxiliaryMethod.mostrarMensagemFlutuante(r, mm, 350, 80);
            
            // estado inicial
            stateInitialize();  

            
        } catch (HeadlessException e) {
            AuxiliaryMethod.mostrarMensagemFlutuante(r, "Ocorreu uma falha ao salvar: " + e.getMessage(), 300, 80);
        }
        
    }
    
    // Metodo para exibição de pacientes recentes
    private void showPacientes() {
        // Instaciando pacienteDAO
        PacienteDAO pdao = new PacienteDAO();

        // Buscar os 5 últimos pacientes cadastrados
        List<Paciente> ultimosPacientes = pdao.getUltimosPacientes(5);

        // Primeiro, ESCONDER todos os cards
        for (int i = 1; i <= 5; i++) {
            r.getCardPanel(i).setVisible(false);
        }

        // Verificar se a lista não está vazia
        if (ultimosPacientes != null && !ultimosPacientes.isEmpty()) {
            for (int i = 0; i < ultimosPacientes.size() && i < 5; i++) {
                Paciente p = ultimosPacientes.get(i);

                // O card correspondente (i + 1 porque o índice começa em 0)
                int cardIndex = i + 1;
                r.getCardPanel(cardIndex).setVisible(true);
                r.getCardsPeoples(cardIndex).setText(p.getNome());
            }
        } else {
            // Se não houver pacientes, mostrar mensagem padrão
            for (int i = 1; i <= 5; i++) {
                r.getCardPanel(i).setVisible(true);
                r.getCardsPeoples(i).setText("Nenhum paciente");
            }
        }
    }
    
    // metodo pra estado inicial da tela
    private void stateInitialize(){
        
        // instancia do panciente nula
        this.pacienteOld = null;
        
        AuxiliaryMethod.setPlaceholder(baseView.getInputSearch(), "");
        AuxiliaryMethod.setPlaceholder(r.getAllInputs(1), "Nome do Paciente");
        AuxiliaryMethod.setPlaceholder(r.getAllInputs(2), "ex: 000.000.000-00");
        AuxiliaryMethod.addMascaraDinamica(r.getAllInputs(2), "CPF");
        AuxiliaryMethod.addMascaraDinamica(r.getAllInputs(3), "DATA");
        AuxiliaryMethod.setPlaceholder(r.getAllInputs(3), "00/00/0000");
        AuxiliaryMethod.setPlaceholder(r.getAllInputs(4), "ex: (00) 0 0000-0000");
        AuxiliaryMethod.addMascaraDinamica(r.getAllInputs(4), "TEL");
        AuxiliaryMethod.setPlaceholder(r.getAllInputs(5), "ex: paciente@paciente.com");
        r.getInpuintDetails().setText("");
        
        r.getCardsPeoples(1).setText("");
        r.getCardsPeoples(2).setText("");
        r.getCardsPeoples(3).setText("");
        r.getCardsPeoples(4).setText("");
        r.getCardsPeoples(5).setText("");
        
        gerenciarEstadoBotoes(false);
        
        showPacientes();
    }
        
    // Metodo de mensagem 
    private static String mensage(String campo, String tipo){
        String mgs ="<html>"
                    +"<div style='text-align:center; 'width=300px'; height='45px'>" +
                        "O " + campo + " do paciente nao deve conter tipos; <br>" +
                        "cacteres ex: " + tipo 
                    +"</div>" +
                    "</html>";
        
        return mgs;
    }
    
    // Gerenciar estado dos botões baseado no modo (cadastro novo ou edição)
    private void gerenciarEstadoBotoes(boolean modoEdicao) {
        
        
        if (modoEdicao) {
            // Modo EDIÇÃO: desabilita Cadastrar, habilita Atualizar e Deletar
            r.getAllPanels(7).setEnabled(false);
            r.getAllLabels(1).setEnabled(false);
            r.getAllPanels(7).setBackground(Color.DARK_GRAY);

            r.getAllPanels(8).setEnabled(true);
            r.getAllPanels(9).setEnabled(true);
            r.getAllPanels(8).setBackground(new Color(0x4299E1));
            r.getAllPanels(9).setBackground(new Color(0xF6AD55));

            // Reaplicar hover nos botões habilitados
            AuxiliaryMethod.buttonsHover(r.getAllLabels(2), r.getAllPanels(8), new Color(0x3182CE), new Color(0x4299E1));
            AuxiliaryMethod.buttonsHover(r.getAllLabels(3), r.getAllPanels(9), new Color(0xED8936), new Color(0xF6AD55));

        } else {
            // Modo CADASTRO: habilita Cadastrar, desabilita Atualizar e Deletar
            r.getAllPanels(7).setEnabled(true);
            r.getAllLabels(1).setEnabled(true);
            r.getAllPanels(7).setBackground(new Color(0x7ED348));

            r.getAllPanels(8).setEnabled(false);
            r.getAllPanels(9).setEnabled(false);
            r.getAllPanels(8).setBackground(Color.DARK_GRAY);
            r.getAllPanels(9).setBackground(Color.DARK_GRAY);

            // Reaplicar hover no botão Cadastrar
            AuxiliaryMethod.buttonsHover(r.getAllLabels(1), r.getAllPanels(7), new Color(0x458C45), new Color(0x7ED348));
        }
    }
        
}
