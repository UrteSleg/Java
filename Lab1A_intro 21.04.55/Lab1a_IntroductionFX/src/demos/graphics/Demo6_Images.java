/****************************************************************
 * Šioje klasėje eksperimentuojama su kuriamų figūrų prioritetais
 * Sukuriami objektai Images klasės pagrindu
 * 
 * Pradžioje vykdykite kodą ir stebėkite atliekamus veiksmus
 * Užduotis atlikite sekdami nurodymus programinio kodo komentaruose
 * Studentams siūloma toliau vystyti Images pagrindų temą.
 * 
 * @author Eimutis Karčiauskas, KTU programų inžinerijos katedra 2019 08 05
 **************************************************************************/
package demos.graphics;

import extendsFX.BaseGraphics;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Demo6_Images extends BaseGraphics {

    void createRects(){
        double x = 0,  y = 0;
        double w = 60, h = 40;
        for(int i=0; i<12; i++){
            Rectangle rect = new Rectangle(x += w/2, y += h/2, w, h);
            rect.setFill(randomColor()); 
            nodes.add(rect);
        }
    }
    void createCircles(){
        double radius = 30;
        for(int i=0; i<12; i++){
            Circle circle = new Circle(canvasW/2, canvasH/2, radius);
            circle.setFill(randomColor());  
            nodes.add(0, circle);
            radius += 15;
        }
    }
    // vykdant tolimesnę funciją stebėkite ir paaiškinkite vaizdo kitimą
    void fromEndToBegin(){
        nodes.add(0, nodes.remove(nodes.size()-1));
    }
//=================================================    
    Image space = new Image( "images\\space.png" );
    Image sun   = new Image( "images\\sun.png" );
    Image earth = new Image( "images\\earth.png" );
    Image ufo   = new Image( "images\\ufo_0.png");
    Image money   = new Image( "images\\moneybag.png");
    
    ImageView earthView = new ImageView(earth);
    ImageView ufoView = new ImageView(ufo);
    ImageView moneyView = new ImageView(money);
    boolean CanSpin = true;
    double finalPossition = 0;
    long finalNow = 0;
    double t = 0;
    // kosmosą ir saulę nupiešime ant drobės, o žemę paleisime suktis
    void createSpace(){
        final double xc = canvasW / 2;
        final double yc = canvasH / 2;
        final double radius = canvasW / 2 - earth.getWidth();
        final double ufoRadius = earth.getWidth();
        
        gc.drawImage( space, 0, 0 );
        gc.drawImage( sun, xc - sun.getWidth() / 2, yc - sun.getHeight() / 2);
        nodes.add(earthView);
        nodes.add(ufoView);
        nodes.add(moneyView);
        
        earthView.setSmooth(false);
        ufoView.setSmooth(false);
         earthView.setScaleX(1.5);
         earthView.setScaleY(1.5); 
         moneyView.setScaleX(0.5);
         moneyView.setScaleY(0.5);
        //final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                //t = (now - startNanoTime) / 1_000_000_000.0;
                if(CanSpin)
                 t += 0.03;  
                earthView.setX(xc - earth.getWidth()/2 + radius * Math.cos(t));
                earthView.setY(yc - earth.getWidth()/2 + radius * Math.sin(t));         
                ufoView.setX(earthView.getX() - ufo.getWidth()/2 + ufoRadius * Math.cos(-t*.6));
                ufoView.setY(earthView.getY() - ufo.getWidth()/2 + ufoRadius * Math.sin(t));
                moneyView.setX(earthView.getX() + ufoRadius * Math.cos(-t));
                moneyView.setY(earthView.getY() + ufoRadius * Math.sin(0.6*t));
            }
        }.start();        
    }
    // paaiškinkite kintamojo t skaičiavime naudojamą konstantą
    // paskaičiuokite per kiek laiko apskrieja žemė aplink saulę
    // sukurkite start - stopinį mygtuką, kuris aktyvuotų ir stabdytų žemę
    // papildykite erdvę kitais kosminiais ar fantastiniais kūnais
    // 
    //*****************************************
    @Override
    public void createControls(){
        addButton("clear",    e -> nodes.clear()); 
        addButton("Rects",    e -> createRects());
        addButton("Circles",  e -> createCircles());
        addButton("reverse1", e -> fromEndToBegin());
        addButton("clearFirst", e -> {if(nodes.size()>0)
                                        nodes.remove(0);});
        addButton("clearLast",  e -> {if(nodes.size()>0)
                                        nodes.remove(nodes.size()-1);});
        addButton("Sun_System", e -> createSpace());
//      addButton("Stop/Start", e -> stopStartSolarSystem());
        addNewHBox();
    }
    @Override
    public void start(Stage stage) throws Exception {
        setCanvas(Color.CYAN.brighter(), space.getWidth(), space.getHeight());
        super.start(stage);
        stage.setTitle("Prioritetų ir Images Demo ");
        baseGrid();
    } 
    
    public static void main(String[] args) {
        launch(args);
    } 
    
    private void stopStartSolarSystem() {  
        if(CanSpin)
            CanSpin = false;
        else
            CanSpin = true;
    }
} // *********************** Demo klasės pabaiga