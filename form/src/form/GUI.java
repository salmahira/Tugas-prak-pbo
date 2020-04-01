package form;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener {
    String Nama, jk, Alamat, hobi;
    JRadioButton rd_l, rd_p;
    JScrollPane scroll;
    JComboBox jc_hobi;

    JButton cari    = new JButton();
    JLabel nama     = new JLabel();
    JLabel alamat   = new JLabel();
    JLabel jkelamin = new JLabel();
    JLabel Hobi     = new JLabel();

    JButton simpan  = new JButton();
    JButton ubah    = new JButton();
    JButton hapus   = new JButton();
    JButton baca    = new JButton();
    JButton reset   = new JButton();

    JTextField txtnama  = new JTextField();
    JTextArea TXTalamat = new JTextArea();
    JTextArea TXTtampil = new JTextArea();

    public GUI(){
        Font isi = new Font("Times New Roman", Font.PLAIN, 14);
        cari.setFont(isi);
        nama.setFont(isi);
        alamat.setFont(isi);
        jkelamin.setFont(isi);
        Hobi.setFont(isi);
        simpan.setFont(isi);
        baca.setFont(isi);


        // NAMA MAHASISWA
        nama.setText("NAMA");
        nama.setBounds(50, 20, 100, 25);
        Component add = add(nama);
        txtnama.setBounds(190, 20, 300, 25);
        Component add1 = add(txtnama);

        // GENDER
        jkelamin.setText("JENIS KELAMIN");
        jkelamin.setBounds(50, 60, 150, 25);
        Component add2 = add(jkelamin);
        //radio button
        rd_l = new JRadioButton("Laki-Laki");
        rd_l.setBounds(190, 60, 80, 25);
        Component add3 = add(rd_l);
        rd_p = new JRadioButton("Perempuan");
        rd_p.setBounds(290, 60, 80, 25);
        add(rd_p);

        // ALAMAT
        alamat.setText("ALAMAT");
        alamat.setBounds(50, 100, 100, 25);
        add(alamat);
        TXTalamat.setBounds(190, 100, 300, 70);
        scroll = new JScrollPane(TXTalamat);
        scroll.setBounds(190, 100, 300, 70);
        add(scroll);

        // Hobi
        Hobi.setText("HOBI");
        Hobi.setBounds(50, 190, 150, 25);
        add(Hobi);
        String pilih[] = {"-PILIH-","Ngegame","Makan","Futsallsdsdsdsdsdsdsd"};
        jc_hobi = new JComboBox(pilih);
        jc_hobi.setBounds(190, 190, 300, 25);
        add(jc_hobi);

        simpan.setText("Simpan");
        simpan.setBounds(50, 280, 200, 25);
        simpan.addActionListener(this);
        add(simpan);

        baca.setText("Tampil");
        baca.setBounds(290, 280, 200, 25);
        baca.addActionListener(this);
        add(baca);


        setLayout(null);
        setTitle("Form Biodata Mahasiswa");
        setSize(550, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }


    public void BuatFile() throws IOException {
        String nameFile = "hasil_input.txt";
        FileOutputStream outFile = new FileOutputStream(nameFile);
        try{
            DataOutputStream outStream = new DataOutputStream(outFile);
            outStream.writeUTF("Nama          : "+Nama);
            outStream.writeUTF("Alamat        : "+Alamat);
            outStream.writeUTF("Jenis Kelamin : "+jk);
            outStream.writeUTF("Hobi          : "+hobi);
            outStream.close();
            JOptionPane.showMessageDialog(this, "Data Berhasil Di Simpan");
        }catch (IOException e){
            System.err.println("IOERROR : "+e.getMessage() + "\n");
        }
    }

    public void BacaFile() throws IOException{
        String nameFile = "hasil_input.txt";
        String nama;
        String alamat;
        String jkelamin;
        String hobi;
        String isi;

        try{
            FileInputStream inFile      = new FileInputStream(nameFile);
            DataInputStream inStream    = new DataInputStream(inFile);
            nama        = inStream.readUTF();
            alamat      = inStream.readUTF();
            jkelamin    = inStream.readUTF();
            hobi        = inStream.readUTF();
            isi = nama +"\n"+ alamat +"\n"+ hobi +"\n"+ jkelamin;
            inStream.close();
            System.out.println(isi);
        }catch (FileNotFoundException e){
            System.err.println("File "+nameFile +"Tidak Ada! \n");
        }catch (IOException ex ){
            System.err.println("IOERROR : "+ex.getMessage() + "\n");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)  {
        if(e.getActionCommand().equals("Simpan")){
            Nama = txtnama.getText();
            Alamat = TXTalamat.getText();
            if(rd_l.isSelected()){
                jk = "Pria";
            }else{
                jk = "Wanita";
            }
            hobi = (String) jc_hobi.getSelectedItem();
            try {
                BuatFile();
            } catch (IOException ex) {}

        }else if(e.getActionCommand().equals("Tampil")){
            try {
                BacaFile();
            } catch (IOException ex) {}
        }else{
            dispose();
        }
    }
}

