import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Anamnese extends JFrame {
    private DefaultTableModel tabelaModelo; 
    private JTable tabela;

    public Anamnese() {
        setTitle("Anamnese - Academia");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelEntrada = new JPanel(new GridLayout(5, 2, 5, 5));
        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();
        JLabel lblIdade = new JLabel("Idade:");
        JTextField txtIdade = new JTextField();
        JLabel lblPeso = new JLabel("Peso (kg):");
        JTextField txtPeso = new JTextField();
        JLabel lblAltura = new JLabel("Altura (m):");
        JTextField txtAltura = new JTextField();

        painelEntrada.add(lblNome);
        painelEntrada.add(txtNome);
        painelEntrada.add(lblIdade);
        painelEntrada.add(txtIdade);
        painelEntrada.add(lblPeso);
        painelEntrada.add(txtPeso);
        painelEntrada.add(lblAltura);
        painelEntrada.add(txtAltura);

        JButton btnSalvar = new JButton("Salvar");
        JButton btnLimpar = new JButton("Limpar");

        painelEntrada.add(btnSalvar);
        painelEntrada.add(btnLimpar);

        String[] colunas = { "Nome", "Idade", "Peso (kg)", "Altura (m)" };
        tabelaModelo = new DefaultTableModel(colunas, 0);
        tabela = new JTable(tabelaModelo);
        JScrollPane scrollPane = new JScrollPane(tabela);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                String idade = txtIdade.getText();
                String peso = txtPeso.getText();
                String altura = txtAltura.getText();

                if (nome.isEmpty() || idade.isEmpty() || peso.isEmpty() || altura.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos!", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                //Validação do nome
                try {
                    if (!nome.matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$")) {
                        JOptionPane.showMessageDialog(null, "Nome inválido", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }catch (Exception x){
                        JOptionPane.showMessageDialog(null, "Nome deve só conter letras", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                    }
                
                // Validação da idade
                
                try {
                    int id = Integer.parseInt(idade);
                    if (id < 0 || id > 150) {
                        JOptionPane.showMessageDialog(null, "Idade inválida", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (Exception x) {
                    JOptionPane.showMessageDialog(null, "Idade deve ser um número", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validação do peso
                try {
                    double p = Double.parseDouble(peso);
                    if (p < 25 || p > 635) {
                        JOptionPane.showMessageDialog(null, "Peso inválido", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (Exception x) {
                    JOptionPane.showMessageDialog(null, "Peso deve ser um número", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validação da altura
                try {
                    double alt = Double.parseDouble(altura);
                    if (alt < 0.83 || alt > 2.51) {
                        JOptionPane.showMessageDialog(null, "Altura inválida", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (Exception x) {
                    JOptionPane.showMessageDialog(null, "Altura deve ser um número", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                tabelaModelo.addRow(new Object[] { nome, idade, peso, altura });

                txtNome.setText("");
                txtIdade.setText("");
                txtPeso.setText("");
                txtAltura.setText("");

                JOptionPane.showMessageDialog(null, "Anamnese salva com sucesso!");
            }
        });

        btnLimpar.addActionListener(e -> {
            txtNome.setText("");
            txtIdade.setText("");
            txtPeso.setText("");
            txtAltura.setText("");
        });

        add(painelEntrada, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
