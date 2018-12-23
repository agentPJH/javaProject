package dynamic_beat_1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/게임정보.png")).getImage();
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/구분.png")).getImage();
	private Image judgementImage = new ImageIcon(Main.class.getResource("../images/판단.png")).getImage();

	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/상단바.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/상단바.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/상단바.png")).getImage();
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/상단바.png")).getImage();
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/상단바.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/상단바.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/상단바.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/상단바.png")).getImage();
	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;
	ArrayList<Note> noteList = new ArrayList<Note>();

	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle,false);

	}




	public void screenDraw(Graphics2D g) {
		g.drawImage(noteRouteSImage, 228, 30, null);
		g.drawImage(noteRouteDImage, 332, 30, null);
		g.drawImage(noteRouteFImage, 436, 30, null);
		g.drawImage(noteRouteSpace1Image, 540, 30, null);
		g.drawImage(noteRouteSpace2Image, 640, 30, null);
		g.drawImage(noteRouteJImage, 744, 30, null);
		g.drawImage(noteRouteKImage, 848, 30, null);
		g.drawImage(noteRouteLImage, 952, 30, null);
		g.drawImage(noteRouteLineImage, 224, 30, null);
		g.drawImage(noteRouteLineImage, 328, 30, null);
		g.drawImage(noteRouteLineImage, 432, 30, null);
		g.drawImage(noteRouteLineImage, 536, 30, null);
		g.drawImage(noteRouteLineImage, 740, 30, null);
		g.drawImage(noteRouteLineImage, 844, 30, null);
		g.drawImage(noteRouteLineImage, 948, 30, null);
		g.drawImage(noteRouteLineImage, 1052, 30, null);
		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(judgementImage, 0, 580, null);
		for(int i=0; i<noteList.size(); i++) {
			Note note  = noteList.get(i);
			if(!note.isProceeded()) {
				noteList.remove(i);
				i--;
			}
			else {
				note.screenDraw(g);
			}
		}
		g.setColor(Color.WHITE);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 20 ,702);
		g.drawString(difficulty,1190,702);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString("000000", 565, 702);
		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.setColor(Color.DARK_GRAY);
		g.drawString("S",270,609);
		g.drawString("D",374,609);
		g.drawString("F",478,609);
		g.drawString("SPACE BAR",580,609);
		g.drawString("J",784,609);
		g.drawString("K",889,609);
		g.drawString("L",993,609);


	}
	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/맞춤.png")).getImage();
	}
	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/상단바.png")).getImage();
	}
	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/맞춤.png")).getImage();
	}
	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/상단바.png")).getImage();
	}
	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/맞춤.png")).getImage();
	}
	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/상단바.png")).getImage();
	}
	public void pressSpace() {
		judge("Space");
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/맞춤.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/맞춤.png")).getImage();
	}
	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/상단바.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/상단바.png")).getImage();
	}
	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/맞춤.png")).getImage();
	}
	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/상단바.png")).getImage();
	}
	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/맞춤.png")).getImage();
	}
	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/상단바.png")).getImage();
	}
	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/맞춤.png")).getImage();
	}
	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/상단바.png")).getImage();
	}

	@Override
	public void run() {
		dropNotes();


	}
	public void close() {
		gameMusic.close();
		this.interrupt();
	}
	public void dropNotes() {
		Beat[] beats = null;
		if(titleName.equals("MC 몽 - 미치겠어.mp3") && difficulty.equals("easy")) {
			int startTime = 4460 - Main.REACH_TIME*1000;
			int gap = 125;
			beats = new Beat[] {
					new Beat(startTime + gap * 2, "S"),
					new Beat(startTime + gap * 4, "Space"),
					new Beat(startTime + gap * 6, "D"),
					new Beat(startTime + gap * 8, "K"),
					new Beat(startTime + gap * 10, "L"),
					new Beat(startTime + gap * 10, "Space"),
			};	
		}
		else if(titleName.equals("MC 몽 - 미치겠어.mp3") && difficulty.equals("hard")) {
			int startTime = 4460 - Main.REACH_TIME*1000;
			int gap = 125;
			beats = new Beat[] {
					new Beat(startTime + gap * 1, "S"),
					new Beat(startTime + gap * 2, "Space"),
					new Beat(startTime + gap * 3, "D"),
					new Beat(startTime + gap * 4, "K"),
					new Beat(startTime + gap * 5, "L"),
					new Beat(startTime + gap * 7, "Space"),
			};	
		}
		else if(titleName.equals("MC몽 - So Fresh (Feat. 김태우).mp3")&& difficulty.equals("easy")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "Space"),
			};	
		}
		else if(titleName.equals("MC몽 - So Fresh (Feat. 김태우).mp3")&& difficulty.equals("hard")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "Space"),
			};	
		}
		else if(titleName.equals("친구라는 건 (with. 김범수)-박효신.mp3")&& difficulty.equals("easy")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "Space"),
			};	
		}
		else if(titleName.equals("친구라는 건 (with. 김범수)-박효신.mp3")&& difficulty.equals("hard")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "Space"),
			};	
		}
		int i=0;
		gameMusic.start();
		while(i<beats.length && !isInterrupted()) {
			boolean dropped = false;
			if(beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if(!dropped) {
				try {
					Thread.sleep(5);
				}catch(Exception e) {
					e.printStackTrace();
				}

			}

		}
	}
	public void judge(String input) {
		for(int i=0; i<noteList.size(); i++) {
			Note note = noteList.get(i);
			if(input.equals(note.getNoteType())) {
				note.judge();
				break;
			}
		}
	}
}
