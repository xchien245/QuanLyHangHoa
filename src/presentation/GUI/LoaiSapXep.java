package presentation.GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import domain.*;

public class LoaiSapXep extends JFrame {
    private JButton theoMaHangButton, theoSLTonButton, theoDonGiaButton, theoTenHang;
    private JRadioButton tangRadioButton, giamRadioButton;
    private JPanel panel;

    public LoaiSapXep(QuanLyKhoGUI viewRemote) {
        Facade facadeRemote = Facade.getInstance();
        panel = new JPanel(new GridLayout(2, 4, 10, 10));

        theoMaHangButton = new JButton("Theo mã");
        theoMaHangButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isTangDan() == 0) {
                    facadeRemote.sapXepHH("maHH",true);
                    dispose();
                } else if(isTangDan() == 1) {
                    facadeRemote.sapXepHH("maHH",false);
                    dispose();
                } else{
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn xếp theo tăng dần hoặc giảm dần");
                }
            }
        });

        theoSLTonButton = new JButton("Theo số lượng tồn kho");
        theoSLTonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isTangDan() == 0) {
                    facadeRemote.sapXepHH("slTon",true);
                    dispose();
                } else if(isTangDan() == 1) {
                    facadeRemote.sapXepHH("slTon",false);
                    dispose();
                } else{
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn xếp theo tăng dần hoặc giảm dần");
                }
            }
        });

        theoDonGiaButton = new JButton("Theo đơn giá");
        theoDonGiaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isTangDan() == 0) {
                    facadeRemote.sapXepHH("donGia",true);
                    dispose();
                } else if(isTangDan() == 1) {
                    facadeRemote.sapXepHH("donGia",false);
                    dispose();
                } else{
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn xếp theo tăng dần hoặc giảm dần");
                }
            }
        });

        theoTenHang = new JButton("Theo tên");
        theoTenHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isTangDan() == 0) {
                    facadeRemote.sapXepHH("tenHH",true);
                    dispose();
                } else if(isTangDan() == 1) {
                    facadeRemote.sapXepHH("tenHH",false);
                    dispose();
                } else{
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn xếp theo tăng dần hoặc giảm dần");
                }
            }
        });

        ButtonGroup group = new ButtonGroup();
        tangRadioButton = new JRadioButton("Tăng dần");
        giamRadioButton = new JRadioButton("Giảm dần");

        group.add(tangRadioButton);
        group.add(giamRadioButton);
        panel.add(theoMaHangButton);
        panel.add(theoTenHang);
        panel.add(theoSLTonButton);
        panel.add(theoDonGiaButton);
        panel.add(new Label());
        panel.add(tangRadioButton);
        panel.add(giamRadioButton);
        panel.add(new Label());


        setTitle("Chọn thể loại sắp xếp");
        setSize(800, 100);
        setLocation(560, 490);
        setDefaultCloseOperation(LoaiSapXep.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        add(panel);
    }

    private int isTangDan() {
        if(tangRadioButton.isSelected()) {
            return 0;
        } else if(giamRadioButton.isSelected()) {
            return 1;
        } else{
            return -1;
        }
    }
}
