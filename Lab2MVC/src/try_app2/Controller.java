package try_app2;

public class Controller {
    Fir f;
    Window w;

    @SuppressWarnings("deprecation")
	public Controller(Fir t,Window w){
        
        this.f = t;
        f.addObserver(w);
        this.w = w;
        
    }

    
}