package GUI;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelGroupButton extends JPanel {

	/**
	 * Create the panel.
	 */
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnSave;
	private JButton btnDelete;
	private JButton btnReload;

	public PanelGroupButton() {
		ImageIcon iconAdd = new ImageIcon(MainFrame.class.getResource("/images/add.png"));
		Image iconImage = iconAdd.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		iconAdd = new ImageIcon(iconImage);
		btnAdd = new JButton("Thêm", iconAdd);
		btnAdd.setIconTextGap(12);
	
		ImageIcon iconEdit = new ImageIcon(MainFrame.class.getResource("/images/edit.png"));
		iconImage = iconEdit.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		iconEdit = new ImageIcon(iconImage);
		btnEdit = new JButton("Sửa", iconEdit);
		btnEdit.setIconTextGap(12);
		
		ImageIcon iconSave = new ImageIcon(MainFrame.class.getResource("/images/save.png"));
		iconImage = iconSave.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		iconSave = new ImageIcon(iconImage);
		btnSave = new JButton("Lưu", iconSave);
		btnSave.setIconTextGap(12);
		
		ImageIcon iconDelete = new ImageIcon(MainFrame.class.getResource("/images/delete.png"));
		iconImage = iconDelete.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		iconDelete = new ImageIcon(iconImage);
		btnDelete = new JButton("Xóa", iconDelete);
		btnDelete.setIconTextGap(12);
		
		ImageIcon iconReload = new ImageIcon(MainFrame.class.getResource("/images/reload.png"));
		iconImage = iconReload.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		iconReload = new ImageIcon(iconImage);
		btnReload = new JButton("Làm mới", iconReload);
		btnReload.setIconTextGap(12);
	}

	public JPanel getGroupButton() {
		JPanel pn = new JPanel(new GridLayout(5, 1, 8, 8));
		pn.setBorder(new EmptyBorder(12, 12, 12, 12));
		pn.add(btnAdd);
		pn.add(btnEdit);
		pn.add(btnSave);
		pn.add(btnDelete);
		pn.add(btnReload);
		return pn;
	}

}
