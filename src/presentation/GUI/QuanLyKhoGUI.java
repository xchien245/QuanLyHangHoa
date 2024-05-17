package presentation.GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import domain.Facade;
import domain.HangHoa;
import presentation.QuanLyKhoController;
import presentation.Subscriber;
import presentation.Command.Command;
import presentation.Command.NhapFile;
import presentation.Command.TimKiem;
import presentation.Command.Xoa;
import presentation.Command.XuatFile;

//View
public class QuanLyKhoGUI extends JFrame implements Subscriber {
    private QuanLyKhoController controllerRemote;
    private DefaultTableModel tableModel;
    private JTable table;
    private JButton themButton, capnhatButton, xoaButton, timkiemButton, hethanButton, xemAllButton, sapXepButton;
    private JTextField tuKhoaTextField;
    private JLabel tongTonKhoTP, tongTonKhoDM, tongTonKhoSS;

    public QuanLyKhoController getControllerRemote() {
        return controllerRemote;
    }

    public QuanLyKhoGUI() {
        setTitle("Quản lý hàng hóa trong kho");
        setSize(1600, 400);
        setLocation(160, 340);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Facade.getInstance().subscribe(this);
        controllerRemote = new QuanLyKhoController();

        JMenuBar menuBar = new JMenuBar();

        JMenu nhapMenu = new JMenu("Nhập");
        JMenuItem ghiDeItem = new JMenuItem("Ghi dè lên bảng hiện tại");
        JMenuItem ghiThemItem = new JMenuItem("Thêm vào bảng hiện tại");
        JMenu xuatMenu = new JMenu("Xuất");
        JMenuItem xuatExcelItem = new JMenuItem("Xuất dữ liệu ra file Excel");
        nhapMenu.add(ghiDeItem);
        nhapMenu.add(ghiThemItem);
        xuatMenu.add(xuatExcelItem);

        menuBar.add(nhapMenu);
        menuBar.add(xuatMenu);
        setJMenuBar(menuBar);

        xuatExcelItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    xuatFile();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        ghiDeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    nhapFile(0);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        ghiThemItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    nhapFile(1);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã hàng hóa");
        tableModel.addColumn("Tên hàng hóa");
        tableModel.addColumn("Số lượng tồn");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Thuế VAT");
        tableModel.addColumn("Ngày sản suất");
        tableModel.addColumn("Ngày hết hạn");
        tableModel.addColumn("Nhà cung cấp");
        tableModel.addColumn("Thời gian BH");
        tableModel.addColumn("Công suất");
        tableModel.addColumn("Ngày nhập kho");
        tableModel.addColumn("Nhà sản suất");
        table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getComponent() != table) {
                    table.clearSelection();
                }
            }
        });

        themButton = new JButton("Thêm");
        themButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themHangHoa();
            }
        });

        capnhatButton = new JButton("Cập nhật");
        capnhatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capnhatHangGUI(table.getSelectedRow());
            }
        });

        xoaButton = new JButton("Xóa");
        xoaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoaHangHoaGUI(table.getSelectedRow());
            }
        });

        timkiemButton = new JButton("Tìm kiếm");
        timkiemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timTTHH();
            }
        });
        tuKhoaTextField = new JTextField();

        hethanButton = new JButton("Hàng sắp hết hạn");
        hethanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xemDSHetHan();
            }
        });

        xemAllButton = new JButton("Danh sách hàng hóa");
        xemAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xemDSHangHoa();
            }
        });

        sapXepButton = new JButton("Sắp xếp");
        sapXepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sapXepHH();
            }
        });

        JPanel functionPanel = new JPanel(new GridLayout(9, 0, 0, 10));

        functionPanel.add(tuKhoaTextField);
        functionPanel.add(timkiemButton);
        functionPanel.add(new Label());

        functionPanel.add(xemAllButton);
        functionPanel.add(hethanButton);
        functionPanel.add(themButton);
        functionPanel.add(capnhatButton);
        functionPanel.add(xoaButton);
        functionPanel.add(sapXepButton);

        add(functionPanel, BorderLayout.EAST);

        JPanel inforPanel = new JPanel(new GridLayout(2, 6, 0, 0));
        tongTonKhoTP = new JLabel();
        tongTonKhoDM = new JLabel();
        tongTonKhoSS = new JLabel();

        inforPanel.add(new Label("Số lượng tồn kho hàng thực phẩm"));
        inforPanel.add(tongTonKhoTP);
        inforPanel.add(new Label("Số lượng tồn kho hàng điện máy"));
        inforPanel.add(tongTonKhoDM);
        inforPanel.add(new Label("Số lượng tồn kho hàng sành sứ"));
        inforPanel.add(tongTonKhoSS);

        inforPanel.add(new Label());
        inforPanel.add(new Label());
        inforPanel.add(new Label());
        inforPanel.add(new Label());
        inforPanel.add(new Label());
        inforPanel.add(new Label());
        add(inforPanel, BorderLayout.SOUTH);

        xemDSHangHoa();
        loadTongTonKho();
    }

    @Override
    public void update(List<HangHoa> dsHangHoa) {
        if(dsHangHoa != null) {
        while (tableModel.getRowCount() != 0) {
            tableModel.removeRow(0);
        }
        for (HangHoa hangHoa : dsHangHoa) {
            Double VAT = tinhVAT(hangHoa);
            Object[] rowData = { hangHoa.getMaHH(), hangHoa.getTenHH(), hangHoa.getSlTon(),
                    hangHoa.getDonGia(), VAT, hangHoa.getNgaySX(), hangHoa.getNgayHH(),
                    hangHoa.getNhaCC(), hangHoa.getThoiGianBH(), hangHoa.getCongSuat(), hangHoa.getNgayNK(),
                    hangHoa.getNhaSX() };
            tableModel.addRow(rowData);
        }
        }
    }

    @Override
    public void update(String[] tongTonKho) {
        tongTonKhoTP.setText(tongTonKho[0]);
        tongTonKhoDM.setText(tongTonKho[1]);
        tongTonKhoSS.setText(tongTonKho[2]);
    }

    public void xemDSHangHoa() {
        Facade.getInstance().xemTTAllHH();
    }

    public void themHangHoa() {
        new LoaiHangHoa(this, controllerRemote).setVisible(true);
    }

    public void capnhatHangGUI(int rowIndex) {
        String maHang;
        HangHoa hanghoa;
        if (rowIndex == -1) {
            maHang = JOptionPane.showInputDialog(null, "Nhập số mã hàng hóa cần sửa");
            if (maHang != null) {
                hanghoa = xemThongTin1HH(maHang);
                capnhatHang(hanghoa);
            }
        } else {
            maHang = tableModel.getValueAt(rowIndex, 0).toString();
            hanghoa = xemThongTin1HH(maHang);
            capnhatHang(hanghoa);
        }
    }

    public void capnhatHang(HangHoa hanghoa) {
        if (hanghoa.getNhaCC() != null) {
            HangThucPhamGUI temp = new HangThucPhamGUI(this, controllerRemote, 1);
            temp.setMaHang(hanghoa.getMaHH());
            temp.getTenHangTextField().setText(hanghoa.getTenHH());
            temp.getSlTonTextField().setText(Integer.toString(hanghoa.getSlTon()));
            temp.getDonGiaTextField().setText(Double.toString(hanghoa.getDonGia()));
            temp.getNgaySXTextField().setText(hanghoa.getNgaySX().toString());
            temp.getNgayHetHanTextField().setText(hanghoa.getNgayHH().toString());
            temp.getNhaCungCapTextField().setText(hanghoa.getNhaCC());
            temp.setVisible(true);
        } else if (hanghoa.getCongSuat() != null) {
            HangDienMayGUI temp = new HangDienMayGUI(this, controllerRemote, 1);
            temp.setMaHang(hanghoa.getMaHH());
            temp.getTenHangTextField().setText(hanghoa.getTenHH());
            temp.getSlTonTextField().setText(Integer.toString(hanghoa.getSlTon()));
            temp.getDonGiaTextField().setText(Double.toString(hanghoa.getDonGia()));
            String[] tgBaoHanh = hanghoa.getThoiGianBH().split(" ");
            temp.getTgBaoHanhTextField().setText(tgBaoHanh[0]);
            String[] congSuat = hanghoa.getCongSuat().split(" ");
            temp.getCongSuatTextField().setText(congSuat[0]);
            temp.setVisible(true);
        } else if (hanghoa.getNhaSX() != null) {
            HangSanhSuGUI temp = new HangSanhSuGUI(this, controllerRemote, 1);
            temp.setMaHang(hanghoa.getMaHH());
            temp.getTenHangTextField().setText(hanghoa.getTenHH());
            temp.getSlTonTextField().setText(Integer.toString(hanghoa.getSlTon()));
            temp.getDonGiaTextField().setText(Double.toString(hanghoa.getDonGia()));
            temp.getNgayNhapKhoTextField().setText(hanghoa.getNgayNK().toString());
            temp.getNhaSXTextField().setText(hanghoa.getNhaSX());
            temp.setVisible(true);
        }
    }

    public void xoaHangHoaGUI(int rowIndex) {
        String maHang;
        if (rowIndex == -1) {
            maHang = JOptionPane.showInputDialog(null, "Nhập số mã hàng hóa cần xóa");
            if (maHang != null) {
                xoaHangHoa(maHang);
            }
        } else {
            maHang = tableModel.getValueAt(rowIndex, 0).toString();
            xoaHangHoa(maHang);
        }
    }

    public void xoaHangHoa(String maHang) {
        Command xoaHang = new Xoa(maHang);
        controllerRemote.execute(xoaHang);
    }

    public void timTTHH() {
        Command timKiem = new TimKiem(tuKhoaTextField.getText());
        controllerRemote.execute(timKiem);
    }

    public HangHoa xemThongTin1HH(String maHang) {
        return Facade.getInstance().xemThongTin1HH(maHang);
    }

    private void loadTongTonKho() {
        Facade.getInstance().tongTonKho();
    }

    public void xemDSHetHan() {
        Facade.getInstance().xemDSHetHan();
    }

    public void sapXepHH() {
        new LoaiSapXep(this).setVisible(true);
    }

    public void xuatFile() {
        Command xuatFile = new XuatFile();
        controllerRemote.execute(xuatFile);
    }

    public void nhapFile(int nhuCau) {
        Command nhapFile = new NhapFile(nhuCau);
        controllerRemote.execute(nhapFile);
    }

    
    public double tinhVAT(HangHoa hanghoa) {
        return Facade.getInstance().tinhVAT(hanghoa);
    }
}
