import java.awt.*;

import javax.swing.*;

public class Demo12 extends JFrame {

    // 定义组件
    JPanel jp1, jp2, jp3;
    JLabel jlb1, jlb2;
    JCheckBox jcb1, jcb2, jcb3;
    JRadioButton jrb1, jrb2;
    JButton jb1, jb2;
    // 单选设置
    ButtonGroup bg, bg2;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Demo12 d2 = new Demo12();

    }

    // 构造函数
    public Demo12() {
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();

        jlb1 = new JLabel("你喜欢的运动");
        jlb2 = new JLabel("你的性别");

        jcb1 = new JCheckBox("足球");
        jcb2 = new JCheckBox("篮球");
        jcb3 = new JCheckBox("网球");
        // 下面可以设置单选
        // ButtonGroup bg2=new ButtonGroup();
        // bg2.add(jcb1);
        // bg2.add(jcb2);
        // bg2.add(jcb2);

        jrb1 = new JRadioButton("男");
        jrb2 = new JRadioButton("女");
        // 一定要把jrb1，jrb2放入到一个ButtonGroup里面
        ButtonGroup bg = new ButtonGroup();
        bg.add(jrb1);
        bg.add(jrb2);

        jb1 = new JButton("注册用户");
        jb2 = new JButton("取消用户");

        this.setLayout(new GridLayout(3, 1));

        jp1.add(jlb1);
        jp1.add(jcb1);
        jp1.add(jcb2);
        jp1.add(jcb3);

        jp2.add(jlb2);
        jp2.add(jrb1);
        jp2.add(jrb2);

        jp3.add(jb1);
        jp3.add(jb2);

        // 加入到JFrame
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);

        this.setSize(300, 200);
        this.setTitle("用户注册界面");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}