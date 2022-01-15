package com.company;

import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Yonetim {

    JLabel toplamB = new JLabel("Toplam Etkinlik: "); JLabel toplamE = new JLabel("Toplam Çalışan: ");

    public JFrame yonetimFrame;
    public JFrame bilgiler2;

    DefaultTableModel model = new DefaultTableModel();
    Object [] kolonlar = {"ID","Ad","Soyad", "Şifre","Telefon","Pozisyon","Doğum Tarihi","Kayıt Tarihi","Cinsiyet"};
    Object[] satirlar = new Object[9];
    JTable calisantablo = new JTable();
    JScrollPane calisanscroll = new JScrollPane(calisantablo);

    DefaultTableModel model2 = new DefaultTableModel();
    Object [] kolonlar2 = {"ID","Tarih","Başlangıç", "Bitiş","Yer","Oluşturan ID","Açıklama"};
    Object[] satirlar2 = new Object[7];
    JTable etkinliktablo = new JTable();
    JScrollPane calisanscroll2 = new JScrollPane(etkinliktablo);

    JLabel calisanAd = new JLabel("Ad: ");
    JLabel calisanSoy = new JLabel("Soyad: ");
    JLabel calisanSif = new JLabel("Şifre: ");
    JLabel calisanTel = new JLabel("Telefon: ");
    JTextField calAd = new JTextField();
    JTextField calSoy = new JTextField();
    JTextField calSif = new JTextField();
    JTextField calTel = new JTextField();
    JLabel calisanPoz = new JLabel("Pozisyon: ");
    String poz[] = {"Yönetim","Ekip Sorumlusu","Personel Sorumlusu", "Developer", "Muhasebe", "Pazarlama", "Teknisyen", "İş Güvenliği Uzmanı"};
    JComboBox pozisyonJ = new JComboBox(poz);
    JLabel calisanCins = new JLabel("Cinsiyet: ");
    String c[]={"E","K"};
    JComboBox cins = new JComboBox(c);
    JLabel calisanDog = new JLabel("Doğum Tarihi: ");
    JLabel gg = new JLabel("GG: ");
    String gun[] = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15",
            "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    JComboBox gunler = new JComboBox(gun);
    JLabel aa = new JLabel("AA: ");
    String ay[] = {"01","02","03","04","05","06","07","08","09","10","11","12"};
    JComboBox aylar = new JComboBox(ay);
    JLabel yil = new JLabel("Yıl: ");
    String yyyy[] = {"2003","2002","2001","2000","1999","1998","1997","1996","1995","1994","1993","1992","1991","1990",
            "1989","1988","1987","1986","1985","1984","1983","1982","1981","1980",
            "1979","1978","1977","1976","1975","1974","1973","1972","1971","1970"};
    JComboBox yillar = new JComboBox(yyyy);
    JButton ekle = new JButton("Ekle");   JButton sil = new JButton("Sil");JButton gunc = new JButton("Güncelle");
    JLabel id = new JLabel("ID: "); JTextField idText=new JTextField();
    JButton temizle = new JButton("Temizle");

    JLabel baslikL = new JLabel("Çalışan Listesi");

    //bilgilerim:

    JLabel adim = new JLabel("İsim: "); JLabel soyadim = new JLabel("Soyisim: "); JLabel telefonum = new JLabel("Telefon: ");
    JLabel adCek = new JLabel("İsim: "); JLabel soyCek = new JLabel("Soyisim: "); JLabel telCek = new JLabel("Telefon: ");

    JLabel ekipid = new JLabel("Kayıt Tarihi: "); JLabel pos = new JLabel("Pozisyon: "); JLabel dogum = new JLabel("Doğ. Tarihi: ");
    JLabel ekipidCek = new JLabel("--- "); JLabel posCek = new JLabel("Pozisyon: "); JLabel dogumCek = new JLabel("--- ");

    JLabel sorumluOlEkip = new JLabel("Cinsiyet: "); JLabel sorumluOlEkipCek = new JLabel("---");

    //etkinlik işlemleri
    JLabel etkinlikid = new JLabel("Etkinlik ID: "); JTextField etkID = new JTextField();
    JButton etkOnay = new JButton("Onayla");
    JButton etkRet = new JButton("Reddet"); JButton etkSil = new JButton("Sil");

    //stats
    String topetk, topper;

    String gelenAd, gelenSoyad, gelenID, gelenPoz;

    JButton getirID = new JButton("Getir"); JButton getirPoz = new JButton("Pozisyon");

    void takvim(){

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

    void yonetim (String girisID, String girisAd, String girisSoyad, String girisPoz){

        gelenAd = girisAd; gelenSoyad=girisSoyad; gelenID=girisID; gelenPoz=girisPoz;

        yonetimFrame = new JFrame("Ofis | Yönetim");

      model.setColumnIdentifiers(kolonlar);
        calisantablo.setBounds(20,220,950,270);
        calisanscroll.setViewportView(calisantablo);
        calisanscroll.setBounds(28,227,1015,322);
        calisanscroll.setVisible(true);
        yonetimFrame.add(calisanscroll);
        //
        model2.setColumnIdentifiers(kolonlar2);
        etkinliktablo.setBounds(20,220,950,270);
        calisanscroll2.setViewportView(etkinliktablo);
        calisanscroll2.setBounds(28,227,1015,322);
        calisanscroll2.setVisible(false);
        yonetimFrame.add(calisanscroll2);
        //


        baslikL.setBounds(30,195,180,40);
        baslikL.setFont(new Font("Calibri", Font.BOLD, 20));
        //idL.setForeground(Color.white);
        yonetimFrame.add(baslikL);

        //

        JLabel idL = new JLabel("ID: "+gelenID);
        idL.setBounds(30,5,100,40);
        idL.setFont(new Font("Calibri", Font.BOLD, 14));
        //idL.setForeground(Color.white);
        yonetimFrame.add(idL);

        JLabel isimL = new JLabel(gelenAd+" "+gelenSoyad, JLabel.CENTER);
        isimL.setBounds(90,5,130,40);
        isimL.setFont(new Font("Calibri", Font.BOLD, 14));
        //idL.setForeground(Color.white);
        yonetimFrame.add(isimL);

        JLabel pozL = new JLabel(gelenPoz, JLabel.CENTER);
        pozL.setBounds(235,5,80,40);
        pozL.setFont(new Font("Calibri", Font.BOLD, 14));
        //idL.setForeground(Color.white);
        yonetimFrame.add(pozL);

        //


        toplamB.setBounds(32,576,250,30);
        toplamB.setFont(new Font("Calibri", Font.BOLD, 16));
        //idL.setForeground(Color.white);
        yonetimFrame.add(toplamB);


        toplamE.setBounds(220,576,250,30);
        toplamE.setFont(new Font("Calibri", Font.BOLD, 16));
        //idL.setForeground(Color.white);
        yonetimFrame.add(toplamE);

        //

        JButton calisanB = new JButton("Bilgilerim");
        calisanB.setFocusPainted(false);
        calisanB.setBounds(350,11,150,21);
        yonetimFrame.add(calisanB);

        calisanB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               bilgilerim2();

            }
        });

        //

        JButton etkinlikB = new JButton("Çalışan İşlemleri");
        etkinlikB.setFocusPainted(false);
        etkinlikB.setBounds(510,11,150,21);
        yonetimFrame.add(etkinlikB);

        etkinlikB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calisanAd.setVisible(true);
                ekle.setVisible(true);sil.setVisible(true);gunc.setVisible(true);
                yillar.setVisible(true);aylar.setVisible(true);aa.setVisible(true);gg.setVisible(true);
                gunler.setVisible(true);
                calisanAd.setVisible(true);calisanSoy.setVisible(true);calisanTel.setVisible(true);calisanSif.setVisible(true);
                calAd.setVisible(true);calSif.setVisible(true);calTel.setVisible(true);calSoy.setVisible(true);
                calisanPoz.setVisible(true);calisanDog.setVisible(true);calisanCins.setVisible(true);
                pozisyonJ.setVisible(true);  cins.setVisible(true); yil.setVisible(true);
                id.setVisible(true); idText.setVisible(true);

                etkinlikid.setVisible(false);        etkID.setVisible(false);
                etkOnay.setVisible(false);
                etkRet.setVisible(false); etkSil.setVisible(false); temizle.setVisible(true); getirPoz.setVisible(true); getirID.setVisible(true);

                calisanscroll2.setVisible(false);
                calisanscroll.setVisible(true);
                baslikL.setText("Çalışan Listesi");
                try {
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "system", "Orc-194-f");
                    Statement mystate = conn.createStatement();
                    model.setRowCount(0);
                    ResultSet myRes = mystate.executeQuery("SELECT * FROM Personel");
                    while (myRes.next()){

                        satirlar [0] = myRes.getString("calisanID");
                        satirlar [1] = myRes.getString("ad");
                        satirlar [2] = myRes.getString("soyad");
                        satirlar [3] = myRes.getString("sifre");
                        satirlar [4] = myRes.getString("telefon");
                        satirlar [5] = myRes.getString("pozisyon");
                        satirlar [6] = myRes.getString("dogumTarihi");
                        satirlar [7] = myRes.getString("iseGirisTarihi");
                        satirlar [8] = myRes.getString("cinsiyet");
                        model.addRow(satirlar);
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } calisantablo.setModel(model);      model.fireTableDataChanged();


            }
        });

        JButton onayB = new JButton("Etkinlik İşlemleri");
        onayB.setFocusPainted(false);
       // onayB.setOpaque(false);
        //onayB.setContentAreaFilled(false);
        onayB.setBounds(670,11,150,21);
        yonetimFrame.add(onayB);

        onayB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                etkinlikid.setVisible(true);        etkID.setVisible(true);
                etkOnay.setVisible(true);
                etkRet.setVisible(true); etkSil.setVisible(true);


                calisanAd.setVisible(false);
                ekle.setVisible(false);sil.setVisible(false);gunc.setVisible(false);
                yillar.setVisible(false);aylar.setVisible(false);aa.setVisible(false);gg.setVisible(false);
                gunler.setVisible(false);
                calisanAd.setVisible(false);calisanSoy.setVisible(false);calisanTel.setVisible(false);calisanSif.setVisible(false);
                calAd.setVisible(false);calSif.setVisible(false);calTel.setVisible(false);calSoy.setVisible(false);
                calisanPoz.setVisible(false);calisanDog.setVisible(false);calisanCins.setVisible(false);
                pozisyonJ.setVisible(false);  cins.setVisible(false); yil.setVisible(false); id.setVisible(false); idText.setVisible(false);

                temizle.setVisible(false); getirPoz.setVisible(false); getirID.setVisible(false);

                calisanscroll2.setVisible(true);
                calisanscroll.setVisible(false);
                baslikL.setText("Etkinlik Listesi");
                try {
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "system", "Orc-194-f");
                    Statement mystate = conn.createStatement();
                    model2.setRowCount(0);
                    ResultSet myRes = mystate.executeQuery("SELECT * FROM EtkinlikDetay");
                    while (myRes.next()){

                        satirlar2 [0] = myRes.getString("etkinlikID");
                        satirlar2 [1] = myRes.getString("tarih");
                        satirlar2 [2] = myRes.getString("baslangicSaat");
                        satirlar2 [3] = myRes.getString("bitisSaat");
                        satirlar2 [4] = myRes.getString("yer");
                        satirlar2 [5] = myRes.getString("olusturanID");
                        satirlar2 [6] = myRes.getString("aciklama");
                        model2.addRow(satirlar2);
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } etkinliktablo.setModel(model2);      model2.fireTableDataChanged();

            }
        });

        JButton takvimB = new JButton("Takvim");
        takvimB.setFocusPainted(false);
        //takvimB.setOpaque(false);
        //takvimB.setContentAreaFilled(false);
        takvimB.setBounds(830,11,150,21);
        yonetimFrame.add(takvimB);

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
        yonetimFrame.add(cikisB);

        cikisB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                yonetimFrame.setVisible(false);
                Giris geriDon = new Giris();
                geriDon.giris();

            }
        });

        //etkinlik işlemleri

        etkinlikid.setBounds(30,65,70,40);
        yonetimFrame.add(etkinlikid);

        etkID.setBounds(100,75,35,20);
        yonetimFrame.add(etkID);

        etkOnay.setBounds(30,120,80,20);
        yonetimFrame.add(etkOnay);

        etkRet.setBounds(120,120,80,20);
        yonetimFrame.add(etkRet);

        etkSil.setBounds(150,75,50,20);
        yonetimFrame.add(etkSil);

        etkSil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "system", "Orc-194-f");
                    String sql = "DELETE FROM EtkinlikDetay " +
                            "WHERE etkinlikID=?";

                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, etkID.getText());
                    statement.executeUpdate();
                    stats();
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


                stats();
            }
        });

        etkinlikid.setVisible(false);        etkID.setVisible(false);
        etkOnay.setVisible(false); etkSil.setVisible(false);
        etkRet.setVisible(false);


        //çalışan işlemleri:

        calisanAd.setBounds(30,50,40,40);
        yonetimFrame.add(calisanAd);


        calisanSoy.setBounds(30,85,50,40);
        //calisanAd.setFont(new Font("Calibri", Font.BOLD, 16));
        //idL.setForeground(Color.white);
        yonetimFrame.add(calisanSoy);


        calisanSif.setBounds(30,120,50,40);
        //calisanAd.setFont(new Font("Calibri", Font.BOLD, 16));
        //idL.setForeground(Color.white);
        yonetimFrame.add(calisanSif);


        calisanTel.setBounds(260,50,55,40);
        //calisanAd.setFont(new Font("Calibri", Font.BOLD, 16));
        //idL.setForeground(Color.white);
        yonetimFrame.add(calisanTel);

        calAd.setBounds(80,60,120,20);
        yonetimFrame.add(calAd);

        calSoy.setBounds(80,95,120,20);
        yonetimFrame.add(calSoy);


        calSif.setBounds(80,130,120,20);
        yonetimFrame.add(calSif);


        calTel.setBounds(325,60,120,20);
        yonetimFrame.add(calTel);



        calisanPoz.setBounds(260,85,65,40);
        //calisanAd.setFont(new Font("Calibri", Font.BOLD, 16));
        //idL.setForeground(Color.white);
        yonetimFrame.add(calisanPoz);


        pozisyonJ.setBounds(325,95,140,20);
        yonetimFrame.add(pozisyonJ);


        calisanCins.setBounds(260,120,65,40);
        yonetimFrame.add(calisanCins);


        cins.setBounds(325,130,40,20);
        yonetimFrame.add(cins);


        calisanDog.setBounds(490,50,140,40);
        yonetimFrame.add(calisanDog);

        id.setBounds(490,85,140,40);
        yonetimFrame.add(id);
        idText.setBounds(515,95,60,20);
        yonetimFrame.add(idText);

        getirID.setBounds(490,130,90,20); yonetimFrame.add(getirID);
        getirPoz.setBounds(585,130,90,20); yonetimFrame.add(getirPoz);

        getirID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "system", "Orc-194-f");

                    model.setRowCount(0);
                    String sqlString = "SELECT * from Personel WHERE calisanID=?";
                    Statement mystate = conn.createStatement();

                    PreparedStatement login = conn.prepareStatement(sqlString);
                    login.setString(1, idText.getText());

                    ResultSet myRes = login.executeQuery();
                    while (myRes.next()){

                        satirlar [0] = myRes.getString("calisanID");
                        satirlar [1] = myRes.getString("ad");
                        satirlar [2] = myRes.getString("soyad");
                        satirlar [3] = myRes.getString("sifre");
                        satirlar [4] = myRes.getString("telefon");
                        satirlar [5] = myRes.getString("pozisyon");
                        satirlar [6] = myRes.getString("dogumTarihi");
                        satirlar [7] = myRes.getString("iseGirisTarihi");
                        satirlar [8] = myRes.getString("cinsiyet");
                        model.addRow(satirlar);
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } calisantablo.setModel(model);      model.fireTableDataChanged();
            }
        });

        getirPoz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "system", "Orc-194-f");

                    model.setRowCount(0);
                    String sqlString = "SELECT * from Personel WHERE pozisyon=?";
                    Statement mystate = conn.createStatement();

                    PreparedStatement login = conn.prepareStatement(sqlString);
                    login.setString(1, String.valueOf((pozisyonJ.getSelectedItem())));

                    ResultSet myRes = login.executeQuery();
                    while (myRes.next()){

                        satirlar [0] = myRes.getString("calisanID");
                        satirlar [1] = myRes.getString("ad");
                        satirlar [2] = myRes.getString("soyad");
                        satirlar [3] = myRes.getString("sifre");
                        satirlar [4] = myRes.getString("telefon");
                        satirlar [5] = myRes.getString("pozisyon");
                        satirlar [6] = myRes.getString("dogumTarihi");
                        satirlar [7] = myRes.getString("iseGirisTarihi");
                        satirlar [8] = myRes.getString("cinsiyet");
                        model.addRow(satirlar);
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } calisantablo.setModel(model);      model.fireTableDataChanged();
            }

        });

        gg.setBounds(580,50,30,40);
        yonetimFrame.add(gg);

        gunler.setBounds(605,60,45,20);
        yonetimFrame.add(gunler);


        aa.setBounds(660,50,30,40);
        yonetimFrame.add(aa);


        aylar.setBounds(685,60,45,20);
        yonetimFrame.add(aylar);


        yil.setBounds(740,50,30,40);
        yonetimFrame.add(yil);


        yillar.setBounds(765,60,70,20);
        yonetimFrame.add(yillar);


        ekle.setBounds(930,60,90,20);
        yonetimFrame.add(ekle);



        ekle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Date date = new Date(); // This object contains the current date value
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                String adi, soyadi, sifresi, telefonu, pozisyonu, dogumu, isegirisi, cinsi;
                adi = (String) calAd.getText();
                soyadi = (String) calSoy.getText();
                        sifresi = (String) calSif.getText();
                        telefonu = (String)  calTel.getText();
                        pozisyonu = (String) (pozisyonJ.getSelectedItem());
                cinsi= (String) (cins.getSelectedItem());

                                dogumu=  (String) (gunler.getSelectedItem()+"/"+aylar.getSelectedItem()+"/"+yillar.getSelectedItem());
                                        isegirisi = (String) (formatter.format(date));

                try {

                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "system", "Orc-194-f");

                    String sql = "INSERT INTO Personel (ad, soyad, sifre, telefon, pozisyon, dogumTarihi, iseGirisTarihi, cinsiyet) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, calAd.getText());
                    statement.setString(2, calSoy.getText());
                    statement.setString(3, calSif.getText());
                    statement.setString(4, calTel.getText());
                    statement.setString(5, String.valueOf((pozisyonJ.getSelectedItem())));
                    statement.setString(6, (gunler.getSelectedItem()+"/"+aylar.getSelectedItem()+"/"+yillar.getSelectedItem()));
                    statement.setString(7, formatter.format(date));
                    statement.setString(8, String.valueOf(cins.getSelectedItem()));

                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        stats();
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }



            }
        });


        sil.setBounds(930,90,90,20);
        yonetimFrame.add(sil);

        sil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "system", "Orc-194-f");
                    String sql = "DELETE FROM Personel " +
                            "WHERE calisanID=?";

                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, idText.getText());
                    statement.executeUpdate();
                    System.out.print("Başarılı");
                    stats();
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        temizle.setBounds(930,150,90,20);
        yonetimFrame.add(temizle);

        temizle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idText.setText("");   calTel.setText("");  calSif.setText("");  calSoy.setText("");  calAd.setText("");
            }
        });

        calisantablo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                idText.setText(model.getValueAt(calisantablo.getSelectedRow(),0).toString());
                calAd.setText((String)model.getValueAt(calisantablo.getSelectedRow(),1));
                calSoy.setText((String)model.getValueAt(calisantablo.getSelectedRow(),2));
                calSif.setText((String)model.getValueAt(calisantablo.getSelectedRow(),3));
                calTel.setText((String)model.getValueAt(calisantablo.getSelectedRow(),4));
                pozisyonJ.setSelectedItem((String)model.getValueAt(calisantablo.getSelectedRow(),5));
                cins.setSelectedItem((String)model.getValueAt(calisantablo.getSelectedRow(),8));
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        gunc.setBounds(930,120,90,20);
        yonetimFrame.add(gunc);

        gunc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "system", "Orc-194-f");
                    String sql = "UPDATE Personel SET ad=?, soyad=?, sifre=?, telefon=?, pozisyon=?" +
                             "WHERE calisanID=?";

                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, calAd.getText());
                    statement.setString(2, calSoy.getText());
                    statement.setString(3, calSif.getText());
                    statement.setString(4, calTel.getText());
                    statement.setString(5, String.valueOf((pozisyonJ.getSelectedItem())));
                    statement.setString(6, idText.getText());
                    statement.executeUpdate();
                    System.out.print("Başarılı");
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });


        //




        JLabel background;
        yonetimFrame.setSize(1088,648);
        yonetimFrame.setLayout(null);
        yonetimFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("back-2.jpg");
        background = new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,-5,1072,620);
        yonetimFrame.add(background);

        yonetimFrame.setLocationRelativeTo(null);
        yonetimFrame.setResizable(false);
        //yonetimFrame.setLayout(null);
        yonetimFrame.setVisible(true);
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

    void stats (){

        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "system", "Orc-194-f");
            Statement mystate = conn.createStatement();

            ResultSet myRes = mystate.executeQuery("SELECT * FROM istatistikler");
            while (myRes.next()){

                topetk = myRes.getString("toplamEtkinlik");
                topper =  myRes.getString("toplamPersonel");


            }

            toplamB.setText("Toplam Etkinlik: "+topetk);
            toplamE.setText("Toplam Personel: "+topper);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    void calisanTablo(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "system", "Orc-194-f");
            Statement mystate = conn.createStatement();
            model.setRowCount(0);
            ResultSet myRes = mystate.executeQuery("SELECT * FROM Personel");
            while (myRes.next()){

                satirlar [0] = myRes.getString("calisanID");
                satirlar [1] = myRes.getString("ad");
                satirlar [2] = myRes.getString("soyad");
                satirlar [3] = myRes.getString("sifre");
                satirlar [4] = myRes.getString("telefon");
                satirlar [5] = myRes.getString("pozisyon");
                satirlar [6] = myRes.getString("dogumTarihi");
                satirlar [7] = myRes.getString("iseGirisTarihi");
                satirlar [8] = myRes.getString("cinsiyet");
                model.addRow(satirlar);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } calisantablo.setModel(model);      model.fireTableDataChanged();

        }
    }


