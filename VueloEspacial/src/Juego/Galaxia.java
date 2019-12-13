package Juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.swing.JComponent;

public class Galaxia extends javax.swing.JFrame {
    
    private final int D_Ancho = 900;
    private final int D_Alto = 800;
    private final String Version="Viaje Espacial";
    
    private int Dificultad = 5;             //NIVEL 
    private int Vidas = 3;                  //CANTIDAD DE VIDAS
    private int EnergiaVital = 10;          //CANTIDAD DE DISPAROS DE LA NAVE
    private int Puntos = 0;                 //POSICION INICIAL DE LOS PUNTOS
    private final int Destruccion = 45;     //PUNTOS QUE OTORGAN AL DESTRUIR METEORITOS
    private int MaxEnergia = 10;            //VITALIDAD DE LA NAVE
    private int D_NaveX = 55;               //ANCHO DE LA VENTANA
    private int D_NaveY = 64;               //ALTURA DE LA VENTANA
    private int Posicion_NaveX;             //POSICION ANCHO DE LA NAVE
    private int Posicion_NaveY;             //POSICION ALTURA DE LA NAVE 
    private final int Max_Derecho = 897;    //ESTABLECE EL MAXIMO DERECHO QUE SE PUEDE MOVER LA NAVE
    private int Posicion_FondoY;            //POSICION IMAGEN DE FONDO
    
    //VARIABLES QUE SE ENCARGAN DE CONTABILIZAR LOS FOTOGRAMAS POR SEGUNDO DE LOS GRAFICOS
    private long Obtener_Tiempo;
    private long Obtener_Tiempo_Despues;
    private int fps=0;
    private int ObtenerFps;
    
    private int Tiempo_Atras;    //CUENTA ATRAS PARA EL INICIO DEL JUEGO
    private int PropulsorIndice;   //ALMACENA EL INDICE DEL ARRAY CORRESPONDIENTE A LA IMAGEN A IMPLANTAR

    private boolean Parada=false;   //CREA UN BLOQUE EN LA EJECUCION DE QUITAR ENERGIA VITAL A LA NAVE
    private boolean Reiniciar=false;
    private boolean T_Atras;        //ACTIVA Y DESACTIVA EL MENSAJE GRAFICO DE LA CUENTA ATRAS
    private boolean Muerte;         //DETERMINA EL ESTADO DE LA NAVE
    private boolean Pausar=false;   //DETIENE LA EJECUCION DEL JUEGO

    
    //ALMACENA LAS IMAGENES GRAFICAS DEL JUEGO
    
    private ArrayList<BufferedImage> Gra2D = new ArrayList<>();
    private ArrayList<BufferedImage> Propulsor2D = new ArrayList<>();
    private ArrayList<BufferedImage> Asteroide2D = new ArrayList<>();
    
    //ALMACENA LAS IMAGENES DE LAS LETRAS GRAFICAS DEL MENU
    
    private ArrayList<BufferedImage> Letras = new ArrayList<>();
    
    //ALMACENA LAS IMAGENES DE LOS NUMEROS DEL PUNTAJE DEL JUEGO
    
    private ArrayList<BufferedImage> Numeros = new ArrayList<>();
    
    //ALMACENA LAS IMAGENES DE LA BARRA DE ENERGIA
    
    private ArrayList<BufferedImage> BarraG2 = new ArrayList<>();
    
    //ALMACENA LAS IMAGENES DE LA BARRA DE ENERGIA
    
    private ArrayList<BufferedImage> BarraVital = new ArrayList<>();
    
    //ALMACENA LAS COORDENADAS DE POSICION DEL PROYECTIL Y SU ESTADO
    
    private ArrayList<Integer> DisparoX = new ArrayList<>();
    private ArrayList<Integer> DisparoY = new ArrayList<>();
    private ArrayList<Boolean> Es_Proyec = new ArrayList<>();
    
    //GUARDA LAS COORDENADAS Y ESTADO DE LOS ASTEROIDES 
    
    private ArrayList<Integer> Aste_X = new ArrayList<>();
    private ArrayList<Integer> Aste_Y = new ArrayList<>();
    private ArrayList<Boolean> Aste_Estado = new ArrayList<>();
    private ArrayList<BufferedImage> Aste_Tipo = new ArrayList<>();
    private ArrayList<Integer> Aste_Impacto = new ArrayList<>();
    
    //ALMACENA LAS COORDENADAS Y ESTADO DEL EFECTO PROVOCADO AL GOPLEAR UN PROYECTIL SOBRE EL ASTEROIDE
    
    private ArrayList<Rectangle> Efect_Aste = new ArrayList<>();
    private ArrayList<Boolean> Efect_Esta = new ArrayList<>();
    
    //ALMACENA LAS COORDENADAS Y ESTADO EL EFECTO PROVOCADO AL GOPLEAR LA NAVE SOBRE EL ASTEROIDE
    
    private ArrayList<Rectangle> Efect_Nave = new ArrayList<>();
    private ArrayList<Boolean> Efect_Nave_Esta = new ArrayList<>();

    //CLASE EXTERNA ENCARGADA DE GUARDAR LAS IMAGENES EN UN ARRAYLIST DE BUFFEREDIMAGE 
    
    private Imagenes_Juego Im_Ju = new Imagenes_Juego(this);
    
    //CLASE EXTERNA ENCARGADA DE REPRODUCIR LOS AUDIOS DEL JUEGO
    
    private Audios Re_Au = new Audios();
    
    
    public static void main(String arg[]){
        
        new Galaxia().setVisible(true);
        
    }
    
//CONSTRUCTOR DE LA CLASE PRINCIPAL
    
    public Galaxia() {
        
        setTitle(Version);
        setSize(D_Ancho, D_Alto);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        add(new PlantillaGrafica());
        
        CrearBucle();
        
        EventosDelJuego();
        
        CargarGraficos();
        
        MusicFondoFX();
        
    }
    
//CLASE INTERNA ENCARGADA DE CREAR Y REDIBUJAR LOS GRAFICOS 
    
    public class PlantillaGrafica extends JComponent{
        
        public void paint(Graphics g){
            
            Graphics2D g2 = (Graphics2D) g;
            
            
 //DIBUJA EL FONDO GRAFICO DEL JUEGO
            g2.drawImage(Gra2D.get(1),0,Posicion_FondoY, rootPane);
            
 //DIBUJA LOS ASTEROIDES CUANDO APARECEN EN LA PANTALLA
        int a=0;
        try{
            for(boolean Estado: Aste_Estado){
                
                if(Estado){
                    try{
                        g2.drawImage(Aste_Tipo.get(a), Aste_X.get(a), Aste_Y.get(a), rootPane);
                    }
                    catch(IndexOutOfBoundsException e)
                    {
                    System.out.print("Error al dibujar Asteroide");
                    }
                    
                }
                a++;
            }
        }
        catch(IndexOutOfBoundsException e)
            {
             System.out.print("Error en el bucle Aste_Estado");
            }
 
 //DIBUJA EL EFECTO QUE PRODUCE EL PROYECTIL AL IMPACTAR EN EL ASTEROIDE
        int b=0;
        for (boolean Estado: Efect_Esta){
            
            if (Estado){
                
                Rectangle Efecto = Efect_Aste.get(b);
                int X = (int) Efecto.getWidth();
                int Y = (int) Efecto.getHeight();
                g2.drawImage(Asteroide2D.get(3), X , Y,rootPane);
            }
            b++;
        }
        
//DIBUJA LOS PROYECTILES DE BOLA DE FUEGO
        int w=0;
        try{
            for(Boolean bool: Es_Proyec){
                
                if(bool){
                    
                    g2.drawImage(Gra2D.get(2), DisparoX.get(w), DisparoY.get(w), rootPane);
                }
                w++;
            }
            
        }
        catch(IndexOutOfBoundsException e)
            {
             System.out.print("Error en el dibujado de proyectiles" + e);
             e.printStackTrace();
            }
            try{
            g2.drawImage(BarraVital(EnergiaVital), 650, 20, this);
        }
        catch(IndexOutOfBoundsException e){}
            
//DIBUJA LA BARRA DE ENERGIA DE LOS DISPAROS
        try{
            g2.drawImage(BarraGrafica(MaxEnergia), 650, 80, this);
        }
        catch(IndexOutOfBoundsException e){}
        
//DIBUJA LA NAVE DEL JUEGO
        try{
            g2.drawImage(Gra2D.get(0),Posicion_NaveX, Posicion_NaveY, rootPane);
        }
        catch(IndexOutOfBoundsException e){System.out.println("Error en el dibujado de la nave");
        }
        
//DIBUJA EL EFECTO QUE PRODUCE LA NAVE AL IMPACTAR EN EL ASTEROIDE 
        int c=0;
        for(boolean Estado: Efect_Nave_Esta){
            
            if (Estado){
                
                Rectangle Efecto = Efect_Nave.get(c);
                int X = (int) Efecto.getWidth();
                int Y = (int) Efecto.getHeight();
                g2.drawImage(Gra2D.get(3), X, Y, rootPane);
                
            }
            c++;
        }
//DIBUJA EL PROPULSOR DE LA NAVE
        try{
            g2.drawImage(Propulsor2D.get(PropulsorIndice),Posicion_NaveX+42, Posicion_NaveY+74, rootPane);
        }
        catch(IndexOutOfBoundsException e){System.out.println("Error en el dibujado del propulsor");
        }
        
//DIBUJA UNA CUENTA ATRAS AL EMPEZAR EL JUEGO O TRAS PERDER UNA VIDA

        if (T_Atras){
            if (Tiempo_Atras<=0){
                Dibuja_Texto_Grafico(g2, 300, 400 , 50, 70, 40, "Empieza");             
            }
            else{
                Dibuja_Texto_Grafico(g2, 400, 400, 50, 70, 40, ""+Tiempo_Atras);
            }
                
        }
        
//DIBUJA LOS PUNTOS Y ESTADO DE VIDA EN EL JUEGO

            Dibuja_Texto_Grafico(g2, 10, 10, 30, 50, 30, " Puntos " + Puntos);
            Dibuja_Texto_Grafico(g2, 20, 700, 30, 50, 30, " Vidas " + Vidas);
            
           
            if (!Pausar){
                EvaluarCondiciones();
            }
            else{
                Dibuja_Texto_Grafico(g2, 200, 500, 50, 70, 40, " Juego Pausado " );
            }
              
            ObtenerFps(g2);
        }
    }

// LLAMA A LA CLASE EXTERNA PARA ALMACENAR LOS GRAFICOS EN LOS ARRAYS  
    public void CargarGraficos(){
        
        Im_Ju.CargarGraficos();
        Im_Ju.CargarPropulsor();
        Im_Ju.CargarAsteroide();
        Im_Ju.CargarLetras();
        Im_Ju.CargarNumeros();
        Im_Ju.CargarBarraEnergia();
        Im_Ju.CargarBarraVital();
        
        CuentaAtras();
        
    }
 
//DEVUELVE EL GRAFICO CORRESPONDIENTE DEL ARRAY DEPENDIENDO DEL INDICE
    public BufferedImage BarraGrafica(int Energia){
        
        return BarraG2.get(Energia);
    }
    
//DEVUELVE EL GRAFICO CORRESPONDIENTE DEL ARRAY DEPENDIENDO DEL INDICE
    public BufferedImage BarraVital(int E_Vital){
        
        return BarraVital.get(E_Vital);
    }   
    
//EVENTOS
    
    public void EventosDelJuego(){
        
    addMouseMotionListener(new MouseAdapter() {
        @Override
        public void mouseMoved(MouseEvent evt){
            
            Posicion_NaveX = 400;
            Posicion_NaveY = 500;
            
            if(!Muerte){
                
                int X = evt.getX();
                int Y = evt.getY();
                int SobreDerecho = D_NaveX +8;
                int MaximoDerecho = D_NaveX/2;       //ESTABLECE LA MAXIMA DISTANCIA QUE RECORRE LA BARRA EN LA DERECHA
                        
                Posicion_NaveX = X-D_NaveX/2;
                Posicion_NaveY = Y-D_NaveX/2-40;
                
            if (X<D_NaveX/2+2){   //EVITAMOS QUE LA BARRA SOBREPASE EL LIMITE IZQUIERDO
                Posicion_NaveX=2;
                
            }
            
            int PeTotalD = Max_Derecho+3;
            if (X>PeTotalD-MaximoDerecho){   //EVITAMOS QUE LA BARRA SOBREPASE EL LIMITE DERECHO
                Posicion_NaveX = PeTotalD-SobreDerecho;
                
                }
            }
        }
     });  
        
    addKeyListener(new KeyAdapter(){
        
        @Override
        public void keyReleased(KeyEvent e){
        
        int Code = e.getKeyCode();
        
        if (Code ==27){
            if(Pausar){
                Pausar = false;                
            }
            else {
                Pausar = true;
            }
        }
        
        if (Code==32 && MaxEnergia>=0 && !Muerte && !Pausar){
            
            Re_Au.Fx(9);
            Es_Proyec.add(true);
            DisparoY.add(Posicion_NaveY-40);
            DisparoX.add(Posicion_NaveX+44);
            Movimiento_Del_Proyectil();
        }
     }        
    });
    
    }
    
//EVALUA LAS CONDICIONES DE INTERSECCION DE IMAGENES
    public void EvaluarCondiciones(){
        
        //CAPTURAMOS LA DIMENSION DE NUESTRA NAVE EN UNA VARIABLE
        Rectangle D_nave= new Rectangle(Posicion_NaveX, Posicion_NaveY, D_NaveX, D_NaveY);
        
        //IMPACTO DEL ASTEROIDE CON LA NAVE
        int a=0;
                for(boolean Estado: Aste_Estado){
                    
                    if(Estado){
                        
                        Rectangle D_Asteroide = new Rectangle(Aste_X.get(a), Aste_Y.get(a), 80, 60);
                        
                        if (D_nave.intersects(D_Asteroide)){
                            if (!Muerte && !Parada){
                                
                                EfectoNave(Posicion_NaveX, Posicion_NaveY);
                            }
                            
                            if (EnergiaVital<=0 && !Muerte){
                                Muerte = true;
                                EnergiaVital = 0;
                                Vidas = Vidas -1;
                                CuentaAtras();
                                             
                            }
                        }
                    }
                    a++;
                }
    
        //IMPACTO DEL PROYECTIL CON EL ASTEROIDE
        int b=0;
        for(boolean Estado: Es_Proyec){
            
            if (Estado){
                int c=0;
                try{
                    for(boolean Esta: Aste_Estado){
                        
                        if ( Esta){
                            Rectangle D_Asteroide = new Rectangle(Aste_X.get(c), Aste_Y.get(c), 80, 60);
                            Rectangle D_Proyectil = new Rectangle(DisparoX.get(b),DisparoY.get(b), 29, 41);
                        
                        if ( D_Proyectil.intersects(D_Asteroide)){
                            Es_Proyec.set(b,false);
                            DisparoX.set(b, -500);
                            
                            int resis = Aste_Impacto.get(c);
                            if (resis<=0){
                                Aste_Estado.set(c, false);
                                Aste_X.set(c, 1000);
                                Aste_Y.set(c, 1000);
                                Puntos = Puntos + Destruccion;
                            }
                            else {
                                EfectoAsteroide(Aste_X.get(c), Aste_Y.get(c), c);
                                Re_Au.Fx(4);
                                Puntos = Puntos+1;
                                
                                resis = resis-1;
                                Aste_Impacto.set(c, resis);
                            }
                        }    
                                                        
                        }
                    c++;
                    }
                } catch(ConcurrentModificationException e){System.out.println("Error en for (boolean Esta: Aste_Estadi");}
            }
            b++;
        }
    
    
    }
    
//EFECTO DE GOLPE EN LA NAVE CUANDO IMPACTA UN ASTEROIDE
    public void EfectoNave(int Ancho, int Alto){
        
        Thread Efecto = new Thread(new Runnable(){
            @Override
            public void run() {
                Parada = true;
                EnergiaVital = EnergiaVital -1;
                Re_Au.Fx(5);
                
                int Num = Efect_Nave_Esta.size();
                Efect_Nave_Esta.add(true);
                Efect_Nave.add(new Rectangle(Ancho, Alto));
                int Empezar = 10;
                while (Empezar >=0){
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex){}       
                    
                    Efect_Nave.set(Num, new Rectangle(Posicion_NaveX, Posicion_NaveY));
                    Empezar--;
                }
                Efect_Nave_Esta.set(Num, false);
                Parada = false;
                
            }
        });
        
        Efecto.start();
    }

//EFECTO DE GOLPE EN EL ASTEROIDE CUANDO ES IMPACTADO CON UN PROYECTIL
    public void EfectoAsteroide(int Ancho, int Alto, int index){
        
        Thread Efecto = new Thread(new Runnable(){
            @Override
            public void run() {
                
                int Num = Efect_Esta.size();
                Efect_Esta.add(true);
                Efect_Aste.add(new Rectangle(Ancho, Alto));
                int Empezar = 10;
                while (Empezar>=0){
                    
                    try{
                        Thread.sleep(10);
                    } catch (InterruptedException ex){}
                    
                    Efect_Aste.set(Num, new Rectangle(Aste_X.get(index),Aste_Y.get(index)));
                    
                    Empezar--;
                }
                Efect_Esta.set(Num, false);
            }
        });
        
        Efecto.start();
    }
    
    
//CREACION DE ASTEROIDES ALEATORIAMENTE EN LA VENTANA
    public void CrearAsteroide(){
        
        Thread As = new Thread(new Runnable(){
            @Override
            public void run(){
                
                int Crear, Tipo, X;
                
                while (true && !Muerte){
                    
                    if (!Pausar){
                        
                        //GENERA UN NUMERO ALEATORIO DE ASTEROIDES
                        Crear = (int) (Math.random()*Dificultad);
                        //GENERA EL TIPO DE ASTEROIDE
                        Tipo = (int) (Math.random()*10);
                        //ESTABLECE LA POSICION DEL ASTEROIDE
                        X = (int) (Math.random()*900-50);
                        
                        if (Crear == 0){
                            
                            if (Tipo <= 5){ 
                                //ESTABLECE EL TIPO DE ASTEROIDE GRAFICO
                                Aste_Tipo.add(Asteroide2D.get(2));
                                //ESTABLECE LA CANTIDAD DE IMPACTOS PARA SER DESTRUIDO
                                Aste_Impacto.add(4);     
                            }
                            if ( Tipo == 6){ 
                                Aste_Tipo.add(Asteroide2D.get(4));
                                Aste_Impacto.add(5);             
                            }
                            if ( Tipo == 7){ 
                                Aste_Tipo.add(Asteroide2D.get(5));
                                Aste_Impacto.add(6);             
                            }
                            if ( Tipo == 8){ 
                                Aste_Tipo.add(Asteroide2D.get(6));
                                Aste_Impacto.add(8);             
                            }
                            if ( Tipo == 9){ 
                                Aste_Tipo.add(Asteroide2D.get(7));
                                Aste_Impacto.add(10);             
                            }
                            
                            Aste_Estado.add(true);
                            Aste_X.add(X);
                            Aste_Y.add(-100);
                            MovimientoAsteroides();      //LLAMA AL METODO PARA GENERAR EL MOVIMIENTO
                        }
                    }
                    
                    try {
                        Thread.sleep(300);      //CONTROLA EL INTERVALO DE REPETICION DEL CODIGO
                    } catch (InterruptedException ex){}
                }
            }
        });
        
        As.start();
    }
    
//GENERA EL MOVIMIENTO GRAFICO DE LOS ASTEROIDES 
    public void MovimientoAsteroides(){
        
        Thread As = new Thread(new Runnable(){
            @Override
            public void run(){
                int Indice = Aste_Estado.size()-1;
                int Pos_As_Y = Aste_Y.get(Indice);
                
                while (Pos_As_Y < 800 && !Reiniciar){
                    
                    if (!Pausar){
                        
                        Pos_As_Y++;
                        try{
                            Aste_Y.set(Indice, Pos_As_Y);
                        } catch(IndexOutOfBoundsException e){System.out.print("Error Movimiento De Asteroide");
                        }
                    }
                    try {
                        Thread.sleep(20);
                    } catch(InterruptedException ex){}
                }
                try{
                    Aste_Estado.set(Indice, false);
                } catch(IndexOutOfBoundsException e){System.out.print("Error Al Cambiar Estado Del Asteroide");
                    
                }
            }
        });
        
        As.start();
    }

//DEVUELVE EL TIEMPO EN SEGUNDOS DEL METODO NANOTIME()
    public long GetTime(){
        
        long time = System.nanoTime()/10000000;
        return time;
    }

//GENERA UNA SECUENCIA DE IMAGENES REPETIDAS DURANTE UN INTERVALO DE TIEMPO
    public void MovimientoPropulsor(){
        
        Thread bucle = new Thread(new Runnable(){  //EVITA EL BLOQUEO DEL PROGRAMA PRINCIPAL
            @Override
            public void run(){
                PropulsorIndice = 0;
                
                while ( true && !Muerte){
                    
                    if (PropulsorIndice >=4){
                        PropulsorIndice = 0;
                    }
                    try{
                        
                        Thread.sleep(100);      //VELOCIDAD DE LA IMAGEN DEL PROPULSOR
                    } catch(InterruptedException ex){}
                    
                    PropulsorIndice++;
                }
                PropulsorIndice = 0;
            }
        });
        
        bucle.start();  //INICIO DEL METODO RUN() THREAD
    }
    
// MOVIMIENTO DEL PROYECTIL
    public void Movimiento_Del_Proyectil(){
        
        
        Thread Lanzar = new Thread(new Runnable(){
            @Override
            public void run() {
                
                MaxEnergia--;
                int ind = Es_Proyec.size()-1;
                int PosX = 0;
                boolean evalua = true;
                
                PosX = DisparoY.get(ind);
                while (evalua && PosX>-10){
                    
                    if (!Pausar){
                        
                        try{
                            
                            evalua = Es_Proyec.get(ind);
                            DisparoY.set(ind, PosX--);
                            
                        } catch(IndexOutOfBoundsException e){
                            evalua =false;
                            System.out.println("Error En El Movimiento Del Proyectil");
                        }
                    }
                    
                    try{
                        Thread.sleep(2);    // VELOCIDAD DEL PROYECTIL
                    }
                    catch (InterruptedException ex){}
                }
                
                MaxEnergia++;
                try{
                    Es_Proyec.set(ind, false);
                } catch(IndexOutOfBoundsException e){
                    System.out.print("Error Al Cambiar Estado De Proyectil");
                }
            }
        });
        
        Lanzar.start();
    }

//SONIDO DEL JUEGO
    public void MusicFondoFX(){
        
        Thread Music = new Thread(new Runnable() {
            @Override
            public void run(){
                
                Re_Au.Fx(10);
                
                long EfecEmpieza = GetTime();
                
                while (true){
                    
                    if (GetTime()>EfecEmpieza + 279){
                        Re_Au.Fx(10);
                        EfecEmpieza = GetTime();
                    }
                    
                    try{
                        Thread.sleep(100);
                    } catch (InterruptedException ex){}
                }
            }
    
        });
        
        Music.start();
    }
    
    
 //FONDO GRAFICO
    public void BucleFondo(){
        
        Thread bucle = new Thread(new Runnable() {
            @Override
            public void run(){
                
                while (true && !Reiniciar){
                    
                    if (!Pausar){
                        
                        if (Posicion_FondoY > 0){
                            Posicion_FondoY = -1000;                  
                        }
                        
                        Posicion_FondoY = Posicion_FondoY+1;
                        
                    }
                try{
                    
                    Thread.sleep(20);
                    } catch(InterruptedException ex) {}   
                }
            }
            
        });
        
        bucle.start();
    }
 
//FOTOGRAMAS 
    public void ObtenerFps(Graphics2D g2){
        
        Obtener_Tiempo = GetTime();
        
            if (Obtener_Tiempo > Obtener_Tiempo_Despues+1){
                Obtener_Tiempo_Despues = GetTime();
                ObtenerFps=fps;
                fps = 0;
            }
            g2.setColor(Color.red);
            g2.setFont(new Font("Verdana", Font.BOLD,20));
            g2.drawString("Fps" + ObtenerFps,790,750);
            fps++;
    }
    
//TIEMPO PARA EL SIGUIENTE NIVEL   
    public void CuentaAtras(){
        
        Thread timer = new Thread(new Runnable(){
            
            @Override
            public void run(){
                
                Reiniciar = true;
                Muerte = true;
                Tiempo_Atras = 3;
                T_Atras = true;
                
                while (Tiempo_Atras > -1){
                    
                    try{
                        Thread.sleep(1000);
                    } catch(InterruptedException ex){}
                    
                    Tiempo_Atras--;
                }
                
                Reiniciar();
                
                T_Atras = false;
                Muerte = false;
                MovimientoPropulsor();
                
                BucleFondo();
                
                CrearAsteroide();
            }
        });
        
        timer.start();
    }
    
//TEXTO NUMEROS Y LETRAS
    public void Dibuja_Texto_Grafico(Graphics2D g2, int X, int Y, int Ancho, int Alto, int separacion, String texto){
        
        int longitud = texto.length();
        
        int vueltas = 0;
        int restar = 0;
        
        for (int p=0;p<longitud;p++){
            
            String letra = ""+texto.charAt(p);
            
            BufferedImage img = null;
            
            if (letra.equalsIgnoreCase("a")){img = Letras.get(0);}
            if (letra.equalsIgnoreCase("b")){img = Letras.get(1);}
            if (letra.equalsIgnoreCase("c")){img = Letras.get(2);}
            if (letra.equalsIgnoreCase("d")){img = Letras.get(3);}
            if (letra.equalsIgnoreCase("e")){img = Letras.get(4);}
            if (letra.equalsIgnoreCase("f")){img = Letras.get(5);}
            if (letra.equalsIgnoreCase("g")){img = Letras.get(6);}
            if (letra.equalsIgnoreCase("h")){img = Letras.get(7);}
            if (letra.equalsIgnoreCase("i")){img = Letras.get(8);}
            if (letra.equalsIgnoreCase("j")){img = Letras.get(9);}
            if (letra.equalsIgnoreCase("k")){img = Letras.get(10);}
            if (letra.equalsIgnoreCase("l")){img = Letras.get(11);}
            if (letra.equalsIgnoreCase("m")){img = Letras.get(12);}
            if (letra.equalsIgnoreCase("n")){img = Letras.get(13);}
            if (letra.equalsIgnoreCase("o")){img = Letras.get(14);}
            if (letra.equalsIgnoreCase("p")){img = Letras.get(15);}
            if (letra.equalsIgnoreCase("q")){img = Letras.get(16);}
            if (letra.equalsIgnoreCase("r")){img = Letras.get(17);}
            if (letra.equalsIgnoreCase("s")){img = Letras.get(18);}
            if (letra.equalsIgnoreCase("t")){img = Letras.get(19);}
            if (letra.equalsIgnoreCase("u")){img = Letras.get(20);}
            if (letra.equalsIgnoreCase("v")){img = Letras.get(21);}
            if (letra.equalsIgnoreCase("w")){img = Letras.get(22);}
            if (letra.equalsIgnoreCase("x")){img = Letras.get(23);}
            if (letra.equalsIgnoreCase("y")){img = Letras.get(24);}
            if (letra.equalsIgnoreCase("z")){img = Letras.get(25);}
            
            if (letra.equals("0")){img = Numeros.get(0);}
            if (letra.equals("1")){img = Numeros.get(1);}
            if (letra.equals("2")){img = Numeros.get(2);}
            if (letra.equals("3")){img = Numeros.get(3);}
            if (letra.equals("4")){img = Numeros.get(4);}
            if (letra.equals("5")){img = Numeros.get(5);}
            if (letra.equals("6")){img = Numeros.get(6);}
            if (letra.equals("7")){img = Numeros.get(7);}
            if (letra.equals("8")){img = Numeros.get(8);}
            if (letra.equals("9")){img = Numeros.get(9);}
            
            if (Ancho !=0){
                g2.drawImage(img, X+vueltas, Y, Ancho, Alto, rootPane);
            }
            
            vueltas = vueltas + separacion - restar;
            restar = 0;
            
        }
    }
    
//REINICIAR
    public void Reiniciar(){
        
        EnergiaVital = 10;
        DisparoX.clear();
        DisparoY.clear();
        Es_Proyec.clear();
        
        Aste_X.clear();
        Aste_Y.clear();
        Aste_Estado.clear();
        Aste_Tipo.clear();
        Aste_Impacto.clear();
        
        Efect_Aste.clear();
        Efect_Esta.clear();
        Reiniciar = false;
    }
    
//ACTUALIZAR
    public void CrearBucle(){
        
        Thread ac = new Thread(new Runnable() {
            @Override
            public void run() {
                
                while(true){
                    
                    try{
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {}
                    repaint();
                }
            }
        });
        
        ac.start();
    }
    
//SET DEL PROGRAMA
    public void setGra2D(ArrayList < BufferedImage > Gra2D){
        this.Gra2D = Gra2D;
    }
    
    public void setPropulsor2D(ArrayList < BufferedImage > Propulsor2D){
        this.Propulsor2D = Propulsor2D;
    }
    
    public void setAsteroide2D(ArrayList < BufferedImage > Asteroide2D){
        this.Asteroide2D = Asteroide2D;
    }
    
    public void setLetras(ArrayList < BufferedImage > Letras){
        this.Letras = Letras;
    }
    
    public void setNumeros(ArrayList < BufferedImage > Numeros){
        this.Numeros = Numeros;
    }
    
    public void setBarraG2(ArrayList < BufferedImage > BarraG2){
        this.BarraG2 = BarraG2;
    }
    
    public void setBarraVital(ArrayList < BufferedImage > BarraVital){
        this.BarraVital = BarraVital;
    }
    
}


