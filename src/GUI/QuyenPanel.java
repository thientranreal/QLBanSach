package GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class QuyenPanel extends JPanel{
	DefaultTableModel tablemodel;
	DefaultTableModel tablemodel1;
	JTextField txtmaquyen;
		JTextField txttenquyen;
	JComboBox comboBox;
	JButton btnremoveright;
	JButton btnaddright;
	JButton btnremoveleft;

	public QuyenPanel(){
		Init();
		loadData();
	}
	public void loadData(){
		btnaddright.setEnabled(false);
		tablemodel.setRowCount(0);
		tablemodel1.setRowCount(0);
	    java.util.List<DTO.Quyen_DTO>  list = BUS.PhanQuyen_BUS.getInstance().getAllQuyen();
		for(DTO.Quyen_DTO dto:list){
			tablemodel.addRow(new Object[]{dto.getMaquyen(),dto.getTenquyen()});
		}
		txtmaquyen.setText("");
		txttenquyen.setText("");
        btnremoveright.setEnabled(false);
	    comboBox.setEnabled(false);
		comboBox.removeAllItems();
		comboBox.removeAllItems();
	}
	public void Init(){
		setLayout(new GridLayout(1,2,20,0));
        setBackground(new Color(250, 238, 232));
		JPanel left = new JPanel();
		left.setBackground(new Color(250, 238, 232));
		left.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		left.setLayout(new BorderLayout(0,10));
		JPanel centerleft = new JPanel();
		centerleft.setBackground(new Color(250, 238, 232));
		centerleft.setLayout(new GridLayout(1,1));
		tablemodel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String column[] = {"Mã quyền","Tên quyền"};

		tablemodel.setColumnIdentifiers(column);
		JTable table = new JTable(tablemodel);
		table.addMouseListener(
				new MouseAdapter() {
					@Override
					public void mouseClicked(java.awt.event.MouseEvent e
					) {
						int row = table.getSelectedRow();
						txtmaquyen.setText("");
						txttenquyen.setText("");
						if(row!=-1){
							tablemodel1.setRowCount(0);
							java.util.List<String> list = BUS.PhanQuyen_BUS.getInstance().getTenChucNang(tablemodel.getValueAt(row,0).toString());
							for(String item :list){
							    tablemodel1.addRow(new Object[]{item});
							}
							txtmaquyen.setText(tablemodel.getValueAt(row,0).toString());
							txttenquyen.setText(tablemodel.getValueAt(row,1).toString());
							comboBox.removeAllItems();
							 java.util.List<String> list2 = BUS.PhanQuyen_BUS.getInstance().getLeftChucNang(tablemodel.getValueAt(row,0).toString());
                             for(String item :list2 ){
								 comboBox.addItem(item);
 							 }
							 btnaddright.setEnabled(true);
                             comboBox.setEnabled(true);
							 comboBox.setSelectedIndex(-1);
							 btnremoveright.setEnabled(true);
						}
					}
				}
		);
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane sp = new JScrollPane(table);
		centerleft.add(sp);
		JPanel southleft = new JPanel();
		southleft.setBackground(new Color(250, 238, 232));
		southleft.setPreferredSize(new Dimension(0,100));///////
		southleft.setLayout(new GridLayout(2,1));
		JPanel txtleft = new JPanel();
		txtleft.setBackground(new Color(250, 238, 232));
		txtleft.setLayout(new FlowLayout(FlowLayout.CENTER));
		txtmaquyen = new JTextField();
		txtmaquyen.setPreferredSize(new Dimension(200,28));
		 txttenquyen =new JTextField();
		txttenquyen.setPreferredSize(new Dimension(200,28));
		txtleft.add(txtmaquyen);
		txtleft.add(txttenquyen);
		JPanel btnleft = new JPanel();
		btnleft.setBackground(new Color(250, 238, 232));
		btnleft.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton btnaddleft = new JButton("Thêm");
		btnaddleft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtmaquyen.getText().trim().trim().equals("")||txttenquyen.getText().trim().trim().equals("")){
					JOptionPane.showMessageDialog(null,"Vui lòng điền đầy đủ thông tin");
					return;
				}
				if(BUS.PhanQuyen_BUS.getInstance().isExists(txtmaquyen.getText().trim().trim())){
					JOptionPane.showMessageDialog(null,"Đã tồn tại quyền");
				    return;
				}
				if(BUS.PhanQuyen_BUS.getInstance().isExistsTenQuyen(txttenquyen.getText().trim().trim())){
					JOptionPane.showMessageDialog(null,"Đã tồn tại tên quyền");
					return ;
				}
                if(!BUS.PhanQuyen_BUS.getInstance().insert(txtmaquyen.getText().trim().trim(),txttenquyen.getText().trim().trim())) {
					JOptionPane.showMessageDialog(null, "Thêm quyền thất bại");
					return;
				}
				loadData();
			}
		});


		btnaddleft.setFocusable(false);
		btnaddleft.setBackground(Color.WHITE);
		btnaddleft.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));
		JButton btneditleft=new JButton("Cập nhật");
		btneditleft.setFocusable(false);
		btneditleft.setBackground(Color.WHITE);
		btneditleft.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));
	    btneditleft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtmaquyen.getText().trim().equals("")||txttenquyen.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null,"Vui lòng nhập thông tin đầy đủ");
					return;
				}
				if(!BUS.PhanQuyen_BUS.getInstance().isExists(txtmaquyen.getText().trim())){
					JOptionPane.showMessageDialog(null,"Không tồn tại quyền");
					return;
				}
				if(BUS.PhanQuyen_BUS.getInstance().isExistsTenQuyenExceptMe(txtmaquyen.getText().trim(),txttenquyen.getText().trim())){
					JOptionPane.showMessageDialog(null,"Đã tồn tại tên quyền");
					return ;
				}
				if(!BUS.PhanQuyen_BUS.getInstance().updateQuyen(txtmaquyen.getText().trim(),txttenquyen.getText().trim())){
					JOptionPane.showMessageDialog(null,"Cập nhật quyền thất bại");
					return;
				}
				loadData();
			}
		});
		btnremoveleft = new JButton("Xoá");
		btnremoveleft.setFocusable(false);
		btnremoveleft.setBackground(Color.WHITE);
		btnremoveleft.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));
		btnremoveleft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                if(txtmaquyen.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null,"Vui lòng nhập mã quyền để xoá");
					return;
				}
				if(!BUS.PhanQuyen_BUS.getInstance().isExists(txtmaquyen.getText().trim())){
					JOptionPane.showMessageDialog(null,"Không tồn tại quyền để xoá");
					return;
				}
				if(!BUS.PhanQuyen_BUS.getInstance().removeQuyen(txtmaquyen.getText().trim())){
					JOptionPane.showMessageDialog(null,"Xoá quyền thất bại");
					return;
				}
				loadData();

			}
		});
		btnleft.add(btnaddleft);
		btnleft.add(btneditleft);
		btnleft.add(btnremoveleft);


		southleft.add(txtleft);
		southleft.add(btnleft);

		left.add(centerleft,BorderLayout.CENTER);
		left.add(southleft,BorderLayout.SOUTH);
		JPanel right = new JPanel();
		right.setBackground(new Color(250, 238, 232));
		right.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		right.setLayout(new BorderLayout(10,0));
		JPanel centerright = new JPanel();
		centerright.setBackground(new Color(250, 238, 232));
		centerright.setLayout(new GridLayout(1,1));
	    tablemodel1 = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String column1[] = {"Tên chức năng"};

		tablemodel1.setColumnIdentifiers(column1);
		JTable table1 = new JTable(tablemodel1);
		table1.getSelectionModel()
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane sp1 = new JScrollPane(table1);
		centerright.add(sp1);
		JPanel rightright = new JPanel();
		rightright.setBackground(new Color(250, 238, 232));
		rightright.setPreferredSize(new Dimension(200,0));
		rightright.setLayout(new BoxLayout(rightright,BoxLayout.Y_AXIS));
		comboBox = new JComboBox();
		comboBox.setSelectedIndex(-1);
		comboBox.setMinimumSize(new Dimension(200,30));
		comboBox.setMaximumSize(new Dimension(200,30));
        comboBox.setEnabled(false);


		btnaddright = new JButton("Thêm");
		btnaddright.setBackground(Color.WHITE);
		btnaddright.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
		btnaddright.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnaddright.setFocusable(false);
		btnaddright.setEnabled(false);
		btnaddright.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                  if(comboBox.getSelectedIndex()==-1){
					  JOptionPane.showMessageDialog(null,"Vui lòng chọn chức năng để thêm vào");
					  return;
				  }
				  if(!BUS.PhanQuyen_BUS.getInstance().isExists(table.getValueAt(table.getSelectedRow(),0).toString())){
					  JOptionPane.showMessageDialog(null,"Quyền không tồn tại");
					  return;
				  }

				  if(BUS.PhanQuyen_BUS.getInstance().isExistsTenChucNangInQuyen(table.getValueAt(table.getSelectedRow(),0).toString(),comboBox.getSelectedItem().toString())){
					  JOptionPane.showMessageDialog(null,"Quyền này đã tồn tại chức năng đó");
					  return;
				  }
				  if(!BUS.PhanQuyen_BUS.getInstance().insertChucNangToQuyen(table.getValueAt(table.getSelectedRow(),0).toString(),comboBox.getSelectedItem().toString())){
					  JOptionPane.showMessageDialog(null,"Thêm chức năng thất bại");
					  return;
				  }
				  loadData();

			}
		});
		JPanel addcn = new JPanel();
		addcn.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(15,5,15,5)));
		addcn.setLayout(new BoxLayout(addcn,BoxLayout.Y_AXIS));
		addcn.add(comboBox);
		addcn.setBackground(new Color(250, 238, 232));
		addcn.add(btnaddright);
		btnremoveright = new JButton("Xoá");
		btnremoveright.setBackground(Color.WHITE);
		btnremoveright.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
		btnremoveright.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnremoveright.setFocusable(false);
		btnremoveright.setEnabled(false);
		btnremoveright.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table1.getSelectedRow()==-1){
					JOptionPane.showMessageDialog(null,"Vui lòng chức năng để xoá");
					return;
				}
				if(!BUS.PhanQuyen_BUS.getInstance().isExistsTenChucNangInQuyen(table.getValueAt(table.getSelectedRow(),0).toString(), table1.getValueAt(table1.getSelectedRow(),0).toString())){
					JOptionPane.showMessageDialog(null,"Quyền này không tồn tại chức năng đó");
					return;
				}
				if(!BUS.PhanQuyen_BUS.getInstance().isExists(table.getValueAt(table.getSelectedRow(),0).toString())){
					JOptionPane.showMessageDialog(null,"Quyền không tồn tại");
					return;
				}
				if(!BUS.PhanQuyen_BUS.getInstance().removeTenChucNangInQuyen(table.getValueAt(table.getSelectedRow(),0).toString(),table1.getValueAt(table1.getSelectedRow(),0).toString())){
					JOptionPane.showMessageDialog(null,"Xoá chức năng thất bại");
					return;
				}
				loadData();


			}
		});

		rightright.add(addcn);
		rightright.add(btnremoveright);
		JPanel southright = new JPanel();
		southright.setBackground(new Color(250, 238, 232));
		southright.setPreferredSize(new Dimension(0,200));////

		right.add(centerright,BorderLayout.CENTER);
		right.add(rightright,BorderLayout.EAST);
		right.add(southright,BorderLayout.SOUTH);
		add(left);
		add(right);


	}


}