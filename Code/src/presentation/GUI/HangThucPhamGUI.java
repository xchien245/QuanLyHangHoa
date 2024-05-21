package presentation.GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.*;
import presentation.*;
import presentation.Command.*;

public class HangThucPhamGUI extends JFrame{
    private JTextField maHangTextField, tenHangTextField, slTonTextField, donGiaTextField, ngaySXTextField, ngayHetHanTextField, nhaCungCapTextField;
    private JButton luuButton, huyButton;
    private int choice;
    private String maHang;
    
    /*
     Yêu cầu:
     0: Thêm mới hàng hóa
     1: Cập nhật lại thông tin hàng hóa
     */
    public HangThucPhamGUI(QuanLyKhoGUI viewRemote, QuanLyKhoController controllerRemote, int choice) {
        this.choice = choice;
        JPanel inputPanel;
        if(choice == 0) {
            inputPanel = new JPanel(new GridLayout(10, 2));
            setTitle("Thêm mới thông tin hàng thực phẩm");

        } else {
            inputPanel = new JPanel(new GridLayout(9, 2));
            setTitle("Cập nhật lại thông tin hàng thực phẩm");
        }
        maHangTextField = new JTextField();
        tenHangTextField = new JTextField();
        slTonTextField = new JTextField();
        donGiaTextField = new JTextField();
        ngaySXTextField = new JTextField();
        ngayHetHanTextField = new JTextField();
        nhaCungCapTextField = new JTextField();
        luuButton = new JButton("Lưu");
        huyButton = new JButton("Hủy");

        luuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkException()) {
                    if(choice == 0) {
                        themHangHoa(controllerRemote);
                        dispose();
                    } else if (choice == 1) {
                        capnhatHangHoa(controllerRemote);
                        dispose();
                    }
                }
            }
        });
        huyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel());
        if (choice == 0) {
            inputPanel.add(new JLabel("Mã hàng hóa:"));
            inputPanel.add(maHangTextField);
        }
        inputPanel.add(new JLabel("Tên hàng hóa:"));
        inputPanel.add(tenHangTextField);
        inputPanel.add(new JLabel("Số lượng tồn kho:"));
        inputPanel.add(slTonTextField);
        inputPanel.add(new JLabel("Đơn giá:"));
        inputPanel.add(donGiaTextField);
        inputPanel.add(new JLabel("Ngày sản suất:"));
        inputPanel.add(ngaySXTextField);
        inputPanel.add(new JLabel("Ngày hết hạn:"));
        inputPanel.add(ngayHetHanTextField);
        inputPanel.add(new JLabel("Nhà cung cấp:"));
        inputPanel.add(nhaCungCapTextField);
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel());
        inputPanel.add(luuButton);
        inputPanel.add(huyButton);
        
        setSize(600, 300);
        setLocation(660, 390);    
        setDefaultCloseOperation(HangThucPhamGUI.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        add(inputPanel);
    }
    
    public void themHangHoa(QuanLyKhoController controllerRemote) {
        Command themHH = new Them(1, luuThongTin());
        controllerRemote.execute(themHH);
    }

    public void capnhatHangHoa(QuanLyKhoController controllerRemote) {
        Command capnhatHang = new CapNhat(1, luuThongTin());
        controllerRemote.execute(capnhatHang);
    }

    public HangHoa luuThongTin() {
        String tenHang = tenHangTextField.getText();
        int slTon = Integer.parseInt(slTonTextField.getText());
        double donGia = Double.parseDouble(donGiaTextField.getText());
        Date ngaySX = java.sql.Date.valueOf(ngaySXTextField.getText());
        Date ngayHetHan = java.sql.Date.valueOf(ngayHetHanTextField.getText());
        String nhaCungCap = nhaCungCapTextField.getText();
        if(choice == 0) {
            maHang = maHangTextField.getText();
        }
        return (HangHoa)new HangThucPham(maHang, tenHang, slTon, donGia, ngaySX, ngayHetHan, nhaCungCap);

    }

    public boolean checkException() {
        if(choice == 0) {
            if(maHangTextField.getText() == null || maHangTextField.getText().length() > 5) {
                JOptionPane.showMessageDialog(this, "Mã hàng hóa là chuỗi 5 kí tự bất kì", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }else if(Facade.getInstance().xemThongTin1HH(maHangTextField.getText()) != null) {
                JOptionPane.showMessageDialog(this, "Mã hàng hóa vừa nhập đã tồn tại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        
        if(tenHangTextField.getText() == null) {
            JOptionPane.showMessageDialog(this, "Tên hàng hóa không được để trống", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        try {
            int slTon = Integer.parseInt(slTonTextField.getText());
            if(slTon < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng tồn kho phải lớn hơn hoặc bằng 0", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng tồn kho phải là số nguyên", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }   

        try {
            Double donGia = Double.parseDouble(donGiaTextField.getText());
            if(donGia < 0) {
                JOptionPane.showMessageDialog(this, "Giá thành phải là số lớn hơn 0", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Giá thành phải là số", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } 

        try {
            Date ngaySX = Date.valueOf(ngaySXTextField.getText());
            Date ngayHetHan = Date.valueOf(ngayHetHanTextField.getText());
            if(ngaySX.compareTo(ngayHetHan) >= 0) {
                JOptionPane.showMessageDialog(this, "Ngày hết hạn phải sau hoặc bằng ngày sản xuất", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Nhập vào ngày hợp lệ (YYYY-MM-DD)", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        return true;
    }

    public JTextField getMaHangTextField() {
        return maHangTextField;
    }

    public JTextField getTenHangTextField() {
        return tenHangTextField;
    }

    public JTextField getSlTonTextField() {
        return slTonTextField;
    }

    public JTextField getDonGiaTextField() {
        return donGiaTextField;
    }

    public JTextField getNgaySXTextField() {
        return ngaySXTextField;
    }

    public JTextField getNgayHetHanTextField() {
        return ngayHetHanTextField;
    }

    public JTextField getNhaCungCapTextField() {
        return nhaCungCapTextField;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }
}
