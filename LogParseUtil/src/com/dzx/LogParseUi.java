package com.dzx;

import com.dzx.bean.ApkFileSaveNameBean;
import com.dzx.util.*;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.lang.management.ThreadInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class LogParseUi extends JFrame implements ActionListener {

    /**
     * 界面容器,用于放置其他按钮,文本等
     */
    private JPanel mContainerPanel = new JPanel();

    /**
     * 标签组件,可以用于显示文本标签或者图片
     * 显示最终的执行结果
     */
    private JLabel mResultLabel = new JLabel();

    /**
     * 要解析的目标log文件路径指示标签
     */
    private JLabel mTargetFilePathLabel = new JLabel("要要解析的log文件的路径");
    /**
     * 文本框,用来编辑单行的文本
     * 用于显示要解析的log文件路径,可以直接拖曳
     */
    private JTextField mTargetFilePathTextField = new JTextField(10);


    /**
     * 用于指示要过滤的字段的标签
     */
    private JLabel mScreeningConditionsLabel = new JLabel("要过滤的字段使用#进行分隔");
    /**
     * 用于输入要筛选的条件,使用#进行分割
     */
    private JTextField mScreeningConditionsTextField = new JTextField(10);

    /**
     * 用于指示要过滤的字段的标签
     */
    private JLabel mOutFilepathLabel = new JLabel("筛选输出文件目录");
    /**
     * 用于输入要筛选的条件,使用#进行分割
     */
    private JTextField mOutFilepathTextField = new JTextField(10);

    /**
     * 用于指示要过滤的字段的标签
     */
    private JLabel mUploadApkToFtpLabel = new JLabel("要上传的apk目录");
    /**
     * 用于输入要筛选的条件,使用#进行分割
     */
    private JTextField mUploadApkToFtpTextField = new JTextField(10);


    /**
     * mMarginLeft   距离边框左边距
     * mMarginTop   距离边框上边距
     * mLabelWidth   左侧说明标签的统一宽度
     * mLabelHeight   左侧说明标签的统一高度
     * mLabelAndTextFieldGaps    说明标签与文本编辑框的间距
     * mTextFieldWidth    右侧文本编辑框的统一宽度
     * mTextFieldHeight    右侧文本编辑框的统一高度
     * mLineGaps    两行之间的统一间距
     * mScreenShowPositionX    程序窗口在屏幕显示的x坐标
     * mScreenPositionY    程序窗口在屏幕显示的y坐标
     * mScreenShowWidth    程序窗口在屏幕显示的宽度
     * mScreenShowHeight    程序窗口在屏幕显示的高度
     * mResultLabelWidth    执行结果显示label宽度
     * mResultLabelHeight    执行结果显示label高度
     * mLogParseButtonWidth    执行按钮宽度
     * mLogParseButtonHeight    执行按钮高度
     * mInstallApkButtonWidth    安装apk按钮高度
     * mInstallApkButtonHeight     安装apk按钮高度
     */
    private int mMarginLeft = 30, mMarginTop = 20, mLabelWidth = 200, mLabelHeight = 30;
    private int mLabelAndTextFieldGaps = 30, mTextFieldWidth = 360, mTextFieldHeight = 30, mLineGaps = 15;
    private int mScreenShowPositionX = 250, mScreenPositionY = 150, mScreenShowWidth = 850, mScreenShowHeight = 600;
    private int mResultLabelWidth = 800, mResultLabelHeight = 400;
    private int mLogParseButtonWidth = 180, mLogParseButtonHeight = 30, mButtonAndButtonGaps = 30;
    private int mInstallApkButtonWidth = 270, mInstallApkButtonHeight = 30;
    private int mUploadApkToFtpButtonWidth = 180, mUploadApkToFtpButtonHeight = 30;


    /**
     * 最终的显示信息
     */
    private static String mResultInfo = "<html>";

    private static final String LOG_PARSE_BUTTON_NAME = "开始解析日志";
    private static final String INSTALL_APK_BUTTON_NAME = "下载安装apk到指定位置";
    private static final String UPLOAD_APK_TO_FTP = "上传apk到ftp";

    private JButton mLogParseButton = new JButton(LOG_PARSE_BUTTON_NAME);
    private JButton mInstallApkButton = new JButton(INSTALL_APK_BUTTON_NAME);
    private JButton mUploadApkToFtpButton = new JButton(UPLOAD_APK_TO_FTP);

    /**
     * 解析日志路径行位置
     */
    private int mLogParseTextFieldPosition = 0;
    /**
     * 解析日志筛选条件行位置
     */
    private int mScreeningConditionsTextFieldPosition = 1;
    /**
     * 解析日志输出路径行位置
     */
    private int mOutLogTextFieldPosition = 2;
    /**
     * 上传apk到ftp所在的行位置
     */
    private int mUploadApkToFtpTextFieldPosition = 3;
    /**
     * 按钮行位置
     */
    private int mButtonsPosition = 5;
    /**
     * 执行信息显示的行数
     */
    private int mFtpInfoPosition = 4;
    /**
     * ftp账号相关信息行数
     */
    private int mResultInfoLabelPosition = 6;

    /**
     * ftp账号相关信息
     */
    private int mFtpTextFieldWidth = 150, mFtpTextFieldHeight = 30;
    private JTextField mFtpIpTextField = new JTextField(10);
    private JTextField mFtpPortTextField = new JTextField(10);
    private JTextField mFtpNameTextField = new JTextField(10);
    private JTextField mFtpPwdTextField = new JTextField(10);

    public LogParseUi() throws HeadlessException {
        //设置界面左上角显示的标题
        this.setTitle("Android日志解析辅助软件");
        //关闭时直接退出
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //置空Lsyout
        mContainerPanel.setLayout(null);

        //要解析的log路径的说明标签相关设置
        //--在容器中左上角的坐标x,y,控件的宽度与高度
        labelAndTextFieldSet(mTargetFilePathLabel, mTargetFilePathTextField, mMarginLeft, mMarginTop
                , mMarginLeft + mLabelWidth + mLabelAndTextFieldGaps
                , mMarginTop);


        //过滤条件相关设置
        labelAndTextFieldSet(mScreeningConditionsLabel, mScreeningConditionsTextField, mMarginLeft, mMarginTop + mLabelHeight + mLineGaps
                , mMarginLeft + mLabelWidth + mLabelAndTextFieldGaps
                , mMarginTop + mLineGaps + mTextFieldHeight);


        //筛选后的文件输出目录相关设置

        labelAndTextFieldSet(mOutFilepathLabel, mOutFilepathTextField, mMarginLeft, mMarginTop + (mLabelHeight + mLineGaps) * mOutLogTextFieldPosition
                , mMarginLeft + mLabelWidth + mLabelAndTextFieldGaps
                , mMarginTop + (mLineGaps + mTextFieldHeight) * mOutLogTextFieldPosition);
        mOutFilepathTextField.setText("C:\\Users\\20313\\Desktop\\筛选结果.txt");


        //上传apk到ftp相关设置
        labelAndTextFieldSet(mUploadApkToFtpLabel, mUploadApkToFtpTextField, mMarginLeft, mMarginTop + (mLabelHeight + mLineGaps) * mUploadApkToFtpTextFieldPosition
                , mMarginLeft + mLabelWidth + mLabelAndTextFieldGaps
                , mMarginTop + (mLineGaps + mTextFieldHeight) * mUploadApkToFtpTextFieldPosition);
        mUploadApkToFtpTextField.setText("#huixiangjia_.apk#存储当前apk序号.txt");


        ftpInfoSet();

        //执行按钮相关设置
        mLogParseButton.setBounds(mMarginLeft, mMarginTop + (mLabelHeight + mLineGaps) * mButtonsPosition,
                mLogParseButtonWidth, mLogParseButtonHeight);
        mContainerPanel.add(mLogParseButton);
        mLogParseButton.addActionListener(this);


        //安装apk按钮相关设置
        mInstallApkButton.setBounds(mMarginLeft + mButtonAndButtonGaps + mLogParseButtonWidth,
                mMarginTop + (mLabelHeight + mLineGaps) * mButtonsPosition,
                mInstallApkButtonWidth, mInstallApkButtonHeight);
        mContainerPanel.add(mInstallApkButton);
        mInstallApkButton.addActionListener(this);

        //安装apk按钮相关设置
        mUploadApkToFtpButton.setBounds(mMarginLeft + mButtonAndButtonGaps * 2 + mLogParseButtonWidth + mInstallApkButtonWidth,
                mMarginTop + (mLabelHeight + mLineGaps) * mButtonsPosition,
                mUploadApkToFtpButtonWidth, mUploadApkToFtpButtonHeight);
        mContainerPanel.add(mUploadApkToFtpButton);
        mUploadApkToFtpButton.addActionListener(this);


        //最终执行结果相关设置,设置字体,内容个位置
        mResultLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        mResultLabel.setHorizontalAlignment(SwingConstants.LEFT);
        mResultLabel.setVerticalAlignment(SwingConstants.TOP);
        mResultLabel.setBounds(mMarginLeft, mMarginTop + (mLabelHeight + mLineGaps) * mResultInfoLabelPosition, mResultLabelWidth, mResultLabelHeight);
        mContainerPanel.add(mResultLabel);

        //将面板添加到程序窗口中
        this.add(mContainerPanel);

        //设置整个界面的大小与在屏幕中的位置
        this.setBounds(mScreenShowPositionX, mScreenPositionY, mScreenShowWidth, mScreenShowHeight);
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

    private void ftpInfoSet() {
        int positionY = mMarginTop + (mLineGaps + mTextFieldHeight) * mFtpInfoPosition;
        textFieldSet(mFtpIpTextField, mMarginLeft, positionY);
//        mFtpIpTextField.setText("10.18.216.92");
        mFtpIpTextField.setText("192.168.0.12");
        textFieldSet(mFtpPortTextField, mMarginLeft + mLabelAndTextFieldGaps + mFtpTextFieldWidth, positionY);
        mFtpPortTextField.setText("21");
        textFieldSet(mFtpNameTextField, mMarginLeft + (mLabelAndTextFieldGaps + mFtpTextFieldWidth) * 2, positionY);
//        mFtpNameTextField.setText("testsd");
        mFtpNameTextField.setText("zhangxiaodong1");
        textFieldSet(mFtpPwdTextField, mMarginLeft + (mLabelAndTextFieldGaps + mFtpTextFieldWidth) * 3, positionY);
//        mFtpPwdTextField.setText("testsd");
        mFtpPwdTextField.setText("zhangxiaodong1");
    }

    private void textFieldSet(JTextField textField, int positionX, int positionY) {
        textField.setEnabled(true);
        textField.setEditable(true);
        textField.setBounds(positionX, positionY, mFtpTextFieldWidth, mFtpTextFieldHeight);
        mContainerPanel.add(textField);
    }


    private void labelAndTextFieldSet(JLabel label, JTextField textField, int labelPositionX, int labelPositonY
            , int textFieldPositionX, int textFieldPositionY) {
        //设置label
        label.setBounds(labelPositionX, labelPositonY, mLabelWidth, mLabelHeight);
        mContainerPanel.add(label);

        //设置textFiled
        textField.setEnabled(true);
        textField.setEditable(true);
        textField.setBounds(textFieldPositionX, textFieldPositionY, mTextFieldWidth, mTextFieldHeight);
        mContainerPanel.add(textField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("不同的点击事件=" + e.getActionCommand() + "=");
        String actionCommand = e.getActionCommand();
        mResultInfo = "<html>";
        mResultLabel.setText(mResultInfo + "</html>");
        if (LOG_PARSE_BUTTON_NAME.equals(actionCommand)) {
            //解析log
            executeLogParseButtonClick();
        } else if (INSTALL_APK_BUTTON_NAME.equals(actionCommand)) {
            //安装apk
            new Thread() {
                @Override
                public void run() {
                    downloadFromFtpAndInstall();
                }
            }.start();
        } else if (UPLOAD_APK_TO_FTP.equals(actionCommand)) {
            //上传apk到ftp
            new Thread() {
                @Override
                public void run() {
                    uploadApkToFtp();
                }
            }.start();
        } else {

        }
    }

    /**
     * 上传文件到ftp
     */
    private void uploadApkToFtp() {
        String target = mUploadApkToFtpTextField.getText();
        String[] result = target.split("#");
        boolean success = FileRenameUtil.renameApkFile(result[0], result[1], result[2]);
        System.out.println("文件重命名成功  " + success);
        if (success) {
            FtpUpload ftpUpload = new FtpUpload(mFtpIpTextField.getText(),
                    TextUtils.getStringInt(mFtpPortTextField.getText()), mFtpNameTextField.getText(),
                    mFtpPwdTextField.getText());
            String content = FileUtil.getFileContent(new File(result[0], result[2]));
            ApkFileSaveNameBean apkFileSaveNameBean = new Gson().fromJson(content, ApkFileSaveNameBean.class);
            File file = new File(apkFileSaveNameBean.getUploadPath());
            ftpUpload.putFileToFTpFastByPath(apkFileSaveNameBean.getUploadPath(), "/dzx/" + file.getName());
            mResultInfo = mResultInfo + "<br>上传apk到ftp成功!   "+file.getName()+"<br>";
            mResultLabel.setText(mResultInfo + "</html>");
        }

        mResultInfo = mResultInfo + "<br>上传apk到ftp已完成!<br>";
        mResultLabel.setText(mResultInfo + "</html>");
    }

    /**
     * 从ftp下载文件并安装
     */
    private void downloadFromFtpAndInstall() {

        FtpDownload ftpDownload = new FtpDownload(mFtpIpTextField.getText(),
                TextUtils.getStringInt(mFtpPortTextField.getText()), mFtpNameTextField.getText(),
                mFtpPwdTextField.getText());
        ftpDownload.setPhaseListener(new PhaseListener() {
            @Override
            public void start(String startInfo) {
                mResultInfo = mResultInfo + "开始从ftp下载apk<br>" + startInfo;
                mResultLabel.setText(mResultInfo + "</html>");
            }

            @Override
            public void finish(String finishInfo) {
                mResultInfo = mResultInfo + "从ftp下载apk完成<br>" + finishInfo + "<br> 开始安装apk<br>";
                mResultLabel.setText(mResultInfo + "</html>");
                Utils.runtimeCommand("adb install -r -d C:\\Users\\20313\\Desktop\\bug\\bug\\999.apk");
            }

            @Override
            public void error(String errorInfo) {
                mResultInfo = mResultInfo + "从ftp下载apk失败<br>" + errorInfo;
                mResultLabel.setText(mResultInfo + "</html>");
            }

            @Override
            public void progress(String progressInfo) {
                System.out.println("当前下载进度   " + progressInfo);
                mResultLabel.setText(mResultInfo + "当前下载进度<br>" + progressInfo + "</html>");
            }
        });

        ftpDownload.downFileFromFtp("/tvb/huixiangjia_012.apk", "C:\\Users\\20313\\Desktop\\bug\\bug\\999.apk");
    }

    /**
     * 执行日志解析操作
     */
    private void executeLogParseButtonClick() {
//C:\Users\20313\Desktop\bug\bug\log.txt
        //RequestBean#onReceive, deviceStatusChanged#click,controlView
        System.out.println("日志解析操作已执行!");
        System.out.println(mLogParseButton.getBounds().getY());
        String conditionsStr = mScreeningConditionsTextField.getText();
        List<String> conditions = new ArrayList<>();
        if (TextUtils.nonEmpty(conditionsStr)) {
            conditions.addAll(Arrays.asList(conditionsStr.split("#")));
        }
        System.out.println(mTargetFilePathTextField.getText());
        System.out.println(mScreeningConditionsTextField.getText());
        System.out.println(mOutFilepathTextField.getText());
        mResultLabel.setText(mResultInfo + "<br>正在解析文件......<br></html>");
        mResultInfo = "<html>" + ParseLogUtil.parseLog(mTargetFilePathTextField.getText(), mOutFilepathTextField.getText(), conditions);
        mResultLabel.setText(mResultInfo + "<br>文件解析完成!<br></html>");

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
                    mResultInfo = mResultInfo + "<br><br>拖曳获取文件路径失败:<br>" + e.toString();
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
}