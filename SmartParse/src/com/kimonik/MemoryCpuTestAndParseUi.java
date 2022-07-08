package com.kimonik;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MemoryCpuTestAndParseUi extends JFrame implements ActionListener {

    /**
     * 界面容器,用于放置其他按钮,文本等
     */
    private JPanel mContainerPanel = new JPanel();
    /**
     * 窗口在屏幕上的位置
     */
    private int mScreenShowPositionX = 200, mScreenPositionY = 150, mScreenShowWidth = 1130, mScreenShowHeight = 800;

    private static final String START_MEM_CMD = "启动获取内存信息";
    private static final String STOP_MEM_CMD = "终止获取内存信息";
    private static final String PARSE_MEMORY = "分析内存信息";
    private static final String PARSE_CPU = "分析CPU信息";
    private static final String START_CPU_CMD = "启动获取CPU信息";
    private static final String STOP_CPU_CMD = "终止获取CPU信息";

    public static final int START_MEM_ID = 0;
    public static final int STOP_MEM_ID = 1;
    public static final int START_CPU_ID = 2;
    public static final int STOP_CPU_ID = 3;
    public static final int PARSE_MEM_ID = 4;
    public static final int PARSE_CPU_ID = 5;

    private MyButton mStartMemButton = new MyButton(START_MEM_ID, START_MEM_CMD);
    private MyButton mStopMemButton = new MyButton(STOP_MEM_ID, STOP_MEM_CMD);
    private MyButton mStartCpuButton = new MyButton(START_CPU_ID, START_CPU_CMD);
    private MyButton mStopCpuButton = new MyButton(STOP_CPU_ID, STOP_CPU_CMD);
    private MyButton mParseMemoryButton = new MyButton(PARSE_MEM_ID, PARSE_MEMORY);
    private MyButton mParseCpuButton = new MyButton(PARSE_CPU_ID, PARSE_CPU);

    private static String mSettingsFileFullPath = "";
    private static final String SETTINGS_FILE_FULL_PATH = "配置文件绝对路径:";
    private static final String CPU_FILE_FULL_PATH = "CPU信息文件绝对路径:";
    private static final String MEMORY_FILE_FULL_PATH = "内存信息文件绝对路径:";

    private JLabel mSettingsFileFullPathLabel = new JLabel(SETTINGS_FILE_FULL_PATH);
    private JTextField mSettingsFileFullPathTextField = new JTextField(10);
    private JLabel mCpuInfoFileFullPathLabel = new JLabel(CPU_FILE_FULL_PATH);
    private JTextField mCpuInfoFileFullPathTextField = new JTextField(10);
    private JLabel mMemoryInfoFileFullPathLabel = new JLabel(MEMORY_FILE_FULL_PATH);
    private JTextField mMemoryInfoFileFullPathTextField = new JTextField(10);

    private static int mLabelWidth = 200;
    private static int mLabelHeight = 50;
    private static int mTextFieldWidth = 600;
    private static int mTextFieldHeight = 50;

    private int mLeftOffset = 50;
    private int mTopOffset = 50;
    private int mButtonGaps = 30;
    private int mButtonWidth = 230;
    private int mButtonHeight = 50;
    private static String mSettingsFileName = "配置文件.txt";


    public MemoryCpuTestAndParseUi() throws HeadlessException {

        this.setTitle("测试内存CPU并解析");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mContainerPanel.setLayout(null);
        this.add(mContainerPanel);

        //第一行按钮
        setButton(mStartMemButton, mLeftOffset, mTopOffset, mButtonWidth, mButtonHeight);
        setButton(mStopMemButton, mLeftOffset + mButtonGaps + mButtonWidth, mTopOffset, mButtonWidth, mButtonHeight);
        setButton(mStartCpuButton, mLeftOffset + mButtonGaps * 2 + mButtonWidth * 2, mTopOffset, mButtonWidth, mButtonHeight);
        setButton(mStopCpuButton, mLeftOffset + mButtonGaps * 3 + mButtonWidth * 3, mTopOffset, mButtonWidth, mButtonHeight);

        //第二行按钮
        setButton(mParseMemoryButton, mLeftOffset, mTopOffset + mButtonGaps + mButtonHeight, mButtonWidth, mButtonHeight);
        setButton(mParseCpuButton, mLeftOffset + mButtonGaps + mButtonWidth, mTopOffset + mButtonGaps + mButtonHeight, mButtonWidth, mButtonHeight);

        //第三行
        labelAndTextFieldSet(mSettingsFileFullPathLabel, mSettingsFileFullPathTextField, mLeftOffset, mTopOffset + mButtonGaps * 2 + mButtonHeight * 2,
                mLeftOffset + mButtonGaps + mLabelWidth, mTopOffset + mButtonGaps * 2 + mButtonHeight * 2);
        mSettingsFileFullPathTextField.setText("C:\\Users\\dingzhixin.ex\\Desktop\\性能测试");

        //第四行
        labelAndTextFieldSet(mMemoryInfoFileFullPathLabel, mMemoryInfoFileFullPathTextField, mLeftOffset, mTopOffset + mButtonGaps * 3 + mButtonHeight * 3,
                mLeftOffset + mButtonGaps + mLabelWidth, mTopOffset + mButtonGaps * 3 + mButtonHeight * 3);

        //第五行
        labelAndTextFieldSet(mCpuInfoFileFullPathLabel, mCpuInfoFileFullPathTextField, mLeftOffset, mTopOffset + mButtonGaps * 4 + mButtonHeight * 4,
                mLeftOffset + mButtonGaps + mLabelWidth, mTopOffset + mButtonGaps * 4 + mButtonHeight * 4);

        //第六行
        addResultScrollArea();

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

    @Override
    public void actionPerformed(ActionEvent e) {
        LUtils.i(e.getSource());
        if (e.getSource() instanceof MyButton) {
            switch (((MyButton) e.getSource()).getClickId()) {
                case START_MEM_ID:
                    initSettings();
                    CpuMemoryTest.mRunningToggle = true;
                    CpuMemoryTest.analysisMemInfo(this);
                    break;
                case STOP_MEM_ID:
                    CpuMemoryTest.mRunningToggle = false;
                    break;
                case START_CPU_ID:
                    break;
                case STOP_CPU_ID:
                    break;
                case PARSE_MEM_ID:
                    String memStart = "正在解析CPU信息";
                    mResultLabel.setText(memStart);
                    String memResult = CpuMemoryTest.parseMemory(mMemoryInfoFileFullPathTextField.getText());
                    mResultLabel.setText("<html><br>CPU信息解析完成<br>" + memResult + "</html>");
                    break;
                case PARSE_CPU_ID:
                    String cpuStart = "正在解析CPU信息";
                    mResultLabel.setText(cpuStart);
                    String cpuResult = CpuMemoryTest.parseCpuInfo(mCpuInfoFileFullPathTextField.getText());
                    mResultLabel.setText("<html><br>CPU信息解析完成<br>" + cpuResult + "</html>");
                    break;
                default:
                    break;
            }
        }

    }

    private JLabel mResultLabel = new JLabel();
    private JScrollPane mResultInfoScrollPan = new JScrollPane();
    private int mResultLabelWidth = 980, mResultLabelHeight = 300;

    private void addResultScrollArea() {
        //第六行
        //最终执行结果相关设置, 设置字体, 内容个位置
        mResultLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        mResultLabel.setHorizontalAlignment(SwingConstants.LEFT);
        mResultLabel.setVerticalAlignment(SwingConstants.TOP);
        mResultLabel.setBounds(10, 10, mResultLabelWidth - 30, mResultLabelHeight);
        mResultInfoScrollPan.setBorder(new CompoundBorder(mResultInfoScrollPan.getBorder(), BorderFactory.createEmptyBorder(5, 10, 5, 5)));
        mResultInfoScrollPan.setBounds(
                mLeftOffset, (mTopOffset + mButtonGaps * 5 + mButtonHeight * 5),
                mResultLabelWidth + 35, mResultLabelHeight);
        //设置滚轮的滚动距离
        mResultInfoScrollPan.getVerticalScrollBar().setUnitIncrement(100);
        //设置JScrollPan的包含view
        mResultInfoScrollPan.setViewportView(mResultLabel);
        mContainerPanel.add(mResultInfoScrollPan);
    }

    private void setButton(MyButton button, int left, int top, int width, int height) {
        Font f = new Font(null, Font.BOLD, 20);
        button.setFont(f);
        button.setBounds(left, top, width, height);
        mContainerPanel.add(button);
        button.addActionListener(this);
    }


    private void labelAndTextFieldSet(JLabel label, JTextField textField, int labelPositionX, int labelPositonY
            , int textFieldPositionX, int textFieldPositionY) {
        //设置label
        label.setBounds(labelPositionX, labelPositonY, mLabelWidth, mLabelHeight);
        label.setFont(new Font("宋体", Font.PLAIN, 20));
        mContainerPanel.add(label);

        //设置textFiled
        textField.setEnabled(true);
        textField.setEditable(true);
        textField.setFont(new Font("宋体", Font.PLAIN, 20));
        textField.setBounds(textFieldPositionX, textFieldPositionY, mTextFieldWidth, mTextFieldHeight);
        mContainerPanel.add(textField);
    }


    private void initSettings() {
        File file = new File(mSettingsFileFullPathTextField.getText(), mSettingsFileName);
        if (!file.exists()) {
            LUtils.i("配置文件不存在,生成默认配置文件");
            SettingsEntity entity = new SettingsEntity();
            try {
                FileUtils.write(file, new Gson().toJson(entity));
            } catch (IOException e) {
                e.printStackTrace();
            }
            mSettingsFileFullPathTextField.setText(entity.settingsFilePath);
            CpuMemoryTest.init(entity, this);
        } else {
            try {
                String settings = FileUtils.readFileToString(file);
                SettingsEntity entity = new Gson().fromJson(settings, SettingsEntity.class);
                if (entity != null && !TextUtils.isEmpty(entity.settingsFilePath)) {
                    mSettingsFileFullPathTextField.setText(entity.settingsFilePath);
                }
                CpuMemoryTest.init(entity, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setMemoryFileFullPath(String memFilePath) {
        mMemoryInfoFileFullPathTextField.setText(memFilePath);
    }

    public void updateResult(List<String> list) {
        StringBuilder builder = new StringBuilder();
        builder.append("<html>");
        for (String s : list) {
            builder.append(s).append("<br>");
        }
        builder.append("<br><br></html>");
        mResultLabel.setText(builder.toString());
        mResultInfoScrollPan.doLayout();
        JScrollBar jscrollBar = mResultInfoScrollPan.getVerticalScrollBar();
        if (jscrollBar != null){
            jscrollBar.setValue(jscrollBar.getMaximum());
        }
    }
}
