/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iamSoftware.Classes;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Horario {

public static void start(final JLabel textFieldHorario){
	Thread atualizaHora = new Thread(new Runnable() {
		
		@Override
		public void run() {
			try {
				while (true) {
					Date date = new Date();
					StringBuffer data = new StringBuffer();
					
                                        SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat t = new SimpleDateFormat("HH:mm:ss"); // Para Atualizar a DATA so mudar para o formato dd/MM/yyyy
					textFieldHorario.setText(data.toString() +d.format(date)+" "+t.format(date));
					Thread.sleep(1000);
					textFieldHorario.revalidate();
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			
		}
	});
	atualizaHora.start();
}
}
/**
 *
 * @author ga_br
 */

