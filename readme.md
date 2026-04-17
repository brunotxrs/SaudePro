## 🏥 SaúdePro - Sistema de Gestão de Clínica Médica

> Sistema desktop completo para gestão de clínicas médicas, desenvolvido em Java Swing com arquitetura MVC e Hibernate/JPA para persistência, permitindo cadastro de pacientes, agendamento de consultas, prontuários eletrônicos e relatórios gerenciais.

---

## 📋 Sobre o Projeto

O **SaúdePro** é uma aplicação desktop desenvolvida para uso interno de clínicas médicas, consultórios e unidades de saúde. O sistema foi projetado para otimizar o fluxo de atendimento, desde o cadastro de pacientes até o registro de prontuários eletrônicos, passando por agendamento de consultas e geração de relatórios gerenciais.

---

## 🎯 Problema Resolvido

| Problema | Solução do SaúdePro |
|:---|:---|
| Falta de organização de pacientes | Cadastro completo com histórico médico |
| Conflito de horários | Sistema de agendamento com verificação de disponibilidade |
| Prontuários em papel | Prontuário eletrônico acessível e seguro |
| Dificuldade em relatórios | Relatórios de faturamento e produtividade |
| Controle de faltas | Listagem e filtro de faltas por período |

---

## 👥 Perfis de Usuário e Permissões

| Perfil | Permissões | Telas Acessíveis |
|:---|:---|:---|
| **Gerente** | Acesso total | Todas as telas + relatórios |
| **Recepcionista** | Cadastro de pacientes, agendamento de consultas | Login, Cadastro, Agendamento |
| **Profissional de Saúde** | Prontuários, registro de serviços | Login, Início (prontuário), Histórico Médico |

---

## 📱 Funcionalidades do Sistema

### 🔐 Tela de Login
| Funcionalidade | Descrição |
|:---|:---|
| Autenticação de usuário | Valida login e senha no banco de dados |
| Placeholder nos campos | Texto guia que some ao clicar |
| Ícones nos campos | Usuário, senha e visibilidade |
| Validação de campos | Mensagens de erro para campos vazios |
| Design moderno | RoundedPanel, bordas arredondadas, cor institucional (#7ED348) |

### 🏠 Tela Inicial (Dashboard)
| Funcionalidade | Descrição |
|:---|:---|
| Próximos atendimentos | Lista de consultas do dia com horário e médico |
| Cards informativos | Pacientes do dia, aguardando atendimento, consultas em aberto, médicos de plantão |
| Tabela de agendamentos | Horário, paciente, médico e botão de confirmação |
| Menu lateral | Início, Cadastro, Agendamento, Sair |
| Botão Novo Encaixe | Adicionar consulta de emergência |

### 👤 Tela de Cadastro de Pacientes
| Funcionalidade | Descrição |
|:---|:---|
| Buscar paciente | Por nome ou CPF (filtro dinâmico) |
| Formulário completo | Nome, CPF, telefone, data de nascimento, e-mail, observações |
| Pacientes recentes | Lista lateral dos últimos cadastros |

### 📅 Tela de Agendamento
| Funcionalidade | Descrição |
|:---|:---|
| Seleção de médico | Lista de profissionais com especialidade |
| Busca de paciente | Por nome ou CPF |
| Calendário interativo | Seleção de data para agendamento |
| Horários disponíveis | Lista de horários vagos do médico selecionado |
| Lista de agendados | Visualização de consultas já marcadas |
| Exclusão de agendamento | Com confirmação de deleção |

### 📋 Tela de Prontuário (Profissional de Saúde)
| Funcionalidade | Descrição |
|:---|:---|
| Paciente em atendimento | Exibição do paciente atual com CPF |
| Histórico médico | Lista de consultas anteriores com data e descrição |
| Avaliação médica | Campo para registrar anamnese e prescrições |
| Finalizar atendimento | Salva o prontuário e encerra consulta |
| Chamar próximo paciente | Avança para o próximo da fila |

### 📊 Telas de Relatórios (Gerente)
| Funcionalidade | Descrição |
|:---|:---|
| Atendimentos hoje | Quantitativo de consultas realizadas |
| Tempo médio de espera | Métrica de eficiência |
| Médico mais produtivo | Ranking por número de atendimentos |
| Relatório de faturamento | Financeiro por profissional |
| Produtividade por médico | Consultas realizadas por período |
| Lista de faltas | Registro de ausências com filtro (hoje/semana/mês) |

### 👨‍⚕️ Tela de Cadastro de Médicos
| Funcionalidade | Descrição |
|:---|:---|
| Buscar médico | Por nome ou CRM |
| Formulário completo | Nome, CRM, telefone, especialidade, turno de trabalho |
| CRUD completo | Cadastrar, atualizar e deletar médicos |
| Médicos recém cadastrados | Lista lateral para acesso rápido |

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Finalidade |
|:---|:---|:---|
| **Java** | SE 17+ | Linguagem principal |
| **Java Swing** | - | Interface gráfica desktop |
| **Hibernate / JPA** | Jakarta Persistence 3.0 | Mapeamento objeto-relacional |
| **MySQL / MariaDB** | 8.0+ | Banco de dados relacional |
| **MVC Pattern** | - | Arquitetura do sistema |
| **Maven** | - | Gerenciamento de dependências |
| **NetBeans** | - | IDE de desenvolvimento |

---

## 🏗️ Arquitetura do Projeto (MVC + Hibernate)

```
SaúdePro/
├── src/
│   └── main/
│       ├── java/
│       │   └── br/com/senac/saudepro/
│       │       ├── gui/           # VIEW - Telas do sistema
│       │       │   ├── Login.java
│       │       │   ├── Dashboard.java
│       │       │   ├── CadastroPaciente.java
│       │       │   ├── Agendamento.java
│       │       │   ├── Prontuario.java
│       │       │   ├── Relatorios.java
│       │       │   └── CadastroMedico.java
│       │       ├── controller/    # CONTROLLER - Lógica de negócio
│       │       │   ├── LoginController.java
│       │       │   ├── AgendamentoController.java
│       │       │   └── ...
│       │       ├── model/         # MODEL - Entidades JPA
│       │       │   ├── Paciente.java
│       │       │   ├── Medico.java
│       │       │   ├── Consulta.java
│       │       │   └── Usuario.java
│       │       ├── dao/           # DATA ACCESS OBJECT (com JPA)
│       │       │   ├── PacienteDAO.java
│       │       │   ├── MedicoDAO.java
│       │       │   └── ConsultaDAO.java
│       │       ├── persistence/   # JPA UTIL
│       │       │   ├── JPAUtil.java
│       │       │   └── META-INF/
│       │       │       └── persistence.xml
│       │       └── util/          # UTILITÁRIOS
│       │           ├── RoundedPanel.java
│       │           ├── IconTextField.java
│       │           ├── ImageLogo.java
│       │           └── PanelBackground.java
│       └── resources/
│           ├── img/               # Imagens do sistema
│           └── db.properties      # Configuração do banco
└── pom.xml
```

---

## 📊 Estrutura do Banco de Dados (JPA)

```sql
-- O Hibernate pode criar as tabelas automaticamente com:
-- hibernate.hbm2ddl.auto = update

-- Mas a estrutura esperada é:

-- Tabela de usuários (autenticação)
CREATE TABLE usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    login VARCHAR(50) UNIQUE,
    senha VARCHAR(50),
    tipo VARCHAR(20)  -- 'Gerente', 'Recepcionista', 'Profissional'
);

-- Tabela de pacientes
CREATE TABLE paciente (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    cpf VARCHAR(14) UNIQUE,
    data_nascimento DATE,
    telefone VARCHAR(15),
    email VARCHAR(100),
    observacoes TEXT
);

-- Tabela de médicos
CREATE TABLE medico (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    crm VARCHAR(20) UNIQUE,
    telefone VARCHAR(15),
    especialidade VARCHAR(50),
    turno VARCHAR(20)  -- 'Manhã', 'Tarde'
);

-- Tabela de consultas (relacionamentos)
CREATE TABLE consulta (
    id INT PRIMARY KEY AUTO_INCREMENT,
    paciente_id INT,
    medico_id INT,
    data_consulta DATE,
    hora_consulta TIME,
    status VARCHAR(20),  -- 'Agendada', 'Realizada', 'Cancelada'
    FOREIGN KEY (paciente_id) REFERENCES paciente(id),
    FOREIGN KEY (medico_id) REFERENCES medico(id)
);

-- Tabela de prontuários
CREATE TABLE prontuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    consulta_id INT,
    avaliacao TEXT,
    prescricao TEXT,
    FOREIGN KEY (consulta_id) REFERENCES consulta(id)
);
```

---

## 📋 Requisitos do Sistema

### Requisitos Funcionais (RF)

| Código | Descrição |
|:---|:---|
| **RF001** | Cadastro de Paciente com nome, data de nascimento e contato |
| **RF002** | Agendamento de Consulta com verificação de disponibilidade |
| **RF003** | Registro de Prontuário por profissionais de saúde |
| **RF004** | Relatórios de faturamento e produtividade |
| **RF005** | Controle de faltas com filtros por período |

### Requisitos Não Funcionais (RNF)

| Código | Descrição |
|:---|:---|
| **RNF001** | Segurança e confidencialidade dos dados dos pacientes |
| **RNF002** | Backup regular dos dados para recuperação |
| **RNF003** | Interface intuitiva para recepcionistas e profissionais |
| **RNF004** | Sistema desktop para uso interno da clínica |

---

## 🧩 Códigos Úteis para Reaproveitar

| Componente | O que faz | Onde encontrar |
|:---|:---|:---|
| **RoundedPanel** | JPanel com bordas arredondadas | `util/RoundedPanel.java` |
| **IconTextField** | Campo de texto com ícone | `util/IconTextField.java` |
| **ImageLogo** | Logo redimensionável | `util/ImageLogo.java` |
| **PanelBackground** | Painel com imagem de fundo | `util/PanelBackground.java` |
| **LoginView** | Tela de login completa | `gui/Login.java` |
| **JPAUtil** | Gerenciador EntityManager | `persistence/JPAUtil.java` |

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

```bash
# Java 17+
java --version

# MySQL ou MariaDB
mysql --version
```

### Passos

1. **Clone o repositório**
   ```bash
   git clone https://github.com/brunotxrs/saudepro.git
   ```

2. **Crie o banco de dados**
   ```sql
   CREATE DATABASE saudepro_db;
   USE saudepro_db;
   ```

3. **Configure o arquivo `db.properties`**
   ```properties
   db.driver=org.mariadb.jdbc.Driver
   db.url=jdbc:mariadb://localhost:3306/saudepro_db
   db.user=seu_usuario
   db.password=sua_senha
   db.dialect=org.hibernate.dialect.MariaDBDialect
   ```

4. **Configure o `persistence.xml`**
   ```xml
   <persistence-unit name="saudepro-PU">
       <properties>
           <property name="hibernate.show_sql" value="true" />
           <property name="hibernate.format_sql" value="true" />
           <property name="hibernate.hbm2ddl.auto" value="update" />
       </properties>
   </persistence-unit>
   ```

5. **Adicione as imagens** na pasta `src/main/resources/img/`

6. **Execute o projeto** pelo NetBeans ou via linha de comando:
   ```bash
   mvn clean install
   mvn exec:java -Dexec.mainClass="br.com.senac.saudepro.gui.Login"
   ```

---

## 📸 Telas do Sistema

| Tela | Descrição |
|:---|:---|
| Login | Autenticação de usuários |
| Dashboard | Visão geral do dia |
| Cadastro de Pacientes | Registro de novos pacientes |
| Agendamento | Marcação de consultas |
| Prontuário | Registro médico |
| Relatórios | Faturamento e produtividade |
| Cadastro de Médicos | Gestão de profissionais |

---

## 🔄 Próximas Evoluções

- [ ] Implementar API REST com Spring Boot
- [ ] Versão web com React
- [ ] Emissão de receituário em PDF
- [ ] Lembretes por e-mail/SMS
- [ ] Integração com sistemas de convênios

---

## 👤 Autor

**Bruno Teixeira**  
*Desenvolvedor Java em constante evolução.*

[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/brunotxrs)

---

## 📝 Licença

Este projeto é destinado para fins educacionais e consulta pessoal.

---

⭐ **Se este projeto te ajudou de alguma forma, considere dar uma estrela!**

---

**SaúdePro - Sua saúde em boas mãos!** 🏥💚

