package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class Giris {

    public static void main(String[] args) {

       giris();

    }

    static void giris() {

    JFrame girisFrame = new JFrame("Ofis");

    JLabel kulGirisLabel = new JLabel("Kullanıcı Girişi");
    kulGirisLabel.setBounds(240,50, 200, 30);
    kulGirisLabel.setFont(new Font("Calibri", Font.BOLD, 20));
    girisFrame.add(kulGirisLabel);

    JButton girisButon = new JButton("Giriş");
    girisButon.setFocusPainted(false);
    girisButon.setOpaque(false);
    girisButon.setContentAreaFilled(false);
    girisButon.setBounds(375,235, 60, 30);
    girisFrame.add(girisButon);

    JLabel idLabel = new JLabel("ID:");
    idLabel.setBounds(325,165,100,25);
    girisFrame.add(idLabel);

    JTextField idText = new JTextField();
    idText.setBounds(375,165,100,25);
    idText.setOpaque(false);
    girisFrame.add(idText);

    JLabel sifreLabel = new JLabel("Şifre:");
    sifreLabel.setBounds(325,200,100,25);
    girisFrame.add(sifreLabel);

    JPasswordField sifreText = new JPasswordField();
    sifreText.setBounds(375,200,100,25);
    sifreText.setOpaque(false);
    girisFrame.add(sifreText);

    girisButon.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {


            String kulID = idText.getText();
            String kulSif = String.valueOf(sifreText.getPassword());

            try {
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin://l@ocalhost:----/-----", "system", "password");


                String sqlString = "SELECT * from Personel WHERE calisanID=? and sifre=?";

                PreparedStatement login = conn.prepareStatement(sqlString);
                login.setString(1, kulID);
                login.setString(2, kulSif);

                ResultSet rs = login.executeQuery();

                while(rs.next()){
                   String pozisyon = rs.getString("pozisyon");
                   String gelenID =  rs.getString("calisanID");
                    String gelenSif =  rs.getString("sifre");
                    String gelenAd = rs.getString("ad");
                    String gelenSoyad = rs.getString("soyad");


                    if(pozisyon.equals("Yönetim") && kulID.equals(gelenID) && kulSif.equals(gelenSif)) {
                        idText.setText("");
                       sifreText.setText("");
                        girisFrame.setVisible(false);
                       Yonetim yonet = new Yonetim();
                       yonet.yonetim(gelenID, gelenAd, gelenSoyad, pozisyon);
                       yonet.calisanTablo();
                       yonet.stats();

                   }

                   else if (pozisyon.equals("Personel Sorumlusu") && kulID.equals(gelenID) && kulSif.equals(gelenSif)){
                       idText.setText("");
                       sifreText.setText("");
                       girisFrame.setVisible(false);
                       PerSorumlu perSoru = new PerSorumlu();
                       perSoru.perSor(gelenID, gelenAd, gelenSoyad, pozisyon);
                       perSoru.etkinliktab();
                        perSoru.stats();

                    }

                   else if (!pozisyon.equals("Yönetim") && !pozisyon.equals("Personel Sorumlusu") && kulID.equals(gelenID) && kulSif.equals(gelenSif)){
                       idText.setText("");
                       sifreText.setText("");
                       girisFrame.setVisible(false);
                       Personel pers = new Personel();
                     pers.personel(gelenID, gelenAd, gelenSoyad, pozisyon);
                     pers.etkinliktab();
                      pers.stats();

                   }

                   else {

                        showMessageDialog(null, "Yanlış ID-şifre kombinasyonu!" );
                   }
                }


                } catch (SQLException sqlException) {
                sqlException.printStackTrace();  showMessageDialog(null, "Yanlış ID-şifre kombinasyonu!");
            }

        }

    });

    JLabel background;
    girisFrame.setSize(600,445);
    girisFrame.setLayout(null);
    girisFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    ImageIcon img = new ImageIcon("back-giris-yeni.jpg");
    background = new JLabel("",img,JLabel.CENTER);
    background.setBounds(0,-30,600,475);
    girisFrame.add(background);

    girisFrame.setLocationRelativeTo(null);
    girisFrame.setResizable(false);
    girisFrame.setVisible(true);

}

}

