/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import org.imgscalr.Scalr;

/**
 *
 * @author Usuario
 */
public class GUI_Interface extends javax.swing.JFrame {

    private String Link_1, Link_2, Link_3, Link_4, Link_5;
    private String LinkIgnitial, LinkGlobal;
    private String RutaGuardarMiniaturas;
    private int VariableCalidadDownload;
    private FileReader fr;
    private BufferedReader br;
    private FileWriter fw;
    private BufferedWriter bw;
    private PrintWriter pw;
    private String ArchivoDatos="Datos Aplicacion/Datos Programa.txt";
    private String RutaFilnetDownCompleted=null;
    private JTextArea txt_AreaTextoLincks=new JTextArea();
    private boolean InicioSecion=false;
    private String NombreImagenDescargada;
    
    public GUI_Interface() {
        //Dar Estilo Tipo Windows
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI_Interface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(GUI_Interface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GUI_Interface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GUI_Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/icono.png")).getImage());
        setLocationRelativeTo(null);
        LoadDatos();
        //======================================================================  
        jRadioButton1.addActionListener(new ActionListener() {//Sobreponer Programa Pantalla
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jRadioButton1.isSelected()==true){
                    setAlwaysOnTop(true);
                }
                else{
                    setAlwaysOnTop(false);
                }
            }
        });
        
        jTextField1.addMouseListener(new MouseListener() {//Campo de Texto
            @Override
            public void mouseClicked(MouseEvent e) {
                jTextField1.selectAll();
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
        
        jButton4.addActionListener(new ActionListener() {//Abrir ruta se guada imagenes
            @Override
            public void actionPerformed(ActionEvent e) {
                if(RutaGuardarMiniaturas.isEmpty()||RutaGuardarMiniaturas.equals(null)||RutaGuardarMiniaturas.equals("")){
                    JOptionPane.showMessageDialog(null, "Por favor de clik en el botón \"Buscar Directorio\" y seleccione una carpeta!");
                }
                else{
                    try{
                        Desktop escriorio=Desktop.getDesktop();
                        escriorio.open(new File(RutaGuardarMiniaturas));
                    }
                    catch(Exception ex){
                        
                    }
                }
            }
        });
        
        jTextField1.addKeyListener(new KeyListener() {//Campo de Texto
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    BuscarMiniaturaVideo(jTextField1.getText());
                }
                else{
                    
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
                
        jButton1.addActionListener(new ActionListener() {//Buscar Miniatura
            @Override
            public void actionPerformed(ActionEvent e) {
                String LinckObtener;
                try{
                    LinckObtener=ClipboardWindows.get();
                    BuscarMiniaturaVideo(LinckObtener);
                    jTextField1.setText(LinckObtener);
                    jTextField1.setCaretPosition(0);
                    jButton2.requestFocus();
                }
                catch(Exception ex){
                    
                }
                
            }
        });
        
        jButton2.addActionListener(new ActionListener() {//Descargar
            @Override
            public void actionPerformed(ActionEvent e) {
                if(RutaGuardarMiniaturas.isEmpty()||RutaGuardarMiniaturas.equals(null)||RutaGuardarMiniaturas.equals("")){
                    JOptionPane.showMessageDialog(null, "Por favor de clik en el botón \"Buscar Directorio\" y seleccione una carpeta!");
                }
                else{                    
                    DescargaImagenMiniatura();
                    jButton1.requestFocus();
                }                
            }
        });
        
        jButton3.addActionListener(new ActionListener() {//Buscar Directorio
            @Override
            public void actionPerformed(ActionEvent e) {
                ObtenerRutaGuardarImagenesMiniaturas();
            }
        });
        
        jLabel6.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    try{
                        Desktop OpenFileSistemWin=Desktop.getDesktop();
                        OpenFileSistemWin.open(new File(RutaFilnetDownCompleted));
                    }
                    catch(Exception ex){
                        
                    }
                }
                else{
                    
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(RutaFilnetDownCompleted!=null)
                    jLabel6.setBorder(new LineBorder(new Color(255,0,0)));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(RutaFilnetDownCompleted!=null)
                    jLabel6.setBorder(new LineBorder(new Color(0,0,0)));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
//                if(RutaFilnetDownCompleted!=null)
//                    jLabel6.setBorder(new LineBorder(new Color(255,153,0)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(RutaFilnetDownCompleted!=null)
                    jLabel6.setBorder(new LineBorder(new Color(0,0,0)));
            }
        });
        
        jButton1.requestFocus();
    }
    
    private void LeerSaveLincks(JTextArea txt_Area){
        File ArchivoSaveLincks=new File(RutaGuardarMiniaturas+"/SaveLincks"+".txt");
        String Contenido;  
        
        if(ArchivoSaveLincks.exists()==true){
            try {
                fr = new FileReader (ArchivoSaveLincks);
                br = new BufferedReader(new InputStreamReader(new FileInputStream(ArchivoSaveLincks), StandardCharsets.UTF_8));

                try {
                    while((Contenido=br.readLine())!=null)
                        txt_Area.append(Contenido+"\n");

                } catch (Exception ex) {

                }             

            } catch (Exception e) {

            }
            finally{
                try {
                    if(null!=fr){
                        fr.close();
                        br.close();
                    }
                } catch (Exception e) {

                }
            } 
            txt_Area.append("\n");
        }
        else{
            
        }            
    }
    
    private void OrganizerInfoDownloadMinuiatura(){
        if(InicioSecion==false){
            if(txt_AreaTextoLincks.getText().isEmpty()==true){

            }
            else{
                txt_AreaTextoLincks.append("\n");
            }
            txt_AreaTextoLincks.append("//------------------------------"+"\n");
            
            DateTimeFormatter Date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter Hour = DateTimeFormatter.ofPattern("HH:mm: a");
            
            LocalDateTime DateTime = LocalDateTime.now();
            LocalDateTime HourTime = LocalDateTime.now();
            
            String Fecha=DateTime.format(Date);
            String Hora=HourTime.format(Hour);
            
            txt_AreaTextoLincks.append("Seción: ("+Fecha+") ("+Hora+")"+"\n");//Este registra el Linck del Video
            InicioSecion=true;
        }
            
        
        txt_AreaTextoLincks.append(LinkIgnitial+"\n");//Esto registra el Linck del Video
        txt_AreaTextoLincks.append("->"+LinkGlobal+"\n");//Esto registra el Linck de la IMagen
        txt_AreaTextoLincks.append("(N)"+NombreImagenDescargada+"\n");//Esto Registra el Nombre Final de La Imagen Miniatura Descargada
        txt_AreaTextoLincks.append("\n");
        
        
        GuardarSaveLincks(txt_AreaTextoLincks);
    }
    
    private void GuardarSaveLincks(JTextArea TXTDescripcion){        
        File ArchivoSaveLincks=new File(RutaGuardarMiniaturas+"/SaveLincks"+".txt");                                                  
        if(ArchivoSaveLincks.exists()){
            ArchivoSaveLincks.delete();
        }        
        try {
            fw=new FileWriter(ArchivoSaveLincks,true);
            bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ArchivoSaveLincks), "UTF-8"));    
            pw=new PrintWriter(bw);    
            String [] lineas =TXTDescripcion.getText().split("\n");
            for(int i=0; i<lineas.length; i++){
                pw.println(lineas[i]);              
            }
            pw.close();
            lineas=null;

        } catch (Exception e) {   

        }
        finally{
            try{
                bw.close();
                fw.close();
            }
            catch(Exception ex){

            }
        }      
    }
    
    private void ObtenerRutaGuardarImagenesMiniaturas(){
        JFileChooser chooser=new JFileChooser();
        chooser.setDialogTitle("Obtener Folder");chooser.setApproveButtonText("Obtener");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        int Opcion;
        Opcion= chooser.showOpenDialog(null);
        if(Opcion==JFileChooser.APPROVE_OPTION){            
            RutaGuardarMiniaturas=chooser.getSelectedFile().getPath()+"/";
            SaveDatos();
            jTextField2.setText(RutaGuardarMiniaturas);
        }
        else{
            
        }
    }
    
    private void LoadDatos(){
        File ArchivoDatosValore=new File(ArchivoDatos);
        if(ArchivoDatosValore.exists()==true){
            ArrayList<String> ValuesButtons=new ArrayList<String>();
            ArrayList<String> ValuesButtonsApoyo=new ArrayList<String>();

            String Contenido;                
            try {
                fr = new FileReader (ArchivoDatosValore);
                br = new BufferedReader(new InputStreamReader(new FileInputStream(ArchivoDatosValore), StandardCharsets.UTF_8));

                    try {
                        while((Contenido=br.readLine())!=null)                        
                            ValuesButtons.add(Contenido);

                } catch (IOException ex) {                           

                }             

                } catch (Exception e) {            

                }                         
                finally{
                    try {
                        if(null!=fr){
                            fr.close();
                            br.close();
                        }
                    } catch (Exception e) {

                    }
                } 
            //-------------------------------------------------------------        
            for(int i=0; i<=ValuesButtons.size()-1; i++){            
                String Apoyyo=ValuesButtons.get(i);                  
                int value=ReturnSubStringEspecificoNumerPosition(Apoyyo, '-');
                Apoyyo=Apoyyo.substring(value+1, Apoyyo.length());
                ValuesButtonsApoyo.add(Apoyyo);
            }        
            //-------------------------------------------------------------//Recupera todos los datos en Orden y por indexacion del Array        
            RutaGuardarMiniaturas=ValuesButtonsApoyo.get(0); 
            jTextField2.setText(RutaGuardarMiniaturas);
            jTextField2.setCaretPosition(0);
        }
        else{
            RutaGuardarMiniaturas="";
        }    
        
        LeerSaveLincks(txt_AreaTextoLincks);
    }
    
    private void SaveDatos(){        
        File ArchivoDatosValore=new File(ArchivoDatos);
        
        ArrayList<String> ValuesButtons=new ArrayList<String>();
        //------------------------------//Cargar todos los datos que sean necesarios guardar en el Array List
        ValuesButtons.add("Ruta Guardar Imagenes Miniatura Youtube"+"-"+RutaGuardarMiniaturas);
        
        //------------------------------
        if(ArchivoDatosValore.exists()){
            ArchivoDatosValore.delete();
        } 
        try {
            fw=new FileWriter(ArchivoDatosValore,true);
            bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ArchivoDatosValore), "UTF-8"));
            pw=new PrintWriter(bw);                                   
            for(int i=0; i<=ValuesButtons.size()-1; i++){
                pw.println(ValuesButtons.get(i));
            }
            pw.close();            
            bw.close();
            fw.close(); 
        } catch (Exception e) {  
            
        }    
    }
    
    private void DescargaImagenMiniatura(){
        if(VariableCalidadDownload==5){
            LinkGlobal=Link_5;
            DownloadImagenMiniaturaVideoYoutube(Link_5);
        }
        else if(VariableCalidadDownload==4){
            LinkGlobal=Link_4;
            DownloadImagenMiniaturaVideoYoutube(Link_4);
        }
        else if(VariableCalidadDownload==3){
            LinkGlobal=Link_3;
            DownloadImagenMiniaturaVideoYoutube(Link_3);
        }
        else if(VariableCalidadDownload==2){
            LinkGlobal=Link_2;
            DownloadImagenMiniaturaVideoYoutube(Link_2);
        }
        else if(VariableCalidadDownload==1){
            LinkGlobal=Link_1;
            DownloadImagenMiniaturaVideoYoutube(Link_1);
        }
        else{
            
        }
    }
    
    private void DownloadImagenMiniaturaVideoYoutube(String URLVideoImagenMiniatura){
        try{
            ClassDownloader downloader = new ClassDownloader(new URL(URLVideoImagenMiniatura), jLabel7, RutaGuardarMiniaturas, "");
            downloader.start();
        }
        catch(Exception ex){
            
        }
    }

    private void BuscarMiniaturaVideo(String LinkVideo){
        jLabel6.setIcon(null);
        BuscarLinck bl=new BuscarLinck(LinkVideo);
        bl.start();
    }
    
    /*Este clase privada hara uso de Hilos para evitar que durante el proceso de busqueda de la miniatura del video 
    la aplicaion se trabe o congele.*/
    private class BuscarLinck extends Thread{
        private String Url;
        public BuscarLinck(String UrlVideo){
            this.Url=UrlVideo;
        }
        
        public void run(){
            //Metodos
            BuscarLinckVideo();
            super.run();
        }
        
        private void BuscarLinckVideo(){
            ComprovarVideoUrl(Url);            
        }
    }
    
    private void ComprovarVideoUrl(String Url){
        LinkIgnitial=Url;
        if(ComprobarSubcadena_en_Cadena(Url, "youtube")||ComprobarSubcadena_en_Cadena(Url, "google")){
            jLabel7.setText("Estatus: Buscando...");
            String UrlValidar="";            
            if(ComprobarSubcadena_en_Cadena(Url, "shorts")){//Videos Miniatura Shorts
                UrlValidar=ReturnSubString(Url, "/");
            }
            else{                                           //Videos Miniatura Video Normal
                if(ComprobarSubcadena_en_Cadena(Url, "?v=")){
                    if(ComprobarSubcadena_en_Cadena(Url, "&")){
                        String CadenaEspecifica=ReturnSubStringEspecifico(Url, '&');
                        UrlValidar=ReturnSubString(CadenaEspecifica, "=");
                    }
                    else{
                        UrlValidar=ReturnSubString(Url, "=");
                    }
                }
                else if(ComprobarSubcadena_en_Cadena(Url, "vid:")){
                    UrlValidar=ReturnSubString(Url, ":");
                }
                else{
                    
                }
            }
            
            //Generando las Diversas Calidades de la Imagen            
            Link_1="https://img.youtube.com/vi/"+UrlValidar+"/default.jpg";//Imagen Pequeña
            Link_2="https://img.youtube.com/vi/"+UrlValidar+"/hqdefault.jpg";//Imagen Mediana
            Link_3="https://img.youtube.com/vi/"+UrlValidar+"/mqdefault.jpg";//Imagen Estándar o Normal
            Link_4="https://img.youtube.com/vi/"+UrlValidar+"/sddefault.jpg";//Imagen Grande
            Link_5="https://img.youtube.com/vi/"+UrlValidar+"/maxresdefault.jpg";//Imagen Máxima Calidad
            
            //Mandar a comprobar la existencia de la imagen miniatura del video
            if(LoadMiniaturaImag(Link_5, jLabel6, jLabel8)){//Mandar A comprobar si existe la Imagen en su Maxima Calidad
                jLabel5.setText("Tamaño: Maxima Calidad.");
                jLabel4.setText("Nombre: maxresdefault.jpg");
                VariableCalidadDownload=5;
                if(jRadioButton2.isSelected()==true)
                    DescargaImagenMiniatura();
                    jButton1.requestFocus();
            }
            else{
                if(LoadMiniaturaImag(Link_4, jLabel6, jLabel8)){//Comprobar Existe Imagen Tamaño Grande
                    jLabel5.setText("Tamaño: Grande.");
                    jLabel4.setText("Nombre: sddefault.jpg");
                    VariableCalidadDownload=4;
                    if(jRadioButton2.isSelected()==true)
                        DescargaImagenMiniatura();
                        jButton1.requestFocus();
                }
                else{
                    if(LoadMiniaturaImag(Link_3, jLabel6, jLabel8)){//Comprobar Si Existe Imagen Tamaño Estándar o Normal
                        jLabel5.setText("Tamaño: Estándar.");
                        jLabel4.setText("Nombre: mqdefault.jpg");
                        VariableCalidadDownload=3;
                        if(jRadioButton2.isSelected()==true)
                            DescargaImagenMiniatura();
                            jButton1.requestFocus();
                    }
                    else{
                        if(LoadMiniaturaImag(Link_2, jLabel6, jLabel8)){//Comprobar Si Existe Imagen Tamaño Mediano
                            jLabel5.setText("Tamaño: Mediana.");
                            jLabel4.setText("Nombre: hqdefault.jpg");
                            VariableCalidadDownload=2;
                            if(jRadioButton2.isSelected()==true)
                                DescargaImagenMiniatura();
                                jButton1.requestFocus();
                        }
                        else{
                            if(LoadMiniaturaImag(Link_1, jLabel6, jLabel8)){//Comprobar Si Existe Imagen Tamaño Chico
                                jLabel5.setText("Tamaño: Pequeña.");
                                jLabel4.setText("Nombre: default.jpg");
                                VariableCalidadDownload=1;
                                if(jRadioButton2.isSelected()==true)
                                    DescargaImagenMiniatura();
                                    jButton1.requestFocus();
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Lo sentimos no hemos encontrado ninguna imagen miniatura del video :(");
                            }
                        }
                    }
                }
            }
            
        }
        else{
            jLabel7.setText("Estatus: Null");
            JOptionPane.showMessageDialog(null, "Por favor ingrese un link valido de YouTube!");
        }
    }
    
    private boolean LoadMiniaturaImag(String Url, JLabel lbl_IconoReference, JLabel lbl_Size){
        try {
            lbl_IconoReference.setText("");
            Image img=ImageIO.read(new URL(Url));
            BufferedImage BufferImage=(BufferedImage)img;
            BufferedImage MedidaSizeTamaño=(BufferedImage)img;
            BufferImage=Scalr.resize(BufferImage, Scalr.Method.QUALITY, 190, new BufferedImageOp[]{Scalr.OP_BRIGHTER});
            lbl_IconoReference.setIcon(new ImageIcon(BufferImage));
            lbl_IconoReference.repaint();
            lbl_IconoReference.updateUI();            
            lbl_Size.setText("Dimenciones: Ancho: "+MedidaSizeTamaño.getWidth()+"px  Alto: "+MedidaSizeTamaño.getHeight()+"px");
            if(jRadioButton2.isSelected()==true)
                jButton2.setEnabled(false);
            else
                jButton2.setEnabled(true);
            jLabel7.setText("Estatus: Imagen Encontrada.");
            jButton2.requestFocus();
            return true;
        } catch (Exception ex) {
            lbl_IconoReference.removeAll();
            lbl_IconoReference.setIcon(null);
            lbl_IconoReference.setText("No Imagen");
            lbl_Size.setText("Dimenciones: -  -");
            jButton2.setEnabled(false);
            jLabel7.setText("Estatus: Imagen No Encontrada.");
            return false;
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jButton4 = new javax.swing.JButton();
        jRadioButton2 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Downloader Miniatura Video Youtube");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTextField1.setSelectionColor(new java.awt.Color(255, 0, 0));

        jButton1.setText("Buscar Miniatura");

        jButton2.setText("Descargar");
        jButton2.setEnabled(false);

        jLabel1.setText("Linck Video:");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setText("Directorio de Descarga:");

        jButton3.setText("Buscar Directorio");

        jTextField2.setEditable(false);
        jTextField2.setSelectionColor(new java.awt.Color(255, 0, 0));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Información.");

        jLabel4.setText("Nombre:");

        jLabel5.setText("Tamaño:");

        jLabel7.setText("Estatus:");

        jLabel8.setText("Dimenciones:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Opciones.");

        jRadioButton1.setText("Sobreponer programa pantalla");
        jRadioButton1.setOpaque(false);

        jButton4.setText("Abrir ruta se guadan imagenes");

        jRadioButton2.setSelected(true);
        jRadioButton2.setText("Iniciar descarga automática");
        jRadioButton2.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jRadioButton1)
                            .addComponent(jButton4)
                            .addComponent(jRadioButton2))
                        .addGap(0, 20, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean ComprobarSubcadena_en_Cadena(String Cadena, String SubCadena){     
        boolean ispresent = Pattern.compile(Pattern.quote(SubCadena), Pattern.CASE_INSENSITIVE).matcher(Cadena).find();
        if(ispresent){            
            return true;
        }
        else{           
            return  false;        
        }
    }
    
    private String ReturnSubString(String Cadena, String Simbolo){
        String Palabra = "";
        int i = Cadena.lastIndexOf(Simbolo);
        if (i > 0) {
            Palabra = Cadena.substring(i+1);
        }                
        return Palabra;
    }
    
    private String ReturnSubStringEspecifico(String Cadena, char Simbolo){   
        int pos =0;        
        for (int i = 0; i < Cadena.length(); i++){
            if(Cadena.charAt(i) == Simbolo){
                pos = i;
                break;
            }    
        }        
        String SubStringEspecifico=Cadena.substring(0,pos);
        return SubStringEspecifico;
    }
    
    private int ReturnSubStringEspecificoNumerPosition(String Cadena, char Simbolo){   
        int pos =0;        
        for (int i = 0; i < Cadena.length(); i++){
            if(Cadena.charAt(i) == Simbolo){
                pos = i;
                break;
            }    
        }                
        return pos;
    }
    
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
            java.util.logging.Logger.getLogger(GUI_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
    private class ClassDownloader extends Thread{
        private URL Url=null;
        private URLConnection Coneccion=null;
        private JLabel lbl_Stado=null;
        private String RutaDownloadFileSaved=null;
        private String NameFile;
//        private String RutaFilnetDownCompleted=null;
       
        public ClassDownloader(URL url, JLabel Stado, String RutaDownloadFileSaved, String NameFile) {
            super("Downloader");
            this.Url = url;             
            this.lbl_Stado=Stado;            
            this.RutaDownloadFileSaved=RutaDownloadFileSaved;
            this.NameFile=NameFile;            
        }
        
        @Override
        public void run() {
            DownloadImage();   
            super.run();
        }
        
        final int getLength(URL urlFile){
            URLConnection connection = null;
            int size = 0;
            try {
                connection = urlFile.openConnection();
                size = connection.getContentLength();
            } catch (IOException e) {                
                lbl_Stado.setText("Estatus: Descarga Error.");
              
            } catch (Exception e) {                
                lbl_Stado.setText("Estatus: Descarga Error.");    
            }
            return size;
        }
        
        String getFileName(URL URL){
            String path=URL.getPath();
            int lastIndexOf=path.lastIndexOf("/");
            String name = path.substring(lastIndexOf+1);
            String Extencion=ReturnExtencionFile(name);
            if(Extencion.equals("")){                                   
                name=name+".png";                   
            }
            else{
                
            }
           return name;
        }
        
       //---------------------------------------------------------------------------
       public String cadenaAleatoria(int longitud) {            
            String CaracteresConsiderar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";            
            String Cadena = "";
            for (int x = 0; x < longitud; x++) {
                int indiceAleatorio = NumeroAleatorioRango(0, CaracteresConsiderar.length() - 1);
                char caracterAleatorio = CaracteresConsiderar.charAt(indiceAleatorio);
                Cadena += caracterAleatorio;
            }
            return Cadena;
        }

        public int NumeroAleatorioRango(int minimo, int maximo) {            
            return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
        }

        private String ReturnRutaName(String RutaName){       
            int lastIndex = RutaName.lastIndexOf('.');
            if (lastIndex != -1) {
                RutaName = RutaName.substring(0, lastIndex);
            }    
            return RutaName;
        }

        private String ReturnExtencionFile(String Ruta){        
            int i = Ruta.lastIndexOf('.')-1;
            String extencionFormatoFichero="";
            if (i > 0) {
                extencionFormatoFichero = Ruta.substring(i+1);
            }
            return extencionFormatoFichero;
        }

        //==========================================================================
        public void DownloadImage(){
            try {                                              
                Coneccion = Url.openConnection();
                Coneccion.connect();                  
            } catch (Exception e) {                
                lbl_Stado.setText("Estatus: Volder a intentar.");   
            } 
            
            try {
                int read = 0;
                final int SIZE = getLength(Url);                
                lbl_Stado.setText("Estatus: ...");    
                InputStream stream  =  null;
                //----------------------------------------  
                stream = Coneccion.getInputStream();                
    
                final FileOutputStream fileOutputStream;                    
                if(NameFile.equals("")){
                    File ArchiEqual=new File(RutaDownloadFileSaved+""+getFileName(Url));
                    String RutaFilnetDownComplet="";                 
                    if(ArchiEqual.exists()==true){
                        int longitud = 12;
                        String cadena = cadenaAleatoria(longitud);
                        RutaFilnetDownComplet=ReturnRutaName(ArchiEqual.getPath())+"_"+cadena+ReturnExtencionFile(ArchiEqual.getName());
                    }
                    else{
                        RutaFilnetDownComplet=ArchiEqual.getPath();
                    }    
                    RutaFilnetDownCompleted=RutaFilnetDownComplet;
                    fileOutputStream = new FileOutputStream(RutaFilnetDownComplet);
                }
                else{
                    File ArchiEqual=new File(RutaDownloadFileSaved+""+NameFile);
                    String RutaFilnetDownComplet="";
                    if(ArchiEqual.exists()==true){
                        int longitud = 12;
                        String cadena = cadenaAleatoria(longitud);                        
                        RutaFilnetDownComplet=ReturnRutaName(ArchiEqual.getPath())+"_"+cadena+ReturnExtencionFile(ArchiEqual.getName());
                    }
                    else{
                        RutaFilnetDownComplet=ArchiEqual.getPath();
                    }    
                    RutaFilnetDownCompleted=RutaFilnetDownComplet;
                    fileOutputStream = new FileOutputStream(RutaFilnetDownComplet);
                }          

                final byte[] data = new byte[ SIZE ];
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {                    
                    lbl_Stado.setText("Estatus: Descarga Error.");    
                }
                int offset = 0;                
                while((read = stream.read(data)) > 0){
                   offset += read;                   
                   fileOutputStream.write(data,0,read);
                }      
                lbl_Stado.setText("Estatus: Descarga Completa!");
                //--------------------------------------------------------------------    
                if(RutaFilnetDownCompleted.endsWith(".png")||RutaFilnetDownCompleted.endsWith(".jpg")){

                }
                else{
                    File AcNotName=new File(RutaFilnetDownCompleted);
                    String RutaRename=RutaFilnetDownCompleted+".png"+"";
                    File AcRename=new File(RutaRename);
                    boolean correcto = AcNotName.renameTo(AcRename);
                    if (correcto){
                        RutaFilnetDownCompleted=AcRename.getPath();
                        jLabel4.setText("Nombre: "+new File(RutaRename).getName());
                    }
                    else{
                        
                    }
                }
                NombreImagenDescargada=new File(RutaFilnetDownCompleted).getName();
                jLabel4.setToolTipText("Nombre: "+NombreImagenDescargada);
                //--------------------------------------------------------------------
                try{
                    stream.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    offset = 0;
                }catch (Exception e) {      
                    lbl_Stado.setText("Estatus: Descarga Error.");
                }
                }catch (IOException e) {    
                    lbl_Stado.setText("Estatus: Descarga Error.");
                }catch (Exception e) {                    
            }
            OrganizerInfoDownloadMinuiatura();
        }
    }
}
