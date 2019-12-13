package Juego;
        
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Imagenes_Juego {
    
   private Galaxia Bk_Br;
   
   public Imagenes_Juego(Galaxia Bk_Br){
       this.Bk_Br=Bk_Br;
   }

   public void CargarGraficos(){
       
       ArrayList<BufferedImage> ArrayImg =new ArrayList<>();
       Alma_Img("Graficos/Naves/Nave2.png", ArrayImg);
       Alma_Img("Graficos/Fondos/Fondo1.jpg", ArrayImg);
       Alma_Img("Graficos/Naves/Proyectil1.png", ArrayImg);
       Alma_Img("Graficos/Naves/NaveDaño.png", ArrayImg);

       Bk_Br.setGra2D(ArrayImg);
   }
   public void CargarPropulsor(){
       
   
       ArrayList<BufferedImage> ArrayImg = new ArrayList<>();
       Alma_Img("Graficos/Naves/Propulsor1.png",ArrayImg);
       Alma_Img("Graficos/Naves/Propulsor2.png",ArrayImg);
       Alma_Img("Graficos/Naves/Propulsor3.png",ArrayImg);
       Alma_Img("Graficos/Naves/Propulsor4.png",ArrayImg);
       Bk_Br.setPropulsor2D(ArrayImg);
    }
   public void CargarAsteroide(){
        
    
       ArrayList<BufferedImage> ArrayImg = new ArrayList<>();
       Alma_Img("Graficos/Asteroides/Met1.png",ArrayImg);
       Alma_Img("Graficos/Asteroides/Met2.png",ArrayImg);
       Alma_Img("Graficos/Asteroides/Met3.png",ArrayImg);
       Alma_Img("Graficos/Asteroides/Met4.png",ArrayImg);
       Alma_Img("Graficos/Asteroides/Met5.png",ArrayImg);
       Alma_Img("Graficos/Asteroides/Met6.png",ArrayImg);
       Alma_Img("Graficos/Asteroides/Met7.png",ArrayImg);
       Alma_Img("Graficos/Asteroides/Met8.png",ArrayImg);
       Bk_Br.setAsteroide2D(ArrayImg);
   }

   public void CargarLetras(){
    ArrayList<BufferedImage> VectorImg = new ArrayList<>();
    Alma_Img("Graficos/Letras/A.png",VectorImg);
    Alma_Img("Graficos/Letras/B.png",VectorImg);
    Alma_Img("Graficos/Letras/C.png",VectorImg);
    Alma_Img("Graficos/Letras/D.png",VectorImg);
    Alma_Img("Graficos/Letras/E.png",VectorImg);
    Alma_Img("Graficos/Letras/F.png",VectorImg);
    Alma_Img("Graficos/Letras/G.png",VectorImg);
    Alma_Img("Graficos/Letras/H.png",VectorImg);
    Alma_Img("Graficos/Letras/I.png",VectorImg);
    Alma_Img("Graficos/Letras/J.png",VectorImg);
    Alma_Img("Graficos/Letras/K.png",VectorImg);
    Alma_Img("Graficos/Letras/L.png",VectorImg);
    Alma_Img("Graficos/Letras/M.png",VectorImg);
    Alma_Img("Graficos/Letras/N.png",VectorImg);
    Alma_Img("Graficos/Letras/O.png",VectorImg);
    Alma_Img("Graficos/Letras/P.png",VectorImg);
    Alma_Img("Graficos/Letras/Q.png",VectorImg);
    Alma_Img("Graficos/Letras/R.png",VectorImg);
    Alma_Img("Graficos/Letras/S.png",VectorImg);
    Alma_Img("Graficos/Letras/T.png",VectorImg);
    Alma_Img("Graficos/Letras/U.png",VectorImg);
    Alma_Img("Graficos/Letras/V.png",VectorImg);
    Alma_Img("Graficos/Letras/W.png",VectorImg);
    Alma_Img("Graficos/Letras/X.png",VectorImg);
    Alma_Img("Graficos/Letras/Y.png",VectorImg);
    Alma_Img("Graficos/Letras/Z.png",VectorImg);
    Bk_Br.setLetras(VectorImg);
    }
    public void CargarNumeros(){
        ArrayList<BufferedImage> VectorImg = new ArrayList<>();
        Alma_Img("Graficos/Numeros/0.png",VectorImg);
        Alma_Img("Graficos/Numeros/1.png",VectorImg);
        Alma_Img("Graficos/Numeros/2.png",VectorImg);
        Alma_Img("Graficos/Numeros/3.png",VectorImg);
        Alma_Img("Graficos/Numeros/4.png",VectorImg);
        Alma_Img("Graficos/Numeros/5.png",VectorImg);
        Alma_Img("Graficos/Numeros/6.png",VectorImg);
        Alma_Img("Graficos/Numeros/7.png",VectorImg);
        Alma_Img("Graficos/Numeros/8.png",VectorImg);
        Alma_Img("Graficos/Numeros/9.png",VectorImg);
    
        Bk_Br.setNumeros(VectorImg);
    }
    
    public void CargarBarraEnergia(){
        
        ArrayList<BufferedImage> VectorImg = new ArrayList<>();
        Alma_Img("Graficos/BarraDeEnergia/Barra10.png",VectorImg);
        Alma_Img("Graficos/BarraDeEnergia/Barra9.png",VectorImg);
        Alma_Img("Graficos/BarraDeEnergia/Barra8.png",VectorImg);
        Alma_Img("Graficos/BarraDeEnergia/Barra7.png",VectorImg);
        Alma_Img("Graficos/BarraDeEnergia/Barra6.png",VectorImg);
        Alma_Img("Graficos/BarraDeEnergia/Barra5.png",VectorImg);
        Alma_Img("Graficos/BarraDeEnergia/Barra4.png",VectorImg);
        Alma_Img("Graficos/BarraDeEnergia/Barra3.png",VectorImg);
        Alma_Img("Graficos/BarraDeEnergia/Barra2.png",VectorImg);
        Alma_Img("Graficos/BarraDeEnergia/Barra1.png",VectorImg);
        Alma_Img("Graficos/BarraDeEnergia/Barra0.png",VectorImg);
    
        Bk_Br.setBarraG2(VectorImg);
    }
    public void CargarBarraVital(){
        
        ArrayList<BufferedImage> VectorImg = new ArrayList<>();
        Alma_Img("Graficos/BarraVital/Barra10.png",VectorImg);
        Alma_Img("Graficos/BarraVital/Barra9.png",VectorImg);
        Alma_Img("Graficos/BarraVital/Barra8.png",VectorImg);
        Alma_Img("Graficos/BarraVital/Barra7.png",VectorImg);
        Alma_Img("Graficos/BarraVital/Barra6.png",VectorImg);
        Alma_Img("Graficos/BarraVital/Barra5.png",VectorImg);
        Alma_Img("Graficos/BarraVital/Barra4.png",VectorImg);
        Alma_Img("Graficos/BarraVital/Barra3.png",VectorImg);
        Alma_Img("Graficos/BarraVital/Barra2.png",VectorImg);
        Alma_Img("Graficos/BarraVital/Barra1.png",VectorImg);
        Alma_Img("Graficos/BarraVital/Barra0.png",VectorImg);
    
        Bk_Br.setBarraVital(VectorImg);
    }   
    //Este método se encarga de almacenar o agregar a un ArrayList las imagenes.
    public void Alma_Img(String Ruta, ArrayList<BufferedImage>ArrayImg){
        //Abrimos y cargamos las imagenes de dibujo del programa
        try{
            BufferedImage Img= ImageIO.read(new File(Ruta).getAbsoluteFile());
            ArrayImg.add(Img);
        }
        catch (IllegalArgumentException ex){
            System.out.println("Error al CargarGraficos....");
        }
        catch(IOException ex){
            File file=(new File(Ruta).getAbsoluteFile());
            System.out.println("Error Ruta incorrecta..."+file);
        }
    } 
}