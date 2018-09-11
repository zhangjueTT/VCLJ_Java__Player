package zhouyun.mplayer;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

public class MControl {
	private boolean isLoad = false;
	private boolean isStart = false;
	private float mRate = 1.0f;
	private final int maxBar = 10000;
	private int mPosition = 0;
	private long mMovieLength = 0;
	
	private long mTotalMimute = 0;
	private long mTotalSecond = 0;
	
	private long mNowMimute  = 0;
	private long mNowSecond  = 0;

	
	
	
	public void mload(EmbeddedMediaPlayerComponent mediaPlayerComponent){
		if(isLoad)
			mediaPlayerComponent.getMediaPlayer().pause();
		JFileChooser fDialog = new JFileChooser();
		fDialog.setDialogTitle("请选择视频文件");
		// 文件过滤
		fDialog.setFileFilter(new FileNameExtensionFilter("视频(*.avi, *.mp4)", "avi", "mp4"));
		int returnVal = fDialog.showOpenDialog(null);	
		if(JFileChooser.APPROVE_OPTION == returnVal){
		    //如果正确的选择了文件
			mediaPlayerComponent.getMediaPlayer().playMedia(fDialog.getSelectedFile().toString());
			
			// 这里无法获取视频长度，并不知道是为什么。
			isLoad = true;
			isStart = true;
			mRate = 1.0f;
		}
	}
	
	public void mstart(EmbeddedMediaPlayerComponent mediaPlayerComponent){
		if( !isLoad) return;
		if( isStart) return;
		mediaPlayerComponent.getMediaPlayer().start();
		isStart = true;
	}
	public int getmaxBar(){
		return maxBar;
	}
	
	public long getMovieLength(){
		return mMovieLength;
	}
	
	public String getTime(EmbeddedMediaPlayerComponent mediaPlayerComponent){	
		if( 0 == mMovieLength)
		{
			mMovieLength = mediaPlayerComponent.getMediaPlayer().getLength()/1000;
			mTotalMimute = mMovieLength / 60;
			mTotalSecond = mMovieLength % 60;
		}
		long ntime = mediaPlayerComponent.getMediaPlayer().getTime()/1000;
		mNowMimute = ntime / 60;
		mNowSecond= ntime % 60;
		
		String stime = mNowMimute+":"+mNowSecond+"/"+mTotalMimute+":"+mTotalSecond;
		return stime;
	}
	
	public int getBarPosition(EmbeddedMediaPlayerComponent mediaPlayerComponent){

		this.mPosition = (int)(mediaPlayerComponent.getMediaPlayer().getPosition() * this.maxBar);
		return this.mPosition;
	}
	
	public void mpause(EmbeddedMediaPlayerComponent mediaPlayerComponent){
		if( !isLoad) return;
		if( !isStart) return;
		mediaPlayerComponent.getMediaPlayer().pause();
		mRate = 1.0f;
		isStart = false;
	}
	public void maccelerate(EmbeddedMediaPlayerComponent mediaPlayerComponent){
		if( !isLoad) return;
		mRate = mRate + 0.2f;
		if(mRate > 2)
			mRate = 0.4f;
		mediaPlayerComponent.getMediaPlayer().setRate(mRate);
	}
	public void mrewind(EmbeddedMediaPlayerComponent mediaPlayerComponent){
		mediaPlayerComponent.getMediaPlayer().skip(-30000);
	}
	public void mfast(EmbeddedMediaPlayerComponent mediaPlayerComponent){
		mediaPlayerComponent.getMediaPlayer().skip(15000);
	}
	public void mexit(EmbeddedMediaPlayerComponent mediaPlayerComponent){
		if( !isLoad) return;
		mediaPlayerComponent.getMediaPlayer().stop();
		isLoad = false;
	}
}

