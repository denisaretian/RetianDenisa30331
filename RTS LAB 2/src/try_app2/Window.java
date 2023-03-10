package try_app2;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class Window extends JFrame implements Observer{
            ArrayList<JProgressBar> bars=new ArrayList<JProgressBar>();
            public Window(int nrThreads) {
                        setLayout(null);
                        setSize(450,400);
                        setDefaultCloseOperation(EXIT_ON_CLOSE);
                        init(nrThreads);
                        this.setVisible(true);
             }
            private void init(int n){
                      for(int i=0 ;i<n; i++){
                                   JProgressBar pb=new JProgressBar();
                                   pb.setMaximum(1000);
                                   pb.setBounds(50,(i+1)*30,350,20);
                               this.add(pb);
                               this.bars.add(pb);
                         }
              }
            public void setProgressValue(int id,int val){
                        bars.get(id).setValue(val);
              }
			@Override
			public void update(Observable arg0, Object arg1) {
				bars.get(((Fir)arg0).id).setValue(((Fir)arg0).getC());
				bars.get(((Fir)arg0).id).repaint();
			}
}

