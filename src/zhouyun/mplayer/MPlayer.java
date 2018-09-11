package zhouyun.mplayer;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class MPlayer {
	public static MSwingGUI ms = null;
	public static void main(String[] args) {	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ms = new MSwingGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String ntime = ms.getMControl().getTime(ms.getMediaPlayerComponent());
            	int nprogressBar = ms.getMControl().getBarPosition( ms.getMediaPlayerComponent() );
            	
            	ms.getTimeLabel().setText(ntime);
                ms.getProgressBar().setValue(nprogressBar);
            }
        }).start();
		
	}
}
