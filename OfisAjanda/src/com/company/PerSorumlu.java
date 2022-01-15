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

public class PerSorumlu {

    public JFrame perSorFrame;
    public JFrame bilgiler;

    String topetk1, topper1;

    JButton katilimciB = new JButton("Katılımcılar");

    JLabel toplamB = new JLabel();
    JLabel toplamE = new JLabel();

    public JFrame bilgiler2;

    JLabel baslikL = new JLabel("Etkinlik Listesi");

    DefaultTableModel model = new DefaultTableModel();
    Object [] kolonlar = {"ID","Tarih","Başlangıç", "Bitiş","Yer","Oluşturan ID","Açıklama"};
    Object[] satirlar = new Object[7];
    JTable etkinliktablo = new JTable();
    JScrollPane calisanscroll = new JScrollPane(etkinliktablo);

    DefaultTableModel model2 = new DefaultTableModel();
    Object [] kolonlar2 = {"ID","Ad","Soyad", "Şifre","Telefon","Pozisyon","Doğum Tarihi","Kayıt Tarihi","Cinsiyet"};
    Object[] satirlar2 = new Object[9];
    JTable calisantablo = new JTable();
    JScrollPane calisanscroll2 = new JScrollPane(calisantablo);

    DefaultTableModel modelKatilim = new DefaultTableModel();
    Object [] kolonlarKatil = {"Çalışan ID","Ad","Soyad", "Pozisyon"};
    Object[] satirlarKatil = new Object[4];
    JTable katilTablo = new JTable();
    JScrollPane katilScroll = new JScrollPane(katilTablo);

    JLabel tarih = new JLabel("Tarih: ");

    JLabel gg = new JLabel("GG: ");
    String gun[] = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15",
            "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    JComboBox gunler = new JComboBox(gun);
    JLabel aa = new JLabel("AA: ");
    String ay[] = {"01","02","03","04","05","06","07","08","09","10","11","12"};
    JComboBox aylar = new JComboBox(ay);
    JLabel yil = new JLabel("Yıl: ");
    String yyyy[] = {"2022"};
    JComboBox yillar = new JComboBox(yyyy);

    JLabel bas = new JLabel("Başlangıç: ");
    String saat[] = {"08.00","09.00","10.00","11.00","12.00","13.00","14.00","15.00","16.00","17.00","18.00","19.00",
    "20.00","21.00","22.00","23.00","00.00"};
    JComboBox baslangic = new JComboBox(saat);

    JLabel bit = new JLabel("Bitiş: ");
    JComboBox bitis = new JComboBox(saat);

    String yerListe[] = {"(20) Toplantı Salonu (1. Kat)", "(40) Toplantı Salonu (2. Kat)", "(50) Toplantı Salonu (3. Kat)",
            "(20) Etkinlik Salonu (1. Kat)", "(50) Etkinlik Salonu (1. Kat)", "(100) Etkinlik Salonu (2. Kat)", "(50) Etkinlik Salonu (3. Kat)",
            "(20) Proje Ofisi (1. Kat)", "(50) Proje Ofisi (1. Kat)", "(30) Proje Ofisi (2. Kat)", "(20) Proje Ofisi (3. Kat)",
    };
    JComboBox yer = new JComboBox(yerListe);
    JLabel yerLabel = new JLabel("Yer: ");

    JLabel acikLabel = new JLabel("Açıklama: ");
    JTextField acikText = new JTextField();

    JLabel etkIDLabel = new JLabel("ID: ");
    JTextField etkIDText = new JTextField();
    JButton etkEkle = new JButton("Ekle");
    JButton etkGunc = new JButton("Güncelle");
    JLabel calisanIDLabel = new JLabel("Çalışan ID: ");
    JTextField calisanIDText = new JTextField();
    JButton calisanEkle = new JButton("Ekle");
    JButton calisanSil = new JButton("Sil");

    //bilgilerim:

    JLabel adim = new JLabel("İsim: "); JLabel soyadim = new JLabel("Soyisim: "); JLabel telefonum = new JLabel("Telefon: ");
    JLabel adCek = new JLabel("İsim: "); JLabel soyCek = new JLabel("Soyisim: "); JLabel telCek = new JLabel("Telefon: ");

    JLabel ekipid = new JLabel("Kayıt Tarihi: "); JLabel pos = new JLabel("Pozisyon: "); JLabel dogum = new JLabel("Doğ. Tarihi: ");
    JLabel ekipidCek = new JLabel("--- "); JLabel posCek = new JLabel("Pozisyon: "); JLabel dogumCek = new JLabel("--- ");

    JLabel sorumluOlEkip = new JLabel("Cinsiyet: "); JLabel sorumluOlEkipCek = new JLabel("---");

    String gelenAd, gelenSoyad, gelenID, gelenPoz;



    void perSor(String girisID, String girisAd, String girisSoyad, String girisPoz) {

        gelenAd = girisAd; gelenSoyad=girisSoyad; gelenID=girisID; gelenPoz=girisPoz;
        perSorFrame = new JFrame("Ofis | Personel Sorumlusu");

        model.setColumnIdentifiers(kolonlar);
        etkinliktablo.setBounds(20,220,950,270);
        calisanscroll.setViewportView(etkinliktablo);
        calisanscroll.setBounds(28,227,1015,322);
        calisanscroll.setVisible(true);
        perSorFrame.add(calisanscroll);
        //
        model2.setColumnIdentifiers(kolonlar2);
        calisantablo.setBounds(20,220,950,270);
        calisanscroll2.setViewportView(calisantablo);
        calisanscroll2.setBounds(28,227,1015,322);
        calisanscroll2.setVisible(false);
        perSorFrame.add(calisanscroll2);
        //
        modelKatilim.setColumnIdentifiers(kolonlarKatil);
        katilTablo.setBounds(20,220,950,270);
        katilScroll.setViewportView(katilTablo);
        katilScroll.setBounds(28,227,1015,322);
        katilScroll.setVisible(false);
        perSorFrame.add(katilScroll);


        baslikL.setBounds(30, 195, 500, 40);
        baslikL.setFont(new Font("Calibri", Font.BOLD, 20));
        //idL.setForeground(Color.white);
        perSorFrame.add(baslikL);

        //

        JLabel idL = new JLabel("ID: "+gelenID);
        idL.setBounds(30, 5, 100, 40);
        idL.setFont(new Font("Calibri", Font.BOLD, 14));
        //idL.setForeground(Color.white);
        perSorFrame.add(idL);

        JLabel isimL = new JLabel(gelenAd+" "+gelenSoyad, JLabel.CENTER);
        isimL.setBounds(90, 5, 130, 40);
        isimL.setFont(new Font("Calibri", Font.BOLD, 14));
        //idL.setForeground(Color.white);
        perSorFrame.add(isimL);

        JLabel pozL = new JLabel(gelenPoz, JLabel.CENTER);
        pozL.setBounds(235, 5, 80, 40);
        pozL.setFont(new Font("Calibri", Font.BOLD, 14));
        //idL.setForeground(Color.white);
        perSorFrame.add(pozL);

        //


        toplamB.setBounds(32, 576, 250, 30);
        toplamB.setFont(new Font("Calibri", Font.BOLD, 16));
        //idL.setForeground(Color.white);
        perSorFrame.add(toplamB);


        toplamE.setBounds(220, 576, 250, 30);
        toplamE.setFont(new Font("Calibri", Font.BOLD, 16));
        //idL.setForeground(Color.white);
        perSorFrame.add(toplamE);

        katilimciB.setBounds(650,140,110,20);perSorFrame.add(katilimciB);

        katilimciB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calisanscroll2.setVisible(false);
                calisanscroll.setVisible(false);
                katilScroll.setVisible(true);
                baslikL.setText("Katılımcı Listesi - "+ "Etkinlik: "+etkIDText.getText());

                try {
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "system", "Orc-194-f");
                    modelKatilim.setRowCount(0);
                    String sql = "SELECT * FROM EtkinlikKatilimci WHERE etkID=?";

                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, etkIDText.getText());

                    ResultSet myRes = statement.executeQuery();
                    while (myRes.next()){

                        satirlarKatil [0] = myRes.getString("katilimciID");
                        String calID = myRes.getString("katilimciID");
                        String sonuc[] = calBilgiGetir(calID);
                        satirlarKatil [1] = sonuc[0];
                        satirlarKatil [2] = sonuc[1];
                        satirlarKatil [3] = sonuc[2];
                        modelKatilim.addRow(satirlarKatil);
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } katilTablo.setModel(modelKatilim);      modelKatilim.fireTableDataChanged();

            }
        });

        JButton calisanB = new JButton("Bilgilerim");
        calisanB.setFocusPainted(false);
        // calisanB.setOpaque(false);
        // calisanB.setContentAreaFilled(false);
        calisanB.setBounds(350, 11, 150, 21);
        perSorFrame.add(calisanB);

        calisanB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bilgilerim2();
            }
        });

        JButton etkinlikB = new JButton("Çalışan Listesi");
        etkinlikB.setFocusPainted(false);
        //  etkinlikB.setOpaque(false);
        //etkinlikB.setContentAreaFilled(false);
        etkinlikB.setBounds(510, 11, 150, 21);
        perSorFrame.add(etkinlikB);

        etkinlikB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calisanscroll.setVisible(false); calisanscroll2.setVisible(true);
                baslikL.setText("Çalışan Listesi");
                try {
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "system", "Orc-194-f");
                    Statement mystate = conn.createStatement();
                    model2.setRowCount(0);
                    ResultSet myRes = mystate.executeQuery("SELECT * FROM Personel");
                    while (myRes.next()){

                        satirlar2 [0] = myRes.getString("calisanID");
                        satirlar2 [1] = myRes.getString("ad");
                        satirlar2 [2] = myRes.getString("soyad");
                        satirlar2 [3] = myRes.getString("sifre");
                        satirlar2 [4] = myRes.getString("telefon");
                        satirlar2 [5] = myRes.getString("pozisyon");
                        satirlar2 [6] = myRes.getString("dogumTarihi");
                        satirlar2 [7] = myRes.getString("iseGirisTarihi");
                        satirlar2 [8] = myRes.getString("cinsiyet");
                        model.addRow(satirlar2);
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } calisantablo.setModel(model2);      model2.fireTableDataChanged();

            }
        });

        tarih.setBounds(30,50,40,40);
        perSorFrame.add(tarih);
        bas.setBounds(30,85,85,40);
        perSorFrame.add(bas);
        bit.setBounds(30,120,85,40);
        perSorFrame.add(bit);

        baslangic.setBounds(100,95,75,20);
        perSorFrame.add(baslangic);
        bitis.setBounds(100,130,75,20);
        perSorFrame.add(bitis);

        gg.setBounds(70,50,30,40);
        perSorFrame.add(gg);
        gunler.setBounds(100,60,45,20);
        perSorFrame.add(gunler);
        aa.setBounds(155,50,30,40);
        perSorFrame.add(aa);
        aylar.setBounds(185,60,45,20);
        perSorFrame.add(aylar);
        yil.setBounds(240,50,30,40);
        perSorFrame.add(yil);
        yillar.setBounds(270,60,70,20);
        perSorFrame.add(yillar);

        yerLabel.setBounds(360,60,70,20); perSorFrame.add(yerLabel);
        yer.setBounds(435,60,190,20); perSorFrame.add(yer);

        acikLabel.setBounds(360,85,85,40);
        perSorFrame.add(acikLabel);
        acikText.setBounds(435,95,190,20);perSorFrame.add(acikText);

        etkIDLabel.setBounds(360,130,85,40);perSorFrame.add(etkIDLabel);
        etkIDText.setBounds(390,140,30,20);perSorFrame.add(etkIDText);
        etkEkle.setBounds(435,140,90,20);perSorFrame.add(etkEkle);

        etkEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//tarih, baslangicSaat, bitisSaat, yer, olusturanID

                try {
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "system", "Orc-194-f");

                    String sql = "INSERT INTO EtkinlikDetay (tarih, baslangicSaat, bitisSaat, yer, olusturanID, aciklama) VALUES (?, ?, ?, ?, ?, ?)";

                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, gunler.getSelectedItem()+"/"+aylar.getSelectedItem()+"/"+yillar.getSelectedItem());
                    statement.setString(2, String.valueOf(baslangic.getSelectedItem()));
                    statement.setString(3, String.valueOf(bitis.getSelectedItem()));
                    statement.setString(4, String.valueOf(yer.getSelectedItem()));
                    statement.setString(5, gelenID);
                    statement.setString(6, acikText.getText());

                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("A new data was inserted successfully!");
                        stats();
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        etkGunc.setBounds(540,140,90,20);perSorFrame.add(etkGunc);

        etkGunc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "system", "Orc-194-f");
                    String sql = "UPDATE EtkinlikDetay SET tarih=? baslangicSaat=? bitisSaat=?, yer=?, aciklama=? WHERE etkinlikID=?";

                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, gunler.getSelectedItem()+"/"+aylar.getSelectedItem()+"/"+yillar.getSelectedItem());
                    statement.setString(2, String.valueOf(baslangic.getSelectedItem()));
                    statement.setString(3, String.valueOf(bitis.getSelectedItem()));
                    statement.setString(4, String.valueOf(yer.getSelectedItem()));
                    statement.setString(5, acikText.getText());
                    statement.setString(6, etkIDText.getText());
                    statement.executeUpdate();
                    System.out.print("Başarılı");
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });


        calisanIDLabel.setBounds(650,50,85,40);perSorFrame.add(calisanIDLabel);
        calisanIDText.setBounds(725,60,30,20);perSorFrame.add(calisanIDText);

        calisanEkle.setBounds(650,95,85,20);perSorFrame.add(calisanEkle);
        calisanSil.setBounds(755,95,85,20);perSorFrame.add(calisanSil);

        calisanEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "system", "Orc-194-f");

                    String sql = "INSERT INTO EtkinlikKatilimci (etkID, katilimciID) VALUES (?, ?)";

                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, etkIDText.getText());
                    statement.setString(2, calisanIDText.getText());

                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("A new data was inserted successfully!");
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        calisanSil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                try {
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "system", "Orc-194-f");
                    String sql = "DELETE FROM EtkinlikKatilimci " +
                            "WHERE katilimciID=?";

                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, calisanIDText.getText());
                    statement.executeUpdate();
                    System.out.print("Başarılı");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        JButton onayB = new JButton("Etkinlik İşlemleri");
        onayB.setFocusPainted(false);
        // onayB.setOpaque(false);
        //onayB.setContentAreaFilled(false);
        onayB.setBounds(670, 11, 150, 21);
        perSorFrame.add(onayB);

        onayB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calisanscroll2.setVisible(false);
                calisanscroll.setVisible(true);
                baslikL.setText("Etkinlik Listesi");
                try {
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "system", "Orc-194-f");
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
        });

        JButton takvimB = new JButton("Takvim");
        takvimB.setFocusPainted(false);
        //takvimB.setOpaque(false);
        //takvimB.setContentAreaFilled(false);
        takvimB.setBounds(830, 11, 150, 21);
        perSorFrame.add(takvimB);

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
        cikisB.setBounds(950, 11, 150, 21);
        cikisB.setBorderPainted(false);
        perSorFrame.add(cikisB);

        cikisB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                perSorFrame.setVisible(false);
                Giris geriDon = new Giris();
                geriDon.giris();

            }
        });

        JLabel background;
        perSorFrame.setSize(1088, 648);
        perSorFrame.setLayout(null);
        perSorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("back-2.jpg");
        background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0, -5, 1072, 620);
        perSorFrame.add(background);

        perSorFrame.setLocationRelativeTo(null);
        perSorFrame.setResizable(false);
        perSorFrame.setVisible(true);

    }

    void takvim() {

        JFrame frame = new JFrame("Takvim");
        JPanel panel = new JPanel();

        frame.setBounds(920, 120, 250, 74);

        JXDatePicker picker = new JXDatePicker();
        picker.setDate(Calendar.getInstance().getTime());
        picker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));

        panel.add(picker);
        frame.getContentPane().add(panel);

        frame.setVisible(true);
    }

    void bilgilerim() {

        //bilgilerim:

        bilgiler = new JFrame("Bilgilerim");


        adim.setBounds(30, 50, 70, 40);
        bilgiler.add(adim);

        adCek.setBounds(85, 50, 70, 40);
        bilgiler.add(adCek);


        soyadim.setBounds(30, 85, 80, 40);
        bilgiler.add(soyadim);

        soyCek.setBounds(85, 85, 70, 40);
        bilgiler.add(soyCek);


        telefonum.setBounds(30, 120, 70, 40);
        bilgiler.add(telefonum);

        telCek.setBounds(85, 120, 70, 40);
        bilgiler.add(telCek);

        //

        ekipid.setBounds(30, 155, 70, 40);
        bilgiler.add(ekipid);

        ekipidCek.setBounds(85, 155, 70, 40);
        bilgiler.add(ekipidCek);


        pos.setBounds(30, 190, 70, 40);
        bilgiler.add(pos);

        posCek.setBounds(85, 190, 120, 40);
        bilgiler.add(posCek);


        dogum.setBounds(30, 225, 70, 40);
        bilgiler.add(dogum);

        dogumCek.setBounds(85, 225, 80, 40);
        bilgiler.add(dogumCek);


        sorumluOlEkip.setBounds(30, 260, 150, 40);
        bilgiler.add(sorumluOlEkip);

        sorumluOlEkipCek.setBounds(85, 260, 70, 40);
        bilgiler.add(sorumluOlEkipCek);


        JLabel background;
        bilgiler.setSize(366, 500);
        bilgiler.setLayout(null);
        //bilgiler.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("back-3.jpg");
        background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0, 0, 366, 500);
        bilgiler.add(background);

        bilgiler.setLocationRelativeTo(null);
        bilgiler.setResizable(false);
        bilgiler.setVisible(true);

    }

    void stats() {

        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "system", "Orc-194-f");
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

        calisanscroll2.setVisible(false);
        calisanscroll.setVisible(true);
        baslikL.setText("Etkinlik Listesi");
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "system", "Orc-194-f");
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
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "system", "Orc-194-f");

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

    public static String[] calBilgiGetir(String calisanID) {

        String[] bilgiler = new String[3];

        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "system", "Orc-194-f");


            String sqlString = "SELECT * from Personel WHERE calisanID=?";

            PreparedStatement login = conn.prepareStatement(sqlString);
            login.setString(1, calisanID);

            ResultSet myRes = login.executeQuery();
            while (myRes.next()){

                bilgiler [0] = myRes.getString("ad");
                bilgiler [1] = myRes.getString("soyad");
                bilgiler [2] = myRes.getString("pozisyon");

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

            return bilgiler;
    }


    }

