package copyfile;


import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 复制apk文件到U盘并删除之前的apk文件
 * <p>
 * 桌面程序,复制与删除文件
 */
@SuppressWarnings({"FieldCanBeLocal", "unused", "SameParameterValue"})
public class CopyFileToUsb extends JFrame implements ActionListener {
    private static int copySuccess = 0b1, deleteSuccess = 0b11, maskFlag = 0;

    private static String resultInfo = "";
    private JTextField copyFlieName = new JTextField(10);
    private JTextField deleteFileName = new JTextField(10);

    //    private JPasswordField jpf = new JPasswordField(10);
    private JLabel copyFlieInfo = new JLabel("要复制的文件名称(不需要.apk)：");
    private JLabel deleteFlieInfo = new JLabel("要删除的文件名称(不需要.apk)：");

    private JButton excuteButton = new JButton("开始执行");
    private JButton resetButton = new JButton("重置文件名称");
    private JButton removeUsbButton = new JButton("移除U盘");

    private JLabel result = new JLabel();

    private JPanel container = new JPanel();

    private int leftX = 30, copyFlieInfoLeftY = 20, explainWidth = 200, explainHeight = 30,
            deleteFlieInfoLeftY = 70, gaps = 30, fieldWidth = 360, fieldHeight = 30,
            excuteButtonLeftX = 50, ButtonLeftY = 130, excuteButtonWidth = 180,
            excuteButtonHeight = 30, resultLeftX = 30, resultLeftY = 180, resultWidth = 790,
            resultHeight = 400, resetButtonLeftX = 280, removeUsbButtonLeftX = 510;

    private CopyFileToUsb() {
        //获取jar包所在路径
//        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
//        System.out.println("ja包所在路径=" +
//                this.getClass().getResource("/resource/USB_Disk_Eject.exe"));
        this.setTitle("复制与删除apk文件");
        //关闭时直接退出
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        container.setLayout(null);
        copyFlieName.setEnabled(true);
        copyFlieName.setEditable(true);

        //设置字体,内容个位置
        result.setFont(new Font("宋体", Font.PLAIN, 20));
        result.setHorizontalAlignment(SwingConstants.LEFT);
        result.setVerticalAlignment(SwingConstants.TOP);

        getPath(copyFlieName);
        //--在容器中左上角的坐标x,y,控件的宽度与高度
        copyFlieInfo.setBounds(leftX, copyFlieInfoLeftY, explainWidth, explainHeight);
        container.add(copyFlieInfo);
        deleteFlieInfo.setBounds(leftX, deleteFlieInfoLeftY, explainWidth, explainHeight);
        container.add(deleteFlieInfo);

        copyFlieName.setBounds(explainWidth + leftX + gaps, copyFlieInfoLeftY, fieldWidth, fieldHeight);
        container.add(copyFlieName);

//        jpf.setBounds(80, 70, 180, 30);
//        container.add(jpf);

        deleteFileName.setBounds(explainWidth + leftX + gaps, deleteFlieInfoLeftY, fieldWidth, fieldHeight);
        container.add(deleteFileName);

        excuteButton.setBounds(excuteButtonLeftX, ButtonLeftY, excuteButtonWidth, excuteButtonHeight);
        container.add(excuteButton);

        resetButton.setBounds(resetButtonLeftX, ButtonLeftY, excuteButtonWidth, excuteButtonHeight);
        container.add(resetButton);

        removeUsbButton.setBounds(removeUsbButtonLeftX, ButtonLeftY, excuteButtonWidth, excuteButtonHeight);
        container.add(removeUsbButton);

        result.setBounds(resultLeftX, resultLeftY, resultWidth, resultHeight);
        container.add(result);


        excuteButton.addActionListener(this);
        resetButton.addActionListener(this);
        removeUsbButton.addActionListener(this);
        this.add(container);

        //设置整个界面的大小与在屏幕中的位置
        this.setBounds(250, 150, 850, 600);
        this.setVisible(true);
        //监听窗口右上角的关闭按钮
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("执行了关闭点击事件");
                System.exit(0);
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("不同的点击事件=" + e.getActionCommand() + "=");
        String actionCommand = e.getActionCommand();
        if ("开始执行".equals(actionCommand)) {
            excuteButtonClick();
        } else if ("重置文件名称".equals(actionCommand)) {
            resetButtonClick();
        } else if ("移除U盘".equals(actionCommand)) {
            removeUsbButtonClick();
        }


    }

    /**
     * 重置文件名称
     */
    private void resetButtonClick() {
        result.setText("");
        String copyFileNameNew = copyFlieName.getText();
        copyFlieName.setText(handleFileName(copyFileNameNew));
        String deleteFileNameNew = deleteFileName.getText();
        deleteFileName.setText(handleFileName(deleteFileNameNew));

        //执行速度快
//        System.exit(0);
        //调用cmd命令杀死进程,执行速度较慢
//        runtimeCommand("taskkill /pid " + getProcessID() + " -t -f");
    }

    private String handleFileName(String target) {
        int copyFileNum;
        try {
            copyFileNum = Integer.parseInt(target);
            copyFileNum++;
        } catch (Exception e) {
            int start = target.lastIndexOf("\\") + 1;
            int end = target.indexOf(".apk");
            copyFileNum = Integer.parseInt(target.substring(start, end));
            copyFileNum++;
        }

        if (("" + copyFileNum).length() > 2) {
            target = "" + copyFileNum;
        } else if (("" + copyFileNum).length() == 1) {
            target = "00" + copyFileNum;
        } else if (("" + copyFileNum).length() == 2) {
            target = "0" + copyFileNum;
        }

        return target;
    }

    /**
     * 移除U盘点击事件,执行该命令需要首先下载USB_Disk_Eject.exe程序
     */
    private void removeUsbButtonClick() {
        runtimeCommand("E:\\USB_Disk_Eject.exe /REMOVELETTER H");
    }

    /**
     * 开始执行按钮点击事件
     */
    private void excuteButtonClick() {
        resultInfo = "";
        result.setText("");
        String s1 = copyFlieName.getText();
        String s2 = deleteFileName.getText();

        File sourceFile;
        File targetFile;
        sourceFile = new File(s1);
        System.out.println("要复制的文件存在" + sourceFile.exists());
        System.out.println("要复制的文件存在" + sourceFile.getName());
        if (!sourceFile.exists()) {
            sourceFile = new File("C:\\Users\\dingzhixin.ex\\Desktop\\" + s1 + ".apk");
            targetFile = new File("H:\\" + s1 + ".apk");
        } else {
            targetFile = new File("H:\\" + sourceFile.getName());
        }
        File deleteFile = new File("H:\\" + s2 + ".apk");

        if (!sourceFile.exists()) {
            resultInfo = "要复制的文件不存在<br><br>";
            result.setText("<html>" + resultInfo + "<br>当前要复制的文件路径是:<br>" + sourceFile.getAbsolutePath());
            return;
        }
        try {
            System.out.println("复制文件出错-----001-----------");
            resultInfo = resultInfo + "文件复制成功:<br>" + copyFileUsingJava7Files(sourceFile, targetFile);
            System.out.println("复制文件出错--------002--------");
            maskFlag |= copySuccess;
        } catch (Exception ex) {
            //---------------------获取完整的异常输出信息------------------------------
//            StringWriter stringWriter= new StringWriter();
//            PrintWriter writer= new PrintWriter(stringWriter);
//            ex.printStackTrace(writer);
//            StringBuffer buffer= stringWriter.getBuffer();
//            System.out.println("文件复制异常"+buffer.toString());
            //---------------------获取完整的异常输出信息------------------------------
            maskFlag &= ~copySuccess;
            resultInfo = resultInfo + "文件复制出错:<br><br>" + ex.toString();
            System.out.println("复制文件出错----------------");
            ex.printStackTrace();
        }
        boolean deleteResult = delete(deleteFile);
        if (deleteResult) {
            maskFlag |= deleteSuccess;
            resultInfo = resultInfo + "<br><br>文件删除成功:<br><br>文件删除成功!";
        } else {
            maskFlag &= ~deleteSuccess;
            resultInfo = resultInfo + "<br><br>文件删除失败:<br><br>文件删除失败!";
        }

        result.setText("<html>" + resultInfo);
        if ((maskFlag & copySuccess) != 0 &&
                (maskFlag & deleteSuccess) != 0) {//复制删除成功,移除U盘
            removeUsbButtonClick();
        }
//        String s2 = jpf.getText();
    }

    public static void main(String[] args) {
        //jps  命令可以获取进程号
//        runtimeCommand("jps");
        CopyFileToUsb kongJian = new CopyFileToUsb();
        CopyFileFromJar copyFileFromJar = new CopyFileFromJar();
        copyFileFromJar.loadRecourseFromJar("/resource/USB_Disk_Eject.exe");
    }

//    private static void test() {
//        System.out.println("======" + ((maskFlag & copySuccess) != 0));
//        maskFlag |= copySuccess;//添加状态
//        System.out.println("======" + ((maskFlag & copySuccess) != 0));//包含该状态
//        maskFlag &= ~copySuccess;//移除状态
//        System.out.println("======" + ((maskFlag & copySuccess) != 0));
//        maskFlag &= copySuccess;//包含状态
//        System.out.println("======" + ((maskFlag & copySuccess) != 0));
//
//    }

    private static String copyFileUsingJava7Files(File source, File dest)
            throws IOException {
        Path result = Files.copy(source.toPath(), dest.toPath());
        System.out.println("复制文件成功!");
        return result.toString() + "文件复制成功!";
    }

    private static boolean delete(File file) {
        if (file.isFile()) {
            boolean success = file.delete();
            if (success) {
                System.out.println("文件=" + file.getName() + "=删除成功!");
                return true;
            } else {
                System.out.println("文件=" + file.getName() + "=删除失败!");
                return false;
            }
        }
        return false;
    }

    /**
     * 拖曳文件到控件获取文件路径并显示
     */
    private static void getPath(JTextField field) {
        field.setTransferHandler(new TransferHandler() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean importData(JComponent comp, Transferable t) {
                try {
                    Object o = t.getTransferData(DataFlavor.javaFileListFlavor);
                    String filepath = o.toString();
                    if (filepath.startsWith("[")) {
                        filepath = filepath.substring(1);
                    }
                    if (filepath.endsWith("]")) {
                        filepath = filepath.substring(0, filepath.length() - 1);
                    }
                    System.out.println(filepath);
                    field.setText(filepath);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    resultInfo = resultInfo + "<br><br>拖曳获取文件路径失败:<br>" + e.toString();
                }
                return false;
            }

            @Override
            public boolean canImport(JComponent comp, DataFlavor[] flavors) {
                for (DataFlavor flavor : flavors) {
                    if (DataFlavor.javaFileListFlavor.equals(flavor)) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    /**
     * 获取当前Java进程的pid号
     */
    private static int getProcessID() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println(runtimeMXBean.getName());
        return Integer.valueOf(runtimeMXBean.getName().split("@")[0]);
    }

    /**
     * Java执行cmd命令
     */
    private static void runtimeCommand(String command) {
        Process process;
        try {
            process = Runtime.getRuntime().exec("cmd.exe /c " + command);
            int status = process.waitFor();

            System.out.println(status);
            InputStream in = process.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        } catch (Exception e) {
            resultInfo = resultInfo + "<br><br>执行cmd命令出错<br>" + e.toString();
            e.printStackTrace();
        }

    }
}