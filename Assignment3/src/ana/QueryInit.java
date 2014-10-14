package ver1;

import java.io.*;


public class QueryInit implements Serializable{
	SDS sds=new SDS();

	String filename = "umfrageErgebnisse.txt";
	 
	FileOutputStream fos = null;
	ObjectOutputStream oos = null;
	FileInputStream fis=null;
	ObjectInputStream ois =null;

	public synchronized void  speichern(String antwort) {
		try {
			this.sds = (SDS) SDSliefern();
			fos = new FileOutputStream(filename);
			oos = new ObjectOutputStream(fos);
			if (antwort.equals("ja")){
				this.sds.ja++;
			}
			if (antwort.equals("nein")){
				this.sds.nein++;
			}
			if (antwort.equals("sonstiges")){
				this.sds.sonstiges++;
			}
			oos.writeObject(this.sds);
			oos.flush();
			oos.close();
			fos.close();
			System.out.println("Datei gespeichert");
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}

	}

	private SDS SDSliefern() {
		
		try {
			fis = new FileInputStream(filename);
			ois = new ObjectInputStream(fis);
			this.sds = (SDS) ois.readObject();
			
			} catch (EOFException e) {
				
				} catch (FileNotFoundException e) {
				
			} catch (IOException e) {
					
				} catch (ClassNotFoundException e) {
						
			}
		if (this.sds==null){this.sds.ja=0; this.sds.nein=0; this.sds.sonstiges=0;}
		try {
			ois.close();
			fis.close();
		} catch (IOException e) {
			
		}
catch (NullPointerException e) {
			System.out.println("Erste Anfrage!");
		}
		

		return sds;
	}

	public String anzeigen() {
		
			this.sds = SDSliefern();
			String nachricht="Der aktuelle Stand der Abstimmung: \nJa: "+this.sds.ja+" \nNein: "+this.sds.nein+" \nEnthaltung: "+this.sds.sonstiges+".";
		return nachricht;
	}
}