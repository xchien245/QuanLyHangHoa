package presentation.GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.*;
import presentation.*;

public class LoaiHangHoa extends JFrame{
    private JButton thucphamButton, dienmayButton, sanhsuButton;
    private JPanel panel;
    private HangHoa hangHoa;
    private int loaiHangHoa;
    
    /*
     0: Hàng thực phẩm
     1: Hàng điện máy
     2: Hàng sành sứ
     */
    public LoaiHangHoa(QuanLyKhoGUI viewRemote, QuanLyKhoController controllerRemote) {
        panel = new JPanel(new GridLayout(0,3,10,10));
        thucphamButton = new JButton("Thực phẩm");
        thucphamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HangThucPhamGUI(viewRemote, controllerRemote, 0).setVisible(true);
                dispose();
            }
        });

        dienmayButton = new JButton("Điện máy");
        dienmayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HangDienMayGUI(viewRemote, controllerRemote, 0).setVisible(true);
                dispose();
            }
        });

        sanhsuButton = new JButton("Sành sứ");
        sanhsuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HangSanhSuGUI(viewRemote, controllerRemote, 0).setVisible(true);
                dispose();
            }
        });
        panel.add(thucphamButton);
        panel.add(dienmayButton);
        panel.add(sanhsuButton);

        setTitle("Chọn loại hàng hóa");
        setSize(500, 100);
        setLocation(760, 390);    
        setDefaultCloseOperation(LoaiHangHoa.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        add(panel);
    }

    public HangHoa getHangHoa() {
        return hangHoa;
    }

    public int getLoaiHangHoa() {
        return loaiHangHoa;
    }

}
