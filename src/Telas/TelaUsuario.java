/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Telas;

import dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.InputMismatchException;




/**
 *
 * @author CT Desenvolvimento
 */
public class TelaUsuario extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;   

    /**
     * Creates new form TelaUsuario
     */
    public TelaUsuario() {
        initComponents();

        conexao = ModuloConexao.conector();
    }
  public static boolean isCPF(String CPF) {
        // considera-se erro CPF"s formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
            CPF.equals("11111111111") ||
            CPF.equals("22222222222") || CPF.equals("33333333333") ||
            CPF.equals("44444444444") || CPF.equals("55555555555") ||
            CPF.equals("66666666666") || CPF.equals("77777777777") ||
            CPF.equals("88888888888") || CPF.equals("99999999999") ||
            (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
        // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
        // converte o i-esimo caractere do CPF em um numero:
        // por exemplo, transforma o caractere "0" no inteiro 0
        // (48 eh a posicao de "0" na tabela ASCII)
            num = (int)(CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)){
                dig10 = (char) '0';
            }else {dig10 = (char)(r + 48);
            }// converte no respectivo caractere numerico

        // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
            num = (int)(CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)){
                 dig11 = '0';
            }else{ dig11 = (char)(r + 48);
            }

        // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))){
                 return(true);
            }else {
                return(false);
            }
                } catch (InputMismatchException erro) {
                return(false);
            }
        }

  
       
          

    private void Adicionar() {
        String sql = "INSERT INTO tbl_agenda ( Nome,DDD,Celular,Email,Nascimento, CPF, Endereco, Numero, Bairro, Complemento, CEP, Cidade, Estado) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
       
            if(!isCPF(CPF.getText())) {
                JOptionPane.showMessageDialog(null, "CPF inválido, favor digitar um número válido!");
            } else if(Nome.getText().length() == 0 || Celular.getText().length()==0 ||Email.getText().length()==0 || CEP.getText().length()==0 ||CPF.getText().length()==0 ||Nascimento.getText().length()==0){
                JOptionPane.showMessageDialog(null, "Campo Obrigatório, favor digitar a informação");
            }
                
            
            else {
            try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, Nome.getText());
            pst.setString(2, DDD.getText());
            pst.setString(3, Celular.getText());
            pst.setString(4, Email.getText());
            pst.setString(5, Nascimento.getText());
            pst.setString(6, CPF.getText());
            pst.setString(7, Endereco.getText());
            pst.setString(8, Numero.getText());
            pst.setString(9, Bairro.getText());
            pst.setString(10, Complemento.getText());
            pst.setString(11, CEP.getText());
            pst.setString(12, Cidade.getText());
            pst.setString(13, Estado.getText());

            int adicionado = pst.executeUpdate();// retorna 1 se estiver ok

            //condição para validar o cep
            
    if (adicionado> 0) {
                JOptionPane.showMessageDialog(null, "USUARIO CADASTRADO");
        // As linhas abaixo irão limpar o formulário
        ID.setText(null);
        Nome.setText(null);
        DDD.setText(null);
        Celular.setText(null);
        Email.setText(null);
        Nascimento.setText(null);
        CPF.setText(null);
        Endereco.setText(null);
        Numero.setText(null);
        Bairro.setText(null);
        Complemento.setText(null);
        CEP.setText(null);
        Cidade.setText(null);
        Estado.setText(null);
    }
}
catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    }

    private void Editar() {
        String sql = "UPDATE tbl_agenda SET Nome=?,DDD=?,Celular=?,Email=?,Nascimento=?, CPF=?, Endereco=?, Numero=?, Bairro=?, Complemento=?, CEP=?, Cidade=?, Estado=? "
                + "WHERE ID=?";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, Nome.getText());
            pst.setString(2, DDD.getText());
            pst.setString(3, Celular.getText());
            pst.setString(4, Email.getText());
            pst.setString(5, Nascimento.getText());
            pst.setString(6, CPF.getText());
            pst.setString(7, Endereco.getText());
            pst.setString(8, Numero.getText());
            pst.setString(9, Bairro.getText());
            pst.setString(10, Complemento.getText());
            pst.setString(11, CEP.getText());
            pst.setString(12, Cidade.getText());
            pst.setString(13, Estado.getText());
            pst.setString(14, ID.getText());

            int Editado = pst.executeUpdate(); // retorna 1 se ok

            if (Editado > 0) {
                JOptionPane.showMessageDialog(null, "Usuário editado com Sucesso!");
                //Limpar os campos do Cadastro

                Nome.setText(null);
                DDD.setText(null);
                Celular.setText(null);
                Email.setText(null);
                Nascimento.setText(null);
                CPF.setText(null);
                Endereco.setText(null);
                Numero.setText(null);
                Bairro.setText(null);
                Complemento.setText(null);
                CEP.setText(null);
                Cidade.setText(null);
                Estado.setText(null);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    private void Visualizar() {
        String sql = "SELECT * FROM tbl_agenda WHERE id=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, ID.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                Nome.setText(rs.getString(2));
                DDD.setText(rs.getString(3));
                Celular.setText(rs.getString(4));
                Email.setText(rs.getString(5));
                Nascimento.setText(rs.getString(6));
                CPF.setText(rs.getString(7));
                Endereco.setText(rs.getString(8));
                Numero.setText(rs.getString(9));
                Bairro.setText(rs.getString(10));
                Complemento.setText(rs.getString(11));
                CEP.setText(rs.getString(12));
                Cidade.setText(rs.getString(13));
                Estado.setText(rs.getString(14));
            } else {
                JOptionPane.showMessageDialog(null, "USUÁRIO NÃO CADASTRADO...");
                // As linhas abaixo limpam os campos do formulário
                Nome.setText(null);
                DDD.setText(null);
                Celular.setText(null);
                Email.setText(null);
                Nascimento.setText(null);
                CPF.setText(null);
                Endereco.setText(null);
                Numero.setText(null);
                Bairro.setText(null);
                Complemento.setText(null);
                CEP.setText(null);
                Cidade.setText(null);
                Estado.setText(null);
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void Apagar() {
        // a estrutura abaixo cnfirma a exclusão do usuário da tabela
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esse usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_NO_OPTION) {
            String sql = "DELETE FROM tbl_agenda WHERE Id=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, ID.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "USUÁRIO APAGADO");
                    //Limpar o formulário
                    Nome.setText(null);
                    DDD.setText(null);
                    Celular.setText(null);
                    Email.setText(null);
                    Nascimento.setText(null);
                    CPF.setText(null);
                    Endereco.setText(null);
                    Numero.setText(null);
                    Bairro.setText(null);
                    Complemento.setText(null);
                    CEP.setText(null);
                    Cidade.setText(null);
                    Estado.setText(null);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        LabelID = new javax.swing.JLabel();
        ID = new javax.swing.JTextField();
        LabelNome = new javax.swing.JLabel();
        Nome = new javax.swing.JTextField();
        LabelDDD = new javax.swing.JLabel();
        DDD = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        LabelEmail = new javax.swing.JLabel();
        Email = new javax.swing.JTextField();
        LabelNascimento = new javax.swing.JLabel();
        LabelCelular = new javax.swing.JLabel();
        Celular = new javax.swing.JTextField();
        LabelEndereco = new javax.swing.JLabel();
        Endereco = new javax.swing.JTextField();
        LabelNumero = new javax.swing.JLabel();
        Numero = new javax.swing.JTextField();
        LabelCPF = new javax.swing.JLabel();
        CPF = new javax.swing.JTextField();
        LabelBairro = new javax.swing.JLabel();
        Bairro = new javax.swing.JTextField();
        LabelComplemento = new javax.swing.JLabel();
        Complemento = new javax.swing.JTextField();
        LabelCEP = new javax.swing.JLabel();
        CEP = new javax.swing.JTextField();
        LabelCidade = new javax.swing.JLabel();
        Cidade = new javax.swing.JTextField();
        LabelEstado = new javax.swing.JLabel();
        Estado = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnVisualizar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnApagar = new javax.swing.JButton();
        Nascimento = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro");

        jTextField1.setText("TELA DE CADASTRO");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        LabelID.setText("ID");

        ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDActionPerformed(evt);
            }
        });

        LabelNome.setText("Nome");

        Nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NomeActionPerformed(evt);
            }
        });

        LabelDDD.setText("DDD");

        DDD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DDDActionPerformed(evt);
            }
        });

        LabelEmail.setText("Email");

        Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailActionPerformed(evt);
            }
        });

        LabelNascimento.setText("Mês Nascimento");

        LabelCelular.setText("Celular");

        LabelEndereco.setText("Endereço");

        LabelNumero.setText("Nº");

        Numero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumeroActionPerformed(evt);
            }
        });

        LabelCPF.setText("CPF");

        LabelBairro.setText("Bairro");

        Bairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BairroActionPerformed(evt);
            }
        });

        LabelComplemento.setText("Complemento");

        Complemento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComplementoActionPerformed(evt);
            }
        });

        LabelCEP.setText("CEP");

        CEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CEPActionPerformed(evt);
            }
        });

        LabelCidade.setText("Cidade");

        LabelEstado.setText("Estado");

        btnAdicionar.setText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnVisualizar.setText("Visualizar");
        btnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnApagar.setText("Apagar");
        btnApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagarActionPerformed(evt);
            }
        });

        Nascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NascimentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LabelDDD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LabelCPF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(29, 29, 29))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LabelID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(37, 37, 37))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LabelBairro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LabelCEP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(29, 29, 29)))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(CEP)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                                                .addGap(47, 47, 47)))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(LabelCidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(Cidade)
                                                .addGap(18, 18, 18)
                                                .addComponent(LabelEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(Estado)
                                                .addGap(18, 18, 18))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                                                .addGap(56, 56, 56)
                                                .addComponent(btnVisualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                                .addGap(34, 34, 34)
                                                .addComponent(btnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(Bairro)
                                        .addGap(18, 18, 18)
                                        .addComponent(LabelComplemento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Complemento)))
                                .addGap(111, 111, 111))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Endereco)
                                .addGap(18, 18, 18)
                                .addComponent(LabelNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Numero, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(103, 103, 103)))
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(DDD)
                        .addGap(28, 28, 28)
                        .addComponent(LabelCelular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(Celular)
                        .addGap(18, 18, 18)
                        .addComponent(LabelEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(152, 152, 152))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ID)
                                .addGap(18, 18, 18)
                                .addComponent(LabelNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(CPF, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LabelNascimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Nascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(231, 231, 231)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LabelID)
                        .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LabelNome)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(LabelCPF)
                    .addComponent(CPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelNascimento)
                    .addComponent(Nascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LabelEmail))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LabelDDD)
                        .addComponent(LabelCelular)
                        .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Endereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelNumero)
                    .addComponent(LabelEndereco))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelBairro)
                    .addComponent(Bairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelComplemento)
                    .addComponent(Complemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelCEP)
                    .addComponent(CEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelCidade)
                    .addComponent(Cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelEstado)
                    .addComponent(Estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar)
                    .addComponent(btnEditar)
                    .addComponent(btnApagar)
                    .addComponent(btnVisualizar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void DDDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DDDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DDDActionPerformed

    private void NomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NomeActionPerformed

    private void IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDActionPerformed

    private void NumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NumeroActionPerformed

    private void BairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BairroActionPerformed

    private void EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailActionPerformed

    private void ComplementoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComplementoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComplementoActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        Adicionar();

    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        Editar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarActionPerformed
        // TODO add your handling code here:
        Visualizar();
    }//GEN-LAST:event_btnVisualizarActionPerformed

    private void btnApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApagarActionPerformed
        // TODO add your handling code here:
        Apagar();
    }//GEN-LAST:event_btnApagarActionPerformed

    private void NascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NascimentoActionPerformed

    private void CEPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CEPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CEPActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

}
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario.class  

.getName()).log(java.util.logging.Level.SEVERE, null, ex);

} catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario.class  

.getName()).log(java.util.logging.Level.SEVERE, null, ex);

} catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario.class  

.getName()).log(java.util.logging.Level.SEVERE, null, ex);

} catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario.class  

.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Bairro;
    private javax.swing.JTextField CEP;
    private javax.swing.JTextField CPF;
    private javax.swing.JTextField Celular;
    private javax.swing.JTextField Cidade;
    private javax.swing.JTextField Complemento;
    private javax.swing.JTextField DDD;
    private javax.swing.JTextField Email;
    private javax.swing.JTextField Endereco;
    private javax.swing.JTextField Estado;
    private javax.swing.JTextField ID;
    private javax.swing.JLabel LabelBairro;
    private javax.swing.JLabel LabelCEP;
    private javax.swing.JLabel LabelCPF;
    private javax.swing.JLabel LabelCelular;
    private javax.swing.JLabel LabelCidade;
    private javax.swing.JLabel LabelComplemento;
    private javax.swing.JLabel LabelDDD;
    private javax.swing.JLabel LabelEmail;
    private javax.swing.JLabel LabelEndereco;
    private javax.swing.JLabel LabelEstado;
    private javax.swing.JLabel LabelID;
    private javax.swing.JLabel LabelNascimento;
    private javax.swing.JLabel LabelNome;
    private javax.swing.JLabel LabelNumero;
    private javax.swing.JTextField Nascimento;
    private javax.swing.JTextField Nome;
    private javax.swing.JTextField Numero;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnApagar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
