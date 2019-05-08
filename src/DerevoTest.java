import java.awt.*;
import java.awt.event.*;
 
import javax.swing.*;
import javax.swing.tree.*;
public class DerevoTest {
 
	public static void main(String[] args) {
EventQueue.invokeLater(new Runnable() {
 
			public void run() {
				JFrame frame = new DerevoFrameEdit();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
 
}
class DerevoFrameEdit extends JFrame {
	private static final long serialVersionUID = 1L;
	public DerevoFrameEdit() {
		setTitle ("ДеревоМир");//имя окна. 
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
 
		TreeNode root = makeSampleDerevo();
		model = new DefaultTreeModel(root);
		tree = new JTree(model);
		tree.setEditable(true);
 
		JScrollPane scrollPane = new JScrollPane(tree);
		add(scrollPane, BorderLayout.CENTER);
 
		makeButtons();
	}
	public TreeNode makeSampleDerevo() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Мир");//название корня дерева.
		DefaultMutableTreeNode country = new DefaultMutableTreeNode("Белорусь");
		root.add(country);//добавление и название наследника, страна. 
		DefaultMutableTreeNode city = new DefaultMutableTreeNode("Минск");
		country.add(city);// добавить наследника, город.
		country = new DefaultMutableTreeNode("Россия");
		root.add(country);//добавить страну.
		city = new DefaultMutableTreeNode("Москва");
		country.add(city);//добавить город.
		country = new DefaultMutableTreeNode("Германия");
		root.add(country);//добавить страну.
		city = new DefaultMutableTreeNode("Берлин");
		country.add(city);//добавит город.
		return root;
	}
	public void makeButtons() {
	} {
		JPanel panel = new JPanel();
		JButton addSiblingButton = new JButton("Добавить страну");
		addSiblingButton.addActionListener(new ActionListener() {
 
			public void actionPerformed(ActionEvent event) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)
						tree.getLastSelectedPathComponent();
				if(selectedNode == null) return;
				DefaultMutableTreeNode parent = (DefaultMutableTreeNode)
						selectedNode.getParent();
				if(parent == null) return;
 
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("Новый");
				int selectedIndex = parent.getIndex(selectedNode);
				model.insertNodeInto(newNode, parent, selectedIndex + 1);
 
				TreeNode[] nodes = model.getPathToRoot(newNode);
				TreePath path = new TreePath(nodes);
				tree.scrollPathToVisible(path);
			}
		});
		panel.add(addSiblingButton);
 
		JButton addChildButton = new JButton("Добавить наследника");
		addChildButton.addActionListener(new ActionListener() {
 
			@Override
			public void actionPerformed(ActionEvent event) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)
				tree.getLastSelectedPathComponent();
 
				if(selectedNode == null) return;
 
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("Новый");
				model.insertNodeInto(newNode, selectedNode, selectedNode.getChildCount());
 
				TreeNode[] nodes = model.getPathToRoot(newNode);
				TreePath path = new TreePath(nodes);
				tree.scrollPathToVisible(path);
			}
		});
		panel.add(addChildButton);
		JButton deleteButton = new JButton("Удалить");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)
				tree.getLastSelectedPathComponent();
 
				if(selectedNode != null && selectedNode.getParent() != null)
					model.removeNodeFromParent(selectedNode); } });
					panel.add(deleteButton);
					add(panel, BorderLayout.SOUTH);
	}
	private DefaultTreeModel model;
	private JTree tree;
	private static final int DEFAULT_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 500;
}
