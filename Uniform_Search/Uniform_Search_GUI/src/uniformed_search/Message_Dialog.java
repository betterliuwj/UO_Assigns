package uniformed_search;

import javax.swing.JDialog;
import javax.swing.JLabel;

class messageDialog extends JDialog{
	public messageDialog(String message){
		JLabel tmp_label=new JLabel(message);
		this.add(tmp_label);
		this.setVisible(true);
	}
}
