package com.company;

import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Personel {

    public JFrame personelFrame;
    public JFrame bilgiler;

    JLabel toplamE = new JLabel("");    JLabel toplamB = new JLabel("");
    String topetk1, topper1;

    String gelenAd, gelenSoyad, gelenID, gelenPoz;
    public JFrame bilgiler2;

    DefaultTableModel model = new DefaultTableModel();
    Object [] kolonlar = {"ID","Tarih","Başlangıç", "Bitiş","Yer","Oluşturan ID","Açıklama"};
    Object[] satirlar = new Object[7];
    JTable etkinliktablo = new JTable();
    JScrollPane calisanscroll = new JScrollPane(etkinliktablo);

    JLabel adim = new JLabel("İsim: "); JLabel soyadim = new JLabel("Soyisim: "); JLabel telefonum = new JLabel("Telefon: ");
    JLabel adCek = new JLabel("İsim: "); JLabel soyCek = new JLabel("Soyisim: "); JLabel telCek = new JLabel("Telefon: ");

    JLabel ekipid = new JLabel("Kayıt Tarihi: "); JLabel pos = new JLabel("Pozisyon: "); JLabel dogum = new JLabel("Doğ. Tarihi: ");
    JLabel ekipidCek = new JLabel("--- "); JLabel posCek = new JLabel("Pozisyon: "); JLabel dogumCek = new JLabel("--- ");

    JLabel sorumluOlEkip = new JLabel("Cinsiyet: "); JLabel sorumluOlEkipCek = new JLabel("---");


    void personel(String girisID, String girisAd, String girisSoyad, String girisPoz){

        gelenAd = girisAd; gelenSoyad=girisSoyad; gelenID=girisID; gelenPoz=girisPoz;

        personelFrame = new JFrame("Ofis | Personel");

        model.setColumnIdentifiers(kolonlar);
        etkinliktablo.setBounds(20,220,950,270);
        calisanscroll.setViewportView(etkinliktablo);
        calisanscroll.setBounds(28,227,1015,322);
        calisanscroll.setVisible(true);
        personelFrame.add(calisanscroll);
        //

        JLabel baslikL = new JLabel("Etkinlik Listesi");
        baslikL.setBounds(30,195,180,40);
        baslikL.setFont(new Font("Calibri", Font.BOLD, 20));
        //idL.setForeground(Color.white);
        personelFrame.add(baslikL);

        //

        JLabel idL = new JLabel("ID: "+gelenID);
        idL.setBounds(30,5,100,40);
        idL.setFont(new Font("Calibri", Font.BOLD, 14));
        //idL.setForeground(Color.white);
        personelFrame.add(idL);

        JLabel isimL = new JLabel(gelenAd+" "+gelenSoyad, JLabel.CENTER);
        isimL.setBounds(90,5,130,40);
        isimL.setFont(new Font("Calibri", Font.BOLD, 14));
        personelFrame.add(isimL);

        JLabel pozL = new JLabel(gelenPoz, JLabel.CENTER);
        pozL.setBounds(235,5,80,40);
        pozL.setFont(new Font("Calibri", Font.BOLD, 14));
        personelFrame.add(pozL);

        //

        toplamB.setBounds(32,576,250,30);
        toplamB.setFont(new Font("Calibri", Font.BOLD, 16));
        personelFrame.add(toplamB);

        toplamE.setBounds(220,576,250,30);
        toplamE.setFont(new Font("Calibri", Font.BOLD, 16));
        personelFrame.add(toplamE);

        JButton calisanB = new JButton("Bilgilerim");
        calisanB.setFocusPainted(false);
        calisanB.setBounds(350,11,150,21);
        personelFrame.add(calisanB);

        calisanB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bilgilerim2();
            }
        });

        JButton etkinlikB = new JButton("Davetler");
        etkinlikB.setFocusPainted(false);
        //  etkinlikB.setOpaque(false);
        //etkinlikB.setContentAreaFilled(false);
        etkinlikB.setBounds(510,11,150,21);
        personelFrame.add(etkinlikB);

        JButton onayB = new JButton("Etkinlik Listesi");
        onayB.setFocusPainted(false);
        // onayB.setOpaque(false);
        //onayB.setContentAreaFilled(false);
        onayB.setBounds(670,11,150,21);
        personelFrame.add(onayB);

        JButton takvimB = new JButton("Takvim");
        takvimB.setFocusPainted(false);
        //takvimB.setOpaque(false);
        //takvimB.setContentAreaFilled(false);
        takvimB.setBounds(830,11,150,21);
        personelFrame.add(takvimB);

        takvimB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                takvim();
            }
        });

        JButton cikisB = new JButton("Çıkış");
        cikisB.setFocusPainted(false);
        cikisB.setOpaque(false);
        cikisB.setContentAreaFilled(false);
        cikisB.setBounds(950,11,150,21);
        cikisB.setBorderPainted(false);
        personelFrame.add(cikisB);

        cikisB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                personelFrame.setVisible(false);
                Giris geriDon = new Giris();
                geriDon.giris();

            }
        });

        JLabel background;
        personelFrame.setSize(1088,648);
        personelFrame.setLayout(null);
        personelFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("back-2.jpg");
        background = new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,-5,1072,620);
        personelFrame.add(background);

        personelFrame.setLocationRelativeTo(null);
        personelFrame.setResizable(false);
        //yonetimFrame.setLayout(null);
        personelFrame.setVisible(true);

    }

    void takvim(){

        JFrame frame = new JFrame("Takvim");
        JPanel panel = new JPanel();

//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(920, 120, 250, 74);

        JXDatePicker picker = new JXDatePicker();
        picker.setDate(Calendar.getInstance().getTime());
        picker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));

        panel.add(picker);
        frame.getContentPane().add(panel);

        frame.setVisible(true);
    }
    void bilgilerim2(){

        //bilgilerim:

        bilgiler2 = new JFrame("Bilgilerim");


        adim.setBounds(30,30,70,40);
        bilgiler2.add(adim);

        adCek.setBounds(65,30,70,40);
        bilgiler2.add(adCek);


        soyadim.setBounds(30,65,80,40);
        bilgiler2.add(soyadim);

        soyCek.setBounds(85,65,70,40);
        bilgiler2.add(soyCek);


        telefonum.setBounds(30,100,70,40);
        bilgiler2.add(telefonum);

        telCek.setBounds(80,100,85,40);
        bilgiler2.add(telCek);

        //

        ekipid.setBounds(30,135,70,40);
        bilgiler2.add(ekipid);

        ekipidCek.setBounds(100,135,70,40);
        bilgiler2.add(ekipidCek);



        pos.setBounds(30,170,70,40);
        bilgiler2.add(pos);

        posCek.setBounds(90,170,120,40);
        bilgiler2.add(posCek);



        dogum.setBounds(30,205,70,40);
        bilgiler2.add(dogum);

        dogumCek.setBounds(100,205,80,40);
        bilgiler2.add(dogumCek);


        sorumluOlEkip.setBounds(30,240,150,40);
        bilgiler2.add(sorumluOlEkip);

        sorumluOlEkipCek.setBounds(85,240,70,40);
        bilgiler2.add(sorumluOlEkipCek);

        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:/", "system", "");

            String sqlString = "SELECT * from Personel WHERE calisanID=?";
            Statement mystate = conn.createStatement();

            PreparedStatement login = conn.prepareStatement(sqlString);
            login.setString(1, gelenID);

            ResultSet myRes = login.executeQuery();
            while (myRes.next()){

                adCek.setText(myRes.getString("ad"));
                soyCek.setText(myRes.getString("soyad"));telCek.setText(myRes.getString("telefon"));
                ekipidCek.setText(myRes.getString("iseGirisTarihi"));
                posCek.setText(myRes.getString("pozisyon"));
                dogumCek.setText(myRes.getString("dogumTarihi"));sorumluOlEkipCek.setText(myRes.getString("cinsiyet"));


            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        JLabel background;
        bilgiler2.setSize(320,350);
        bilgiler2.setLayout(null);
        //bilgiler.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("back-3.jpg");
        background = new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,0,320,350);
        bilgiler2.add(background);

        bilgiler2.setLocationRelativeTo(null);
        bilgiler2.setResizable(false);
        bilgiler2.setVisible(true);

    }

    void stats() {

        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:/", "system", "");
            Statement mystate = conn.createStatement();

            ResultSet myRes = mystate.executeQuery("SELECT * FROM istatistikler");
            while (myRes.next()) {

                topetk1 = myRes.getString("toplamEtkinlik");
                topper1 = myRes.getString("toplamPersonel");

            }

            toplamB.setText("Toplam Etkinlik: " + topetk1);
            toplamE.setText("Toplam Personel: " + topper1);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void etkinliktab () {

        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:/", "system", "");
            Statement mystate = conn.createStatement();
            model.setRowCount(0);
            ResultSet myRes = mystate.executeQuery("SELECT * FROM EtkinlikDetay");
            while (myRes.next()){

                satirlar [0] = myRes.getString("etkinlikID");
                satirlar [1] = myRes.getString("tarih");
                satirlar [2] = myRes.getString("baslangicSaat");
                satirlar [3] = myRes.getString("bitisSaat");
                satirlar [4] = myRes.getString("yer");
                satirlar [5] = myRes.getString("olusturanID");
                satirlar [6] = myRes.getString("aciklama");
                model.addRow(satirlar);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } etkinliktablo.setModel(model);      model.fireTableDataChanged();


    }
}


